package com.kotikan.android.hack.wear.helloworld.abstractions;

import android.view.ViewPropertyAnimator;

public interface Block extends VisibilityModifier {
    int x();

    int width();

    int y();

    int height();

    void setX(int x);

    void setY(int y);

    void setRotation(int rotation);

    ViewPropertyAnimator animate();

    void setBackgroundColor(int colour);
}
