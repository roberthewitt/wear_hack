package com.kotikan.android.hack.wear.helloworld.utils;

import com.kotikan.android.hack.wear.helloworld.abstractions.Block;

public class BlockState {

    public final int x, y, rotation;

    public BlockState(Block block) {
        x = block.x();
        y = block.y();
        rotation = 0;
    }

    public void setOnBlock(Block block, int visibility) {
        block.setX(x);
        block.setY(y);
        block.setRotation(rotation);
        block.setVisibility(visibility);
    }
}
