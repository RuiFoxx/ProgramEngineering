package com;

import java.util.ArrayList;
import java.util.HashMap;

public class Check {
    public static int checkAuthentication(ArrayList<User> users, HashMap<String, String> arrArgValues) throws Throwable {
        String login="", pass="";
        User curUser=null;
        if(arrArgValues.containsKey("login")&&(arrArgValues.containsKey("password"))) {
            login=arrArgValues.get("login");
            pass=arrArgValues.get("password");
        }

        boolean exists=false;
        for(int i=0; i<users.size() && !exists; i++) {
            if(users.get(i).getLogin().equals(login)) {
                exists=true;
                curUser=users.get(i);
            }
        }

        if(!exists) {
            System.out.printf("User not found");
            return 1;
        }

        if(!curUser.getPassword().equals(pass)) {
            System.out.printf("Wrong password");
            return 2;
        }
        return 0;
    }
}
