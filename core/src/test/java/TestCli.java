import com.AaaDao;
import com.Cli;
import com.domain.Role;
import com.domain.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestCli {
    private static AaaDao aaa;
    private static User curUser;
    private static ArrayList<Role> roles = new ArrayList<>();

    @BeforeClass
    public static void createTest() throws Throwable {
        aaa = mock(AaaDao.class);
    }

    @AfterClass
    public static void closeTest() throws Throwable {
    }

    @Test
    public void testCliAuthentication() throws Throwable {
        when(aaa.getUsers("jdoe")).thenReturn(curUser = new User(1, "John Doe", "jdoe", "sup3rpaZZ"));
        new Cli().parse(aaa, "-l", "jdoe", "-p", "sup3rpaZZ");
        assertTrue(Cli.getCmdData().isAuthentication());
    }

    @Test
    public void testCliAuthorization() throws Throwable {
        when(aaa.getUsers("jdoe")).thenReturn(curUser = new User(1, "John Doe", "jdoe", "sup3rpaZZ"));
        roles.add(new Role(1, curUser.getId(), "READ", "a"));
        roles.add(new Role(2, curUser.getId(), "WRITE", "a.b"));
        roles.add(new Role(4, curUser.getId(), "EXECUTE", "a.b.c"));
        when(aaa.getRoles(curUser.getId())).thenReturn(roles);
        new Cli().parse(aaa, "-l", "jdoe", "-p", "sup3rpaZZ", "-rol", "READ", "-res", "a");
        assertTrue(Cli.getCmdData().isAuthorization());
    }

    @Test
    public void testCliAccounting() throws Throwable {
        when(aaa.getUsers("jdoe")).thenReturn(curUser = new User(1, "John Doe", "jdoe", "sup3rpaZZ"));
        roles.add(new Role(1, curUser.getId(), "READ", "a"));
        roles.add(new Role(2, curUser.getId(), "WRITE", "a.b"));
        roles.add(new Role(4, curUser.getId(), "EXECUTE", "a.b.c"));
        when(aaa.getRoles(curUser.getId())).thenReturn(roles);
        new Cli().parse(aaa, "-l", "jdoe", "-p", "sup3rpaZZ", "-rol", "READ", "-res", "a",
                "-ds", "2016-01-20", "-de", "2016-01-21", "-vol", "20");
        assertTrue(Cli.getCmdData().isAccounting());
    }

    @Test
    public void testLoginIncorrect() throws Throwable {
        when(aaa.getUsers("jdoe")).thenReturn(curUser = new User(1, "John Doe", "jdoe", "sup3rpaZZ"));
        assertEquals(1, new Cli().parse(aaa, "-l", "jd", "-p", "sup3rpaZZ"));
    }

    @Test
    public void testPasswordIncorrect() throws Throwable {
        when(aaa.getUsers("jdoe")).thenReturn(curUser = new User(1, "John Doe", "jdoe", "sup3rpaZZ"));
        assertEquals(2, new Cli().parse(aaa, "-l", "jdoe", "-p", "sup3r"));
    }

    @Test
    public void testRoleIncorrect() throws Throwable {
        when(aaa.getUsers("jdoe")).thenReturn(curUser = new User(1, "John Doe", "jdoe", "sup3rpaZZ"));
        roles.add(new Role(1, curUser.getId(), "READ", "a"));
        roles.add(new Role(2, curUser.getId(), "WRITE", "a.b"));
        roles.add(new Role(4, curUser.getId(), "EXECUTE", "a.b.c"));
        when(aaa.getRoles(curUser.getId())).thenReturn(roles);
        assertEquals(3, new Cli().parse(aaa, "-l", "jdoe", "-p", "sup3rpaZZ", "-rol", "XXX", "-res", "a"));
    }

    @Test
    public void testNoAccess() throws Throwable {
        when(aaa.getUsers("jdoe")).thenReturn(curUser = new User(1, "John Doe", "jdoe", "sup3rpaZZ"));
        roles.add(new Role(1, curUser.getId(), "READ", "a"));
        roles.add(new Role(2, curUser.getId(), "WRITE", "a.b"));
        roles.add(new Role(4, curUser.getId(), "EXECUTE", "a.b.c"));
        when(aaa.getRoles(curUser.getId())).thenReturn(roles);
        assertEquals(4, new Cli().parse(aaa, "-l", "jdoe", "-p", "sup3rpaZZ", "-rol", "READ", "-res", "XXX"));
    }

    @Test
    public void testDatesIncorrect() throws Throwable {
        when(aaa.getUsers("jdoe")).thenReturn(curUser = new User(1, "John Doe", "jdoe", "sup3rpaZZ"));
        roles.add(new Role(1, curUser.getId(), "READ", "a"));
        roles.add(new Role(2, curUser.getId(), "WRITE", "a.b"));
        roles.add(new Role(4, curUser.getId(), "EXECUTE", "a.b.c"));
        when(aaa.getRoles(curUser.getId())).thenReturn(roles);
        assertEquals(5, new Cli().parse(aaa, "-l", "jdoe", "-p", "sup3rpaZZ", "-rol", "READ", "-res", "a",
                "-ds", "XXX", "-de", "XXX", "-vol", "20"));
    }

    @Test
    public void testVolumeIncorrect() throws Throwable {
        when(aaa.getUsers("jdoe")).thenReturn(curUser = new User(1, "John Doe", "jdoe", "sup3rpaZZ"));
        roles.add(new Role(1, curUser.getId(), "READ", "a"));
        roles.add(new Role(2, curUser.getId(), "WRITE", "a.b"));
        roles.add(new Role(4, curUser.getId(), "EXECUTE", "a.b.c"));
        when(aaa.getRoles(curUser.getId())).thenReturn(roles);
        assertEquals(5, new Cli().parse(aaa, "-l", "jdoe", "-p", "sup3rpaZZ", "-rol", "READ", "-res", "a",
                "-ds", "2061-01-20", "-de", "206-01-21", "-vol", "-5"));
    }

    @Test
    public void testArgs() throws Throwable {
        when(aaa.getUsers("jdoe")).thenReturn(curUser = new User(1, "John Doe", "jdoe", "sup3rpaZZ"));
        roles.add(new Role(1, curUser.getId(), "READ", "a"));
        roles.add(new Role(2, curUser.getId(), "WRITE", "a.b"));
        roles.add(new Role(4, curUser.getId(), "EXECUTE", "a.b.c"));
        when(aaa.getRoles(curUser.getId())).thenReturn(roles);

        new Cli().parse(aaa, "-p", "sup3rpaZZ", "-l", "jdoe", "-rol", "READ", "-res", "a",
                "-ds", "2016-02-02", "-de", "2016-03-03", "-vol", "20");
        assertEquals(Cli.getCmdData().getLogin(), "jdoe");
        assertEquals(Cli.getCmdData().getPassword(), "sup3rpaZZ");
        assertEquals(Cli.getCmdData().getResource(), "a");
        assertEquals(Cli.getCmdData().getRole(), "READ");
        assertEquals(Cli.getCmdData().getDate_start(), "2016-02-02");
        assertEquals(Cli.getCmdData().getDate_end(), "2016-03-03");
        assertEquals(Cli.getCmdData().getVolume(), "20");
    }
}
