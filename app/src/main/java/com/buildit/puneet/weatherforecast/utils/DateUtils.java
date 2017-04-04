package com.buildit.puneet.weatherforecast.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dell laptop on 4/4/2017.
 */

public class DateUtils {

    public static String formatDate(String dateValue) {
        // Convert input string into a date
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = inputFormat.parse(dateValue.toString());
            DateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy");
            String outputString = outputFormat.format(date);
            return  outputString;
        } catch (ParseException e) {
            e.printStackTrace();
        }
return "";
// Format date into output format

    }
}
