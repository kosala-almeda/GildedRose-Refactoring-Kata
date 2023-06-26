package com.gildedrose.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.gildedrose.behavior.quality.DecreasingQualityStrategy;
import com.gildedrose.behavior.quality.FixedQualityStrategy;
import com.gildedrose.behavior.quality.IncreasingQualityStrategy;
import com.gildedrose.behavior.quality.ThresholdedCompositeStrategy;
import com.gildedrose.behavior.quality.ThresholdedCompositeStrategy.ThresholdStrategy;

/**
 * Unit tests for {@link ItemType}.
 */
public class ItemTypeTest {

    @Test
    void testFromName_normal() {
        ItemType type = ItemType.fromName("foo");

        assertEquals(ItemType.NORMAL, type);

        assertEquals(false, type.isSpecial());
        assertEquals(false, type.isLegendary());

        assertEquals("StandardSellinStrategy", type.getSellInStrategy().getClass().getSimpleName());
        assertEquals("DecreasingQualityStrategy", type.getQualityStrategy().getClass().getSimpleName());

        assertEquals(1, ((DecreasingQualityStrategy) type.getQualityStrategy()).getStep());
    }

    @Test
    void testFromName_agedBrie() {
        ItemType type = ItemType.fromName("Aged Brie");

        assertEquals(ItemType.AGED_BRIE, type);

        assertEquals(true, type.isSpecial());
        assertEquals(false, type.isLegendary());

        assertEquals("StandardSellinStrategy", type.getSellInStrategy().getClass().getSimpleName());
        assertEquals("IncreasingQualityStrategy", type.getQualityStrategy().getClass().getSimpleName());

        assertEquals(1, ((IncreasingQualityStrategy) type.getQualityStrategy()).getStep());
    }

    @Test
    void testFromName_sulfuras() {
        ItemType type = ItemType.fromName("Sulfuras, Hand of Ragnaros");

        assertEquals(ItemType.SULFURAS, type);

        assertEquals(true, type.isSpecial());
        assertEquals(true, type.isLegendary());

        assertEquals("LegendarySellinStrategy", type.getSellInStrategy().getClass().getSimpleName());
        assertEquals("NonChangingQualityStrategy", type.getQualityStrategy().getClass().getSimpleName());
    }

    @Test
    void testFromName_backstagePasses() {
        ItemType type = ItemType.fromName("Backstage passes to a TAFKAL80ETC concert");

        assertEquals(ItemType.BACKSTAGE_PASSES, type);

        assertEquals(true, type.isSpecial());
        assertEquals(false, type.isLegendary());

        assertEquals("StandardSellinStrategy", type.getSellInStrategy().getClass().getSimpleName());
        assertEquals("ThresholdedCompositeStrategy", type.getQualityStrategy().getClass().getSimpleName());

        List<ThresholdStrategy> strategies = ((ThresholdedCompositeStrategy) type.getQualityStrategy()).getStrategies();
        assertEquals(4, strategies.size());

        assertEquals(0, strategies.get(0).getThreshold());
        assert (strategies.get(0).getStrategy() instanceof FixedQualityStrategy);
        assertEquals(0, ((FixedQualityStrategy) strategies.get(0).getStrategy()).getQuality());

        assertEquals(5, strategies.get(1).getThreshold());
        assert (strategies.get(1).getStrategy() instanceof IncreasingQualityStrategy);
        assertEquals(3, ((IncreasingQualityStrategy) strategies.get(1).getStrategy()).getStep());

        assertEquals(10, strategies.get(2).getThreshold());
        assert (strategies.get(2).getStrategy() instanceof IncreasingQualityStrategy);
        assertEquals(2, ((IncreasingQualityStrategy) strategies.get(2).getStrategy()).getStep());

        assertEquals(Integer.MAX_VALUE, strategies.get(3).getThreshold());
        assert (strategies.get(3).getStrategy() instanceof IncreasingQualityStrategy);
        assertEquals(1, ((IncreasingQualityStrategy) strategies.get(3).getStrategy()).getStep());
    }

    @Test
    void testFromName_conjured() {
        ItemType type = ItemType.fromName("Conjured Mana Cake");

        assertEquals(ItemType.CONJURED, type);

        assertEquals(true, type.isSpecial());
        assertEquals(false, type.isLegendary());

        assertEquals("StandardSellinStrategy", type.getSellInStrategy().getClass().getSimpleName());
        assertEquals("DecreasingQualityStrategy", type.getQualityStrategy().getClass().getSimpleName());

        assertEquals(2, ((DecreasingQualityStrategy) type.getQualityStrategy()).getStep());
    }
}
