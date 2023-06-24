package com.gildedrose.inventory;

import com.gildedrose.Item;
import com.gildedrose.behavior.strategy.quality.QualityUpdateStrategy;
import com.gildedrose.behavior.strategy.sellin.SellInUpdateStrategy;

/**
 * InventoryItem is a wrapper for Item that contains the strategies for updating
 * Item is a class from the legacy code that we cannot change
 * We need to wrap it in order to add the strategies
 */
public class InventoryItem {

    /**
     * item is the original item from the legacy code
     */
    private Item item;
    /**
     * sellInUpdateStrategy is the strategy for updating the sellIn value of an item
     */
    private SellInUpdateStrategy sellInUpdateStrategy;
    /**
     * qualityUpdateStrategy is the strategy for updating the quality value of an item
     */
    private QualityUpdateStrategy qualityUpdateStrategy;

    public InventoryItem(Item item, SellInUpdateStrategy sellInUpdateStrategy,
            QualityUpdateStrategy qualityUpdateStrategy) {
        this.item = item;
        this.sellInUpdateStrategy = sellInUpdateStrategy;
        this.qualityUpdateStrategy = qualityUpdateStrategy;
    }

    /**
     * Update the item
     */
    public void update() {
        // Update quality
        qualityUpdateStrategy.updateQuality(item);
        // Update sellIn
        sellInUpdateStrategy.updateSellIn(item);
    }

    public Item getItem() {
        return item;
    }

    public SellInUpdateStrategy getSellInUpdateStrategy() {
        return sellInUpdateStrategy;
    }

    public QualityUpdateStrategy getQualityUpdateStrategy() {
        return qualityUpdateStrategy;
    }

}
