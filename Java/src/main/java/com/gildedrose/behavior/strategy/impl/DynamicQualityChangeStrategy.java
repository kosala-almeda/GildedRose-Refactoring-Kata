package com.gildedrose.behavior.strategy.impl;

import com.gildedrose.Item;
import com.gildedrose.behavior.strategy.QualityUpdateStrategy;

public class DynamicQualityChangeStrategy implements QualityUpdateStrategy {

    private int qualityStep;
    private int sellInMinThreshold;
    private int sellInMaxThreshold;
    
    public DynamicQualityChangeStrategy(int qualityStep) {
        this.qualityStep = qualityStep;
        this.sellInMinThreshold = 0;
        this.sellInMaxThreshold = Integer.MAX_VALUE;
    }

    public DynamicQualityChangeStrategy(int qualityStep, int sellInMinThreshold) {
        this.qualityStep = qualityStep;
        this.sellInMinThreshold = sellInMinThreshold;
        this.sellInMaxThreshold = Integer.MAX_VALUE;
    }
    
    public DynamicQualityChangeStrategy(int qualityStep, int sellInMinThreshold, int sellInMaxThreshold) {
        this.qualityStep = qualityStep;
        this.sellInMinThreshold = sellInMinThreshold;
        this.sellInMaxThreshold = sellInMaxThreshold;
    }

    @Override
    public void updateQuality(Item item) {
        if (item.sellIn > sellInMinThreshold && item.sellIn < sellInMaxThreshold) {
            item.quality = item.quality + qualityStep;
        }
    }
}
