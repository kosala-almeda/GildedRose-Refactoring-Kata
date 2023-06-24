package com.gildedrose.behavior.quality;

import com.gildedrose.Item;

/**
 * IncreasingQualityStrategy has the behavior of increasing the quality value of an item
 */
public class IncreasingQualityStrategy implements QualityUpdateStrategy {
    
    /**
     * The step to increase the quality (default is 1)
     */
    private int step;

    public IncreasingQualityStrategy() {
        this.step = 1;
    }

    public IncreasingQualityStrategy(int step) {
        this.step = step;
    }

    @Override
    public void updateQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality = item.quality + step;
        }
        if (item.quality > MAX_QUALITY) {
            item.quality = MAX_QUALITY;
        }
    }

    public int getStep() {
        return step;
    }
    
}
