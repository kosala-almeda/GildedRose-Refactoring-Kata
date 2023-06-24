package com.gildedrose.inventory.sellin;

import com.gildedrose.Item;

public interface SellInUpdateStrategy {

    public static final int MIN_SELLIN = 0;
    public static final int NO_SELLIN = -1;

    void updateSellIn(Item item);
}
