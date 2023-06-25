package com.gildedrose.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.gildedrose.Item;

/**
 * Unit tests for {@link InventoryItem}.
 */
class InventoryItemTest {

    // Constants for special item tests
    static final String SPECIAL_ITEM_NAME = "Aged Brie";
    static final ItemType SPECIAL_ITEM_TYPE = ItemType.AGED_BRIE;
    static final int SPECIAL_ITEM_QUALITY = 1;
    static final int SPECIAL_ITEM_SELL_IN = 1;
    static final int SPECIAL_ITEM_QUALITY_UPDATED = 2;
    static final int SPECIAL_ITEM_SELL_IN_UPDATED = 0;

    @Test
    void testConstructor_normal() {
        Item origItem = new Item("foo", 1, 1);
        InventoryItem item = new InventoryItem(origItem);

        assertEquals(origItem, item.getItem());
        assertEquals(ItemType.NORMAL, item.getType());
    }

    @Test
    void testConstructor_special() {
        Item origItem = new Item(SPECIAL_ITEM_NAME, SPECIAL_ITEM_SELL_IN, SPECIAL_ITEM_QUALITY);
        InventoryItem item = new InventoryItem(origItem);

        assertEquals(origItem, item.getItem());
        assertEquals(SPECIAL_ITEM_TYPE, item.getType());
    }
    
    @Test
    void testUpdate() {
        Item origItem = new Item("foo", 1, 1);
        InventoryItem item = new InventoryItem(origItem);
        item.update();

        assertEquals(0, item.getItem().quality);
        assertEquals(0, item.getItem().sellIn);
    }

    @Test
    void testUpdate_special() {
        Item origItem = new Item(SPECIAL_ITEM_NAME, SPECIAL_ITEM_SELL_IN, SPECIAL_ITEM_QUALITY);
        InventoryItem item = new InventoryItem(origItem);
        item.update();

        assertEquals(SPECIAL_ITEM_QUALITY_UPDATED, item.getItem().quality);
        assertEquals(SPECIAL_ITEM_SELL_IN_UPDATED, item.getItem().sellIn);
    }
}
