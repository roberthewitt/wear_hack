package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import android.os.Handler;
import android.widget.TextView;

import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.CollisionDetected;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnGameStart;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.ResetGameState;
import com.kotikan.android.hack.wear.helloworld.utils.JavaDateFactory;
import com.kotikan.android.hack.wear.helloworld.utils.NumberFormatter;

public class GameTimer implements EventHandler {

    static private final String DEFAULT_START_STATE = "00m:00s:000m";

    private final Handler handler = new Handler();
    private final TextView timer;
    private NumberFormatter formatter;
    private boolean isRunning = false;
    private boolean shouldUpdate = true;

    private final Runnable r = new Runnable() {
        @Override
        public void run() {
            if (shouldUpdate) {
                timer.setText(formatter.generate());
                updateTextView();
            }
        }
    };

    public GameTimer(TextView timer) {
        this.timer = timer;
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (event == OnGameStart.class) {
            formatter = new NumberFormatter(new JavaDateFactory());
            if (!isRunning) {
                shouldUpdate = true;
                isRunning = true;
                updateTextView();
            }
        } else if (event == CollisionDetected.class) {
            isRunning = false;
            shouldUpdate = false;
        } else if (event == ResetGameState.class) {
            timer.setText(DEFAULT_START_STATE);
        }
    }

    private void updateTextView() {
        handler.postDelayed(r, 1);
    }

}
