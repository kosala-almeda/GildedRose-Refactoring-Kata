package com.gildedrose.behavior.sellin;

import com.gildedrose.Item;

/**
 * StandardSellinStrategy is a singleton class
 * This is the behvior/strategy for updating the sellIn value of a standard item
 */
public class StandardSellinStrategy implements SellInUpdateStrategy {

    /**
     * The singleton instance
     */
    private static StandardSellinStrategy instance;

    /**
     * Private constructor for singleton
     */
    private StandardSellinStrategy() {
    }

    /**
     * @return the StandardSellinStrategy instance
     */
    public static StandardSellinStrategy getInstance() {
        if (instance == null) {
            instance = new StandardSellinStrategy();
        }
        return instance;
    }

    @Override
    /**
     * Standard items decrease sellIn value by 1
     */
    public void updateSellIn(Item item) {
        if (item.sellIn > MIN_SELLIN) {
            item.sellIn = item.sellIn - 1;
        }
    }
    
}
