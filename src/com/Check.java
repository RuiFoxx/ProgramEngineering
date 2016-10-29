package com;

import java.text.*;
import java.util.*;
import org.apache.log4j.*;

import static com.Hash.hash;

public class Check {

    static final Logger logger = Logger.getLogger(Check.class);


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
            System.out.println("User not found");
            logger.error("User "+login+" not found. Exit code: 1");
            System.exit(1);
        }

        pass = hash(hash(pass) + curUser.getSalt());
        if (!curUser.getPassword().equals(pass)) {
            System.out.println("Wrong password");
            logger.error("Password is wrong for user "+login+". Exit code: 2");
            System.exit(2);
        }

        if (Cli.getAuthorization()) {
            ArrayList<Role> currentRoles = new ArrayList<>(); //все роли для пользователя

            for (Role r : roles)
                if (r.getUser().getLogin().equals(curUser.getLogin())) {
                    currentRoles.add(r);
                }

            logger.info("Authentication complete for user "+ login);
            Check.checkAuthorization(currentRoles,cmdData);
        }
        else {
            System.out.println("Authentication complete");
            logger.info("Authentication complete for user "+ login+". Exit code: 0");
            System.exit(0);
        }
    }

    public static void checkAuthorization(ArrayList<Role> currentRoles, CmdUser cmdData) throws ParseException {
        //выделяем реусрс и роль
        String role = cmdData.getRole().toUpperCase();
        String resource = cmdData.getResource();
        Role trueRole = new Role(999, currentRoles.get(0).getUser(), null, null);

        //Проверка
        System.out.println(role + " " + resource);

        if (!role.equals("READ") && !role.equals("WRITE") && !role.equals("EXECUTE")) {
            System.out.println("Invalid role");
            logger.error("Invalid role '"+role+"'. Exit code: 3");
            System.exit(3);
        }

        for (Role r : currentRoles) {
            //проверка ресурсов
            if (new Check().checkResource(resource,r.getResource())) {
                trueRole.setResource(resource);
                if (r.getName().equals(role)) {
                    trueRole.setName(role);
                    break;
                }
            }
        }
        if ((trueRole.getResource() == null)||(trueRole.getName() == null)) {
            System.out.println("No access");
            logger.error("No access to '"+resource+"' for user "+cmdData.getLogin()+". Exit code: 4");
            System.exit(4);
        }

        if (Cli.getAccounting()) {
            logger.info("Authorization complete for user "+cmdData.getLogin());
            Check.checkAccounting(cmdData);
        }
        else {
            System.out.println("Authorization complete");
            logger.info("Authorization complete for user "+cmdData.getLogin()+". Exit code: 0");
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
            logger.error("Failed to parse volume '"+cmdData.getVolume()+"'. Exit code: 5. StackTrace: ", e);
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
            logger.error("Invalid date or wrong format. Exit code: 5. StackTrace: ",e);
            System.exit(5);
        }

        //Проверка объема
        if (vol < 0) {
            System.out.println("Wrong volume");
            logger.error("Wrong volume '"+vol+"'. Exit code: 5");
            System.exit(5);
        }

        System.out.println("Accounting complete");
        logger.info("Accounting complete for user "+cmdData.getLogin()+". Exit code: 0");
        System.exit(0);

    }

    private boolean checkResource (String res1, String res2) {
        if ((res1.indexOf(res2) == 0)
                && ((res1.length() == res2.length())
                || (res1.charAt(res2.length()) == '.')
                && (res1.length() != res2.length()))) {
            return true;
        }
        else return false;
    }
}
