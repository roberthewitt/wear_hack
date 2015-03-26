package com.kotikan.android.hack.wear.helloworld;

import android.app.Activity;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kotikan.android.hack.wear.helloworld.eventbus.Bus;
import com.kotikan.android.hack.wear.helloworld.eventbus.EventBus;
import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.CollisionDetected;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.EnterEnemy;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.Event;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnGameStart;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnScreenClicked;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.GameTimer;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.JumpPlayerListener;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.TranslateEnemyListener;
import com.kotikan.android.hack.wear.helloworld.utils.CollisionDetector;

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
                final TextView timer = (TextView) stub.findViewById(R.id.game_timer);


                final EventBus eventBus = Bus.bus();
                eventBus.register(new GameTimer(timer), OnGameStart.class);
                eventBus.register(new JumpPlayerListener(playerBlock), OnScreenClicked.class);
                eventBus.register(new TranslateEnemyListener(enemy), EnterEnemy.class);
                eventBus.register(new EnemyGenerator(new Handler()), OnGameStart.class);
                eventBus.register(new CollisionDetector(playerBlock, enemy), OnGameStart.class);
                eventBus.sendEvent(OnGameStart.class);
            }
        });


        Bus.bus().register(new EventHandler() {
            @Override
            public void handleEvent(Object o, Class<? extends Event> event) {
                Toast.makeText(MainActivity.this, "hit", Toast.LENGTH_SHORT).show();
            }
        }, CollisionDetected.class);
    }
}
