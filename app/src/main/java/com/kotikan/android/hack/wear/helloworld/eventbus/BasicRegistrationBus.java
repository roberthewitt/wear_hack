package com.kotikan.android.hack.wear.helloworld.eventbus;

import com.kotikan.android.hack.wear.helloworld.eventbus.events.EnterEnemy;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class BasicRegistrationBus implements EventBus {

    private final Map<Class<? extends Event>, Set<EventHandler>> handlers = new HashMap<>();

    @Override
    public void sendEvent(Object o, Class<? extends Event> event) {
        final Set<EventHandler> set = handlers.get(event);
        if (set != null) {
            for (EventHandler h : set) {
                h.handleEvent(o, event);
            }
        }
    }

    @Override
    public void sendEvent(Class<EnterEnemy> enterEnemyClass) {
        sendEvent(null, enterEnemyClass);
    }

    @Override
    public void register(EventHandler eventHandler, Class<? extends Event> event) {

        Set<EventHandler> set = handlers.get(event);
        if (set == null) {
            set = new HashSet<>();
            handlers.put(event, set);
        }

        set.add(eventHandler);
    }
}
