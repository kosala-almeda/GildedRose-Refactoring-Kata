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

    /** Sulfauras legendary items: never change quality */
    SULFURAS(Pattern.compile("^Sulfuras.*"), new NonChangingQualityStrategy(), true),

    /** Backstage passes items: quality change is dynamic */
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

    private Pattern namePattern;
    private boolean legendary;
    private QualityUpdateStrategy qualityStrategy;

    private ItemType(Pattern namePattern, QualityUpdateStrategy qualityStrategy) {
        this(namePattern, qualityStrategy, false);
    }

    private ItemType(Pattern namePattern, QualityUpdateStrategy qualityStrategy, boolean legendary) {
        this.namePattern = namePattern;
        this.qualityStrategy = qualityStrategy;
        this.legendary = legendary;
    }

    public boolean isSpecial() {
        return !NORMAL.equals(this);
    }

    public boolean isLegendary() {
        return legendary;
    }

    public SellInUpdateStrategy getSellInStrategy() {
        return legendary ? LegendarySellinStrategy.getInstance() : StandardSellinStrategy.getInstance();
    }

    public QualityUpdateStrategy getQualityStrategy() {
        return qualityStrategy;
    }

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