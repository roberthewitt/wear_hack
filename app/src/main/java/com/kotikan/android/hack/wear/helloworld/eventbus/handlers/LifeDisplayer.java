package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import android.view.View;
import android.view.ViewGroup;

import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.GameStart;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.LifeChanged;

public class LifeDisplayer implements EventHandler {

    private final ViewGroup lifeContainer;

    public LifeDisplayer(ViewGroup lifeContainer) {
        this.lifeContainer = lifeContainer;
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (LifeChanged.class == event) {
            LifeChanged changed = (LifeChanged) o;
            int lives = changed.lives;
            int maxChildIndex = lifeContainer.getChildCount() - 1;
            for (int i = 0; i <= maxChildIndex; i++) {
                final View childAt = lifeContainer.getChildAt(i);
                int visibility;
                if (lives > 0) {
                    lives--;
                    visibility = View.VISIBLE;
                } else {
                    visibility = View.GONE;
                }
                childAt.setVisibility(visibility);
            }
        } else if (GameStart.class == event) {
            int maxChildIndex = lifeContainer.getChildCount() - 1;
            for (int i = 0; i < maxChildIndex; i++) {
                final View childAt = lifeContainer.getChildAt(i);
                childAt.setVisibility(View.VISIBLE);
            }
        }
    }
}
