/**
 * 
 */
package com.peaceofmind.algorithms;

import java.util.List;

/**
 * @author StarLord
 *
 */
public class SimpleQuestions {

    /* Houses with Gold and non two consecutive houses can be chosen, maximize the output */
    public static int maxGoldCollection(List<Integer> goldInHouse) {
        int[] gold = new int[goldInHouse.size() + 2];

        for (int i = 2; i <= goldInHouse.size(); i++) {
            gold[i] = Math.max(gold[i - 2] + goldInHouse.get(i - 2),
                    gold[i - 1]);
        }
        return gold[goldInHouse.size() + 1];
    }
}
