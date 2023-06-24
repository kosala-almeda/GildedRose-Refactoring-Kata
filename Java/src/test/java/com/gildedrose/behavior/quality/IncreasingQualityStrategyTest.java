package com.gildedrose.behavior.quality;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.gildedrose.Item;

public class IncreasingQualityStrategyTest {

    @Test
    public void testUpdateQuality_default() {
        Item item = new Item("foo", 10, 10);
        IncreasingQualityStrategy iqStrategy = new IncreasingQualityStrategy();
        iqStrategy.updateQuality(item);

        assertEquals(11, item.quality);
        assertEquals(10, item.sellIn);
    }

    @Test
    public void testUpdateQuality_custom() {
        Item item = new Item("bar", 5, 5);
        IncreasingQualityStrategy iqStrategy = new IncreasingQualityStrategy(3);
        iqStrategy.updateQuality(item);

        assertEquals(8, item.quality);
        assertEquals(5, item.sellIn);
    }

    @Test
    public void testUpdateQuality_max() {
        Item item = new Item("fuz", 10, 50);
        IncreasingQualityStrategy iqStrategy = new IncreasingQualityStrategy();
        iqStrategy.updateQuality(item);

        assertEquals(50, item.quality);
        assertEquals(10, item.sellIn);
    }
    
}
