package com.kotikan.android.hack.wear.helloworld.utils;

import com.kotikan.android.hack.wear.helloworld.abstractions.Block;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CollisionMonitorTest {


    @Test
    public void hits_on_x_axis() throws Exception {
        Block a = block().yHit().x(45).build();
        Block b = block().yHit().x(45).build();

        assertEquals(true, CollisionMonitor.hasCollided(a, b));
    }

    @Test
    public void hits_on_x_axis_inside_width() throws Exception {
        Block a = block().yHit().x(10).width(21).build();
        Block b = block().yHit().x(30).width(21).build();

        assertEquals(true, CollisionMonitor.hasCollided(a, b));
    }

    @Test
    public void no_hit_on_x_axis_outside_width() throws Exception {
        Block a = block().yHit().x(10).width(19).build();
        Block b = block().yHit().x(30).width(19).build();

        assertEquals(false, CollisionMonitor.hasCollided(a, b));
    }

    @Test
    public void hits_on_y_axis() throws Exception {
        Block a = block().xHit().y(45).build();
        Block b = block().xHit().y(45).build();

        assertEquals(true, CollisionMonitor.hasCollided(a, b));
    }

    @Test
    public void hits_on_y_axis_inside_height() throws Exception {
        Block a = block().xHit().y(10).height(21).build();
        Block b = block().xHit().y(30).height(21).build();

        assertEquals(true, CollisionMonitor.hasCollided(a, b));
    }

    @Test
    public void no_hit_on_y_axis_outside_height() throws Exception {
        Block a = block().xHit().y(10).height(19).build();
        Block b = block().xHit().y(30).height(19).build();

        assertEquals(false, CollisionMonitor.hasCollided(a, b));
    }

    private Builder block() {
        return new Builder();
    }

    static class Builder {

        final private Block mock = Mockito.mock(Block.class);

        public Builder x(int x) {
            when(mock.x()).thenReturn(x);
            return this;
        }

        Block build() {
            return mock;
        }

        public Builder width(int i) {
            when(mock.width()).thenReturn(i);
            return this;
        }

        public Builder y(int i) {
            when(mock.y()).thenReturn(i);
            return this;
        }

        public Builder height(int i) {
            when(mock.height()).thenReturn(i);
            return this;
        }

        public Builder xHit() {
            x(0);
            return this;
        }

        public Builder yHit() {
            y(0);
            return this;
        }
    }
}