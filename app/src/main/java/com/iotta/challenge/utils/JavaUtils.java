package com.iotta.challenge.utils;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Galya on 06/07/2017.
 */

public class JavaUtils {

    public static String convert(@NonNull Date date) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault());
        return df.format(date);
    }

    public static Date convert(@NonNull String strDate) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            Date date = format.parse(strDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            Logger.error("Failed to parse string", strDate);
            return null;
        }

    }
}
