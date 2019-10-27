package com.aliction.gitproviders.bitbucket.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Utilities class mainly for conversion used through out the library
 * @author Aly Ibrahim
 * Date: Oct 26, 2019
 *
 */
public class Converters {

    /**
     * Convert Date object to ISO 8601 time format String
     * Example : 2019-10-23T20:30:52.000+02:00
     * The default timezone as expected by Bitbucket API is UTC
     * @param datetime
     * @param timezone
     * @return date time String in ISO 8601 format
     */
    public static String ConvertDateToISO8601(Date datetime, String timezone) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        if (timezone == null) {
            timezone = "UTC";
        }
        sdf.setTimeZone(TimeZone.getTimeZone(timezone));
        String iso8601 = sdf.format(datetime);
        return iso8601;
    }

}
