package com.kotikan.android.hack.wear.helloworld.abstractions;

public interface Block extends VisibilityModifier, Animator {
    int x();

    int width();

    int y();

    int height();

    void setX(int x);

    void setY(int y);

    void setRotation(int rotation);

    void setBackgroundColor(int colour);
}
