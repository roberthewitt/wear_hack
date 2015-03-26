package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import android.content.Context;
import android.widget.Toast;

import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;

public class RotatePlayerListener implements EventHandler {

    private final Context context;

    public RotatePlayerListener(Context context) {
        this.context = context;
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        Toast.makeText(context, "screen clicked", Toast.LENGTH_SHORT).show();
    }
}
