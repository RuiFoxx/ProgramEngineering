package com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.Hash.hash;


public class Check
{
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

    public static Accounting CheckAccounting (HashMap <String, String> arrArgValues) throws ParseException
    {
        //выделяем даты и объем
        String ds=arrArgValues.get("date-start");
        String de = arrArgValues.get("date-end");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date_start = format.parse(ds);
        Date date_end = format.parse(de);

        int d=date_start.getDate(); //считанная дата из строки
        int m=date_start.getMonth()+1;
        int y=date_start.getYear()+1900;

     /*   String [] dsParts = ds.split("."); //Если введен день больше чем есть в месяце, то необходимо
                                            //сравнивать с считанной датой тк оно перекидывает на другой месяц
        int temp_d = Integer.parseInt(dsParts[0]);
        int temp_m = Integer.parseInt(dsParts[1]);
        int temp_y = Integer.parseInt(dsParts[2]);
        if ((temp_d!=d)||(temp_m!=m)||(temp_y!=y)) //если перешел в другой день/месяц/год, то ведденая дата значит неверна
        {
            System.out.printf("Wrong start date");
            System.exit(5);
        }*/

        System.out.println(date_start.getDate()+" "+m+" "+y);

        int vol = Integer.parseInt(arrArgValues.get("volume"));

        //Проверка даты
        int [] leap_year ={31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; //Високосный год
        int [] year ={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; //Просто год

        //Проверка даты начала
        if ((date_start.getYear()+1990)%4==0) //Если високосный
        {
            if ((date_start.getMonth()+1>12)||(date_start.getDate()>leap_year[date_start.getMonth()-1]))
            {
                System.out.printf("Wrong start date");
                System.exit(5);
            }
        }
        else //если обычный
        {
            if ((date_start.getMonth()+1>12)||(date_start.getDate()>year[date_start.getMonth()]))
            {
                System.out.printf("Wrong start date");
                System.exit(5);
            }
        }

        //Проверка даты окончания
        if ((date_end.getYear()+1+1990)%4==0)
        {
            if ((date_end.getMonth()+1>12)||(date_end.getDate()>leap_year[date_end.getMonth()-1]))
            {
                System.out.printf("Wrong end date");
                System.exit(5);
            }
        }
        else
        {
            if ((date_end.getMonth()+1>12)||(date_end.getDate()>year[date_end.getMonth()-1]))
            {
                System.out.printf("Wrong end date");
                System.exit(5);
            }
        }
        //Проверка объема
        if (vol<0)
        {
            System.out.printf("Wrong volume");
            System.exit(5);
        }

        Accounting accCheck = new Accounting(vol, null ,date_start,date_end);

        return accCheck;
    }
}
