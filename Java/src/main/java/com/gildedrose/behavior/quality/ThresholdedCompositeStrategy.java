package com.gildedrose.behavior.quality;

import java.util.List;

import com.gildedrose.Item;

/**
 * ThresholdedCompositeStrategy has the behaviors of advanced quality update
 * strategies that are different depending on the sellIn value of the item
 *  - Assuming the thresholds are in ascending order, the first strategy that
 *    has a threshold value greater than or equal to the sellIn value will be
 *    applied for the item quality update
 */
public class ThresholdedCompositeStrategy implements QualityUpdateStrategy {

    /**
     * ThresholdStrategy is a helper class for ThresholdedCompositeStrategy
     * that holds a threshold value and a quality update strategy
     */
    public static class ThresholdStrategy {
        /**
         * The upper bound of the sellIn value for this strategy to be applied
         */
        private int threshold;

        /**
         * The quality update strategy to be applied when the sellIn value is less than
         * or equal to the threshold
         */
        private QualityUpdateStrategy strategy;

        public ThresholdStrategy(int threshold, QualityUpdateStrategy strategy) {
            this.threshold = threshold;
            this.strategy = strategy;
        }
    }

    /**
     * The ordered list of thresholds and strategies
     * Must be in ascending order of threshold values 
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
