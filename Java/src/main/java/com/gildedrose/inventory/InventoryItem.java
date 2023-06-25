package com.gildedrose.inventory;

import com.gildedrose.Item;

/**
 * InventoryItem is a wrapper for Item that contains the strategies for updating
 * Item is a class from the legacy code that we cannot change
 * We need to wrap it in order to add the strategies
 */
public class InventoryItem {

    /**
     * item is the original item from the legacy code
     */
    private Item item;

    /**
     * type is the type of the item
     * (it contains the strategies for updating the item)
     */
    private ItemType type;
    
    /**
     * Constructor
     *  - wrap the original item
     *  - determine the type of the item
     * @param item the original item to wrap
     */
    public InventoryItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }
        this.item = item;
        this.type = ItemType.fromName(item.name);
    }

    /**
     * Update the item (at the end of the day)
     */
    public void update() {
        // Update quality
        type.getQualityStrategy().updateQuality(item);
        // Update sellIn
        type.getSellInStrategy().updateSellIn(item);
    }

    /**
     * @return the original item wrapped by this class
     */
    public Item getItem() {
        return item;
    }

    /**
     * @return the type of the wrapped item
     */
    public ItemType getType() {
        return type;
    }

}
