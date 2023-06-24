package com.gildedrose.behavior.strategy.quality;

import com.gildedrose.Item;

public class FixedQualityStrategy implements QualityUpdateStrategy {

    private int quality;

    public FixedQualityStrategy(int quality) {
        this.quality = quality;
    }
    
    @Override
    public void updateQuality(Item item) {
        item.quality = quality;
    }
    
}
