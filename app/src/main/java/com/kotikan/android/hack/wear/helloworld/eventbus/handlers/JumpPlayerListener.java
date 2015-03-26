package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;

public class JumpPlayerListener implements EventHandler {

    final private int jumpDuration = 600;
    final private View playerBlock;
    boolean alreadyAnimating = false;

    public JumpPlayerListener(View playerBlock) {
        this.playerBlock = playerBlock;
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (!alreadyAnimating) {
            alreadyAnimating = true;

            final float playerHeight = playerBlock.getHeight();
            final int jumpBy = (int) (playerHeight * 2.5f);

            ViewPropertyAnimator rotateAnimation = playerBlock.animate();
            rotateAnimation.rotationBy(180f);
            rotateAnimation.setDuration(jumpDuration);
            rotateAnimation.withEndAction(new Runnable() {
                @Override
                public void run() {
                    alreadyAnimating = false;
                }
            });
            rotateAnimation.start();


            ViewPropertyAnimator upAnimator = playerBlock.animate();
            upAnimator.translationYBy(-jumpBy);
            upAnimator.setDuration(jumpDuration * 2 / 3);
            upAnimator.setInterpolator(new DecelerateInterpolator(2f));
            upAnimator.withEndAction(new Runnable() {
                @Override
                public void run() {
                    ViewPropertyAnimator downAnimator = playerBlock.animate();
                    downAnimator
                            .translationYBy(jumpBy)
                            .setDuration(jumpDuration  / 3)
                            .setInterpolator(new AccelerateInterpolator(2f))
                            .start();
                }
            });
            upAnimator.start();
        }
    }
}
