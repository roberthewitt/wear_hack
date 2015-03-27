package com.kotikan.android.hack.wear.helloworld.abstractions;

import android.widget.TextView;

public class ViewTextOutput implements TextOutput {

    private final TextView view;

    public ViewTextOutput(TextView view) {
        this.view = view;
    }

    @Override
    public void setText(String text) {
        view.setText(text);
    }
}
