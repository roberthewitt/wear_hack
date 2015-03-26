package com.kotikan.android.hack.wear.helloworld.eventbus.handlers;

import android.os.Handler;
import android.widget.TextView;

import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.CollisionDetected;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnGameStart;
import com.kotikan.android.hack.wear.helloworld.utils.JavaDateFactory;
import com.kotikan.android.hack.wear.helloworld.utils.NumberFormatter;

public class GameTimer implements EventHandler {

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
        }
    }

    private void updateTextView() {
        handler.postDelayed(r, 1);
    }

}
