package com.kotikan.android.hack.wear.helloworld.utils;

public class NumberFormatter {

    private final DateFactory dateFactory;
    private final long startTime;

    public NumberFormatter(DateFactory dateFactory) {
        this.dateFactory = dateFactory;
        startTime = dateFactory.getNow().getTime();
    }

    public String generate() {
        long nowTime = dateFactory.getNow().getTime();
        long l = nowTime - startTime;
        final String millis = calcMillis(l);
        final String seconds = calcSeconds(l);
        final String minutes = calcMinutes(l);


        return minutes + "m:" + seconds + "s:" + millis + "m";
    }

    private String calcMinutes(long l) {
        int minutes = (int) (l / 60000);
        String s = String.valueOf(minutes);
        while (s.length() != 2) {
            s = "0" + s;
        }
        return s;
    }

    private String calcSeconds(long l) {
        int seconds = (int) (l / 1000);
        String s = String.valueOf(seconds % 60);
        while (s.length() != 2) {
            s = "0" + s;
        }
        return s;
    }

    private String calcMillis(long l) {
        int milliseconds = (int) (l % 1000);
        String s = String.valueOf(milliseconds);
        while (s.length() != 3) {
            s = "0" + s;
        }
        return s;
    }
}
