package com.gildedrose.behavior.quality;

import com.gildedrose.Item;

/**
 * QualityUpdateStrategy is an interface for updating the quality of an Item
 */
public interface QualityUpdateStrategy {

    /**
     * The maximum quality value for an item during updates
     */
    static final int MAX_QUALITY = 50;

    /**
     * The minimum quality value for an item during updates
     */
    static final int MIN_QUALITY = 0;

    /**
     * Update the quality value of an item
     * @param item the item to update
     */
    void updateQuality(Item item);
}
