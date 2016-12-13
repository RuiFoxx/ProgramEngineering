package com;

import java.sql.SQLException;
import java.text.*;
import java.util.*;

import com.domain.Accounting;
import com.domain.Role;
import com.domain.User;
import com.servlet.InjectLogger;
import org.apache.log4j.*;


import static com.Hash.hash;

public class Processing {

    @InjectLogger Logger logger;


    public int checkAuthentication(AaaDao aaa, CmdUser cmdData) throws Throwable {
        String login = cmdData.getLogin();
        String pass = cmdData.getPassword();
        User curUser = null;

        if (aaa.getUsers(login) != null) {
            curUser = new User(aaa.getUsers(login));
        }

        if (curUser == null) {
            logger.error("User "+login+" not found. Exit code: 1");
            //System.exit(1);
            return 1;
        }

        pass = hash(hash(pass) + curUser.getSalt());
        if (!curUser.getPassword().equals(pass)) {
            logger.error("Password is wrong for user "+login+". Exit code: 2");
            //System.exit(2);
            return 2;
        }

        if (cmdData.isAuthorization()) {
            ArrayList<Role> currentRoles = aaa.getRoles(curUser.getId()); //все роли для пользователя

            logger.info("Authentication complete for user "+ login);
            return new Processing().checkAuthorization(currentRoles, cmdData, aaa);
        }
        else {
            logger.info("Authentication complete for user "+ login+". Exit code: 0");
            //System.exit(0);
            return 0;
        }
    }

    public int checkAuthorization(ArrayList<Role> currentRoles, CmdUser cmdData, AaaDao aaa) throws ParseException {
        //выделяем реусрс и роль
        String role = cmdData.getRole().toUpperCase();
        String resource = cmdData.getResource();
        Role trueRole = new Role(null, currentRoles.get(0).getUserId(), null, null);

        //Проверка
        System.out.println(role + " " + resource);

        if (!role.equals("READ") && !role.equals("WRITE") && !role.equals("EXECUTE")) {
            logger.error("Invalid role '"+role+"'. Exit code: 3");
            //System.exit(3);
            return 3;
        }

        for (Role r : currentRoles) {
            //проверка ресурсов
            if (new Processing().checkResource(resource,r.getResource())) {
                trueRole.setResource(resource);
                if (r.getName().equals(role)) {
                    trueRole.setName(role);
                    trueRole.setId(r.getId());
                    break;
                }
            }
        }
        if ((trueRole.getResource() == null)||(trueRole.getName() == null)) {
            logger.error("No access to '"+resource+"' for user "+cmdData.getLogin()+". Exit code: 4");
            //System.exit(4);
            return 4;
        }

        if (cmdData.isAccounting()) {
            logger.info("Authorization complete for user "+cmdData.getLogin());
            Accounting acc = new Accounting();
            acc.setRole_id(trueRole.getId());
            return new Processing().checkAccounting(cmdData, acc, aaa);
        }
        else {
            logger.info("Authorization complete for user "+cmdData.getLogin()+". Exit code: 0");
            //System.exit(0);
            return 0;
        }
    }

    public int checkAccounting(CmdUser cmdData, Accounting acc, AaaDao aaa) {

        String ds = cmdData.getDateStart();
        String de = cmdData.getDateEnd();
        int vol = 0;
        try {
            vol=Integer.parseInt(cmdData.getVolume());
        } catch (NumberFormatException e) {
            logger.error("Failed to parse volume '"+cmdData.getVolume()+"'. Exit code: 5. StackTrace: ", e);
            //System.exit(5);
            return 5;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);

        Date dateStart = null, dateEnd = null;

        try {
            dateStart = format.parse(ds);

            dateEnd = format.parse(de);
        }
        catch (ParseException e) {
            logger.error("Invalid date or wrong format. Exit code: 5. StackTrace: ", e);
            //System.exit(5);
            return 5;
        }

        //Проверка объема
        if (vol < 0) {
            logger.error("Wrong volume '"+vol+"'. Exit code: 5");
            //System.exit(5);
            return 5;
        }

        acc.setDate_start(dateStart);
        acc.setDate_end(dateEnd);
        acc.setVolume(vol);
        try {
            aaa.setAcc(acc);
        } catch (SQLException e) {
            logger.error("Failed to add accounting class in database. Stacktrace: ", e);
        }

        logger.info("Accounting complete for user "+cmdData.getLogin()+". Exit code: 0");

        //System.exit(0);
        return 0;
    }

    private boolean checkResource (String res1, String res2) {
        return (res1.indexOf(res2) == 0)
                && ((res1.length() == res2.length())
                || (res1.charAt(res2.length()) == '.')
                && (res1.length() != res2.length()));
    }
}
