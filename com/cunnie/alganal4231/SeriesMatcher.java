package com.cunnie.alganal4231;

public class SeriesMatcher {

    public static int matchSeries(int[] a0, int[] b0) {
        int[] a = new int[a0.length + 1];
        int[] b = new int[b0.length + 1];
        System.arraycopy(a0, 0, a, 1, a0.length);
        System.arraycopy(b0, 0, b, 1, b0.length);
        int[] f = new int[a.length];
        int minValue = Integer.MAX_VALUE;
        int i = 1;
        for (int j = 1; j <= b.length; j++) {
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
        for (int i = 1; i < a.length; i++) {
            summedDifference += Math.abs(a[i] - b[f[i]]);
        }
        return summedDifference;
    }


}
