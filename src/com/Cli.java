package com;

import org.apache.commons.cli.*;
import java.util.*;

public class Cli
{
    private static Options options = new Options();
    static HashMap<String, String> parse(String... args) throws ParseException
    {
        HashMap<String, String> arrArgValues=new HashMap<>(); //Map - пара (ключ, значение)

        options.addOption(new Option("l", "login", true, "your login"))
                .addOption(new Option("p", "password", true, "your password"))
                .addOption(new Option("res", "resource", true, "requested resource"))
                .addOption(new Option("rol", "role", true, "your role"))
                .addOption(new Option("ds", "date-start", true, "start date (YYYY-MM-DD)"))
                .addOption(new Option("de", "date-end", true, "end date (YYYY-MM-DD)"))
                .addOption(new Option("v", "volume", true, "volume"))
                .addOption(new Option("h", "help", false, "help")); //добавляем опции для последующего парсинга

        CommandLineParser cmdLineParser=new DefaultParser();
        //CommandLineParser - тип данных, DefaultParser - тип парсера
        CommandLine cmdLine=cmdLineParser.parse(options, args);
        //Комнадлайн - тип данных, парс - функция (массив опций, строка аргументов), цмдлайн - разбитая строка

        if(cmdLine.hasOption("l"))//хэзопшн- проверяет наличие опции
            arrArgValues.put("login", cmdLine.getOptionValue("l")); //помещаем в мап под ключ "логин" значение опции, соответствующей ключу -l
        if(cmdLine.hasOption("p"))
            arrArgValues.put("password", cmdLine.getOptionValue("p"));
        if(cmdLine.hasOption("res"))
            arrArgValues.put("resource", cmdLine.getOptionValue("res"));
        if(cmdLine.hasOption("rol"))
            arrArgValues.put("role", cmdLine.getOptionValue("rol"));
        if(cmdLine.hasOption("ds"))
            arrArgValues.put("date-start", cmdLine.getOptionValue("ds"));
        if(cmdLine.hasOption("de"))
            arrArgValues.put("date-end", cmdLine.getOptionValue("de"));
        if(cmdLine.hasOption("v"))
            arrArgValues.put("volume", cmdLine.getOptionValue("v"));
        if (cmdLine.hasOption("h"))
            arrArgValues.put("help", cmdLine.getOptionValue("h"));

        return arrArgValues;
    }

    public static void help()
    {
        HelpFormatter formatter = new HelpFormatter();

        formatter.printHelp("AAA protocol", options);
        System.exit(0);
    }
}
