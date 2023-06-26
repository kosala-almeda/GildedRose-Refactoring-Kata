package com.gildedrose.behavior.sellin;

import com.gildedrose.Item;

/**
 * LegenderySellinStrategy is a singleton class
 * This is the behvior/strategy for updating the sellIn value of a legendary item
 */
public class LegendarySellinStrategy implements SellInUpdateStrategy {

    /**
     * The singleton instance
     */
    private static LegendarySellinStrategy instance;

    /**
     * Private constructor for singleton
     */
    private LegendarySellinStrategy() {
    }

    /**
     * @return the LegendarySellinStrategy instance
     */
    public static LegendarySellinStrategy getInstance() {
        if (instance == null) {
            instance = new LegendarySellinStrategy();
        }
        return instance;
    }

    @Override
    /**
     * Legendary items do not change sellIn value
     */
    public void updateSellIn(Item item) {
        // Do nothing
    }
    
}
