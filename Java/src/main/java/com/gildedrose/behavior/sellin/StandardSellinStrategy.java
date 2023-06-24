package com.gildedrose.behavior.sellin;

import com.gildedrose.Item;

/**
 * StandardSellinStrategy is a singleton class
 * This is the behvior/strategy for updating the sellIn value of a standard item
 */
public class StandardSellinStrategy implements SellInUpdateStrategy {

    private static StandardSellinStrategy instance;

    private StandardSellinStrategy() {
    }

    public static StandardSellinStrategy getInstance() {
        if (instance == null) {
            instance = new StandardSellinStrategy();
        }
        return instance;
    }

    @Override
    public void updateSellIn(Item item) {
        if (item.sellIn > MIN_SELLIN) {
            item.sellIn = item.sellIn - 1;
        }
    }
    
}
