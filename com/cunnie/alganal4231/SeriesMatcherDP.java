package com.cunnie.alganal4231;

public class SeriesMatcherDP {
    static int[] a = null;  // 1-based a
    static int[] b = null;  // 1-based b
    static int[][] C = null;
    static int[][][] F = null;
    static int n;
    static int m;

    public static int matchSeries(int[] a0, int[] b0) {
        n = a0.length;
        m = b0.length;
        a = new int[a0.length + 1];
        b = new int[b0.length + 1];
        C = new int[a.length][];
        F = new int[a.length][][];
        // i is across.  j is down.
        for (int i = 1; i <= n; i++) {
            F[i] = new int[b.length][];
            C[i] = new int[b.length];
            C[i][0] = 0;
        }
        System.arraycopy(a0, 0, a, 1, a0.length);
        System.arraycopy(b0, 0, b, 1, a0.length);
        int minValue = Integer.MAX_VALUE;
        for (int j = 1; j <= m; j++) {
            int i = 1;
            C[i][j] = Math.abs(a[i] - b[j]);
            F[i][j] = new int[]{j};
        }
        for (int i = 2; i <= n; i++) {
            // new value to compare:  a[i]
            for (int j = 1; j <= m; j++) {
                int baseCost = C[i - 1][j];
                int[] fcurrent = new int[i + 1];
                int[] fmin = new int[i + 1];
                System.arraycopy(F[i][j - 1], 1, fcurrent, 1, F[i][j - 1].length);
                // new value to compare against:  b[j]
                fcurrent[i] = j;
                int currentCost = baseCost + Math.abs(a[i] - b[j]);
                int minCost = currentCost;
                System.arraycopy(fcurrent, 1, fmin, 1, fcurrent.length);
                // 1. Compare all the a values against b[j]
                for (int iprime = i - 1; iprime >= 1; iprime--) {
                    fcurrent[iprime] = j;
                    currentCost = cost1(a, fcurrent, b);
                    if (currentCost < minCost) {
                        minCost = currentCost;
                        System.arraycopy(fcurrent, 1, fmin, 1, fcurrent.length);
                    }
                }
                // compare a[i] back to b[1]
                for (int jprime = i - 1; jprime >= 1; jprime--) {
                    System.arraycopy(F[i][jprime], 1, fcurrent, 1, F[i][jprime].length);
                    for (int iprime = i; iprime <= jprime; i--) {
                        fcurrent[iprime] = jprime;
                        int cost = cost1(a, fcurrent, b);
                        if (cost < minCost) {
                            minCost = cost;
                            System.arraycopy(fcurrent, 1, fmin, 1, fcurrent.length);
                        }
                    }
                }
                F[i][j] = fmin;
            }
        }
        return C[a.length][b.length];
    }

    /*
            10 20 30  999
             |  |  |    |
    11       1 10 29 1017
    21       1  2 11  989
    31       1  2  3  971
    999      1  2  3    3
    10       0  2  3    3
    20       0  0  3    3
    30       0  0  0    3
     */

    private static int cost1(int[] a, int[] f, int[] b) {
        int summedDifference = 0;
        for (int i = 0; i < f.length; i++) {
            summedDifference += Math.abs(a[i] - b[f[i]]);
        }
        return summedDifference;
    }
}
