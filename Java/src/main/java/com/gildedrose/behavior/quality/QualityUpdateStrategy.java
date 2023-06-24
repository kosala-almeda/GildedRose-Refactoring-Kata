package com.gildedrose.behavior.quality;

import com.gildedrose.Item;

/**
 * QualityUpdateStrategy is an interface for updating the quality of an Item
 */
public interface QualityUpdateStrategy {

    static final int MAX_QUALITY = 50;
    static final int MIN_QUALITY = 0;

    /**
     * Update the quality value of an item
     * @param item the item to update
     */
    void updateQuality(Item item);
}
