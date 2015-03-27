package com.kotikan.android.hack.wear.helloworld.abstractions;

import android.view.View;
import android.view.ViewPropertyAnimator;

import com.kotikan.android.hack.wear.helloworld.abstractions.Block;

public class ViewBlock implements Block {

    private final View view;

    public ViewBlock(View view) {
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

    @Override
    public void setX(int x) {
        view.setX(x);
    }

    @Override
    public void setY(int y) {
        view.setY(y);
    }

    @Override
    public void setRotation(int rotation) {
        view.setRotation(rotation);
    }

    @Override
    public ViewPropertyAnimator animate() {
        return view.animate();
    }

    @Override
    public void setVisibility(int visibililty) {
        view.setVisibility(visibililty);
    }

    @Override
    public void setBackgroundColor(int colour) {
        view.setBackgroundColor(colour);
    }
}
