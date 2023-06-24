package com.gildedrose;

import com.gildedrose.inventory.InventoryFactory;
import com.gildedrose.inventory.InventoryItem;

class GildedRose {
    // orginal set of items
    Item[] items;

    // The factory to wrap the items
    InventoryFactory factory = new InventoryFactory();

    // wrapped items
    InventoryItem[] inventoryItems;

    public GildedRose(Item[] items) {
        this.items = items;
        // Make sure to wrap the Items as InventoryItems
        this.inventoryItems = wrapItems(items);
    }

    public void updateQuality() {
        // Use wrapped items to update using the strategies
        for (int i = 0; i < inventoryItems.length; i++) {
            inventoryItems[i].update();
        }
    }

    private InventoryItem[] wrapItems(Item[] items) {
        // Wrap all items using the inventory factory to attach the strategies
        InventoryItem[] inventoryItems = new InventoryItem[items.length];
        for (int i = 0; i < items.length; i++) {
            inventoryItems[i] = factory.wrapItem(items[i]);
        }
        return inventoryItems;
    }
}