package com.gildedrose.inventory;

import java.util.Set;

import com.gildedrose.Item;
import com.gildedrose.behavior.strategy.quality.QualityUpdateStrategy;
import com.gildedrose.inventory.sellin.SellInUpdateStrategy;

/**
 * InventoryItem is a wrapper for Item that contains the strategies for updating
 * Item is a class from the legacy code that we cannot change
 * We need to wrap it in order to add the strategies
 */
public class InventoryItem {

    private Item item;
    private SellInUpdateStrategy sellInUpdateStrategy;
    private QualityUpdateStrategy qualityUpdateStrategy;

    public InventoryItem(Item item, SellInUpdateStrategy sellInUpdateStrategy, QualityUpdateStrategy qualityUpdateStrategy) {
        this.item = item;
        this.sellInUpdateStrategy = sellInUpdateStrategy;
        this.qualityUpdateStrategy = qualityUpdateStrategy;
    }

    public void update() {
        // Update quality
        qualityUpdateStrategy.updateQuality(item);
        // Update sellIn
       sellInUpdateStrategy.updateSellIn(item);
    }

    public Item getItem() {
        return item;
    }

    public QualityUpdateStrategy getQualityUpdateStrategy() {
        return qualityUpdateStrategy;
    }
    
}
