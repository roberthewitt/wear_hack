package com.kotikan.android.hack.wear.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.view.WatchViewStub;
import android.view.View;

import com.kotikan.android.hack.wear.helloworld.eventbus.Bus;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.EnterEnemy;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnScreenClicked;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.JumpPlayerListener;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.TranslateEnemyListener;

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

                Bus.bus().register(new JumpPlayerListener(playerBlock), OnScreenClicked.class);
                Bus.bus().register(new TranslateEnemyListener(enemy), EnterEnemy.class);
            }
        });

        Handler handler = new Handler();
        new EnemyGenerator(handler).generate();
    }

}
