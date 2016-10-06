package com;

import java.util.ArrayList;
import java.util.HashMap;

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

        User currentUser; //Текущий пользователь
        Role role; //Его роль
        Accounting account; //Его параметры

        //Проверка количества введенных ресурсов
        HashMap <String,String> arrArgValues = Cli.parse(args);
        if ((arrArgValues.size()==2)&&(arrArgValues.containsKey("login"))&&(arrArgValues.containsKey("password")))
        {
            //~~~~~~~~~~~~~~~Аутентификация~~~~~~~~~~~~~~~
            currentUser=Check.checkAuthentication(users,arrArgValues);
            System.exit(0);
        }
        else
        {
            if ((arrArgValues.size()==4)&&(arrArgValues.containsKey("login"))&&(arrArgValues.containsKey("password"))
                    &&(arrArgValues.containsKey("resource"))&&(arrArgValues.containsKey("role")))
            {
                //~~~~~~~~~~~~~~Авторизация~~~~~~~~~~~~~~~~~~
                currentUser=Check.checkAuthentication(users,arrArgValues);

                //все роли для пользователя
                ArrayList <Role> currentRoles = new ArrayList<>();

                for (int i=0;i<roles.size();i++)
                {
                    if(roles.get(i).getUser()==currentUser)
                    {
                        currentRoles.add(roles.get(i));
                    }
                }

                role=Check.CheckAuthorization(currentRoles, Cli.parse(args));

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
            else
            {
                if ((arrArgValues.size()==7)&&(arrArgValues.containsKey("login"))&&(arrArgValues.containsKey("password"))
                    &&(arrArgValues.containsKey("resource"))&&(arrArgValues.containsKey("role"))
                        &&(arrArgValues.containsKey("date-start"))&&(arrArgValues.containsKey("date-end"))&&(arrArgValues.containsKey("volume")))
                {
                    //~~~~~~~~~~~~~~~~Аккаунтинг~~~~~~~~~~~~~~~~~~~~~
                    currentUser=Check.checkAuthentication(users,arrArgValues);

                    //все роли для пользователя
                    ArrayList <Role> currentRoles = new ArrayList<>();

                    for (int i=0;i<roles.size();i++)
                    {
                        if(roles.get(i).getUser()==currentUser)
                        {
                            currentRoles.add(roles.get(i));
                        }
                    }

                    role=Check.CheckAuthorization(currentRoles, Cli.parse(args));

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
                    else
                    {
                        account = Check.CheckAccounting(arrArgValues);
                        account.setRole(role);
                        System.exit(0);
                    }
                }
                else //если все неправильно вывод справки
                {
                    Cli.help();
                }
            }
        }








   

    }
}
