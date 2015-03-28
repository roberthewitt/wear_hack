package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.kotikan.android.hack.wear.helloworld.abstractions.ViewBlock;
import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.GameOver;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.GameStart;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnScreenClicked;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.ResetGameState;
import com.kotikan.android.hack.wear.helloworld.utils.BlockState;
import com.kotikan.android.hack.wear.helloworld.utils.GameConstants;

import java.util.HashSet;
import java.util.Set;

public class PlayerAnimator implements EventHandler {

    final private int jumpDuration = GameConstants.PLAYER_JUMP_DURATION;
    private final Set<ViewPropertyAnimator> animators = new HashSet<>();
    private final ViewBlock playerBlock;
    private final BlockState initialState;

    boolean alreadyAnimating = false;
    boolean canJump = false;

    public PlayerAnimator(ViewBlock playerBlock) {
        this.playerBlock = playerBlock;
        this.initialState = new BlockState(playerBlock);
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (event == ResetGameState.class) {
            initialState.setOnBlock(playerBlock, View.VISIBLE);
        } else if (event == OnScreenClicked.class) {
            if (!alreadyAnimating && canJump) {
                animators.clear();
                alreadyAnimating = true;

                final float playerHeight = playerBlock.height();
                final int jumpBy = (int) (playerHeight * 2.5f);
                animators.add(rotateAnimation());
                animators.add(getJumpAnimator(jumpBy));
            }
        } else if (event == GameOver.class) {
            canJump = false;
            alreadyAnimating = false;
            for (ViewPropertyAnimator v : animators) v.cancel();
        } else if (event == GameStart.class) {
            canJump = true;
        }
    }

    private ViewPropertyAnimator getJumpAnimator(final int jumpBy) {
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
                        .setDuration(jumpDuration / 3)
                        .setInterpolator(new AccelerateInterpolator(2f))
                        .start();
            }
        });
        upAnimator.start();
        return upAnimator;
    }

    private ViewPropertyAnimator rotateAnimation() {
        ViewPropertyAnimator rotateAnimation = playerBlock.animate();
        rotateAnimation.rotationBy(360f);
        rotateAnimation.setDuration(jumpDuration);
        rotateAnimation.withEndAction(new Runnable() {
            @Override
            public void run() {
                alreadyAnimating = false;
            }
        });
        rotateAnimation.start();
        return rotateAnimation;
    }
}
