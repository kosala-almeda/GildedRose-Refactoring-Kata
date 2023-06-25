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

    /**
     * Default constructor
     * The step is 1
     */
    public IncreasingQualityStrategy() {
        this.step = 1;
    }

    /**
     * Constructor with step
     * @param step the step size to increase the quality
     */
    public IncreasingQualityStrategy(int step) {
        if (step <= 0) {
            throw new IllegalArgumentException("The step must be greater than 0");
        }
        this.step = step;
    }

    @Override
    /**
     * Increase the quality value of an item by the step size
     * If the quality value is greater than the maximum quality, set it to the maximum quality
     * @param item the item to update
     */
    public void updateQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality = item.quality + step;
        }
        if (item.quality > MAX_QUALITY) {
            item.quality = MAX_QUALITY;
        }
    }

    /**
     * @return the step size to increase the quality
     */
    public int getStep() {
        return step;
    }
    
}
