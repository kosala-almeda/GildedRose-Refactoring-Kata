package com.gildedrose.behavior.sellin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.gildedrose.Item;

public class StandardSellinStrategyTest {

    @Test
    public void testUpdateQuality_default() {
        Item item = new Item("foo", 10, 10);
        StandardSellinStrategy standardStrategy = StandardSellinStrategy.getInstance();
        standardStrategy.updateSellIn(item);

        assertEquals(9, item.sellIn);
        assertEquals(10, item.quality);
    }

    @Test
    public void testUpdateQuality_min() {
        Item item = new Item("bar", 0, 10);
        StandardSellinStrategy standardStrategy = StandardSellinStrategy.getInstance();
        standardStrategy.updateSellIn(item);

        assertEquals(0, item.sellIn);
        assertEquals(10, item.quality);
    }
    
}
