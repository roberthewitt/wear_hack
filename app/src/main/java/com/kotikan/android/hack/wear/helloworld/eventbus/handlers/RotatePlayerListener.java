package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import android.view.View;
import android.view.ViewPropertyAnimator;

import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;

public class RotatePlayerListener implements EventHandler {

    private final View playerBlock;
    boolean alreadyAnimating = false;

    public RotatePlayerListener(View playerBlock) {
        this.playerBlock = playerBlock;
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (!alreadyAnimating) {
            alreadyAnimating = true;
            ViewPropertyAnimator animate = playerBlock.animate();
            animate.rotationBy(180f);
            animate.setDuration(500);
            animate.withEndAction(new Runnable() {
                @Override
                public void run() {
                    alreadyAnimating = false;
                }
            });
            animate.start();


            ViewPropertyAnimator upAnimator = playerBlock.animate();
            upAnimator.translationYBy(-100);
            upAnimator.setDuration(250);
            upAnimator.withEndAction(new Runnable() {
                @Override
                public void run() {
                    playerBlock.animate().translationYBy(100).setDuration(250).start();
                }
            });
            upAnimator.start();
        }
    }
}
