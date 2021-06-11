package com.senior.challenge.user.utils;

public class PatternValidator {

    public static boolean nameMatch(String name){
        String[] letters = name.split("");
        boolean result = letters[0].matches("^[A-Za-z]");
        return result;
    }
}
