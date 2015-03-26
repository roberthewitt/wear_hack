package com.kotikan.android.hack.wear.helloworld.logic;

import android.view.View;

import com.kotikan.android.hack.wear.helloworld.views.EnemyTranslator;

public class GameLoop {

    public static void createWith(View enemy) {
        new EnemyTranslator(enemy).animate();
    }
}
