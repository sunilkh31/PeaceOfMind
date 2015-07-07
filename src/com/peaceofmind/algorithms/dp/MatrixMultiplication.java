package com.peaceofmind.algorithms.dp;

/**
 * Given a sequence of matrices, find the most efficient way to multiply <br/>
 * these matrices together. The problem is not actually to perform the<br/>
 * multiplications, but merely to decide in which order to perform the <br/>
 * multiplications.
 * 
 * @author StarLord
 */
public class MatrixMultiplication {

    // Recursive Solution
    public static int minMatrixChainOrderRec(int[] matSizes, int start, int end) {
        if (start == end)
            return 0;

        int min = Integer.MAX_VALUE;
        int count = 0;
        for (int i = start; i < end; i++) {
            count = minMatrixChainOrderRec(matSizes, start, i)
                    + minMatrixChainOrderRec(matSizes, i + 1, end)
                    + matSizes[start - 1] * matSizes[i] * matSizes[end];
            if (count < min)
                min = count;
        }
        return min;
    }

    protected int[][] m;
    protected int[][] s;

    public int matrixChainOrder(int[] p) {
        int n = p.length - 1;
        m = new int[n][n];
        s = new int[n][n];

        for (int ii = 1; ii < n; ii++) {
            for (int i = 0; i < n - ii; i++) {
                int j = i + ii;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
        return m[0][n - 1];
    }

    public void printOptimalParenthesizations() {
        boolean[] inAResult = new boolean[s.length];
        printOptimalParenthesizations(s, 0, s.length - 1, inAResult);
    }

    void printOptimalParenthesizations(int[][] s, int i, int j, /* for pretty printing: */
            boolean[] inAResult) {
        if (i != j) {
            printOptimalParenthesizations(s, i, s[i][j], inAResult);
            printOptimalParenthesizations(s, s[i][j] + 1, j, inAResult);
            String istr = inAResult[i] ? "_result " : " ";
            String jstr = inAResult[j] ? "_result " : " ";
            System.out.println(" A_" + i + istr + "* A_" + j + jstr);
            inAResult[i] = true;
            inAResult[j] = true;
        }
    }

    public static void main(String[] args) {
        int[] matSizes = { 1, 2, 3, 4, 5, 6, 10 };
        System.out.println(minMatrixChainOrderRec(matSizes, 1,
                matSizes.length - 1));
        MatrixMultiplication mul = new MatrixMultiplication();
        System.out.println(mul.matrixChainOrder(matSizes));
        mul.printOptimalParenthesizations();
    }
}
