package com.gildedrose.inventory;

import java.util.List;
import java.util.regex.Pattern;

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
 * The type of an item.
 */
public enum ItemType {

    /** Regular items: quality decreases by 1 per day */
    NORMAL(null, new DecreasingQualityStrategy()),

    /** Aged brie items: quality increases by 1 per day */
    AGED_BRIE(Pattern.compile("^Aged Brie.*"), new IncreasingQualityStrategy()),

    /** Sulfuras legendary items: never change quality */
    SULFURAS(Pattern.compile("^Sulfuras.*"), new NonChangingQualityStrategy(), true),

    /** Backstage passes items:
     *  - quality increases by 1 per day when sellIn is more than 10
     *  - quality increases by 2 per day when sellIn is 10 or less
     *  - quality increases by 3 per day when sellIn is 5 or less
     *  - quality drops to 0 after concert
     */
    BACKSTAGE_PASSES(Pattern.compile("^Backstage passes.*"), new ThresholdedCompositeStrategy(List.of(
            // Quality drops to 0 after concert
            new ThresholdedCompositeStrategy.ThresholdStrategy(0, new FixedQualityStrategy(0)),
            // Quality increases by 3 when sellIn is 5 or less
            new ThresholdedCompositeStrategy.ThresholdStrategy(5, new IncreasingQualityStrategy(3)),
            // Quality increases by 2 when sellIn is 10 or less
            new ThresholdedCompositeStrategy.ThresholdStrategy(10, new IncreasingQualityStrategy(2)),
            // Quality increases by 1 when sellIn is more than 10
            new ThresholdedCompositeStrategy.ThresholdStrategy(Integer.MAX_VALUE, new IncreasingQualityStrategy(1))))),

    /** Conjured items: decrease in quality twice as fast */
    CONJURED(Pattern.compile("^Conjured.*"), new DecreasingQualityStrategy(2));

    /** The name pattern. (regex) */
    private Pattern namePattern;

    /** The legendary flag. */
    private boolean legendary;

    /** The quality strategy. */
    private QualityUpdateStrategy qualityStrategy;

    private ItemType(Pattern namePattern, QualityUpdateStrategy qualityStrategy) {
        this(namePattern, qualityStrategy, false);
    }

    private ItemType(Pattern namePattern, QualityUpdateStrategy qualityStrategy, boolean legendary) {
        this.namePattern = namePattern;
        this.qualityStrategy = qualityStrategy;
        this.legendary = legendary;
    }

    /**
     * @return true, if is special (not normal) item type.
     */
    public boolean isSpecial() {
        return !NORMAL.equals(this);
    }

    /**
     * @return true, if is legendary item type.
     */
    public boolean isLegendary() {
        return legendary;
    }

    /**
     * @return the sellIn strategy for the type
     */
    public SellInUpdateStrategy getSellInStrategy() {
        return legendary ? LegendarySellinStrategy.getInstance() : StandardSellinStrategy.getInstance();
    }

    /**
     * @return the quality strategy for the type
     */
    public QualityUpdateStrategy getQualityStrategy() {
        return qualityStrategy;
    }

    /**
     * Gets the item type from name.
     * - If the name matches the regex of a special item type, return that type
     * - Otherwise, return NORMAL
     * 
     * @param name the name
     * @return the item type
     */
    public static ItemType fromName(String name) {
        for (ItemType iType : ItemType.values()) {
            // match regex
            if (iType.namePattern != null && iType.namePattern.matcher(name).matches()) {
                return iType;
            }
        }
        return NORMAL;
    }

}