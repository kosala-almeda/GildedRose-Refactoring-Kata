package com.gildedrose.inventory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gildedrose.Item;
import com.gildedrose.behavior.strategy.quality.DecreasingQualityStrategy;
import com.gildedrose.behavior.strategy.quality.FixedQualityStrategy;
import com.gildedrose.behavior.strategy.quality.ThresholdedCompositeStrategy;
import com.gildedrose.inventory.sellin.LegenderySellinStrategy;
import com.gildedrose.inventory.sellin.StandardSellinStrategy;
import com.gildedrose.behavior.strategy.quality.IncreasingQualityStrategy;
import com.gildedrose.behavior.strategy.quality.NonChangingQualityStrategy;
import com.gildedrose.behavior.strategy.quality.QualityUpdateStrategy;

public class InventoryFactory {

    private static QualityUpdateStrategy standardItemStrategy;
    private static QualityUpdateStrategy agedBrieStrategy;
    private static QualityUpdateStrategy sulfurasStrategy;
    private static QualityUpdateStrategy backstagePassesStrategy;
    private static QualityUpdateStrategy conjuredStrategy;

    /**
     * Creates an InventoryItem based on the item
     * with the correct strategies
     */
    public static InventoryItem wrapItem(Item item) {

        if (item.name.startsWith("Aged Brie")) {
            return wrapAgedBrieItem(item);
        } else if (item.name.startsWith("Sulfuras")) {
            return wrapSulfurasItem(item);
        } else if (item.name.startsWith("Backstage passes")) {
            return wrapBackstagePassesItem(item);
        } else if (item.name.startsWith("Conjured")) {
            return wrapConjuredItem(item);
        } else {
            return wrapStandardItem(item);
        }
    }

    private static InventoryItem wrapStandardItem(Item item) {
        if(standardItemStrategy == null) {
            standardItemStrategy = new DecreasingQualityStrategy();
        }
        return new InventoryItem(item, StandardSellinStrategy.getInstance(), standardItemStrategy);
    }

    private static InventoryItem wrapAgedBrieItem(Item item) {
        if(agedBrieStrategy == null) {
            agedBrieStrategy = new IncreasingQualityStrategy();
        }
        return new InventoryItem(item, StandardSellinStrategy.getInstance(), agedBrieStrategy);
    }

    private static InventoryItem wrapSulfurasItem(Item item) {
        if(sulfurasStrategy == null) {
            sulfurasStrategy = new NonChangingQualityStrategy();
        }
        return new InventoryItem(item, LegenderySellinStrategy.getInstance(), sulfurasStrategy);
    }

    private static InventoryItem wrapBackstagePassesItem(Item item) {
        if(backstagePassesStrategy == null) {
            List<ThresholdedCompositeStrategy.ThresholdStrategy> strategies = List.of(
                new ThresholdedCompositeStrategy.ThresholdStrategy(0, new FixedQualityStrategy(0)),
                new ThresholdedCompositeStrategy.ThresholdStrategy(5, new IncreasingQualityStrategy(3)),
                new ThresholdedCompositeStrategy.ThresholdStrategy(10, new IncreasingQualityStrategy(2)),
                new ThresholdedCompositeStrategy.ThresholdStrategy(Integer.MAX_VALUE, new IncreasingQualityStrategy())
            );
            backstagePassesStrategy = new ThresholdedCompositeStrategy(strategies);
        }
        return new InventoryItem(item, StandardSellinStrategy.getInstance(), backstagePassesStrategy);
    }

    private static InventoryItem wrapConjuredItem(Item item) {
        if(conjuredStrategy == null) {
            conjuredStrategy = new DecreasingQualityStrategy(2);
        }
        return new InventoryItem(item, StandardSellinStrategy.getInstance(), conjuredStrategy);
    }
}
