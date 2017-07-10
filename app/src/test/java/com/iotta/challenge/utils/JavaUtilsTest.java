package com.iotta.challenge.utils;

import android.support.annotation.NonNull;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Created by Galya on 09/07/2017.
 */
public class JavaUtilsTest {
    @Test
    public void convert() throws Exception {
        String strDate = "12/25/1985 01:00:00";
        Date date = new Date();
        date.setTime(12);

        assertEquals(new Date(), 2 + 2);
    }
}