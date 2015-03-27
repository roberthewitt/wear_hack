package com.kotikan.android.hack.wear.helloworld.eventbus.events;

public class LifeChanged implements Event {

    public final int lives;

    public LifeChanged(int lives) {
        this.lives = lives;
    }
}
