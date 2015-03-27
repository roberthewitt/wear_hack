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
import com.kotikan.android.hack.wear.helloworld.eventbus.events.ResetGameState;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.SpawnEnemy;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnGameStart;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.OnScreenClicked;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.EnemyAnimator;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.GameTimer;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.PlayerAnimator;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.VibrateOnCollision;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.CollisionDetector;

public class MainActivity extends Activity {

    private EventHandler gameTimer;
    private EventHandler endScreen;
    private EventHandler playerListener;
    private EventHandler enemyListener;
    private EventHandler collisionDetector;
    private EventHandler enemyGenerator;
    private EventHandler vibrateOnCollision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Handler handler = new Handler();
        final EventBus eventBus = Messages.bus();
        vibrateOnCollision = new VibrateOnCollision(this);
        eventBus.register(vibrateOnCollision, CollisionDetected.class);
        enemyGenerator = new Spawner(handler);
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

                gameTimer = new GameTimer(timer);
                eventBus.register(gameTimer, OnGameStart.class);
                eventBus.register(gameTimer, CollisionDetected.class);
                eventBus.register(gameTimer, ResetGameState.class);

                endScreen = new GameEndScreen(clickToRetry);
                eventBus.register(endScreen, CollisionDetected.class);

                playerListener = new PlayerAnimator(playerBlock);
                eventBus.register(playerListener, OnScreenClicked.class);
                eventBus.register(playerListener, CollisionDetected.class);
                eventBus.register(playerListener, ResetGameState.class);

                enemyListener = new EnemyAnimator(enemy);
                eventBus.register(enemyListener, SpawnEnemy.class);
                eventBus.register(enemyListener, CollisionDetected.class);
                eventBus.register(enemyListener, ResetGameState.class);

                collisionDetector = new CollisionDetector(playerBlock, enemy);
                eventBus.register(collisionDetector, OnGameStart.class);
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                eventBus.sendEvent(OnGameStart.class);
            }
        }, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Messages.bus().unRegister(gameTimer);
        Messages.bus().unRegister(endScreen);
        Messages.bus().unRegister(playerListener);
        Messages.bus().unRegister(enemyListener);
        Messages.bus().unRegister(collisionDetector);
        Messages.bus().unRegister(enemyGenerator);
        Messages.bus().unRegister(vibrateOnCollision);
    }
}
