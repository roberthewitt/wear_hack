package com.kotikan.android.hack.wear.helloworld;

import android.view.View;

import com.kotikan.android.hack.wear.helloworld.eventbus.Messages;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnScreenClicked;

public class ScreenClickSender implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        Messages.bus().sendEvent(null, OnScreenClicked.class);
    }

}
