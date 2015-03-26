package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import android.view.View;

import com.kotikan.android.hack.wear.helloworld.utils.Block;

class ViewBlock implements Block {

    private final View view;

    ViewBlock(View view) {
        this.view = view;
    }

    @Override
    public int x() {
        return (int) view.getX();
    }

    @Override
    public int width() {
        return view.getWidth();
    }

    @Override
    public int y() {
        return (int) view.getY();
    }

    @Override
    public int height() {
        return view.getHeight();
    }
}
