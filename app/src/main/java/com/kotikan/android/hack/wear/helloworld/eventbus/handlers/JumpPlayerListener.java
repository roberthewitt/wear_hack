package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.CollisionDetected;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnGameStart;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnScreenClicked;
import com.kotikan.android.hack.wear.helloworld.utils.BlockState;
import com.kotikan.android.hack.wear.helloworld.utils.Timings;

import java.util.HashSet;
import java.util.Set;

public class JumpPlayerListener implements EventHandler {

    final private int jumpDuration = Timings.PLAYER_JUMP_DURATION;
    final private View playerBlock;
    boolean alreadyAnimating = false;
    boolean canJump = false;
    private final Set<ViewPropertyAnimator> animators = new HashSet<>();
    private BlockState initialState;

    public JumpPlayerListener(View playerBlock) {
        this.playerBlock = playerBlock;
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (event == OnGameStart.class) {
            canJump = true;
            if (initialState != null) {
                initialState.setOnBlock(playerBlock, View.VISIBLE);
            }
        } else if (event == OnScreenClicked.class) {
            if (!alreadyAnimating && canJump) {
                initialState = new BlockState(playerBlock);
                animators.clear();
                alreadyAnimating = true;

                final float playerHeight = playerBlock.getHeight();
                final int jumpBy = (int) (playerHeight * 2.5f);
                animators.add(rotateAnimation());
                animators.add(getJumpAnimator(jumpBy));
            }
        } else if (event == CollisionDetected.class) {
            canJump = false;
            alreadyAnimating = false;
            for (ViewPropertyAnimator v : animators) v.cancel();
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
        rotateAnimation.rotationBy(180f);
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
