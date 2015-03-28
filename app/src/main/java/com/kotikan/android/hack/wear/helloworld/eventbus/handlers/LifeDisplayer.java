package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.kotikan.android.hack.wear.helloworld.R;
import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
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
            for (int i = maxChildIndex; i >= 0; i--) {
                final ImageView childAt = (ImageView) lifeContainer.getChildAt(i);
                int heartImg;
                if (lives > 0) {
                    lives--;
                    heartImg = R.mipmap.ic_heart_full;
                } else {
                    heartImg = R.mipmap.ic_heart_empty;
                }
                childAt.setImageResource(heartImg);
            }
        }
    }
}
