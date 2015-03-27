package com.kotikan.android.hack.wear.helloworld;

import android.os.Handler;

import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.Messages;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.GameOver;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.GameStart;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.SpawnEnemy;
import com.kotikan.android.hack.wear.helloworld.utils.GameConstants;

public class Spawner implements EventHandler {
    private final Handler handler;
    private boolean isAnimating = false;
    private int enemyCounter = 0;
    private int nextExpectedEnemy = 1;

    public Spawner(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (event == GameStart.class) {
            if (!isAnimating) {
                isAnimating = true;
                generateWithDelay(GameConstants.ENEMY_SPAWN_MINIMUM_DELAY);
            }
        } else if (event == GameOver.class) {
            isAnimating = false;
        }
    }

    private void generateWithDelay(int i) {
        double spawnAt = Math.random() * GameConstants.ENEMY_SPAWN_RANDOMISER;
        enemyCounter++;
        handler.postDelayed(spawnEnemy(enemyCounter), i + (long) spawnAt);
    }

    private Runnable spawnEnemy(final int spawnEnemyNumber) {
        return new Runnable() {
            @Override
            public void run() {
                if (spawnEnemyNumber == nextExpectedEnemy  && enemyCounter == spawnEnemyNumber) {
                    nextExpectedEnemy++;
                    Messages.bus().sendEvent(SpawnEnemy.class);
                    generateWithDelay(GameConstants.ENEMY_SPAWN_MINIMUM_DELAY);
                }
            }
        };
    }

}
