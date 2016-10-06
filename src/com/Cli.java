package com;

import org.apache.commons.cli.*;


public class Cli {
    private static Options options = new Options();

    static CmdUser parse(String... args) throws ParseException {
        CmdUser сmdData = new CmdUser(null,null,null,null,null,null);

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
            сmdData.setLogin(cmdLine.getOptionValue("l")); //помещаем в класс значение опции, соответствующей ключу -l
        }
        if (cmdLine.hasOption("p")) {
            сmdData.setPassword(cmdLine.getOptionValue("p"));
        }
        if (cmdLine.hasOption("res")) {
            сmdData.setResource(cmdLine.getOptionValue("res"));
        }
        if (cmdLine.hasOption("rol")) {
            сmdData.setRole(cmdLine.getOptionValue("rol"));
        }
        if (cmdLine.hasOption("ds")) {
            сmdData.setDate_start(cmdLine.getOptionValue("ds"));
        }
        if (cmdLine.hasOption("de")) {
            сmdData.setDate_end(cmdLine.getOptionValue("de"));
        }
        if (cmdLine.hasOption("v")) {
            сmdData.setVolume(cmdLine.getOptionValue("v"));
        }

        return сmdData;
    }

    public static void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("AAA protocol", options);
        System.exit(0);
    }
}

