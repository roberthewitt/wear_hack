package com.kotikan.android.hack.wear.helloworld;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;

import com.kotikan.android.hack.wear.helloworld.eventbus.Bus;
import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnScreenClicked;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.RotatePlayerListener;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.TranslateEnemyListener;
import com.kotikan.android.hack.wear.helloworld.logic.GameLoop;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnClickListener(new ScreenClickSender());
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                final View playerBlock = stub.findViewById(R.id.player_block);
                final View enemy = stub.findViewById(R.id.enemy_block);

                Bus.bus().register(new RotatePlayerListener(playerBlock), OnScreenClicked.class);
                Bus.bus().register(new TranslateEnemyListener(enemy), OnScreenClicked.class);
            }
        });
    }

}
