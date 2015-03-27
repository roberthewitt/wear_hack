package com.kotikan.android.hack.wear.helloworld.utils;

public class EnemyHeartLogic {

    private int queriesSinceLastSpawn = 3;

    public boolean shouldSpawnHeart(NumberGenerator generator, int lifeCount) {
        boolean result = false;
        if (livesMissing(lifeCount) && allowedToSpawn() && randomSaysYes(generator)) {
            queriesSinceLastSpawn = 0;
            result = true;
        } else {
            queriesSinceLastSpawn++;
        }
        return result;
    }

    private boolean livesMissing(int lifeCount) {
        return lifeCount != GameConstants.STARTING_LIVES;
    }
    
    private boolean allowedToSpawn() {
        return queriesSinceLastSpawn >= 3;
    }

    private boolean randomSaysYes(NumberGenerator generator) {
        return generator.generateBetween(1, 5) == 1;
    }
}
