package com.kotikan.android.hack.wear.helloworld.utils;

import java.util.Date;

public class JavaDateFactory implements DateFactory {
    @Override
    public Date getNow() {
        return new Date();
    }
}
