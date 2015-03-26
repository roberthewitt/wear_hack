package com.kotikan.android.hack.wear.helloworld;

import android.view.View;

import com.kotikan.android.hack.wear.helloworld.eventbus.Bus;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnScreenClicked;

public class ScreenClickSender implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        Bus.bus().sendEvent(null, OnScreenClicked.class);
    }

}
