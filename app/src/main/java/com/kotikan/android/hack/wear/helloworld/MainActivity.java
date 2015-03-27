package com.kotikan.android.hack.wear.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.view.WatchViewStub;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kotikan.android.hack.wear.helloworld.abstractions.EnemyBlock;
import com.kotikan.android.hack.wear.helloworld.abstractions.EnemyViewBlock;
import com.kotikan.android.hack.wear.helloworld.abstractions.TouchInput;
import com.kotikan.android.hack.wear.helloworld.abstractions.ViewBlock;
import com.kotikan.android.hack.wear.helloworld.abstractions.ViewTextOutput;
import com.kotikan.android.hack.wear.helloworld.abstractions.ViewTouchInput;
import com.kotikan.android.hack.wear.helloworld.eventbus.EventBus;
import com.kotikan.android.hack.wear.helloworld.eventbus.EventHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.Messages;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.CollisionDetected;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.GameOver;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.GameStart;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.JumpPlayer;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.LifeChanged;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.NumberOfLivesResponse;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.RequestNumberOfLives;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.ResetGameState;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.SpawnEnemy;
import com.kotikan.android.hack.wear.helloworld.eventbus.events.SqaushPlayer;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.CollisionDetector;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.CountdownToStart;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.EnemyAnimator;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.GameTimer;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.LifeDisplayer;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.LifeHandler;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.PlayerAnimator;
import com.kotikan.android.hack.wear.helloworld.eventbus.handlers.VibrateOnCollision;

public class MainActivity extends Activity {

    private EventHandler gameTimer;
    private EventHandler endScreen;
    private EventHandler playerListener;
    private EventHandler enemyListener;
    private EventHandler collisionDetector;
    private EventHandler enemyGenerator;
    private EventHandler vibrateOnCollision;
    private EventHandler countDownToStart;
    private EventHandler lifeHandler;
    private EventHandler lifeDisplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Handler handler = new Handler();
        final EventBus eventBus = Messages.bus();
        vibrateOnCollision = new VibrateOnCollision(this);
        eventBus.register(vibrateOnCollision, CollisionDetected.class);
        enemyGenerator = new Spawner(handler);
        eventBus.register(enemyGenerator, GameStart.class);
        eventBus.register(enemyGenerator, GameOver.class);

        lifeHandler = new LifeHandler();
        eventBus.register(lifeHandler, CollisionDetected.class);
        eventBus.register(lifeHandler, GameStart.class);
        eventBus.register(lifeHandler, RequestNumberOfLives.class);

        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnTouchListener(new ScreenClickSender());
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                final ViewBlock playerBlock = new ViewBlock(stub.findViewById(R.id.player_block));
                final EnemyBlock enemy = new EnemyViewBlock((ImageView) stub.findViewById(R.id.enemy_block));
                final ViewTextOutput timer = new ViewTextOutput((TextView) stub.findViewById(R.id.game_timer));
                final TouchInput clickToRetry = new ViewTouchInput(stub.findViewById(R.id.game_over_click_to_retry));
                final ViewTextOutput textOutput = new ViewTextOutput((TextView) stub.findViewById(R.id.countdown_to_start));
                final ViewGroup lifeCounter = (ViewGroup) stub.findViewById(R.id.life_container);

                lifeDisplayer = new LifeDisplayer(lifeCounter);
                eventBus.register(lifeDisplayer, LifeChanged.class);

                gameTimer = new GameTimer(timer);
                eventBus.register(gameTimer, GameStart.class);
                eventBus.register(gameTimer, GameOver.class);
                eventBus.register(gameTimer, ResetGameState.class);

                endScreen = new GameEndScreen(clickToRetry);
                eventBus.register(endScreen, GameOver.class);

                playerListener = new PlayerAnimator(playerBlock);
                eventBus.register(playerListener, JumpPlayer.class);
                eventBus.register(playerListener, SqaushPlayer.class);
                eventBus.register(playerListener, GameOver.class);
                eventBus.register(playerListener, ResetGameState.class);
                eventBus.register(playerListener, GameStart.class);

                enemyListener = new EnemyAnimator(enemy);
                eventBus.register(enemyListener, SpawnEnemy.class);
                eventBus.register(enemyListener, GameOver.class);
                eventBus.register(enemyListener, ResetGameState.class);
                eventBus.register(enemyListener, NumberOfLivesResponse.class);

                countDownToStart = new CountdownToStart(textOutput);
                eventBus.register(countDownToStart, ResetGameState.class);

                collisionDetector = new CollisionDetector(playerBlock, enemy);
                eventBus.register(collisionDetector, GameStart.class);
                eventBus.register(collisionDetector, GameOver.class);
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                eventBus.sendEvent(ResetGameState.class);
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
        Messages.bus().unRegister(lifeHandler);
        Messages.bus().unRegister(lifeDisplayer);
    }
}
