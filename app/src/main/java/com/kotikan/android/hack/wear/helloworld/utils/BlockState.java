package com.kotikan.android.hack.wear.helloworld.utils;

import android.view.View;

public class BlockState {

    public final int x, y, rotation;

    public BlockState(View block) {
        x = (int) block.getX();
        y = (int) block.getY();
        rotation = 0;
    }

    public void setOnBlock(View block, int visibility) {
        block.setX(x);
        block.setY(y);
        block.setRotation(rotation);
        block.setVisibility(visibility);
    }
}
