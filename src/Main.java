import org.apache.commons.cli.*;

import java.util.HashMap;

public class Main
{
    public static void main(String... args) throws ParseException
    {
        User user1=new User("John Doe", "jdoe", "sup3rpaZZ", "q1w2e3r4", 1);
        User user2=new User("Jane Row", "jrow", "Qwerty12", "r4e3w2q1", 2);
        Role role1=new Role(1, user1, "READ", "a");
        Role role2=new Role(2, user1, "WRITE", "a. b");
        Role role3=new Role(3, user2, "EXECUTE", "a.b.c");
        Role role4=new Role(4, user2, "EXECUTE", "a.bc");
        HashMap<String, String> parsedArgs=Cli.parse(args);
    }
}
