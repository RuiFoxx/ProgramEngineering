package com;

import org.flywaydb.core.Flyway;
import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String ...args) throws Throwable  {
        Check.logger.info("Program started");

        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:h2:./aaa", "aaa", "aaa");
        flyway.migrate();

        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:./aaa", "aaa", "aaa");
        // add application code here
        conn.close();

        ArrayList <User> users = new ArrayList<>();
        users.add(new User(1, "John Doe", "jdoe", "sup3rpaZZ"));
        users.add(new User(2, "Jane Row", "jrow", "Qwerty12"));

        ArrayList <Role> roles = new ArrayList<>();
        roles.add(new Role(1, users.get(0), "READ", "a"));
        roles.add(new Role(2, users.get(0), "WRITE", "a.b"));
        roles.add(new Role(3, users.get(1), "EXECUTE", "a.b.c"));
        roles.add(new Role(4, users.get(0), "EXECUTE", "a.bc"));

        new Cli().parse(users, roles, args);
    }
}
