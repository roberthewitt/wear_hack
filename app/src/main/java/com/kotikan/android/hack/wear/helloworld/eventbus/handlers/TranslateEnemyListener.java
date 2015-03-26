package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;

import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.CollisionDetected;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.SpawnEnemy;
import com.kotikan.android.hack.wear.helloworld.utils.Timings;

public class TranslateEnemyListener implements EventHandler {

    private final View enemy;
    private boolean isAnimating = false;
    private float startX;
    private ViewPropertyAnimator animate;

    public TranslateEnemyListener(View enemy) {
        this.enemy = enemy;
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (event == SpawnEnemy.class) {
            if (!isAnimating) {
                isAnimating = true;
                startX = enemy.getX();
                animate = enemy.animate();
                animate.translationXBy(-startX);
                animate.setInterpolator(new LinearInterpolator());
                animate.setDuration(Timings.ENEMY_SLIDE_DURATION);
                animate.withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        enemy.setVisibility(View.VISIBLE);
                    }
                });
                animate.withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        isAnimating = false;
                        enemy.setX(startX);
                        enemy.setVisibility(View.GONE);
                    }
                });
                animate.start();
            }
        } else if (event == CollisionDetected.class) {
            isAnimating = false;
            animate.cancel();
        }
    }
}
