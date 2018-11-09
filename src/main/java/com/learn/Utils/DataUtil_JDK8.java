package com.learn.Utils;

import org.springframework.data.convert.Jsr310Converters;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.joda.DateTimeFormatterFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

public class DataUtil_JDK8 {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        long nt = System.currentTimeMillis();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss:");
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(localDateTime.atZone(zoneId).toInstant().toEpochMilli());
        System.out.println(nt);
    }
}
