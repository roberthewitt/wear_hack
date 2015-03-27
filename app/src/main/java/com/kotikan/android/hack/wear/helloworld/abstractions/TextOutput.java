package com.kotikan.android.hack.wear.helloworld.abstractions;

public interface TextOutput extends Animator, VisibilityModifier {
    void setText(String text);

    void setTextSize(float size);
}
