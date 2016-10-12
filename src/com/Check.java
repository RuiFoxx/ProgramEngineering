package com;

import java.text.*;
import java.util.*;

import static com.Hash.hash;

public class Check {
    public static void checkAuthentication(ArrayList<User> users, ArrayList<Role> roles, CmdUser cmdData) throws Throwable {
        String login = cmdData.getLogin();
        String pass = cmdData.getPassword();
        User curUser = null;

        for (User u : users) {
            if (u.getLogin().equals(login)) {
                curUser = new User(u);
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

        if (Cli.getAuthorization()) {
            ArrayList<Role> currentRoles = new ArrayList<>(); //все роли для пользователя

            for (Role r : roles)
                if (r.getUser().getLogin().equals(curUser.getLogin())) {
                    currentRoles.add(r);
                }

            Check.checkAuthorization(currentRoles,cmdData);
        }
        else {
            System.out.println("Authentication complete");
            System.exit(0);
        }
    }

    public static void checkAuthorization(ArrayList<Role> currentRoles, CmdUser cmdData) throws ParseException {
        //выделяем реусрс и роль
        String role = cmdData.getRole();
        String resource = cmdData.getResource();
        Role trueRole = new Role(999, currentRoles.get(0).getUser(), null, null);

        //Проверка
        System.out.println(role + " " + resource);

        for (Role r : currentRoles) {
            //проверка ресурсов
            if ((resource.indexOf(r.getResource()) == 0)
                    && ((resource.length() == r.getResource().length())
                    || (resource.charAt(r.getResource().length()) == '.')
                    && (resource.length() != r.getResource().length()))) {
                trueRole.setResource(resource);
                if (r.getName().equals(role)) {
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
        }

        if (Cli.getAccounting()) {
            Check.checkAccounting(cmdData);
        }
        else {
            System.out.println("Authorization complete");
            System.exit(0);
        }

    }

    public static void checkAccounting(CmdUser cmdData) {

        String ds = cmdData.getDate_start();
        String de = cmdData.getDate_end();
        int vol = 0;
        try {
            vol=Integer.parseInt(cmdData.getVolume());
        } catch (NumberFormatException e) {
            System.out.println("Failed to parse volume.");
            System.exit(5);
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        try {
            Date date = format.parse(ds);
            Calendar date_start = Calendar.getInstance();
            date_start.setTime(date);

            date = format.parse(de);
            Calendar date_end = Calendar.getInstance();
            date_end.setTime(date);
        }
        catch (ParseException e) {
            System.out.println("Invalid date or wrong format");
            System.exit(5);
        }

        //Проверка объема
        if (vol < 0) {
            System.out.printf("Wrong volume");
            System.exit(5);
        }

        System.out.println("Accounting complete");
        System.exit(0);

    }
}
