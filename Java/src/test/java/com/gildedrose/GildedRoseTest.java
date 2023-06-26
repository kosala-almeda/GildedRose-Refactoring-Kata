package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Feature test for the Gilded Rose kata.
 *  - Regression for {@link GildedRose}.
 */
class GildedRoseTest {

    /**
     * Test the attributes of multiple items after two days.
     */
    @Test
    void twoDaysBulkExisting() {
        // This testing is the same as the original TexttestFixture.main()
        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Item("Conjured Mana Cake", 3, 6) };
        
        GildedRose app = new GildedRose(items);
        app.updateQuality(); // Day 1
        app.updateQuality(); // Day 2

        // should decrease sellin by 2 and quality by 2 for normal item
        assertEquals("+5 Dexterity Vest", items[0].name);
        assertEquals(8, items[0].sellIn);
        assertEquals(18, items[0].quality);

        // should decrease sellin by 2 and increase quality by 2 for Aged Brie
        assertEquals("Aged Brie", items[1].name);
        assertEquals(0, items[1].sellIn);
        assertEquals(2, items[1].quality);

        // should decrease sellin by 2 and quality by 2 for normal item
        assertEquals("Elixir of the Mongoose", items[2].name);
        assertEquals(3, items[2].sellIn);
        assertEquals(5, items[2].quality);

        // should not change sellin and quality for Sulfuras (legendary item)
        assertEquals("Sulfuras, Hand of Ragnaros", items[3].name);
        assertEquals(0, items[3].sellIn);
        assertEquals(80, items[3].quality);

        // should not change sellin and quality for Sulfuras (legendary item)
        assertEquals("Sulfuras, Hand of Ragnaros", items[4].name);
        assertEquals(-1, items[4].sellIn);
        assertEquals(80, items[4].quality);

        // should decrease sellin by 2 and increase quality by 1+1 for Backstage passes
        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[5].name);
        assertEquals(13, items[5].sellIn);
        assertEquals(22, items[5].quality);

        // should decrease sellin by 2 and increase quality by 1+0 for Backstage passes
        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[6].name);
        assertEquals(8, items[6].sellIn);
        assertEquals(50, items[6].quality);

        // should decrease sellin by 2 and increase quality by 1+0 for Backstage passes
        assertEquals("Backstage passes to a TAFKAL80ETC concert", items[7].name);
        assertEquals(3, items[7].sellIn);
        assertEquals(50, items[7].quality);

        // should decrease sellin by 2 and quality by 4 for Conjured
        assertEquals("Conjured Mana Cake", items[8].name);
        assertEquals(1, items[8].sellIn);
        assertEquals(2, items[8].quality);
        
    }

}
