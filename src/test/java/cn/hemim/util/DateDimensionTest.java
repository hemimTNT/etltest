package cn.hemim.util;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DateDimensionTest {
    private Calendar calendar = Calendar.getInstance();
    @Test
    public void testCalendar(){

//        calendar.set(0, 1);
        System.out.println(calendar.getTime());
        calendar.set(2019, 0, 1);
        int WEEK_OF_YEAR = calendar.get(Calendar.WEEK_OF_YEAR);
        System.out.println("WEEK_OF_YEAR:" + WEEK_OF_YEAR);
        int DAY_OF_WEEK = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("DAY_OF_WEEK:" + DAY_OF_WEEK);
        System.out.println("calendarType:" + calendar.getCalendarType());
    }

    @Test
    public void testFirstDayOfWeek(){
        long time = TimeUtil.getFirstDayOfWeek(new Date().getTime());
        calendar.setTimeInMillis(time);
        System.out.println("firstDayOfWeek:" + calendar.getTime());
    }
}