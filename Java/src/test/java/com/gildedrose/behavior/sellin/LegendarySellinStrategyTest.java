package com.gildedrose.behavior.sellin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.gildedrose.Item;

public class LegendarySellinStrategyTest {

    @Test
    public void testUpdateQuality() {
        Item item = new Item("foo", 10, 10);
        LegendarySellinStrategy lsStrategy = LegendarySellinStrategy.getInstance();
        lsStrategy.updateSellIn(item);

        assertEquals(10, item.sellIn);
        assertEquals(10, item.quality);
        
    }
    
}
