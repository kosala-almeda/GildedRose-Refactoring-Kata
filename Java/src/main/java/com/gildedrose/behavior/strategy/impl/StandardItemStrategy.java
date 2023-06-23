package com.gildedrose.behavior.strategy.impl;

import com.gildedrose.Item;
import com.gildedrose.behavior.strategy.QualityUpdateStrategy;
import com.gildedrose.behavior.strategy.SellInUpdateStrategy;

public class StandardItemStrategy implements QualityUpdateStrategy, SellInUpdateStrategy {

    @Override
    public void updateQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    @Override
    public void updateSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

}
