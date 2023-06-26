package com.gildedrose.behavior.quality;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.gildedrose.Item;

/**
 * Unit tests for {@link NonChangingQualityStrategy}.
 */
public class NonChangingQualityStrategyTest {

    @Test
    public void testUpdateQuality() {
        Item item = new Item("foo", 10, 10);
        NonChangingQualityStrategy ncqStrategy = new NonChangingQualityStrategy();
        ncqStrategy.updateQuality(item);

        assertEquals(10, item.quality);
        assertEquals(10, item.sellIn);
    }
    
}
