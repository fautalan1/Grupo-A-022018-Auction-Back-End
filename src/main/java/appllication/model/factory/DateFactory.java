package appllication.model.factory;

import java.util.Calendar;
import java.util.Date;

public class DateFactory {

    public Date now() {
        Calendar nowDate = Calendar.getInstance();
        nowDate.setTime(new Date());
        return nowDate.getTime();
    }


    public static Date add(Date date, int time, int howMuch) {
        Calendar newDate = Calendar.getInstance();
        newDate.setTime(date);
        newDate.add(time, howMuch);
        return newDate.getTime();
    }
}