package com.gildedrose.behavior.strategy.sellin;

import com.gildedrose.Item;

/**
 * SellInUpdateStrategy is an interface for updating the sellIn value of an Item
 */
public interface SellInUpdateStrategy {

    public static final int MIN_SELLIN = 0;

    /**
     * Update the sellIn value of an item
     * @param item the item to update
     */
    void updateSellIn(Item item);
}
