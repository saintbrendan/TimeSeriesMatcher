package com.cunnie.alganal4231;

public class SeriesMatcher {

    public static int matchSeries(int[] a, int[] b) {
        int[] f = new int[a.length];
        int minValue = Integer.MAX_VALUE;
        int i = 0;
        for (int j = 0; j < b.length; j++) {
            f[i] = j;
            int value = matchSeriesRec(a, f, i + 1, b);
            minValue = Math.min(minValue, value);
        }
        return minValue;
    }

    public static int matchSeriesRec(int[] a, int[] f, int i, int[] b) {
        if (i >= a.length) {
            return cost(a, f, b);
        }
        int minValue = Integer.MAX_VALUE;
        for (int j = f[i - 1]; j < b.length; j++) {
            f[i] = j;
            int value = matchSeriesRec(a, f, i + 1, b);
            minValue = Math.min(minValue, value);
        }

        return minValue;
    }

    public static int cost(int[] a, int[] f, int[] b) {
        int summedDifference = 0;
        for (int i = 0; i < a.length; i++) {
            summedDifference += Math.abs(a[i] - b[f[i]]);
        }
        return summedDifference;
    }


}
