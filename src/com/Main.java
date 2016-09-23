package com;

import java.util.ArrayList;
import java.util.HashMap;

public class Main
{
    public static void main(String ...args) throws Throwable
    {
        ArrayList<User> users=new ArrayList<>();
        users.add(new User(1, "John Doe", "jdoe", "sup3rpaZZ"));
        users.add(new User(2, "Jane Row", "jrow", "Qweqrty12"));

        ArrayList<Role> Roles = new ArrayList<>();
        Roles.add(new Role(1, users.get(0), "READ", "a"));
        Roles.add(new Role(2, users.get(0), "WRITE", "a.b"));
        Roles.add(new Role(3, users.get(1), "EXECUTE", "a.b.c"));
        Roles.add(new Role(4, users.get(0), "EXECUTE", "a.bc"));

        HashMap<String, String> arrArgValues = Cli.parse(args);
        Check.checkAuthentication(users, arrArgValues);
    }
}