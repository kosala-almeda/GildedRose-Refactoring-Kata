package com.gildedrose.behavior.quality;

import com.gildedrose.Item;

/**
 * NonChangingQualityStrategy has the behavior of not changing the quality value of an item
 */
public class NonChangingQualityStrategy implements QualityUpdateStrategy {
    
    @Override
    /**
     * Do nothing: the quality value of an item does not change
     * @param item the item to update
     */
    public void updateQuality(Item item) {
        // Do nothing
    }
    
}
