package com;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Main
{
    public static void main(String ...args) throws Throwable
    {

        ArrayList <User> users = new ArrayList<>();
        users.add(new User(1, "John Doe", "jdoe", "sup3rpaZZ"));
        users.add(new User(2, "Jane Row", "jrow", "Qweqrty12"));

        ArrayList <Role> roles = new ArrayList<>();
        roles.add(new Role(1, users.get(0), "READ", "a"));
        roles.add(new Role(2, users.get(0), "WRITE", "a.b"));
        roles.add(new Role(3, users.get(1), "EXECUTE", "a.b.c"));
        roles.add(new Role(4, users.get(0), "EXECUTE", "a.bc"));

        ArrayList <Role> currentRoles = new ArrayList<>();

        for (int i=0;i<roles.size();i++)
        {
            if(roles.get(i).getUser()==users.get(0))
            {
                currentRoles.add(roles.get(i));
            }
        }

        Role role=Check.CheckAuthorization(currentRoles, Cli.parse(args));

        if(role.getResource()==null)
        {
            System.out.println("Wrong resource");
            System.exit(4);
        }

        if(role.getName()==null)
        {
            System.out.println("Wrong role");
            System.exit(3);
        }

        else System.exit(0);


   

    }
}
