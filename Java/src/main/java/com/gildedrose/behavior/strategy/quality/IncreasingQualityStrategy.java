package com.gildedrose.behavior.strategy.quality;

import com.gildedrose.Item;

public class IncreasingQualityStrategy implements QualityUpdateStrategy {
    
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
    
}
