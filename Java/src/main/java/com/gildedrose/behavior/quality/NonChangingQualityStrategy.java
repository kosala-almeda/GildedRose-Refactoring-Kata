package com.gildedrose.behavior.quality;

import com.gildedrose.Item;

/**
 * NonChangingQualityStrategy has the behavior of not changing the quality value of an item
 */
public class NonChangingQualityStrategy implements QualityUpdateStrategy {
    
    @Override
    public void updateQuality(Item item) {
        // Do nothing
    }
    
}
