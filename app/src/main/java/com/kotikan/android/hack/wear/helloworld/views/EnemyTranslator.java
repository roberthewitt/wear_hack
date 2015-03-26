package com.kotikan.android.hack.wear.helloworld.views;

import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;

public class EnemyTranslator {

    private final View enemy;
    private boolean isAnimating = false;
    private float startX;

    public EnemyTranslator(View enemy) {
        this.enemy = enemy;
    }

    public void animate() {
        if (!isAnimating) {
            isAnimating = true;
            startX = enemy.getX();
            ViewPropertyAnimator animate = enemy.animate();
            animate.translationXBy(-startX);
            animate.setInterpolator(new LinearInterpolator());
            animate.setDuration(800l);
            animate.withEndAction(new Runnable() {
                @Override
                public void run() {
                    isAnimating = false;
                    enemy.setX(startX);
                }
            });
            animate.start();
        }
    }
}
