package com;

import org.flywaydb.core.Flyway;
import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String ...args) throws Throwable  {
        Check.logger.info("Program started");

        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:h2:./db/aaa", "aaa", "aaa");
        flyway.migrate();

        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:./db/aaa", "aaa", "aaa");

        ArrayList <User> users = new AaaDao(conn).getUsers(); //всё по-честному, а главное - ...

        ArrayList<Role> roles = new AaaDao(conn).getRoles(); //... совсем без костылей!

        new Cli().parse(users, roles, args);
        conn.close();
    }
}
