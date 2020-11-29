package com.cunnie.alganal4231;

import static org.junit.jupiter.api.Assertions.*;

import com.cunnie.alganal4231.SeriesMatcher;

class SeriesMatcherTest {

    @org.junit.jupiter.api.Test
    void testMatchSeriesOfficial() {
        int[] a = {4, 3, 8, 8, 1};
        int[] b = {3, 4, 8, 2};
        int c = SeriesMatcher.matchSeries(a, b);
        assertEquals(2, c);
    }

    @org.junit.jupiter.api.Test
    void testMatchSeries100() {
        int[] a = {100, 0, 0, 0, 0};
        int[] b = {0, 0, 0, 100};
        int c = SeriesMatcher.matchSeries(a, b);
        assertEquals(100, c);
    }

    @org.junit.jupiter.api.Test
    void testMatchSeriesTailMatch60() {
        int[] a = {100, 10, 20, 30};
        int[] b = {1, 2, 3, 100, 0};
        int c = SeriesMatcher.matchSeries(a, b);
        assertEquals(60, c);
    }

    @org.junit.jupiter.api.Test
    void testMatchSeriesStraightMatch() {
        int[] a = {1, 10, 11, 30};
        int[] b = {1, 10, 11, 30};
        int c = SeriesMatcher.matchSeries(a, b);
        assertEquals(0, c);
    }

    /*
        10 20 30
         \  \  \_________
          \  \_________  \
           \_________  \  \
                     \  \  \
        11 21 31 999 10 20 30
     */
    @org.junit.jupiter.api.Test
    void testMatchSeriesJaggedMatch() {
        int[] a = /***************/{10, 20, 30};
        int[] b = {11, 21, 31, 999, 10, 20, 30};
        int c = SeriesMatcher.matchSeries(a, b);
        assertEquals(0, c);
    }

    /*
        10 20 30 999
        |  |  |   |
        11 21 31 999 10 20 30
     */
    @org.junit.jupiter.api.Test
    void testMatchSeriesStraightMatch999() {
        int[] a = {10, 20, 30, 999};
        int[] b = {11, 21, 31, 999, 10, 20, 30};
        int c = SeriesMatcher.matchSeries(a, b);
        assertEquals(3, c);
    }

    @org.junit.jupiter.api.Test
    void testMatchSeriesJaggedMatch1020() {
        int[] a = /***********/{10, 20};
        int[] b = {11, 24, 999, 10, 20};
        int c = SeriesMatcher.matchSeries(a, b);
        assertEquals(0, c);
    }

    @org.junit.jupiter.api.Test
    void testMatchSeriesStraightMatch1020999() {
        int[] a = {10, 20, 999};
        int[] b = {11, 24, 999, 10, 20};
        int c = SeriesMatcher.matchSeries(a, b);
        assertEquals(5, c);
    }

    @org.junit.jupiter.api.Test
    void testMatchSeriesSraightMatch4410v10() {
        int[] a = {4, 4, 10};
        int[] b = {10};
        int c = SeriesMatcher.matchSeries(a, b);
        assertEquals(12, c);
    }

    @org.junit.jupiter.api.Test
    void testMatchSeriesSraightMatch4410v104() {
        int[] a = {4, 4, 10};
        int[] b = {10, 4};
        int c = SeriesMatcher.matchSeries(a, b);
        assertEquals(6, c);
    }

    @org.junit.jupiter.api.Test
    void testMatchSeriesSraightMatch4410v10410() {
        int[] a = {4, 4, 10};
        int[] b = {10, 4, 10};
        int c = SeriesMatcher.matchSeries(a, b);
        assertEquals(0, c);
    }
}