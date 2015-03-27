package com.kotikan.android.hack.wear.helloworld.utils;

import com.kotikan.android.hack.wear.helloworld.abstractions.Block;

public class CollisionMonitor {

    private CollisionMonitor(){}

    public static boolean hasCollided(Block a, Block b) {
        boolean xHit = Math.abs(a.x() - b.x()) <= a.width();
        boolean yHit = Math.abs(a.y() - b.y()) <= a.height();
        return xHit && yHit;
    }
}
