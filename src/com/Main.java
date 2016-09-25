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

        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role(1, users.get(0), "READ", "a"));
        roles.add(new Role(2, users.get(0), "WRITE", "a.b"));
        roles.add(new Role(3, users.get(1), "EXECUTE", "a.b.c"));
        roles.add(new Role(4, users.get(0), "EXECUTE", "a.bc"));

        HashMap<String, String> arrArgValues = Cli.parse(args);
        User authenticatedUser=null;

        if(arrArgValues.size()==2 && arrArgValues.containsKey("login") && arrArgValues.containsKey("password")) {
            authenticatedUser = Check.checkAuthentication(users, arrArgValues);
            System.out.println("Authentication success for user " + authenticatedUser.getLogin());
        }
        if(arrArgValues.size()==4 && arrArgValues.containsKey("login") && arrArgValues.containsKey("password")
                && arrArgValues.containsKey("role") && arrArgValues.containsKey("resource")) {
            authenticatedUser = Check.checkAuthentication(users, arrArgValues);
            Check.checkAuthorization();
        }
        if(arrArgValues.size()==7) {
            authenticatedUser = Check.checkAuthentication(users, arrArgValues);
            Check.checkAuthorization();
            if(Check.checkAuthorization()==0)
                Check.checkAccounting();
        }
    }
}
