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

        AaaDao aaa = new AaaDao(conn);
        new Cli().parse(aaa, args);
        conn.close();
    }
}
