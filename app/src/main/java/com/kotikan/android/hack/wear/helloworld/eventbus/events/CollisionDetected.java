package com.kotikan.android.hack.wear.helloworld.eventbus.events;

import com.kotikan.android.hack.wear.helloworld.abstractions.EnemyBlock;

public class CollisionDetected implements Event {

    public final EnemyBlock enemyBlock;

    public CollisionDetected(EnemyBlock enemyBlock) {
        this.enemyBlock = enemyBlock;
    }
}
