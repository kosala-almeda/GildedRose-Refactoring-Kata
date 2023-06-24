package com.gildedrose.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.gildedrose.Item;
import com.gildedrose.behavior.quality.DecreasingQualityStrategy;
import com.gildedrose.behavior.quality.IncreasingQualityStrategy;
import com.gildedrose.behavior.quality.NonChangingQualityStrategy;
import com.gildedrose.behavior.quality.ThresholdedCompositeStrategy;
import com.gildedrose.behavior.sellin.LegendarySellinStrategy;
import com.gildedrose.behavior.sellin.StandardSellinStrategy;

class InventoryFactoryTest {

    private InventoryFactory factory = new InventoryFactory();

    @Test
    void testWrapItem_basic() {
        Item origItem = new Item("foo", 0, 0);
        InventoryItem item = factory.wrapItem(origItem);
        
        assertNotEquals(item, null);
        assertEquals(item.getItem(), origItem);
        assertNotEquals(item.getQualityUpdateStrategy(), null);
        assertNotEquals(item.getSellInUpdateStrategy(), null);
    }

    @Test
    void testWrapItem_singleton() {
        Item origItem1 = new Item("foo", 0, 0);
        InventoryItem item1 = factory.wrapItem(origItem1);
        Item origItem2 = new Item("bar", 1, 1);
        InventoryItem item2 = factory.wrapItem(origItem2);

        assertEquals(item1.getQualityUpdateStrategy(), item2.getQualityUpdateStrategy());
        assertEquals(item1.getSellInUpdateStrategy(), item2.getSellInUpdateStrategy());
    }

    @Test
    void testWrapItem_standard() {
        Item origItem = new Item("A standard item", 0, 0);
        InventoryItem item = factory.wrapItem(origItem);
        
        assertEquals(item.getItem(), origItem);
        assert(item.getQualityUpdateStrategy() instanceof DecreasingQualityStrategy);
        assertEquals(1, ((DecreasingQualityStrategy)item.getQualityUpdateStrategy()).getStep());
        assert(item.getSellInUpdateStrategy() instanceof StandardSellinStrategy);
    }

    @Test
    void testWrapItem_agedBrie() {
        Item origItem = new Item("Aged Brie item", 0, 0);
        InventoryItem item = factory.wrapItem(origItem);
        
        assertEquals(item.getItem(), origItem);
        assert(item.getQualityUpdateStrategy() instanceof IncreasingQualityStrategy);
        assertEquals(1, ((IncreasingQualityStrategy)item.getQualityUpdateStrategy()).getStep());
        assert(item.getSellInUpdateStrategy() instanceof StandardSellinStrategy);
    }

    @Test
    void testWrapItem_sulfuras() {
        Item origItem = new Item("Sulfuras item", 0, 0);
        InventoryItem item = factory.wrapItem(origItem);
        
        assertEquals(item.getItem(), origItem);
        assert(item.getQualityUpdateStrategy() instanceof NonChangingQualityStrategy);
        assert(item.getSellInUpdateStrategy() instanceof LegendarySellinStrategy);
    }

    @Test
    void testWrapItem_backstagePass() {
        Item origItem = new Item("Backstage passes item", 0, 0);
        InventoryItem item = factory.wrapItem(origItem);
        
        assertEquals(item.getItem(), origItem);
        assert(item.getQualityUpdateStrategy() instanceof ThresholdedCompositeStrategy);
        assertEquals(4, ((ThresholdedCompositeStrategy)item.getQualityUpdateStrategy()).getStrategies().size());
        assert(item.getSellInUpdateStrategy() instanceof StandardSellinStrategy);
    }

    @Test
    void testWrapItem_conjured() {
        Item origItem = new Item("Conjured item", 0, 0);
        InventoryItem item = factory.wrapItem(origItem);
        
        assertEquals(item.getItem(), origItem);
        assert(item.getQualityUpdateStrategy() instanceof DecreasingQualityStrategy);
        assertEquals(2, ((DecreasingQualityStrategy)item.getQualityUpdateStrategy()).getStep());
        assert(item.getSellInUpdateStrategy() instanceof StandardSellinStrategy);
    }
    
}
