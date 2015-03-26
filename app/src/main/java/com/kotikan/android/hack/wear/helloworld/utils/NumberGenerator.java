package com.kotikan.android.hack.wear.helloworld.utils;

import java.util.Random;

public class NumberGenerator {
    public int generateBetween(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public int generateBetween(int min, int max, int excluding) {
        int result = generateBetween(min, max);
        while (result == excluding) {
            result = generateBetween(min, max);
        }
        return result;
    }
}
