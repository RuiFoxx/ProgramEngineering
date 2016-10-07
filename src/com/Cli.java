package com;

import org.apache.commons.cli.*;

import java.util.ArrayList;


public class Cli {
    private static Options options = new Options();

    private static boolean authentication = false;
    private static boolean authorization = false;
    private static boolean accounting = false;

    static void parse(ArrayList <User> users, ArrayList <Role> roles, String... args) throws Throwable {
        CmdUser cmdData = new CmdUser(null,null,null,null,null,null);

        options.addOption(new Option("l", "login", true, "your login"))
                .addOption(new Option("p", "password", true, "your password"))
                .addOption(new Option("res", "resource", true, "requested resource"))
                .addOption(new Option("rol", "role", true, "your role"))
                .addOption(new Option("ds", "date-start", true, "start date (YYYY-MM-DD)"))
                .addOption(new Option("de", "date-end", true, "end date (YYYY-MM-DD)"))
                .addOption(new Option("v", "volume", true, "volume"))
                .addOption(new Option("h", "help", false, "help")); //добавляем опции для последующего парсинга

        CommandLineParser cmdLineParser = new DefaultParser();
        //CommandLineParser - тип данных, DefaultParser - тип парсера
        CommandLine cmdLine = cmdLineParser.parse(options, args);
        //Комнадлайн - тип данных, парс - функция (массив опций, строка аргументов), цмдлайн - разбитая строка

       //Заполняем класс
        if (cmdLine.hasOption("l")) {//хэзопшн- проверяет наличие опции
            cmdData.setLogin(cmdLine.getOptionValue("l")); //помещаем в класс значение опции, соответствующей ключу -l
        }
        if (cmdLine.hasOption("p")) {
            cmdData.setPassword(cmdLine.getOptionValue("p"));
        }
        if (cmdLine.hasOption("res")) {
            cmdData.setResource(cmdLine.getOptionValue("res"));
        }
        if (cmdLine.hasOption("rol")) {
            cmdData.setRole(cmdLine.getOptionValue("rol"));
        }
        if (cmdLine.hasOption("ds")) {
            cmdData.setDate_start(cmdLine.getOptionValue("ds"));
        }
        if (cmdLine.hasOption("de")) {
            cmdData.setDate_end(cmdLine.getOptionValue("de"));
        }
        if (cmdLine.hasOption("v")) {
            cmdData.setVolume(cmdLine.getOptionValue("v"));
        }

        if (cmdData.getLogin()!=null && cmdData.getPassword()!=null
                && cmdData.getResource()==null && cmdData.getRole()==null && cmdData.getVolume()==null
                && cmdData.getDate_start()==null && cmdData.getDate_end()==null){
            authentication = true;
        }

        if (cmdData.getLogin()!=null && cmdData.getPassword()!=null && cmdData.getResource()!=null && cmdData.getRole()!=null
                && cmdData.getVolume()==null && cmdData.getDate_start()==null && cmdData.getDate_end()==null){
            authentication = true;
            authorization = true;
        }

        if (cmdData.getLogin()!=null && cmdData.getPassword()!=null && cmdData.getResource()!=null && cmdData.getRole()!=null
                && cmdData.getVolume()!=null && cmdData.getDate_start()!=null && cmdData.getDate_end()!=null){
            authentication = true;
            authorization = true;
            accounting = true;
        }

        if (authentication) {
            Check.checkAuthentication(users, roles, cmdData);
        }
        else Cli.help();

    }

    public static void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("AAA protocol", options);
        System.exit(0);
    }

    public static boolean isAuthentication() {
        return authentication;
    }

    public static boolean isAuthorization() {
        return authorization;
    }

    public static boolean isAccounting() {
        return accounting;
    }
}

