package com.lowen.blog.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String NUMBER_REG = "[^0-9]";
    private static final String PATTERN_LAST_PARAM = "\\d+$";
    private static final String PATTERN_SECOND_LAST_PARAM = "/\\d+/";

    public static int getSecondLastIntegerParam(String url) {
        Matcher matcher = Pattern.compile(PATTERN_SECOND_LAST_PARAM).matcher(url);
        String param = null;
        while (matcher.find()) {
            param = matcher.group();
        }
        if (param != null) {
            param = param.replaceAll("/", "");
        }
        return stringToInteger(param);
    }

    public static int getLastIntegerParam(String url) {
        Matcher matcher = Pattern.compile(PATTERN_LAST_PARAM).matcher(url);
        if (matcher.find()) {
            return stringToInteger(matcher.group());
        }
        return 0;
    }

    public static int filterInteger(String text) {
        Pattern p = Pattern.compile(NUMBER_REG);
        Matcher m = p.matcher(text);
        String numberText = m.replaceAll("").trim();
        try {
            return Integer.parseInt(numberText);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Date parseDate(String source) {
        try {
            return DATE_FORMAT.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }


    public static int stringToInteger(String string) {
        try {
            if (string != null) {
                return Integer.parseInt(string);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
