package com.kotikan.android.hack.wear.helloworld.eventbus;

import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;

public interface EventHandler {

    void handleEvent(Object o, Class<? extends Event> event);
}
