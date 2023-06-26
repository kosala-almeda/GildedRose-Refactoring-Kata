package com.gildedrose.behavior.quality;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.gildedrose.Item;

/**
 * Unit tests for {@link FixedQualityStrategy}.
 */
public class FixedQualityStrategyTest {

    @Test
    public void testUpdateQuality() {
        Item item = new Item("foo", 10, 10);
        FixedQualityStrategy fqStrategy = new FixedQualityStrategy(6);
        fqStrategy.updateQuality(item);

        assertEquals(6, item.quality);
        assertEquals(10, item.sellIn);
    }
    
}
