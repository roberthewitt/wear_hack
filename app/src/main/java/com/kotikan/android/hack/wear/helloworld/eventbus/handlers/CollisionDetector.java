package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import android.os.Handler;

import com.kotikan.android.hack.wear.helloworld.abstractions.ViewBlock;
import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.Messages;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.CollisionDetected;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.GameStart;
import com.kotikan.android.hack.wear.helloworld.abstractions.Block;
import com.kotikan.android.hack.wear.helloworld.utils.CollisionMonitor;

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
                isRunning = false;
                Messages.bus().sendEvent(CollisionDetected.class);
            } else {
                runDetector();
            }
        }
    };

    public CollisionDetector(ViewBlock playerBlock, ViewBlock enemy) {
        this.playerBlock = playerBlock;
        this.enemy = enemy;
        handler = new Handler();
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (event == GameStart.class) {
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
