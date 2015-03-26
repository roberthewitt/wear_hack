package com.kotikan.android.hack.wear.helloworld.utils;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CollisionMonitorTest {


    @Test
    public void hits_on_x_axis() throws Exception {
        Block a = block().x(45).build();
        Block b = block().x(45).build();

        assertEquals(true, CollisionMonitor.hasCollided(a, b));
    }

    @Test
    public void hits_on_x_axis_inside_width() throws Exception {
        Block a = block().x(10).width(21).build();
        Block b = block().x(30).width(21).build();

        assertEquals(true, CollisionMonitor.hasCollided(a, b));
    }

    @Test
    public void no_hit_on_x_axis_outside_width() throws Exception {
        Block a = block().x(10).width(19).build();
        Block b = block().x(30).width(19).build();

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
    }
}