package com.kotikan.android.hack.wear.helloworld.utils;

import android.os.Handler;
import android.view.View;

import com.kotikan.android.hack.wear.helloworld.eventbus.Messages;
import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.CollisionDetected;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnGameStart;

public class CollisionDetector implements EventHandler {

    private final Handler handler;
    private final Block playerBlock;
    private final Block enemy;
    private boolean isRunning = false;
    final private Runnable collisionRunnable = new Runnable() {
        @Override
        public void run() {
            boolean hit = CollisionMonitor.hasCollided(playerBlock, enemy);
            if (hit) {
                Messages.bus().sendEvent(CollisionDetected.class);
            } else {
                runDetector();
            }
        }
    };

    public CollisionDetector(View playerBlock, View enemy) {
        this.playerBlock = new ViewBlock(playerBlock);
        this.enemy = new ViewBlock(enemy);
        handler = new Handler();
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (event == OnGameStart.class) {
            if (!isRunning) {
                isRunning = true;
                runDetector();
            }
        }
    }

    private void runDetector() {
        handler.postDelayed(collisionRunnable, 1);
    }
}
