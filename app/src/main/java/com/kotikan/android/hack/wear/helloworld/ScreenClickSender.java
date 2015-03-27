package com.kotikan.android.hack.wear.helloworld;

import android.view.MotionEvent;
import android.view.View;

import com.kotikan.android.hack.wear.helloworld.eventbus.Messages;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnScreenClicked;

public class ScreenClickSender implements View.OnTouchListener {

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN) {
            Messages.bus().sendEvent(null, OnScreenClicked.class);
        }
        return true;
    }
}
