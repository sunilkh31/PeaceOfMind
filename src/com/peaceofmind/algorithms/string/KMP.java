/**
 * 
 */
package com.peaceofmind.algorithms.string;

/**
 * @author StarLord
 *
 */
public class KMP {

    public static int search(String pattern, String text) {
        int ptSize = pattern.length();
        int tSize = text.length();

        int[] lps = calculateLps(pattern);

        for (int i = 0, j = 0; i < tSize;) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == ptSize) {
                return i - j;
            } else if (i < tSize && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return -1;
    }

    private static int[] calculateLps(String pattern) {
        int[] lps = new int[pattern.length()];
        for (int i = 1, len = 0; i < pattern.length();) {
            if (pattern.charAt(len) == pattern.charAt(i)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static void main(String[] args) {
        String pattern = "AAAAB";
        String text = "AAAAAAAAAAAAAAAAAB";
        int begin = search(pattern, text);
        System.out.println(begin);
        System.out.println(text.substring(begin, begin + pattern.length()));
    }
}
