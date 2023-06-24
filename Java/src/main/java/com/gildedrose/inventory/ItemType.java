package com.gildedrose.inventory;

import java.util.regex.Pattern;

/**
 * The type of an item.
 */
public enum ItemType {

    NORMAL(null),
    AGED_BRIE(Pattern.compile("^Aged Brie.*")),
    SULFURAS(Pattern.compile("^Sulfuras.*")),
    BACKSTAGE_PASSES(Pattern.compile("^Backstage passes.*")),
    CONJURED(Pattern.compile("^Conjured.*"));

    private Pattern namePattern;

    private ItemType(Pattern namePattern) {
        this.namePattern = namePattern;
    }

    public boolean isSpecial() {
        return !NORMAL.equals(this);
    }

    public boolean isLegendary() {
        return SULFURAS.equals(this);
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