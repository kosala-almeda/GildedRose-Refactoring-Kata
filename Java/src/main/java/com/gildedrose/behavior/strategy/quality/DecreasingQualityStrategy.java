package com.gildedrose.behavior.strategy.quality;

import com.gildedrose.Item;

/**
 * DecreasingQualityStrategy has the behavior of decreasing the quality value of an item
 */
public class DecreasingQualityStrategy implements QualityUpdateStrategy {

    /**
     * The step to decrease the quality (default is 1)
     */
    private int step;

    public DecreasingQualityStrategy() {
        this.step = 1;
    }

    public DecreasingQualityStrategy(int step) {
        this.step = step;
    }

    @Override
    public void updateQuality(Item item) {
        if (item.quality > MIN_QUALITY) {
            item.quality = item.quality - step;
        }
        if (item.quality < MIN_QUALITY) {
            item.quality = MIN_QUALITY;
        }
    }

    public int getStep() {
        return step;
    }

}
