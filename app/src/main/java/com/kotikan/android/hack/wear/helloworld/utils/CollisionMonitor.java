package com.kotikan.android.hack.wear.helloworld.utils;

public class CollisionMonitor {

    private CollisionMonitor(){}

    public static boolean hasCollided(Block a, Block b) {
        return  Math.abs(a.x() - b.x()) <= a.width();
    }
}
