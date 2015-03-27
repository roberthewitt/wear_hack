package com.kotikan.android.hack.wear.helloworld.abstractions;

import android.view.View;

public interface TouchInput extends VisibilityModifier {
    void setOnClickListener(View.OnClickListener listener);
}
