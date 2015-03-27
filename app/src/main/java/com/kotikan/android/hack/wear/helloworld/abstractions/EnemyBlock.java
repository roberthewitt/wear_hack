package com.kotikan.android.hack.wear.helloworld.abstractions;

public interface EnemyBlock extends Block {
    boolean givesLife();

    int getEnemyNumber();

    void setEnemyNumber(Integer number);
}
