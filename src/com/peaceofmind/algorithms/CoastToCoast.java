package com.peaceofmind.algorithms;

import java.util.ArrayList;

public class CoastToCoast {

    public static boolean[][] coastToCoast(int[][] a) {
        int n = a.length;
        int m = a[0].length;

        boolean[][] pathToWest = new boolean[n][m];
        pathToWest[0][0] = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i != 0 && j != 0)
                    pathToWest[i][j] = false;
                if (i > 0 && pathToWest[i - 1][j] && a[i][j] >= a[i - 1][j]) {
                    pathToWest[i][j] = true;
                }
                if (j > 0 && pathToWest[i][j - 1] && a[i][j] >= a[i][j - 1]) {
                    pathToWest[i][j] = true;
                }
            }
        }

        boolean[][] pathToEast = new boolean[n][m];
        pathToEast[n - 1][m - 1] = true;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {

                if (i != n - 1 && j != m - 1)
                    pathToEast[i][j] = false;
                if (i < n - 1 && pathToEast[i + 1][j] && a[i][j] >= a[i + 1][j]) {
                    pathToEast[i][j] = true;
                }
                if (j < m - 1 && pathToEast[i][j + 1] && a[i][j] >= a[i][j + 1]) {
                    pathToEast[i][j] = true;
                }
            }
        }

        // Combined path
        boolean[][] comb = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                comb[i][j] = pathToEast[i][j] & pathToWest[i][j];
            }
        }

        return comb;
    }

    public static int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        int[] total = new int[triangle.size()];
        int l = triangle.size() - 1;

        for (int i = 0; i < triangle.get(l).size(); i++) {
            total[i] = triangle.get(l).get(i);
        }

        // iterate from last second row
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i + 1).size() - 1; j++) {
                total[j] = triangle.get(i).get(j)
                        + Math.min(total[j], total[j + 1]);
            }
        }

        return total[0];
    }

    public static void main(String[] args) {
        ArrayList<Integer> val = new ArrayList<>();
        val.add(3);
        ArrayList<ArrayList<Integer>> in = new ArrayList<>();
        in.add(val);
        System.out.println(minimumTotal(in));
    }

    private static final int[][] neighbours = { { -1, 0, 0, 1 },
            { 0, -1, 1, 0 }, };

    public static boolean[][] bothCoastsPositions(int[][] a) {
        int n = a.length;
        int m = (n == 0) ? 0 : a[0].length;
        boolean[][] b = new boolean[n][m];
        if (n == 0 || m == 0) {
            return b;
        }
        boolean[][] east = wave(a, n, m, 0, 0);
        boolean[][] west = wave(a, n, m, n - 1, m - 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                b[i][j] = east[i][j] && west[i][j];
            }
        }
        return b;
    }

    static boolean[][] wave(int[][] a, int n, int m, int i0, int j0) {
        int[][] queue = new int[2][n * m];
        boolean[][] b = new boolean[n][m];
        int qsize = 0;
        for (int j = 0; j < m; j++) {
            b[i0][j] = true;
            queue[0][qsize] = i0;
            queue[1][qsize] = j;
            qsize++;
        }
        for (int i = 0; i < n; i++) {
            if (!b[i][j0]) {
                b[i][j0] = true;
                queue[0][qsize] = i;
                queue[1][qsize] = j0;
                qsize++;
            }
        }
        for (int k = 0; k < qsize; k++) {
            int i = queue[0][k];
            int j = queue[1][k];
            for (int d = 0; d < 4; d++) {
                int ni = i + neighbours[0][d];
                int nj = j + neighbours[1][d];
                if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
                    if (!b[ni][nj] && a[i][j] <= a[ni][nj]) {
                        b[ni][nj] = true;
                        queue[0][qsize] = ni;
                        queue[1][qsize] = nj;
                        qsize++;
                    }
                }
            }
        }
        return b;
    }
}
