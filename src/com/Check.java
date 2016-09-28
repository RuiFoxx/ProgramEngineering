package com;

import java.util.ArrayList;
import java.util.HashMap;


public class Check
{
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
