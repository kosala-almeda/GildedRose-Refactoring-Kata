package com.gildedrose.inventory.sellin;

import com.gildedrose.Item;

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
        item.sellIn = NO_SELLIN;
    }
    
}
