package model;

import java.util.Calendar;
import java.util.Date;

public class DateFactory {

    public static Date now() {
        Calendar nowDate = Calendar.getInstance();
        nowDate.setTime(new Date());
        return nowDate.getTime();
    }

    public static Date oneHourAfterTheCurrent() {
        Calendar oneHourAfterTheCurrent = Calendar.getInstance();
        oneHourAfterTheCurrent.setTime(new Date());
        oneHourAfterTheCurrent.add(Calendar.HOUR, 1);
        return oneHourAfterTheCurrent.getTime();
    }

    public static Date oneHourBeforeTheCurrent() {
        Calendar oneHourBeforeTheCurrent = Calendar.getInstance();
        oneHourBeforeTheCurrent.setTime(new Date());
        oneHourBeforeTheCurrent.add(Calendar.HOUR, -1);
        return oneHourBeforeTheCurrent.getTime();
    }

    public static Date oneMinuteAfterTheCurrent() {
        Calendar oneMinuteAfterTheCurrent = Calendar.getInstance();
        oneMinuteAfterTheCurrent.setTime(new Date());
        oneMinuteAfterTheCurrent.add(Calendar.MINUTE, 1);
        return oneMinuteAfterTheCurrent.getTime();
    }

    public static Date fiveMinutesBeforeTheCurrent() {
        Calendar fiveMinutesBeforeTheCurrent = Calendar.getInstance();
        fiveMinutesBeforeTheCurrent.setTime(new Date());
        fiveMinutesBeforeTheCurrent.add(Calendar.MINUTE, -1);
        return fiveMinutesBeforeTheCurrent.getTime();
    }

    public static Date tenMinutesBeforeTheCurrent() {
        Calendar tenMinutesBeforeTheCurrent = Calendar.getInstance();
        tenMinutesBeforeTheCurrent.setTime(new Date());
        tenMinutesBeforeTheCurrent.add(Calendar.MINUTE, -10);
        return tenMinutesBeforeTheCurrent.getTime();
    }

    public static Date addFiveMinutes(Date date) {
        Calendar fiveMinuteMore = Calendar.getInstance();
        fiveMinuteMore.setTime(date);
        fiveMinuteMore.add(Calendar.MINUTE, 5);
        return fiveMinuteMore.getTime();
    }

    public static Date addFortyEightHours(Date date) {
        Calendar fortyEightHoursMore = Calendar.getInstance();
        fortyEightHoursMore.setTime(date);
        fortyEightHoursMore.add(Calendar.DAY_OF_WEEK, 2);
        return fortyEightHoursMore.getTime();
    }

    public static Date fortyEightHoursBeforeTheCurrent() {
        Calendar fortyEightHoursBeforeTheCurrent = Calendar.getInstance();
        fortyEightHoursBeforeTheCurrent.setTime(new Date());
        fortyEightHoursBeforeTheCurrent.add(Calendar.DAY_OF_WEEK, -2);
        return fortyEightHoursBeforeTheCurrent.getTime();
    }
}