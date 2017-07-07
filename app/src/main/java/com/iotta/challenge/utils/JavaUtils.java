package com.iotta.challenge.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Galya on 06/07/2017.
 */

public class JavaUtils {

    public static String convert(Date date) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault());
        return df.format(date);
    }

}
