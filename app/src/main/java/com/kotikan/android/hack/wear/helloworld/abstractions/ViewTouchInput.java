package com.kotikan.android.hack.wear.helloworld.abstractions;

import android.view.View;

public class ViewTouchInput implements TouchInput {

    private final View view;

    public ViewTouchInput(View view) {
        this.view = view;
    }

    @Override
    public void setOnClickListener(View.OnClickListener listener) {
        view.setOnClickListener(listener);
    }

    @Override
    public void setVisibility(int visibililty) {
        view.setVisibility(visibililty);
    }
}
