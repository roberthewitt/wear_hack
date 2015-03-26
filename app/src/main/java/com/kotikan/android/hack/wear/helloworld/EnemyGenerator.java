package com.kotikan.android.hack.wear.helloworld;

import android.os.Handler;

import com.kotikan.android.hack.wear.helloworld.eventbus.Bus;
import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.EnterEnemy;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;

public class EnemyGenerator implements EventHandler {
    private final Handler handler;
    private boolean isAnimating = false;

    public EnemyGenerator(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (!isAnimating) {
            isAnimating = true;
            generateWithDelay(750);
        }
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
