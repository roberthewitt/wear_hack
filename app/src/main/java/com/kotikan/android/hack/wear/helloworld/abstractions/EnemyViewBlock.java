package com.kotikan.android.hack.wear.helloworld.abstractions;

import android.view.View;

public class EnemyViewBlock extends ViewBlock implements EnemyBlock {

    private int enemyNumber;

    public EnemyViewBlock(View view) {
        super(view);
    }

    @Override
    public boolean givesLife() {
        return false;
    }

    @Override
    public int getEnemyNumber() {
        return enemyNumber;
    }

    @Override
    public void setEnemyNumber(Integer number) {
        enemyNumber = number;
    }
}
