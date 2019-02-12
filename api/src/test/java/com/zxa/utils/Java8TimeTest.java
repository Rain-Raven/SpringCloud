package com.zxa.utils;


import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Java8TimeTest {

    @Test
    public void  test(){
        LocalDateTime now=LocalDateTime.now();
        LocalDateTime yesterday=now.plusDays(-1);
        LocalDateTime nextYear=now.plusYears(1);
        System.out.println("now:"+now);
        System.out.println("Yesterday:"+yesterday);
        System.out.println("nextYear:"+nextYear);
    }
}
