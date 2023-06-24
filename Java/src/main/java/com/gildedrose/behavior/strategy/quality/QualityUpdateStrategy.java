package com.gildedrose.behavior.strategy.quality;

import com.gildedrose.Item;

public interface QualityUpdateStrategy {

    static final int MAX_QUALITY = 50;
    static final int MIN_QUALITY = 0;

    void updateQuality(Item item);
}
