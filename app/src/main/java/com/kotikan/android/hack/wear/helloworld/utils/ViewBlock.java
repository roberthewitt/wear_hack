package com.kotikan.android.hack.wear.helloworld.utils;

import android.view.View;

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
