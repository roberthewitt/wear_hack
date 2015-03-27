package com.kotikan.android.hack.wear.helloworld.utils;

import android.os.Handler;
import android.widget.HorizontalScrollView;

import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.GameOver;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.GameStart;

public class InfiniteScroller implements EventHandler {

    private final Handler handler;
    private final HorizontalScrollView scrollView;
    boolean stop = false;

    public InfiniteScroller(final HorizontalScrollView scrollView) {
        this.scrollView = scrollView;
        this.handler = new Handler();
        scrollMeABit(scrollView);
    }

    private void scrollMeABit(final HorizontalScrollView viewById) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!stop) {
                    int width = scrollView.getChildAt(0).getWidth();

                    if (scrollView.getScrollX() >= width - 500) {
                        scrollView.setScrollX(0);
                    } else {
                        viewById.scrollBy(5, 0);
                    }
                    scrollMeABit(viewById);
                }
            }
        }, 5);
    }

    @Override
    public void handleEvent(Object o, Class<? extends Event> event) {
        if (event == GameOver.class) {
            stop = true;
        } else if (event == GameStart.class) {
            stop = false;
            scrollMeABit(scrollView);
        }
    }
}
