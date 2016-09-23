package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String ...args) throws Throwable
    {
        ArrayList<User> users=new ArrayList<>();
        users.add(new User(1, "John Doe", "jdoe", "sup3rpaZZ"));
        users.add(new User(2, "Jane Row", "jrow", "Qweqrty12"));

        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role(1, users.get(0), "READ", "a"));
        roles.add(new Role(2, users.get(0), "WRITE", "a.b"));
        roles.add(new Role(3, users.get(1), "EXECUTE", "a.b.c"));
        roles.add(new Role(4, users.get(0), "EXECUTE", "a.bc"));

        String[] authentication = {"login", "password"};
        String[] authorisation = {"login", "password, resource, role"};
        String[] accounting = {"login", "password", "resource", "role", "date-start", "date-end", "volume"};

        HashMap<String, String> arrArgValues = Cli.parse(args);
        if(Check.checkAuthentication(users, arrArgValues)==0)
            System.out.printf("Authentication success");
    }
}
