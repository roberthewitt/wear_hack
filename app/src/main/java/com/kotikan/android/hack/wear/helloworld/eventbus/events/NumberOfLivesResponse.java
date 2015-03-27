package com.kotikan.android.hack.wear.helloworld.eventbus.events;

public class NumberOfLivesResponse implements Event {
    public NumberOfLivesResponse(int lifeCount) {
        this.lifeCount = lifeCount;
    }

    final public int lifeCount;

}
