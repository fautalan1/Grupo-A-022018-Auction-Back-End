package modelTest;

import java.util.Calendar;
import java.util.Date;

public class DateFactory {

    public static Date now() {
        Calendar nowDate = Calendar.getInstance();
        nowDate.setTime(new Date());
        return nowDate.getTime();
    }

    static Date oneHourAfterTheCurrent() {
        Calendar oneHourAfterTheCurrent = Calendar.getInstance();
        oneHourAfterTheCurrent.setTime(new Date());
        oneHourAfterTheCurrent.add(Calendar.HOUR, 1);
        return oneHourAfterTheCurrent.getTime();
    }

    static Date oneHourBeforeTheCurrent() {
        Calendar oneHourBeforeTheCurrent = Calendar.getInstance();
        oneHourBeforeTheCurrent.setTime(new Date());
        oneHourBeforeTheCurrent.add(Calendar.HOUR, -1);
        return oneHourBeforeTheCurrent.getTime();
    }

    static Date oneMinuteAfterTheCurrent() {
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

    public static Date addFiveMinutes(Date oldFinishDate) {
        Calendar fiveMinuteMore = Calendar.getInstance();
        fiveMinuteMore.setTime(oldFinishDate);
        fiveMinuteMore.add(Calendar.MINUTE, 5);
        return fiveMinuteMore.getTime();
    }
}