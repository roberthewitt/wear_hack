package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;

import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;

public class TranslateEnemyListener implements EventHandler {

    private final View enemy;
    private boolean isAnimating = false;
    private float startX;

    public TranslateEnemyListener(View enemy) {
        this.enemy = enemy;
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (!isAnimating) {
            isAnimating = true;
            startX = enemy.getX();
            ViewPropertyAnimator animate = enemy.animate();
            animate.translationXBy(-startX);
            animate.setInterpolator(new LinearInterpolator());
            animate.setDuration(1000l);
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

    }
}
