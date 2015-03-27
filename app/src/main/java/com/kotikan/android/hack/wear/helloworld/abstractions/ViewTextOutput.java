package com.kotikan.android.hack.wear.helloworld.abstractions;

import android.util.TypedValue;
import android.view.ViewPropertyAnimator;
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

    @Override
    public ViewPropertyAnimator animate() {
        return view.animate();
    }

    @Override
    /**
     * size is expected to be given in SP
     */
    public void setTextSize(float size) {
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    @Override
    public void setVisibility(int visibililty) {
        view.setVisibility(visibililty);
    }
}
