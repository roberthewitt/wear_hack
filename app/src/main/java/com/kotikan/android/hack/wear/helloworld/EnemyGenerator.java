package com.kotikan.android.hack.wear.helloworld;

import android.os.Handler;

import com.kotikan.android.hack.wear.helloworld.eventbus.Bus;
import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.CollisionDetected;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.SpawnEnemy;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnGameStart;

public class EnemyGenerator implements EventHandler {
    private final Handler handler;
    private boolean isAnimating = false;
    private boolean shouldSpawn = true;

    public EnemyGenerator(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (event == OnGameStart.class) {
            if (!isAnimating) {
                shouldSpawn = true;
                isAnimating = true;
                generateWithDelay(750);
            }
        } else if (event == CollisionDetected.class) {
            shouldSpawn = false;
            isAnimating = false;
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
                if (shouldSpawn) {
                    Bus.bus().sendEvent(SpawnEnemy.class);
                    generateWithDelay(750);
                }
            }
        };
    }

}
