import com.*;
import org.flywaydb.core.Flyway;
import org.junit.*;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.Assert.*;

public class TestCli {
    private static AaaDao aaa;
    private static Connection conn;

    @BeforeClass
    public static void createTest() throws Throwable {
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:h2:./db/aaa", "aaa", "aaa");
        flyway.migrate();
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:h2:./db/aaa", "aaa", "aaa");
        aaa = new AaaDao(conn);
    }

    @AfterClass
    public static void closeTest() throws Throwable {
        conn.close();
    }

    @Test
    public void testCliAuthentication() throws Throwable {
        new Cli().parse(aaa, "-l", "jdoe", "-p", "sup3rpaZZ");
        assertTrue(Cli.getCmdData().isAuthentication());
    }

    @Test
    public void testCliAuthorization() throws Throwable {
        new Cli().parse(aaa, "-l", "jdoe", "-p", "sup3rpaZZ", "-rol", "READ", "-res", "a");
        assertTrue(Cli.getCmdData().isAuthorization());
    }

    @Test
    public void testCliAccounting() throws Throwable {
        new Cli().parse(aaa, "-l", "jdoe", "-p", "sup3rpaZZ", "-rol", "READ", "-res", "a",
                "-ds", "2016-01-20", "-de", "2016-01-21", "-vol", "20");
        assertTrue(Cli.getCmdData().isAccounting());
    }

    @Test
    public void testLoginIncorrect() throws Throwable {
        assertEquals(1, new Cli().parse(aaa, "-l", "jd", "-p", "sup3rpaZZ"));
    }

    @Test
    public void testPasswordIncorrect() throws Throwable {
        assertEquals(2, new Cli().parse(aaa, "-l", "jdoe", "-p", "sup3r"));
    }

    @Test
    public void testRoleIncorrect() throws Throwable {
        assertEquals(3, new Cli().parse(aaa, "-l", "jdoe", "-p", "sup3rpaZZ", "-rol", "XXX", "-res", "a"));
    }

    @Test
    public void testNoAccess() throws Throwable {
        assertEquals(4, new Cli().parse(aaa, "-l", "jdoe", "-p", "sup3rpaZZ", "-rol", "READ", "-res", "XXX"));
    }

    @Test
    public void testDatesIncorrect() throws Throwable {
        assertEquals(5, new Cli().parse(aaa, "-l", "jdoe", "-p", "sup3rpaZZ", "-rol", "READ", "-res", "a",
                "-ds", "XXX", "-de", "XXX", "-vol", "20"));
    }

    @Test
    public void testVolumeIncorrect() throws Throwable {
        assertEquals(5, new Cli().parse(aaa, "-l", "jdoe", "-p", "sup3rpaZZ", "-rol", "READ", "-res", "a",
                "-ds", "2061-01-20", "-de", "206-01-21", "-vol", "-5"));
    }
}
