package com.kotikan.android.hack.wear.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.TextView;

import com.kotikan.android.hack.wear.helloworld.eventbus.Messages;
import com.kotikan.android.hack.wear.helloworld.eventbus.EventBus;
import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.CollisionDetected;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.SpawnEnemy;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnGameStart;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnScreenClicked;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.GameTimer;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.JumpPlayerListener;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.TranslateEnemyListener;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.VibrateOnCollision;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.CollisionDetector;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Handler handler = new Handler();
        final EventBus eventBus = Messages.bus();
        eventBus.register(new VibrateOnCollision(this), CollisionDetected.class);
        final EventHandler enemyGenerator = new EnemyGenerator(handler);
        eventBus.register(enemyGenerator, OnGameStart.class);
        eventBus.register(enemyGenerator, CollisionDetected.class);


        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnClickListener(new ScreenClickSender());
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                final View playerBlock = stub.findViewById(R.id.player_block);
                final View enemy = stub.findViewById(R.id.enemy_block);
                final TextView timer = (TextView) stub.findViewById(R.id.game_timer);
                final TextView clickToRetry = (TextView) stub.findViewById(R.id.game_over_click_to_retry);

                final EventHandler gameTimer = new GameTimer(timer);
                eventBus.register(gameTimer, OnGameStart.class);
                eventBus.register(gameTimer, CollisionDetected.class);
                eventBus.register(new GameEndScreen(clickToRetry), CollisionDetected.class);

                EventHandler playerListener = new JumpPlayerListener(playerBlock);
                eventBus.register(playerListener, OnScreenClicked.class);
                eventBus.register(playerListener, CollisionDetected.class);
                eventBus.register(playerListener, OnGameStart.class);

                final EventHandler enemyListener = new TranslateEnemyListener(enemy);
                eventBus.register(enemyListener, SpawnEnemy.class);
                eventBus.register(enemyListener, CollisionDetected.class);
                eventBus.register(enemyListener, OnGameStart.class);

                eventBus.register(new CollisionDetector(playerBlock, enemy), OnGameStart.class);
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                eventBus.sendEvent(OnGameStart.class);
            }
        }, 1000);
    }
}
