package com.gildedrose.behavior.quality;

import com.gildedrose.Item;

/**
 * FixedQualityStrategy has the behavior of setting the quality of an item to a predefined value
 */
public class FixedQualityStrategy implements QualityUpdateStrategy {

    /**
     * The quality value to set
     */
    private int quality;

    /**
     * Constructor with quality
     * @param quality the quality value to set
     */
    public FixedQualityStrategy(int quality) {
        this.quality = quality;
    }
    
    @Override
    /**
     * Set the quality value of an item to the predefined value
     * @param item the item to update
     */
    public void updateQuality(Item item) {
        item.quality = quality;
    }

    /**
     * @return the quality value to set
     */
    public int getQuality() {
        return quality;
    }
    
}
