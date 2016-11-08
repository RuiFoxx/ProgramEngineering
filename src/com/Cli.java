package com;

import org.apache.commons.cli.*;

import java.util.ArrayList;


public class Cli {
    private Options options = new Options();

    private static boolean authentication = false;
    private static boolean authorization = false;
    private static boolean accounting = false;

    public Cli() {
        options.addOption(new Option("l", "login", true, "your login"))
                .addOption(new Option("p", "password", true, "your password"))
                .addOption(new Option("res", "resource", true, "requested resource"))
                .addOption(new Option("rol", "role", true, "your role"))
                .addOption(new Option("ds", "date-start", true, "start date (YYYY-MM-DD)"))
                .addOption(new Option("de", "date-end", true, "end date (YYYY-MM-DD)"))
                .addOption(new Option("v", "volume", true, "volume"))
                .addOption(new Option("h", "help", false, "help")); //добавляем опции для последующего парсинга
    }

    void parse(AaaDao aaa, String... args) throws Throwable {
        CmdUser cmdData = new CmdUser();

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

        if (cmdData.isAuthentication()){
            authentication = true;
        }
        else
            if (cmdData.isAuthorization()) {
                 authentication = true;
                 authorization = true;
            }
            else
            if (cmdData.isAccounting()){
                authentication = true;
                authorization = true;
                accounting = true;
            }

        if (getAuthentication()) {
            Check.checkAuthentication(aaa, cmdData);
        }
        else Cli.help();

    }

    public static void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("AAA protocol", new Cli().options);
        Check.logger.info("Showed help");
        System.exit(0);
    }

    public static boolean getAuthentication() {
        return authentication;
    }

    public static boolean getAuthorization() {
        return authorization;
    }

    public static boolean getAccounting() {
        return accounting;
    }
}

