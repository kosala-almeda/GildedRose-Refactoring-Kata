package com.gildedrose.behavior.strategy.sellin;

import com.gildedrose.Item;

/**
 * LegenderySellinStrategy is a singleton class
 * This is the behvior/strategy for updating the sellIn value of a legendary item
 */
public class LegenderySellinStrategy implements SellInUpdateStrategy {

    private static LegenderySellinStrategy instance;

    private LegenderySellinStrategy() {
    }

    public static LegenderySellinStrategy getInstance() {
        if (instance == null) {
            instance = new LegenderySellinStrategy();
        }
        return instance;
    }

    @Override
    public void updateSellIn(Item item) {
        // Do nothing
    }
    
}
