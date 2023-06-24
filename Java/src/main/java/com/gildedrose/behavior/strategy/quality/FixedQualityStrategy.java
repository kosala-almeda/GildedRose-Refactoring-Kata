package com.gildedrose.behavior.strategy.quality;

import com.gildedrose.Item;

/**
 * FixedQualityStrategy has the behavior of setting the quality of an item to a predefined value
 */
public class FixedQualityStrategy implements QualityUpdateStrategy {

    /**
     * The quality value to set
     */
    private int quality;

    public FixedQualityStrategy(int quality) {
        this.quality = quality;
    }
    
    @Override
    public void updateQuality(Item item) {
        item.quality = quality;
    }

    public int getQuality() {
        return quality;
    }
    
}
