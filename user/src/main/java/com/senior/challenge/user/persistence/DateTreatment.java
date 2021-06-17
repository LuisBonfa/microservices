package com.senior.challenge.user.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTreatment {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public Date formatDate(String date) throws ParseException {
        format.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
        return format.parse(date);
    }
}
