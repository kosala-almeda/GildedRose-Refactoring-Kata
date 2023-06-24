package com.gildedrose.behavior.strategy.quality;

import com.gildedrose.Item;

public class DecreasingQualityStrategy implements QualityUpdateStrategy {

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

}
