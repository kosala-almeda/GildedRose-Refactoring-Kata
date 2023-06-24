package com.gildedrose.inventory;

import java.util.List;
import java.util.Map;

import com.gildedrose.Item;
import com.gildedrose.behavior.quality.DecreasingQualityStrategy;
import com.gildedrose.behavior.quality.FixedQualityStrategy;
import com.gildedrose.behavior.quality.IncreasingQualityStrategy;
import com.gildedrose.behavior.quality.NonChangingQualityStrategy;
import com.gildedrose.behavior.quality.QualityUpdateStrategy;
import com.gildedrose.behavior.quality.ThresholdedCompositeStrategy;
import com.gildedrose.behavior.sellin.LegendarySellinStrategy;
import com.gildedrose.behavior.sellin.SellInUpdateStrategy;
import com.gildedrose.behavior.sellin.StandardSellinStrategy;

/**
 * InventoryFactory is a factory class for wrapping Items and strategies into
 * InventoryItems
 */
public class InventoryFactory {

    // Quality strategy instances (one for each type)
    private Map<ItemType, QualityUpdateStrategy> qualityStrategies = Map.of(
            ItemType.AGED_BRIE, new IncreasingQualityStrategy(), // Aged Brie increases in quality one per day
            ItemType.SULFURAS, new NonChangingQualityStrategy(), // Sulfuras never changes quality
            ItemType.BACKSTAGE_PASSES, new ThresholdedCompositeStrategy(List.of( // Backstage passes quality chane
                    // Quality drops to 0 after concert
                    new ThresholdedCompositeStrategy.ThresholdStrategy(0, new FixedQualityStrategy(0)),
                    // Quality increases by 3 when sellIn is 5 or less
                    new ThresholdedCompositeStrategy.ThresholdStrategy(5, new IncreasingQualityStrategy()),
                    // Quality increases by 2 when sellIn is 10 or less
                    new ThresholdedCompositeStrategy.ThresholdStrategy(10, new IncreasingQualityStrategy()),
                    // Quality increases by 1 when sellIn is more than 10
                    new ThresholdedCompositeStrategy.ThresholdStrategy(Integer.MAX_VALUE,
                            new IncreasingQualityStrategy()))),
            ItemType.CONJURED, new DecreasingQualityStrategy(2), // Conjured items decrease in quality twice as fast
            ItemType.NORMAL, new DecreasingQualityStrategy()); // Normal items decrease in quality one per day

    /**
     * Creates an InventoryItem based on the item
     * with the correct strategies (the factory method)
     * 
     * @param item the item to wrap
     */
    public InventoryItem wrapItem(Item item) {

        // Get strategy based on item type
        ItemType type = ItemType.fromName(item.name);
        if (type.isSpecial()) {
            return wrapSpecialItem(item, type);
        } else {
            return wrapStandardItem(item);
        }

    }

    private InventoryItem wrapStandardItem(Item item) {
        // A normal item (quality decreases by 1, sellIn decreases by 1)
        SellInUpdateStrategy sellinStrategy = StandardSellinStrategy.getInstance();
        QualityUpdateStrategy qualityStrategy = qualityStrategies.get(ItemType.NORMAL);

        return new InventoryItem(item, sellinStrategy, qualityStrategy);
    }

    private InventoryItem wrapSpecialItem(Item item, ItemType type) {
        // A special item
        // sellin strategy depends on type being legendary or not
        SellInUpdateStrategy sellinStrategy;
        if (type.isLegendary()) {
            sellinStrategy = LegendarySellinStrategy.getInstance();
        } else {
            sellinStrategy = StandardSellinStrategy.getInstance();
        }
        // quality strategy depends on type
        QualityUpdateStrategy qualityStrategy = qualityStrategies.get(type);

        return new InventoryItem(item, sellinStrategy, qualityStrategy);
    }
}
