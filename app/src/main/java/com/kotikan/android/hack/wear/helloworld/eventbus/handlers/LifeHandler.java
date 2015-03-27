package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import com.kotikan.android.hack.wear.helloworld.abstractions.EnemyBlock;
import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.Messages;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.CollisionDetected;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.GameOver;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.GameStart;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.LifeChanged;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.NumberOfLivesResponse;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.RequestNumberOfLives;
import com.kotikan.android.hack.wear.helloworld.utils.GameConstants;

import java.util.HashSet;
import java.util.Set;


public class LifeHandler implements EventHandler {

    private int lives = GameConstants.STARTING_LIVES;
    final private Set<Integer> hitBy = new HashSet<>();

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (CollisionDetected.class == event) {
            CollisionDetected castEvent = (CollisionDetected) o;
            final EnemyBlock enemyBlock = castEvent.enemyBlock;

            if (hitByNewBlock(enemyBlock)) {
                rememberWeHaveBeenHitBy(enemyBlock);
                if (enemyBlock.givesLife()) {
                    lives++;
                } else {
                    lives--;
                }
                Messages.bus().sendEvent(new LifeChanged(lives), LifeChanged.class);
                if (lives == 0) {
                    Messages.bus().sendEvent(GameOver.class);
                }
            }
        } else if (GameStart.class == event) {
            lives = GameConstants.STARTING_LIVES;
            Messages.bus().sendEvent(new LifeChanged(lives), LifeChanged.class);
        } else if (RequestNumberOfLives.class == event) {
            Messages.bus().sendEvent(new NumberOfLivesResponse(lives), NumberOfLivesResponse.class);
        }
    }

    private void rememberWeHaveBeenHitBy(EnemyBlock e) {
        hitBy.add(e.getEnemyNumber());
    }

    private boolean hitByNewBlock(EnemyBlock enemyBlock) {
        return !hitBy.contains(enemyBlock.getEnemyNumber());
    }
}
