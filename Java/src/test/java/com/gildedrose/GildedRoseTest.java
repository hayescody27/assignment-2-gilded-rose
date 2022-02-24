package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void testSellInAndQualityDegradesBy1() {
        Item testItem = new Item("+5 Dexterity Vest", 10, 20);

        runTest(testItem);

        assertEquals(9, testItem.sellIn);
        assertEquals(19, testItem.quality);
    }

    @Test
    void testQualityDegradationPastSellBy() {
        Item testItem = new Item("+5 Dexterity Vest", 0, 20);

        runTest(testItem);

        assertEquals(-1, testItem.sellIn);
        assertEquals(18, testItem.quality);
    }

    @Test
    void testItemQualityGreaterThanOrEqualTo0() {
        Item testItem = new Item("+5 Dexterity Vest", 0, 0);

        runTest(testItem);

        assertEquals(-1, testItem.sellIn);
        assertEquals(0, testItem.quality);
    }

    @Test
    void testAgedBrie_QualityIncreasesNormally() {
        Item testItem = new Item("Aged Brie", 1, 10);

        runTest(testItem);

        assertEquals(0, testItem.sellIn);
        assertEquals(11, testItem.quality);
    }

    @Test
    void testAgedBrie_QualityIncreasesDoubleWhenPastSellBuy() {
        Item testItem = new Item("Aged Brie", -3, 10);

        runTest(testItem);

        assertEquals(-4, testItem.sellIn);
        assertEquals(12, testItem.quality);
    }

    @Test
    void testItemQualityDoesNotIncreasePast50(){
        Item testItem = new Item("Aged Brie", 0, 51);

        runTest(testItem);

        assertEquals(-1, testItem.sellIn);
        assertEquals(51, testItem.quality);

        Item testItem2 = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49);

        runTest(testItem2);

        assertEquals(9, testItem2.sellIn);
        assertEquals(50, testItem2.quality);

        Item testItem3 = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48);

        runTest(testItem3);

        assertEquals(4, testItem3.sellIn);
        assertEquals(50, testItem3.quality);
    }

    @Test
    void testSulfurasValuesDoNotChange(){
        Item testItem = new Item("Sulfuras, Hand of Ragnaros", 0, 80);

        runTest(testItem);

        assertEquals(0, testItem.sellIn);
        assertEquals(80, testItem.quality);

        Item testItem2 = new Item("Sulfuras, Hand of Ragnaros", -1, 80);

        runTest(testItem2);

        assertEquals(-1, testItem2.sellIn);
        assertEquals(80, testItem2.quality);
    }

    @Test
    void testBackstagePasses_increasesNormallyWhenGreaterThan10() {
        Item testItem = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 5);

        runTest(testItem);

        assertEquals(10, testItem.sellIn);
        assertEquals(6, testItem.quality);
    }

    @Test
    void testBackstagePasses_increasesBy2WhenLessThanOrEqualTo10() {
        Item testItem = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 5);

        runTest(testItem);

        assertEquals(9, testItem.sellIn);
        assertEquals(7, testItem.quality);
    }

    @Test
    void testBackstagePasses_increasesBy3WhenLessThanOrEqualTo5() {
        Item testItem = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 5);

        runTest(testItem);

        assertEquals(4, testItem.sellIn);
        assertEquals(8, testItem.quality);
    }

    @Test
    void testBackstagePasses_dropTo0AfterConcert(){

        Item testItem = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 15);

        runTest(testItem);

        assertEquals(-1, testItem.sellIn);
        assertEquals(0, testItem.quality);
    }

    @Test
    void conjuredItemsQualityDecreaseTwiceAsFast() {
        Item testItem = new Item("Conjured Mana Cake", 2, 15);

        runTest(testItem);

        assertEquals(1, testItem.sellIn);
        assertEquals(13, testItem.quality);

        Item testItem2 = new Item("Conjured Mana Cake", 0, 15);

        runTest(testItem2);

        assertEquals(-1, testItem2.sellIn);
        assertEquals(11, testItem2.quality);
    }

    private void runTest(Item... items) {
        GildedRose gr = new GildedRose(items);
        gr.updateQuality();
    }

}
