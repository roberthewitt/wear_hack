package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import android.view.View;
import android.view.ViewPropertyAnimator;

import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;

public class JumpPlayerListener implements EventHandler {

    final private int jumpDuration = 750;
    final private View playerBlock;
    boolean alreadyAnimating = false;

    public JumpPlayerListener(View playerBlock) {
        this.playerBlock = playerBlock;
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (!alreadyAnimating) {
            final float playerHeight = playerBlock.getHeight();
            final int jumpBy = (int) (playerHeight * 2.5f);

            alreadyAnimating = true;
            ViewPropertyAnimator animate = playerBlock.animate();
            animate.rotationBy(180f);
            animate.setDuration(jumpDuration);
            animate.withEndAction(new Runnable() {
                @Override
                public void run() {
                    alreadyAnimating = false;
                }
            });
            animate.start();


            ViewPropertyAnimator upAnimator = playerBlock.animate();
            upAnimator.translationYBy(-jumpBy);
            upAnimator.setDuration(jumpDuration / 2);
            upAnimator.withEndAction(new Runnable() {
                @Override
                public void run() {
                    playerBlock.animate().translationYBy(jumpBy).setDuration(jumpDuration/2).start();
                }
            });
            upAnimator.start();
        }
    }
}
