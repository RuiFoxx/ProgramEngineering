package com;

import java.util.ArrayList;
import java.util.HashMap;
import static com.Hash.hash;

public class Check {
    public static User checkAuthentication(ArrayList<User> users, HashMap<String, String> arrArgValues) throws Throwable {
        String login="", pass="";
        User curUser=null;
        if(arrArgValues.containsKey("login")&&(arrArgValues.containsKey("password"))) {
            login=arrArgValues.get("login");
            pass=arrArgValues.get("password");
        }

        for(int i=0; i<users.size(); i++) {
            if(users.get(i).getLogin().equals(login)) {
                curUser=users.get(i);
                break;
            }
        }

        if(curUser==null) {
            System.out.printf("User not found");
            System.exit(1);
        }

        pass=hash(hash(pass)+curUser.getSalt());
        if(!curUser.getPassword().equals(pass)) {
            System.out.printf("Wrong password");
            System.exit(2);
        }
        return curUser;
    }
}
