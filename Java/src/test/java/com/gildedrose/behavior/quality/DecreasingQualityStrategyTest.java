package com.gildedrose.behavior.quality;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.gildedrose.Item;

public class DecreasingQualityStrategyTest {

    @Test
    public void testUpdateQuality_default() {
        Item item = new Item("foo", 10, 10);
        DecreasingQualityStrategy dqStrategy = new DecreasingQualityStrategy();
        dqStrategy.updateQuality(item);

        assertEquals(9, item.quality);
        assertEquals(10, item.sellIn);
    }

    @Test
    public void testUpdateQuality_custom() {
        Item item = new Item("bar", 5, 5);
        DecreasingQualityStrategy dqStrategy = new DecreasingQualityStrategy(3);
        dqStrategy.updateQuality(item);

        assertEquals(2, item.quality);
        assertEquals(5, item.sellIn);
    }

    @Test
    public void testUpdateQuality_min() {
        Item item = new Item("fuz", 10, 0);
        DecreasingQualityStrategy dqStrategy = new DecreasingQualityStrategy();
        dqStrategy.updateQuality(item);

        assertEquals(0, item.quality);
        assertEquals(10, item.sellIn);
    }
    
}
