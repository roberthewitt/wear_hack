package com.kotikan.android.hack.wear.helloworld.eventbus;

public class Bus {

    private static final EventBus bus = new BasicRegistrationBus();

    public static EventBus bus() {
        return bus;
    }
}
