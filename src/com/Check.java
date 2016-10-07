package com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.Hash.hash;

public class Check {
    public static void checkAuthentication(ArrayList<User> users, ArrayList<Role> roles, CmdUser cmdData) throws Throwable {
        String login = cmdData.getLogin();
        String pass = cmdData.getPassword();
        User curUser = null;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getLogin().equals(login)) {
                curUser = users.get(i);
                break;
            }
        }

        if (curUser == null) {
            System.out.printf("User not found");
            System.exit(1);
        }

        pass = hash(hash(pass) + curUser.getSalt());
        if (!curUser.getPassword().equals(pass)) {
            System.out.printf("Wrong password");
            System.exit(2);
        }

        if (Cli.isAuthorization()) {
            ArrayList<Role> currentRoles = new ArrayList<>(); //все роли для пользователя

            for (int i = 0; i < roles.size(); i++)
                if (roles.get(i).getUser() == curUser) {
                    currentRoles.add(roles.get(i));
                }

            Check.checkAuthorization(currentRoles,cmdData);
        }
        else System.exit(0);
    }

    public static void checkAuthorization(ArrayList<Role> currentRoles, CmdUser cmdData) throws ParseException {
        //выделяем реусрс и роль
        String role = "";
        String resource = "";
        Role trueRole = new Role(999, currentRoles.get(0).getUser(), null, null);

        if (cmdData.getRole()!=null && cmdData.getResource()!=null) {
            role = cmdData.getRole();
            resource = cmdData.getResource();
        }
        else Cli.help();// ???

        //Проверка
        System.out.println(role + " " + resource);

        for (int i = 0; i < currentRoles.size(); i++) {
            //проверка ресурсов
            if ((resource.indexOf(currentRoles.get(i).getResource()) == 0) && ((resource.length() == currentRoles.get(i).getResource().length())
                    || (resource.charAt(currentRoles.get(i).getResource().length()) == '.')
                    && (resource.length() != currentRoles.get(i).getResource().length()))) {
                trueRole.setResource(resource);
                if (currentRoles.get(i).getName().equals(role)) {
                    trueRole.setName(role);
                    break;
                }
            }
        }
        if (trueRole.getResource() == null) {
            System.out.println("Wrong resource");
            System.exit(4);
        }
        if (trueRole.getName() == null) {
            System.out.println("Wrong role");
            System.exit(3);
        } else {Check.checkAccounting(cmdData);} // Если роль и ресурс верны  то идем в аккаунтинг

    }

    public static void checkAccounting(CmdUser cmdData) throws ParseException {

        String ds = "";
        String de = "";
        int vol=0;

        if (cmdData.getDate_start()!=null && cmdData.getDate_end()!=null && cmdData.getVolume()!=null) {
           //выделяем даты и объем
            ds = cmdData.getDate_start();
            de = cmdData.getDate_end();
            vol = Integer.parseInt(cmdData.getVolume());
       }
       else Cli.help(); //???

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date_start = format.parse(ds);
        Date date_end = format.parse(de);

        int d_s = date_start.getDate(); //считанная дата старт из строки
        int m_s = date_start.getMonth() + 1;
        int y_s = date_start.getYear() + 1900;

        int d_e = date_end.getDate(); //считанная дата энд из строки
        int m_e = date_end.getMonth() + 1;
        int y_e = date_end.getYear() + 1900;

        String[] dsParts = ds.split("-"); //Если введен день больше чем есть в месяце, то необходимо
        String[] deParts = de.split("-"); //сравнивать с считанной датой тк оно перекидывает на другой месяц

        //проверка
        System.out.println(date_start.getDate() + " " + m_s + " " + y_s);

        int temp_ds = Integer.parseInt(dsParts[2]);
        int temp_ms = Integer.parseInt(dsParts[1]);
        int temp_ys = Integer.parseInt(dsParts[0]);

        int temp_de = Integer.parseInt(deParts[2]);
        int temp_me = Integer.parseInt(deParts[1]);
        int temp_ye = Integer.parseInt(deParts[0]);
        if ((temp_ds != d_s) || (temp_ms != m_s) || (temp_ys != y_s)  //если перешел в другой день/месяц/год, то ведденая дата значит неверна
                || (temp_de != d_e) || (temp_me != m_e) || (temp_ye != y_e)) {
            System.out.printf("Wrong date");
            System.exit(5);
        }

        //Проверка объема
        if (vol < 0) {
            System.out.printf("Wrong volume");
            System.exit(5);
        }
        System.exit(0);
       // return  curUser;
    }
}
