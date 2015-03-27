package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;

import com.kotikan.android.hack.wear.helloworld.abstractions.ViewBlock;
import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.CollisionDetected;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.ResetGameState;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.SpawnEnemy;
import com.kotikan.android.hack.wear.helloworld.utils.BlockState;
import com.kotikan.android.hack.wear.helloworld.utils.GameConstants;
import com.kotikan.android.hack.wear.helloworld.utils.NumberGenerator;

import java.util.HashSet;
import java.util.Set;

public class EnemyAnimator implements EventHandler {

    private final int kRed = Color.argb(255, 220, 4, 81);
    private final int kPurple = Color.argb(255, 128, 55, 155);
    private final int kBlue = Color.argb(255, 0, 169, 224);
    private final int kGreen = Color.argb(255, 105, 190, 40);
    private final int kYellow = Color.argb(255, 234, 171, 0);
    private final int kOrange = Color.argb(255, 224, 82, 6);
    private final int[] kotikanColours = new int[]{kRed, kPurple, kBlue, kGreen, kYellow, kOrange};

    final private Set<ViewPropertyAnimator> animators = new HashSet<>();
    private final NumberGenerator numberGenerator = new NumberGenerator();
    private final ViewBlock enemy;
    private boolean isAnimating = false;
    private int startX;
    private BlockState initialState;

    public EnemyAnimator(ViewBlock enemy) {
        this.enemy = enemy;
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (event == SpawnEnemy.class) {
            if (!isAnimating) {
                initialState = new BlockState(enemy);
                isAnimating = true;
                startX = enemy.x();
                ViewPropertyAnimator animate = enemy.animate();
                animate.translationXBy(-startX);
                animate.setInterpolator(new LinearInterpolator());
                animate.setDuration(GameConstants.ENEMY_SLIDE_DURATION);
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
                animators.add(animate);

                int startIndex = numberGenerator.generateBetween(0, 5);
                int startColour = kotikanColours[startIndex];
                int endColour = kotikanColours[numberGenerator.generateBetween(0, 5, startIndex)];

                ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), startColour, endColour);
                colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        enemy.setBackgroundColor((Integer) animator.getAnimatedValue());
                    }
                });
                colorAnimation.setDuration(GameConstants.ENEMY_SLIDE_DURATION);
                colorAnimation.start();
            }
        } else if (event == CollisionDetected.class) {
            isAnimating = false;
            for (ViewPropertyAnimator p : animators) p.cancel();
        } else if (event == ResetGameState.class) {
            if (initialState != null) {
                initialState.setOnBlock(enemy, View.INVISIBLE);
            }
        }
    }
}
