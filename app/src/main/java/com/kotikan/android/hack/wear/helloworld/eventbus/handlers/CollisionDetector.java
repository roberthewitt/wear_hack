package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import android.os.Handler;

import com.kotikan.android.hack.wear.helloworld.abstractions.EnemyBlock;
import com.kotikan.android.hack.wear.helloworld.abstractions.ViewBlock;
import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.Messages;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.CollisionDetected;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.GameOver;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.GameStart;
import com.kotikan.android.hack.wear.helloworld.abstractions.Block;
import com.kotikan.android.hack.wear.helloworld.utils.CollisionMonitor;

public class CollisionDetector implements EventHandler {

    private final Handler handler;
    private final Block playerBlock;
    private final EnemyBlock enemy;
    private boolean isRunning = false;
    private boolean keepDetecting = true;

    final private Runnable collisionRunnable = new Runnable() {
        @Override
        public void run() {
            if (CollisionMonitor.hasCollided(playerBlock, enemy)) {
                Messages.bus().sendEvent(new CollisionDetected(enemy), CollisionDetected.class);
            }
            if (keepDetecting) {
                runDetector();
            }
        }
    };

    public CollisionDetector(Block playerBlock, EnemyBlock enemy) {
        this.playerBlock = playerBlock;
        this.enemy = enemy;
        handler = new Handler();
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (event == GameStart.class) {
            if (!isRunning) {
                keepDetecting = true;
                isRunning = true;
                runDetector();
            }
        } else if (event == GameOver.class) {
            keepDetecting = false;
        }
    }

    private void runDetector() {
        handler.postDelayed(collisionRunnable, 1);
    }
}
