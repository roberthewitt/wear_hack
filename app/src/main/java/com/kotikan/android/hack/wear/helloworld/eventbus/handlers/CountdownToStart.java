package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.kotikan.android.hack.wear.helloworld.abstractions.TextOutput;
import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.Messages;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnGameStart;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.ResetGameState;
import com.kotikan.android.hack.wear.helloworld.utils.GameConstants;

public class CountdownToStart implements EventHandler {

    private final TextOutput textOutput;

    public CountdownToStart(TextOutput textOutput) {
        this.textOutput = textOutput;
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (event == ResetGameState.class) {
            ObjectAnimator one = newAnimator("1", null);
            one.addListener(onEndFireGameStart());

            ObjectAnimator two = newAnimator("2", one);
            ObjectAnimator three = newAnimator("3", two);
            three.start();
        }
    }

    private ObjectAnimator newAnimator(final String text, final ObjectAnimator nextAnimator) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(textOutput, "textSize", GameConstants.COUNTDOWN_MAX_TEXT_SIZE, 5);
        animator.setDuration(850);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                textOutput.setText(text);
                textOutput.setVisibility(View.VISIBLE);
                textOutput.setTextSize(50);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (nextAnimator != null) {
                    nextAnimator.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return animator;
    }

    private Animator.AnimatorListener onEndFireGameStart() {
        return new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Messages.bus().sendEvent(OnGameStart.class);
                textOutput.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };
    }
}
