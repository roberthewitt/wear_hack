package com.kotikan.android.hack.wear.helloworld.utils;

import junit.framework.Assert;

import org.junit.Test;

import java.util.Date;

public class NumberFormatterTest {

    @Test
    public void generates_zero() throws Exception {
        Date firstTime = new Date(555);
        Date secondTime = new Date(555);
        DateFactory factory = factoryReturning(firstTime, secondTime);
        NumberFormatter f = formatter(factory);

        Assert.assertEquals("00m:00s:000m", f.generate());
    }

    @Test
    public void generates_1_milli_second() throws Exception {
        Date firstTime = new Date(555);
        Date secondTime = new Date(556);
        DateFactory factory = factoryReturning(firstTime, secondTime);
        NumberFormatter f = formatter(factory);

        Assert.assertEquals("00m:00s:001m", f.generate());
    }


    @Test
    public void generates_999_milli_second() throws Exception {
        Date firstTime = new Date(0);
        Date secondTime = new Date(999);
        DateFactory factory = factoryReturning(firstTime, secondTime);
        NumberFormatter f = formatter(factory);

        Assert.assertEquals("00m:00s:999m", f.generate());
    }

    @Test
    public void generates_1_second_for_1000_millis() throws Exception {
        Date firstTime = new Date(0);
        Date secondTime = new Date(1000);
        DateFactory factory = factoryReturning(firstTime, secondTime);
        NumberFormatter f = formatter(factory);

        Assert.assertEquals("00m:01s:000m", f.generate());
    }

    @Test
    public void generates_59_second() throws Exception {
        Date firstTime = new Date(0);
        Date secondTime = new Date(59000);
        DateFactory factory = factoryReturning(firstTime, secondTime);
        NumberFormatter f = formatter(factory);

        Assert.assertEquals("00m:59s:000m", f.generate());
    }


    @Test
    public void generates_1_minute_for_60_second() throws Exception {
        Date firstTime = new Date(0);
        Date secondTime = new Date(60000);
        DateFactory factory = factoryReturning(firstTime, secondTime);
        NumberFormatter f = formatter(factory);

        Assert.assertEquals("01m:00s:000m", f.generate());
    }


    private DateFactory factoryReturning(final Date... dates) {
        return new DateFactory() {
            int i = 0;
            @Override
            public Date getNow() {
                Date date = dates[i];
                i++;
                return date;
            }
        };
    }

    private NumberFormatter formatter(DateFactory dateFactory) {
        return new NumberFormatter(dateFactory);
    }
}