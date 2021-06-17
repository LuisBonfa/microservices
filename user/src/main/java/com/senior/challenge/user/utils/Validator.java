package com.senior.challenge.user.utils;

import java.util.Calendar;

public class Validator {

    public static boolean nameMatch(String name){
        String[] letters = name.split("");
        boolean result = letters[0].matches("^[A-Za-z]");
        return result;
    }

    public static boolean verifyWeekend(Calendar begin) {
        if (begin.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                begin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
            return true;
        return false;
    }
}
