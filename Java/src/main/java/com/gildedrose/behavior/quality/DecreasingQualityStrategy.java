package com.gildedrose.behavior.quality;

import com.gildedrose.Item;

/**
 * DecreasingQualityStrategy has the behavior of decreasing the quality value of an item
 */
public class DecreasingQualityStrategy implements QualityUpdateStrategy {

    /**
     * The step to decrease the quality (default is 1)
     */
    private int step;

    /**
     * Default constructor
     * The step is 1
     */
    public DecreasingQualityStrategy() {
        this.step = 1;
    }

    /**
     * Constructor with step
     * @param step the step size to decrease the quality
     */
    public DecreasingQualityStrategy(int step) {
        if (step <= 0) {
            throw new IllegalArgumentException("The step must be greater than 0");
        }
        this.step = step;
    }

    @Override
    /**
     * Decrease the quality value of an item by the step size
     * If the quality value is less than the minimum quality, set it to the minimum quality
     * @param item the item to update
     */
    public void updateQuality(Item item) {
        if (item.quality > MIN_QUALITY) {
            item.quality = item.quality - step;
        }
        if (item.quality < MIN_QUALITY) {
            item.quality = MIN_QUALITY;
        }
    }

    /**
     * @return the step size to decrease the quality
     */
    public int getStep() {
        return step;
    }

}
