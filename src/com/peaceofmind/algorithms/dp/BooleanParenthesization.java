/**
 * 
 */
package com.peaceofmind.algorithms.dp;

/**
 * Given a boolean expression with following symbols.
 * 
 * Symbols 'T' ---> true 'F' ---> false And following operators filled between symbols
 * 
 * Operators & ---> boolean AND | ---> boolean OR ^ ---> boolean XOR Count the number of ways we can parenthesize the
 * expression so that the value of expression evaluates to true.
 * 
 * @author StarLord
 *
 */
public class BooleanParenthesization {

    public static int countParenthesis(char[] symbols, char[] operator) {
        int N = symbols.length;

        int[][] True = new int[N][N];
        int[][] False = new int[N][N];

        for (int i = 0; i < N; i++) {
            if (symbols[i] == 'T')
                True[i][i] = 1;
            if (symbols[i] == 'F')
                False[i][i] = 1;
        }

        for (int gap = 1; gap < N; gap++) {
            for (int i = 0, j = gap; j < N; i++, j++) {
                True[i][j] = False[i][j] = 0;
                for (int g = 0; g < gap; g++) {
                    int k = i + g;
                    int tik = True[i][k] + False[i][k];
                    int tkj = True[k + 1][j] + False[k + 1][j];

                    if (operator[k] == '&') {
                        True[i][j] += True[i][k] * True[k + 1][j];
                        False[i][j] += (tik * tkj - True[i][k] * True[k + 1][j]);
                    }
                    if (operator[k] == '|') {
                        True[i][j] += (tik * tkj - False[i][k]
                                * False[k + 1][j]);
                        False[i][j] += False[i][k] * False[k + 1][j];
                    }
                    if (operator[k] == '^') {
                        True[i][j] += True[i][k] * False[k + 1][j]
                                + False[i][k] * True[k + 1][j];
                        False[i][j] += True[i][k] * True[k + 1][j]
                                + False[i][k] * False[k + 1][j];
                    }
                }
            }
        }
        return True[0][N - 1];
    }

    public static void main(String[] args) {
        char[] symbols = { 'T', 'T', 'F', 'T' };
        char[] operator = { '|', '&', '^' };
        System.out.println(countParenthesis(symbols, operator));
    }
}
