package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import android.content.Context;
import android.os.Vibrator;

import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.CollisionDetected;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;

public class VibrateOnCollision implements EventHandler {

    final private Context context;

    public VibrateOnCollision(Context context) {
        this.context = context;
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (event == CollisionDetected.class) {
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            if (v != null) {
                v.vibrate(150);
            }
        }
    }
}
