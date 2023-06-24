package com.gildedrose.behavior.strategy.quality;

import java.util.List;

import com.gildedrose.Item;

public class ThresholdedCompositeStrategy implements QualityUpdateStrategy {

    public static class ThresholdStrategy {
        private int threshold;
        private QualityUpdateStrategy strategy;

        public ThresholdStrategy(int threshold, QualityUpdateStrategy strategy) {
            this.threshold = threshold;
            this.strategy = strategy;
        }
    }

    private List<ThresholdStrategy> strategies;

    public ThresholdedCompositeStrategy(List<ThresholdStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public void updateQuality(Item item) {
        for (ThresholdStrategy thresholdStrategy : strategies) {
            if (item.sellIn <= thresholdStrategy.threshold) {
                thresholdStrategy.strategy.updateQuality(item);
                break;
            }
        }
    }

    public List<ThresholdStrategy> getStrategies() {
        return this.strategies;
    }

}
