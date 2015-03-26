package com.kotikan.android.hack.wear.helloworld;

import android.os.Handler;

import com.kotikan.android.hack.wear.helloworld.eventbus.Bus;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.EnterEnemy;

public class EnemyGenerator {
    private final Handler handler;

    public EnemyGenerator(Handler handler) {
        this.handler = handler;
    }

    public void generate() {
        generateWithDelay(1000);
    }

    private void generateWithDelay(int i) {
        double spawnAt = Math.random() * 500d;
        handler.postDelayed(spawnEnemy(), i + (long) spawnAt);
    }

    private Runnable spawnEnemy() {
        return new Runnable() {
            @Override
            public void run() {
                Bus.bus().sendEvent(EnterEnemy.class);
                generateWithDelay(750);
            }
        };
    }
}
