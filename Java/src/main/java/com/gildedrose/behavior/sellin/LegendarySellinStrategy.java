package com.gildedrose.behavior.sellin;

import com.gildedrose.Item;

/**
 * LegenderySellinStrategy is a singleton class
 * This is the behvior/strategy for updating the sellIn value of a legendary item
 */
public class LegendarySellinStrategy implements SellInUpdateStrategy {

    private static LegendarySellinStrategy instance;

    private LegendarySellinStrategy() {
    }

    public static LegendarySellinStrategy getInstance() {
        if (instance == null) {
            instance = new LegendarySellinStrategy();
        }
        return instance;
    }

    @Override
    public void updateSellIn(Item item) {
        // Do nothing
    }
    
}
