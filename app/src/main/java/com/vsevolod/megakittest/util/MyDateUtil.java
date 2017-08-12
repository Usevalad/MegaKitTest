package com.vsevolod.megakittest.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Student Vsevolod on 8/10/17.
 * usevalad.uladzimiravich@gmail.comxZ
 */
public final class MyDateUtil {

    private static SimpleDateFormat searchDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Simple method return date in search format
     * @return String date
     */
    public static String getSearchDate() {
        searchDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return searchDateFormat.format(new Date());
    }
}