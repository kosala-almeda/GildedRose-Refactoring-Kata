package com.gildedrose.inventory;

import java.util.List;
import com.gildedrose.Item;
import com.gildedrose.behavior.strategy.quality.DecreasingQualityStrategy;
import com.gildedrose.behavior.strategy.quality.FixedQualityStrategy;
import com.gildedrose.behavior.strategy.quality.ThresholdedCompositeStrategy;
import com.gildedrose.behavior.strategy.sellin.LegenderySellinStrategy;
import com.gildedrose.behavior.strategy.sellin.StandardSellinStrategy;
import com.gildedrose.behavior.strategy.quality.IncreasingQualityStrategy;
import com.gildedrose.behavior.strategy.quality.NonChangingQualityStrategy;
import com.gildedrose.behavior.strategy.quality.QualityUpdateStrategy;

/**
 * InventoryFactory is a factory class for wrapping Items and strategies into
 * InventoryItems
 */
public class InventoryFactory {

    // Items types (names start with with these)
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras";
    public static final String BACKSTAGE_PASSES = "Backstage passes";
    public static final String CONJURED = "Conjured";

    // Quality strategy instances (singleton style)
    private static QualityUpdateStrategy standardItemStrategy;
    private static QualityUpdateStrategy agedBrieStrategy;
    private static QualityUpdateStrategy sulfurasStrategy;
    private static QualityUpdateStrategy backstagePassesStrategy;
    private static QualityUpdateStrategy conjuredStrategy;

    /**
     * Creates an InventoryItem based on the item
     * with the correct strategies (the factory method)
     * @param item the item to wrap
     */
    public static InventoryItem wrapItem(Item item) {

        if (item.name.startsWith(AGED_BRIE)) {
            return wrapAgedBrieItem(item);
        } else if (item.name.startsWith(SULFURAS)) {
            return wrapSulfurasItem(item);
        } else if (item.name.startsWith(BACKSTAGE_PASSES)) {
            return wrapBackstagePassesItem(item);
        } else if (item.name.startsWith(CONJURED)) {
            return wrapConjuredItem(item);
        } else {
            return wrapStandardItem(item);
        }
    }

    private static InventoryItem wrapStandardItem(Item item) {
        if (standardItemStrategy == null) {
            standardItemStrategy = new DecreasingQualityStrategy();
        }
        return new InventoryItem(item, StandardSellinStrategy.getInstance(), standardItemStrategy);
    }

    private static InventoryItem wrapAgedBrieItem(Item item) {
        if (agedBrieStrategy == null) {
            agedBrieStrategy = new IncreasingQualityStrategy();
        }
        return new InventoryItem(item, StandardSellinStrategy.getInstance(), agedBrieStrategy);
    }

    private static InventoryItem wrapSulfurasItem(Item item) {
        if (sulfurasStrategy == null) {
            sulfurasStrategy = new NonChangingQualityStrategy();
        }
        return new InventoryItem(item, LegenderySellinStrategy.getInstance(), sulfurasStrategy);
    }

    private static InventoryItem wrapBackstagePassesItem(Item item) {
        if (backstagePassesStrategy == null) {
            List<ThresholdedCompositeStrategy.ThresholdStrategy> strategies = List.of(
                    new ThresholdedCompositeStrategy.ThresholdStrategy(0, new FixedQualityStrategy(0)),
                    new ThresholdedCompositeStrategy.ThresholdStrategy(5, new IncreasingQualityStrategy(3)),
                    new ThresholdedCompositeStrategy.ThresholdStrategy(10, new IncreasingQualityStrategy(2)),
                    new ThresholdedCompositeStrategy.ThresholdStrategy(Integer.MAX_VALUE,
                            new IncreasingQualityStrategy()));
            backstagePassesStrategy = new ThresholdedCompositeStrategy(strategies);
        }
        return new InventoryItem(item, StandardSellinStrategy.getInstance(), backstagePassesStrategy);
    }

    private static InventoryItem wrapConjuredItem(Item item) {
        if (conjuredStrategy == null) {
            conjuredStrategy = new DecreasingQualityStrategy(2);
        }
        return new InventoryItem(item, StandardSellinStrategy.getInstance(), conjuredStrategy);
    }
}
