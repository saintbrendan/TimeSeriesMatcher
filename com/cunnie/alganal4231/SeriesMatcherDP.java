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
        System.arraycopy(b0, 0, b, 1, b0.length);
        int minCost = Integer.MAX_VALUE;
        int[] minF = null;
        for (int j = 1; j <= m; j++) {
            int i = 1;
            int costij = Math.abs(a[i] - b[j]);
            if (costij < minCost) {
                minCost = costij;
                minF = new int[i + 1];
                minF[i] = j;
            }
            C[i][j] = minCost;
            F[i][j] = minF;
        }
        for (int i = 2; i <= n; i++) {
            // new value to compare:  a[i]
            for (int j = 1; j <= m; j++) {
                int baseCost = C[i - 1][j];
                int[] fcurrent = new int[i + 1];
                int[] fmin = new int[i + 1];
                System.arraycopy(F[i - 1][j], 1, fcurrent, 1, i - 1);
                // new value to compare against:  b[j]
                fcurrent[i] = j;
                int currentCost = baseCost + Math.abs(a[i] - b[j]);
                minCost = currentCost;
                System.out.println("fmin.length: " + fmin.length);
                System.arraycopy(fcurrent, 1, fmin, 1, i);
                // 1. Compare all the current a values against b[j]
                for (int iprime = i - 1; iprime >= 1; iprime--) {
                    fcurrent[iprime] = j;
                    currentCost = cost1(a, fcurrent, b);
                    if (currentCost < minCost) {
                        minCost = currentCost;
                        System.arraycopy(fcurrent, 1, fmin, 1, i);
                        System.out.println("fmin.length: " + fmin.length);
                        for (int f : fmin) {
                            System.out.println("f: " + f);
                        }
                    }
                }

                // compare a[i] back to b[1]
                for (int jprime = j - 1; jprime >= 1; jprime--) {
                    System.arraycopy(F[i][jprime], 1, fcurrent, 1, i - 1);
                    fcurrent[i] = jprime;
                    currentCost = cost1(a, fcurrent, b);
                    if (currentCost < minCost) {
                        minCost = currentCost;
                        System.arraycopy(fcurrent, 1, fmin, 1, i);
                        System.out.println("fmin.length: " + fmin.length);
                        for (int f : fmin) {
                            System.out.println("f: " + f);
                        }
                    }
                }

                C[i][j] = minCost;
                F[i][j] = fmin;
            }
        }
        return C[a.length - 1][b.length - 1];
    }

    private static int cost1(int[] a, int[] f, int[] b) {
        int summedDifference = 0;
        for (int i = 1; i < f.length; i++) {
            summedDifference += Math.abs(a[i] - b[f[i]]);
        }
        return summedDifference;
    }
}
