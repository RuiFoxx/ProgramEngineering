package com;

import org.apache.commons.cli.*;
import java.util.*;

public class Cli
{
    public static HashMap<String, String> parse(String... args) throws ParseException
    {
        HashMap<String, String> arrArgValues=new HashMap<>(); //Map - пара (ключ, значение)
        Option loginOption=new Option("l", "login", true, " your login"); // Короткое название, длинное, принимет ли ключ данные, текстовое пояснеие
        Option passOption=new Option("p", "password", true, " your password"); //Стандартная функция библиотеки cli
        Option resOption=new Option("res", "resource", true, " resource");
        Option roleOption=new Option("rol", "role", true, " role");
        Option dsOption=new Option("ds", "date-start", true, " start date (DD-MM-YYYY)");
        Option deOption=new Option("de", "date-end", true, " end date (DD-MM-YYYY)");
        Option volOption=new Option("v", "volume", true, " volume");

        loginOption.setArgs(1); // Колличество параметров которые принимаем в опции
        loginOption.setArgName("Login"); // имя аргумента опции
        passOption.setArgs(1);
        passOption.setArgName("Password");
        resOption.setArgs(1);
        resOption.setArgName("Resource");
        roleOption.setArgs(1);
        roleOption.setArgName("com.Role");
        dsOption.setArgs(1);
        dsOption.setArgName("Start date");
        deOption.setArgs(1);
        deOption.setArgName("End date");
        volOption.setArgs(1);
        volOption.setArgName("Volume");

        Options posixOptions=new Options(); //стандартная функция (конструктор класса) создает массив опций
        posixOptions.addOption(loginOption); //добавляем опции для последующего парсинга
        posixOptions.addOption(passOption);
        posixOptions.addOption(resOption);
        posixOptions.addOption(roleOption);
        posixOptions.addOption(dsOption);
        posixOptions.addOption(deOption);
        posixOptions.addOption(volOption);

        CommandLineParser cmdLinePosixParser=new PosixParser();
        //CommandLineParser - тип данных, PosixParser - тип парсера
        CommandLine cmdLine = cmdLinePosixParser.parse(posixOptions, args);
        //Комнадлайн - тип данных, парс - функция (массив опций, строка аргументов), цмдлайн - разбитая строка

        if (cmdLine.hasOption("l"))//хэзопшн- проверяет наличие опции
            arrArgValues.put("login", cmdLine.getOptionValue("l")); //помещаем в мап под ключ "логин" значение опции, соответствующей ключу -l
        if (cmdLine.hasOption("p"))
            arrArgValues.put("password", cmdLine.getOptionValue("p"));
        if (cmdLine.hasOption("res"))
            arrArgValues.put("resource", cmdLine.getOptionValue("res"));
        if (cmdLine.hasOption("rol"))
            arrArgValues.put("role", cmdLine.getOptionValue("rol"));
        if (cmdLine.hasOption("ds"))
            arrArgValues.put("date-start", cmdLine.getOptionValue("ds"));
        if (cmdLine.hasOption("de"))
            arrArgValues.put("date-end", cmdLine.getOptionValue("de"));
        if (cmdLine.hasOption("v"))
            arrArgValues.put("volume", cmdLine.getOptionValue("v"));

            for (HashMap.Entry<String, String> pair : arrArgValues.entrySet()) //вывод мэпа на экран
                System.out.println(pair.getKey() + ": " + pair.getValue());

        return arrArgValues;
    }
}
