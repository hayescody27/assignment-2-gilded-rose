package com.gildedrose;

class GildedRose {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String CONJURED_PREFIX = "Conjured";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            handleNormalQualityChange(item);

            if (!item.name.equals(SULFURAS)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                handleQualityChangePastSellBy(item);
            }
        }
    }

    private void handleNormalQualityChange(Item item) {
        if (!AGED_BRIE.equals(item.name) && !item.name.equals(BACKSTAGE_PASSES)) {
            if (item.quality > 0 && !item.name.equals(SULFURAS)) {
                calculateQualityChangeFactor(item);
            }
        } else {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if(item.name.equals(BACKSTAGE_PASSES)) {
                    handleBackStagePassesQuality(item);
                }
            }
        }
    }

    private void handleBackStagePassesQuality(Item item) {
        if (item.sellIn < 11 && item.quality < 50) {
            item.quality = item.quality + 1;
        }

        if (item.sellIn < 6 && item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private void handleQualityChangePastSellBy(Item item) {
        if (!item.name.equals(AGED_BRIE)) {
            if (!item.name.equals(BACKSTAGE_PASSES)) {
                if (item.quality > 0 && !item.name.equals(SULFURAS)) {
                    calculateQualityChangeFactor(item);
                }
            } else {
                item.quality = 0;
            }
        } else {
            if (item.quality < 50) {
                item.quality = item.quality + 1;
            }
        }
    }

    private void calculateQualityChangeFactor(Item item) {
        item.quality = item.name.startsWith(CONJURED_PREFIX) ? item.quality - 2 : item.quality - 1;
    }
}