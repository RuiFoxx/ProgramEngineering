package com;

import org.apache.commons.cli.*;


public class Cli {
    private Options options = new Options();

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
        int count = 0;

        CommandLineParser cmdLineParser = new DefaultParser();
        //CommandLineParser - тип данных, DefaultParser - тип парсера
        CommandLine cmdLine = cmdLineParser.parse(options, args);
        //Комнадлайн - тип данных, парс - функция (массив опций, строка аргументов), цмдлайн - разбитая строка

       //Заполняем класс
        if (cmdLine.hasOption("l")) {//хэзопшн- проверяет наличие опции
            cmdData.setLogin(cmdLine.getOptionValue("l")); //помещаем в класс значение опции, соответствующей ключу -l
            count += 1;
        }
        if (cmdLine.hasOption("p")) {
            cmdData.setPassword(cmdLine.getOptionValue("p"));
            count += 2;
        }
        if (cmdLine.hasOption("res")) {
            cmdData.setResource(cmdLine.getOptionValue("res"));
            count += 4;
        }
        if (cmdLine.hasOption("rol")) {
            cmdData.setRole(cmdLine.getOptionValue("rol"));
            count += 8;
        }
        if (cmdLine.hasOption("ds")) {
            cmdData.setDate_start(cmdLine.getOptionValue("ds"));
            count += 16;
        }
        if (cmdLine.hasOption("de")) {
            cmdData.setDate_end(cmdLine.getOptionValue("de"));
            count += 32;
        }
        if (cmdLine.hasOption("v")) {
            cmdData.setVolume(cmdLine.getOptionValue("v"));
            count += 64;
        }

        switch (count) {
            case 3 : cmdData.getCheck().setAuthentication();
                break;
            case 15 : cmdData.getCheck().setAuthorization();
                break;
            case 127: cmdData.getCheck().setAccounting();
        }

        if (cmdData.getCheck().isAuthentication()) {
            Processing.checkAuthentication(aaa, cmdData);
        }
        else Cli.help();

    }

    public static void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("AAA protocol", new Cli().options);
        Processing.logger.info("Showed help");
        System.exit(0);
    }
}

