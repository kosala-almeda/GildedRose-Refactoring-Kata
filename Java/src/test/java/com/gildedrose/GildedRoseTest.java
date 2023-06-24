package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Feature test for the Gilded Rose kata.
 *  - Regression
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
        assertEquals("+5 Dexterity Vest, 8, 18", items[0].toString());

        // should decrease sellin by 2 and increase quality by 2 for Aged Brie
        assertEquals("Aged Brie, 0, 2", items[1].toString());

        // should decrease sellin by 2 and quality by 2 for normal item
        assertEquals("Elixir of the Mongoose, 3, 5", items[2].toString());

        // should not change sellin and quality for Sulfuras (legendary item)
        assertEquals("Sulfuras, Hand of Ragnaros, 0, 80", items[3].toString());

        // should not change sellin and quality for Sulfuras (legendary item)
        assertEquals("Sulfuras, Hand of Ragnaros, -1, 80", items[4].toString());

        // should decrease sellin by 2 and increase quality by 1+1 for Backstage passes
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 13, 22", items[5].toString());

        // should decrease sellin by 2 and increase quality by 1+0 for Backstage passes
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 8, 50", items[6].toString());

        // should decrease sellin by 2 and increase quality by 1+0 for Backstage passes
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 3, 50", items[7].toString());

        // should decrease sellin by 2 and quality by 4 for Conjured
        assertEquals("Conjured Mana Cake, 1, 2", items[8].toString());
        
    }

}
