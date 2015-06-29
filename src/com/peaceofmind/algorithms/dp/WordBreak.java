/**
 * 
 */
package com.peaceofmind.algorithms.dp;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author StarLord
 *
 */
public class WordBreak {

    /* Word Break Problem - DP Solution */
    public static boolean canBreakIt(String sent, ArrayList<String> words) {
        HashSet<String> dict = new HashSet<>();
        dict.addAll(words);
        boolean[] canBreak = new boolean[sent.length() + 1];
        canBreak[0] = true;

        for (int i = 1; i <= sent.length(); i++) {
            String word = sent.substring(i);
            if (canBreak[i] == false && dict.contains(word)) {
                canBreak[i] = true;
            }

            if (i == sent.length() && canBreak[i])
                return true;

            if (canBreak[i]) {

                for (int j = i + 1; j <= sent.length(); j++) {
                    String otherWord = sent.substring(i, j);

                    if (!canBreak[j] && dict.contains(otherWord)) {
                        canBreak[j] = true;
                    }

                    if (j == sent.length() && canBreak[j])
                        return true;
                }
            }
        }
        return canBreak[sent.length()];
    }
}
