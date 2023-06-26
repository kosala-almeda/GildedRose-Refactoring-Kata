package com.gildedrose.behavior.quality;

import java.util.List;

import com.gildedrose.Item;
import com.gildedrose.util.validators.ParameterValidator;

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

        /**
         * Constructor with threshold and strategy
         * @param threshold the upper bound of the sellIn value for this strategy to be applied
         * @param strategy the quality update strategy to be applied for threshold
         */
        public ThresholdStrategy(int threshold, QualityUpdateStrategy strategy) {
            this.threshold = threshold;
            this.strategy = strategy;
        }

        public int getThreshold() {
            return this.threshold;
        }

        public QualityUpdateStrategy getStrategy() {
            return this.strategy;
        }
    }

    /**
     * The ordered list of thresholds and strategies
     * Must be in ascending order of threshold values 
     */
    private List<ThresholdStrategy> strategies;

    /**
     * Constructor with strategies
     * @param strategies the ordered list of thresholds and strategies
     */
    public ThresholdedCompositeStrategy(List<ThresholdStrategy> strategies) {
        ParameterValidator.validateNotEmptyNoNullElements(strategies, null);
        // Check the validity of the thresholds
        checkThresholds(strategies); // TODO: Have its own validator
        this.strategies = strategies;
    }

    @Override
    /**
     * Update the quality of an item using the first strategy that has a threshold value
     * greater than or equal to the sellIn value
     * @param item the item to update
     */
    public void updateQuality(Item item) {
        // Find the first strategy that has a threshold value greater than or equal to the sellIn value
        for (ThresholdStrategy thresholdStrategy : strategies) {
            if (item.sellIn <= thresholdStrategy.threshold) {
                thresholdStrategy.strategy.updateQuality(item);
                break;
            }
        }
    }

    /**
     * @return the ordered list of thresholds and strategies
     */
    public List<ThresholdStrategy> getStrategies() {
        return this.strategies;
    }

    private void checkThresholds(List<ThresholdStrategy> strategies) {
        // Check if the thresholds are in ascending order
        int previousThreshold = Integer.MIN_VALUE;
        for (ThresholdStrategy thresholdStrategy : strategies) {
            // Check the validity of the threshold
            if (thresholdStrategy.threshold <= previousThreshold) {
                throw new IllegalArgumentException("The thresholds must be in ascending order");
            }
            previousThreshold = thresholdStrategy.threshold;
        }
    }

}
