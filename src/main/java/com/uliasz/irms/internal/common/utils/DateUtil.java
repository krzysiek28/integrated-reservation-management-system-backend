package com.uliasz.irms.internal.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

    public static Date removeTimeFromDate(Date date) {
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date formattedDate = formatter.parse(formatter.format(date));
            return formattedDate;
        } catch (ParseException e) {
            return date;
        }
    }

    public static boolean isDateAfter(Date date, Date dateAfterToCompare) {
        boolean yearsAfter = date.getYear() > dateAfterToCompare.getYear();
        boolean monthAfter = date.getMonth() > dateAfterToCompare.getMonth();
        boolean dateAfter = date.getDate() > dateAfterToCompare.getDate();

        boolean yearsEquals = date.getYear() == dateAfterToCompare.getYear();
        boolean monthEquals = date.getMonth() == dateAfterToCompare.getMonth();
        boolean dateEquals = date.getDate() == dateAfterToCompare.getDate();

        return yearsAfter || (yearsEquals && monthAfter) || (yearsEquals && monthEquals && (dateAfter || dateEquals));
    }

    public static boolean isDateBefore(Date date, Date dateBeforeToCompare) {
        boolean yearsBefore = date.getYear() < dateBeforeToCompare.getYear();
        boolean monthBefore = date.getMonth() < dateBeforeToCompare.getMonth();
        boolean dateBefore = date.getDate() < dateBeforeToCompare.getDate();

        boolean yearsEquals = date.getYear() == dateBeforeToCompare.getYear();
        boolean monthEquals = date.getMonth() == dateBeforeToCompare.getMonth();
        boolean dateEquals = date.getDate() == dateBeforeToCompare.getDate();

        return yearsBefore || (yearsEquals && monthBefore) || (yearsEquals && monthEquals && (dateBefore || dateEquals));
    }

    public static boolean isDateBetween(Date date, Date startDate, Date endDate) {
        boolean yearsBetween = date.getYear() > startDate.getYear() && date.getYear() < endDate.getYear();
        boolean monthBetween = date.getMonth() > startDate.getMonth() && date.getMonth() < endDate.getMonth();
        boolean dateBetween = date.getDate() > startDate.getDate() && date.getDate() < endDate.getDate();

        boolean yearsEquals = date.getYear() == startDate.getYear();
        boolean monthEquals = date.getMonth() == startDate.getMonth();
        boolean dateEquals = date.getDate() == startDate.getDate();

        return yearsBetween || (yearsEquals && monthBetween) || (yearsEquals && monthEquals && (dateBetween || dateEquals));
    }

    public static boolean isTimeAfter(Date time, String timeAfterToCompare){
        return isTimeAfter(time, createTmpDateForTime(timeAfterToCompare));
    }

    public static boolean isTimeBefore(Date time, String timeBeforeToCompare){
        return isTimeBefore(time, createTmpDateForTime(timeBeforeToCompare));
    }

    private static Date createTmpDateForTime(String time) {
        String[] timeParts = time.split(":");
        Date dateForTime = new Date();

        if(timeParts.length == 2) {
            dateForTime.setHours(Integer.parseInt(timeParts[0]));
            dateForTime.setMinutes(Integer.parseInt(timeParts[1]));
            dateForTime.setSeconds(0);
        }
        if(timeParts.length == 3) {
            dateForTime.setHours(Integer.parseInt(timeParts[0]));
            dateForTime.setMinutes(Integer.parseInt(timeParts[1]));
            dateForTime.setSeconds(Integer.parseInt(timeParts[2]));
        }
        return dateForTime;
    }

    public static boolean isTimeAfter(Date time, Date timeAfterToCompare){
        boolean hourAfter = time.getHours() > timeAfterToCompare.getHours();
        boolean minutesAfter = time.getMinutes() > timeAfterToCompare.getMinutes();
        boolean secondsAfter = time.getSeconds() > timeAfterToCompare.getSeconds();

        boolean hourEqual = time.getHours() == timeAfterToCompare.getHours();
        boolean minutesEqual = time.getMinutes() == timeAfterToCompare.getMinutes();
        boolean secondsEqual = time.getSeconds() == timeAfterToCompare.getSeconds();
        return hourAfter || (hourEqual && minutesAfter) || (hourEqual && minutesEqual && (secondsAfter || secondsEqual));
    }

    public static boolean isTimeBefore(Date time, Date timeBeforeToCompare){
        boolean hourBefore = time.getHours() < timeBeforeToCompare.getHours();
        boolean minutesBefore = time.getMinutes() < timeBeforeToCompare.getMinutes();
        boolean secondsBefore = time.getSeconds() < timeBeforeToCompare.getSeconds();

        boolean hourEqual = time.getHours() == timeBeforeToCompare.getHours();
        boolean minutesEqual = time.getMinutes() == timeBeforeToCompare.getMinutes();
        boolean secondsEqual = time.getSeconds() == timeBeforeToCompare.getSeconds();
        return hourBefore || (hourEqual && minutesBefore) || (hourEqual && minutesEqual && (secondsBefore || secondsEqual));
    }
}
