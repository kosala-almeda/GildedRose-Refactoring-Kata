package com.gildedrose.inventory.sellin;

import com.gildedrose.Item;

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
            item.sellIn--;
        }
    }
    
}
