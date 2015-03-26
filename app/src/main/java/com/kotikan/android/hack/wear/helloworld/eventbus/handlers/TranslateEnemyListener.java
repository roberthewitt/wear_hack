package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import android.view.View;

import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.logic.GameLoop;

public class TranslateEnemyListener implements EventHandler {

    private final View enemy;

    public TranslateEnemyListener(View enemy) {
        this.enemy = enemy;
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        GameLoop.createWith(enemy);
    }
}
