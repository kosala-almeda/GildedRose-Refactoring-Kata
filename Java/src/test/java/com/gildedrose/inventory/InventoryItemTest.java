package com.gildedrose.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.gildedrose.Item;
import com.gildedrose.behavior.quality.QualityUpdateStrategy;
import com.gildedrose.behavior.sellin.SellInUpdateStrategy;

class InventoryItemTest {

    static final int FIXED_QUALITY = 44;
    static final int FIXED_SELLIN = 33;

    // Mocked quality strategy with a fixed quality value
    private class MockQualityStrategy implements QualityUpdateStrategy {

        @Override
        public void updateQuality(Item item) {
            item.quality = FIXED_QUALITY;
        }
        
    }

    // Mocked sellIn strategy with a fixed sellIn value
    private class MockSellInStrategy implements SellInUpdateStrategy {

        @Override
        public void updateSellIn(Item item) {
            item.sellIn = FIXED_SELLIN;
        }
        
    }
    
    @Test
    void testUpdate() {
        Item origItem = new Item("foo", 0, 0);
        InventoryItem item = new InventoryItem(origItem, new MockSellInStrategy(), new MockQualityStrategy());
        item.update();

        assertEquals(FIXED_QUALITY, item.getItem().quality);
        assertEquals(FIXED_SELLIN, item.getItem().sellIn);
    }
}
