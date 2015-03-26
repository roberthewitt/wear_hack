package com.kotikan.android.hack.wear.helloworld;

import android.view.View;
import android.widget.TextView;

import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.Messages;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.CollisionDetected;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnGameStart;

public class GameEndScreen implements EventHandler {

    private final TextView clickToRetry;

    public GameEndScreen(TextView clickToRetry) {
        this.clickToRetry = clickToRetry;
        clickToRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameEndScreen.this.clickToRetry.setVisibility(View.GONE);
                Messages.bus().sendEvent(OnGameStart.class);
            }
        });
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (event == CollisionDetected.class) {
            clickToRetry.setVisibility(View.VISIBLE);
        }
    }
}
