package com.gildedrose.behavior.quality;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.gildedrose.Item;

/**
 * Unit tests for {@link ThresholdedCompositeStrategy}.
 */
public class ThresholdedCompositeStrategyTest {

    static final int FIXED_QUALITY_1 = 44;
    static final int FIXED_QUALITY_2 = 33;

    // Mocked quality strategy with a fixed quality value
    private class MockQualityStrategy1 implements QualityUpdateStrategy {

        @Override
        public void updateQuality(Item item) {
            item.quality = FIXED_QUALITY_1;
        }
        
    }
    
    private class MockQualityStrategy2 implements QualityUpdateStrategy {

        @Override
        public void updateQuality(Item item) {
            item.quality = FIXED_QUALITY_2;
        }
        
    }

    @Test
    void testUpdateQuality_1() {
        Item item = new Item("foo", 5, 10);
        ThresholdedCompositeStrategy tcStrategy = new ThresholdedCompositeStrategy(List.of(
            new ThresholdedCompositeStrategy.ThresholdStrategy(5, new MockQualityStrategy1()),
            new ThresholdedCompositeStrategy.ThresholdStrategy(10, new MockQualityStrategy2())
        ));
        tcStrategy.updateQuality(item);

        assertEquals(FIXED_QUALITY_1, item.quality);
        assertEquals(5, item.sellIn);
    }

    @Test
    void testUpdateQuality_2() {
        Item item = new Item("bar", 7, 10);
        ThresholdedCompositeStrategy tcStrategy = new ThresholdedCompositeStrategy(List.of(
            new ThresholdedCompositeStrategy.ThresholdStrategy(5, new MockQualityStrategy1()),
            new ThresholdedCompositeStrategy.ThresholdStrategy(10, new MockQualityStrategy2())
        ));
        tcStrategy.updateQuality(item);

        assertEquals(FIXED_QUALITY_2, item.quality);
        assertEquals(7, item.sellIn);
    }

    @Test
    void testUpdateQuality_3() {
        Item item = new Item("fuz", 15, 10);
        ThresholdedCompositeStrategy tcStrategy = new ThresholdedCompositeStrategy(List.of(
            new ThresholdedCompositeStrategy.ThresholdStrategy(5, new MockQualityStrategy1()),
            new ThresholdedCompositeStrategy.ThresholdStrategy(10, new MockQualityStrategy2())
        ));
        tcStrategy.updateQuality(item);

        assertEquals(10, item.quality);
        assertEquals(15, item.sellIn);
    }
    
}
