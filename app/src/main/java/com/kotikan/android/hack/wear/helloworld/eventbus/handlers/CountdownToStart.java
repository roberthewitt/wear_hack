package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import com.kotikan.android.hack.wear.helloworld.abstractions.TextOutput;
import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.ResetGameState;

public class CountdownToStart implements EventHandler {

    private final TextOutput textOutput;

    public CountdownToStart(TextOutput textOutput) {
        this.textOutput = textOutput;
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (event == ResetGameState.class) {

        }
    }
}
