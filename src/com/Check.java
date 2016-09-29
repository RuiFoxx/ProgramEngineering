package com;

import java.util.ArrayList;
import java.util.HashMap;
import static com.Hash.hash;

public class Check {
    public static User checkAuthentication(ArrayList<User> users, HashMap<String, String> arrArgValues) throws Throwable {
        String login="", pass="";
        User curUser=null;
        if(arrArgValues.containsKey("login") && arrArgValues.containsKey("password")) {
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

    public static Role CheckAuthorization ( ArrayList <Role> currentRoles, HashMap <String, String> arrArgValues)
    {
       //выделяем реусрс и роль
        String role = "";
        String resource = "";
        Role trueRole=new Role(999, currentRoles.get(0).getUser(), null, null);

        if (arrArgValues.containsKey("role")&&arrArgValues.containsKey("resource"))
        {
            role = arrArgValues.get("role");
            resource = arrArgValues.get("resource");
        }

        System.out.println(role+" "+resource);

        for (int i=0;i<currentRoles.size();i++)
        {

            //проверка ресурсов

            if((resource.indexOf(currentRoles.get(i).getResource())==0)&&((resource.length()==currentRoles.get(i).getResource().length())
                                    ||(resource.charAt(currentRoles.get(i).getResource().length())=='.')
                                    &&(resource.length()!=currentRoles.get(i).getResource().length())))
            {
                trueRole.setResource(resource);
                if(currentRoles.get(i).getName().equals(role))
                {
                    trueRole.setName(role);
                    break;
                }
            }
        }

        return trueRole;
    }
}
