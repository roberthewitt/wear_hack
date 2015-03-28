package com.kotikan.android.hack.wear.helloworld.utils;

import junit.framework.Assert;

import org.junit.Test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class EnemyHeartLogicTest {

    @Test
    public void will_spawn_1_in_5() throws Exception {
        EnemyHeartLogic l = new EnemyHeartLogic();

        NumberGenerator generator = mock(NumberGenerator.class);
        l.shouldSpawnHeart(generator, 0);

        verify(generator).generateBetween(1, 5);
    }

    @Test
    public void spawns_if_generator_returns_1() throws Exception {
        EnemyHeartLogic l = new EnemyHeartLogic();

        NumberGenerator generator = successGenerator();

        Assert.assertTrue(l.shouldSpawnHeart(generator, 0));
    }

    @Test
    public void if_max_lives_no_spawn() throws Exception {
        EnemyHeartLogic l = new EnemyHeartLogic();

        NumberGenerator generator = successGenerator();

        Assert.assertFalse(l.shouldSpawnHeart(generator, GameConstants.LIVES_STARTING_QUANTITY));
    }

    @Test
    public void cannot_double_spawn() throws Exception {
        EnemyHeartLogic l = new EnemyHeartLogic();

        NumberGenerator generator = successGenerator();

        Assert.assertTrue(l.shouldSpawnHeart(generator, 0));
        Assert.assertFalse(l.shouldSpawnHeart(generator, 0));
    }

    @Test
    public void cannot_double_spawn_generator_returning_true_then_false() throws Exception {
        EnemyHeartLogic l = new EnemyHeartLogic();
        NumberGenerator generator = mock(NumberGenerator.class);

        Assert.assertTrue(l.shouldSpawnHeart(successGenerator(), 0));
        Assert.assertFalse(l.shouldSpawnHeart(generator, 0));
        verify(generator, never()).generateBetween(anyInt(), anyInt());
    }

    @Test
    public void three_generations_before_heart_block_allowed() throws Exception {
        EnemyHeartLogic l = new EnemyHeartLogic();
        NumberGenerator generator = successGenerator();

        Assert.assertTrue(l.shouldSpawnHeart(generator, 0));
        Assert.assertFalse(l.shouldSpawnHeart(generator, 0));
        Assert.assertFalse(l.shouldSpawnHeart(generator, 0));
        Assert.assertFalse(l.shouldSpawnHeart(generator, 0));
        Assert.assertTrue(l.shouldSpawnHeart(generator, 0));
    }

    private NumberGenerator successGenerator() {
        return generatorReturning(1);
    }

    private NumberGenerator generatorReturning(final int i) {
        return new NumberGenerator(){
            @Override
            public int generateBetween(int min, int max) {
                return i;
            }
        };

    }
}