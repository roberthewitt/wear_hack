package com.kotikan.android.hack.wear.helloworld.utils;

import android.view.View;

class ViewBlock implements Block {

    private final View view;

    @Override
    public int x() {
        return  (int) view.getX();
    }

    @Override
    public int width() {
        return view.getWidth();
    }

    ViewBlock(View view) {
        this.view = view;
    }
}
