package com;

import org.flywaydb.core.Flyway;
import java.sql.*;

public class Main {
    public static void main(String ...args) throws Throwable  {
        Processing.logger.info("Program started");

        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:h2:./db/aaa", "aaa", "aaa");
        flyway.migrate();

        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:./db/aaa", "aaa", "aaa");

        AaaDao aaa = new AaaDao(conn);
        int cliErrCode = new Cli().parse(aaa, args);
        conn.close();
        System.exit(cliErrCode);
    }
}
