package com.gildedrose.behavior.strategy.quality;

import java.util.List;

import com.gildedrose.Item;

/**
 * ThresholdedCompositeStrategy has the behaviors of advanced quality update
 * strategies that are different depending on the sellIn value of the item
 */
public class ThresholdedCompositeStrategy implements QualityUpdateStrategy {

    /**
     * ThresholdStrategy is a helper class for ThresholdedCompositeStrategy
     * that holds a threshold value and a quality update strategy
     */
    public static class ThresholdStrategy {
        private int threshold;
        private QualityUpdateStrategy strategy;

        public ThresholdStrategy(int threshold, QualityUpdateStrategy strategy) {
            this.threshold = threshold;
            this.strategy = strategy;
        }
    }

    /**
     * The ordered list of thresholds and strategies
     */
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
