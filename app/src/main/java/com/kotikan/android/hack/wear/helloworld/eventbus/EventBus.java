package com.kotikan.android.hack.wear.helloworld.eventbus;

import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;

public interface EventBus {

    void sendEvent(Object o, Class<? extends Event> event);

    void register(EventHandler eventHandler, Class<? extends Event> event);

}
