package com.kotikan.android.hack.wear.helloworld;

import android.view.View;

import com.kotikan.android.hack.wear.helloworld.abstractions.TouchInput;
import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.Messages;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.GameOver;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.ResetGameState;

public class GameEndScreen implements EventHandler {

    private final TouchInput touchInput;

    public GameEndScreen(TouchInput touchInput) {
        this.touchInput = touchInput;
        touchInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameEndScreen.this.touchInput.setVisibility(View.GONE);
                Messages.bus().sendEvent(ResetGameState.class);
            }
        });
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (event == GameOver.class) {
            touchInput.setVisibility(View.VISIBLE);
        }
    }
}
