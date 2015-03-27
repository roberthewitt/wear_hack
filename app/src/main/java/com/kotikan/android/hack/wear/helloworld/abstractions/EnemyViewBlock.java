package com.kotikan.android.hack.wear.helloworld.abstractions;

import android.widget.ImageView;

import com.kotikan.android.hack.wear.helloworld.R;

public class EnemyViewBlock extends ViewBlock implements EnemyBlock {

    private final ImageView view;
    private int enemyNumber;
    private boolean lifeUp = false;

    public EnemyViewBlock(ImageView view) {
        super(view);
        this.view = view;
    }

    @Override
    public boolean givesLife() {
        return lifeUp;
    }

    @Override
    public int getEnemyNumber() {
        return enemyNumber;
    }

    @Override
    public void setEnemyNumber(Integer number) {
        enemyNumber = number;
    }

    @Override
    public void grantsLifeUp(boolean lifeUp) {
        int resId = lifeUp ? R.mipmap.ic_launcher : 0;
        view.setImageResource(resId);
        this.lifeUp = lifeUp;
    }
}
