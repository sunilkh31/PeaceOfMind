/**
 * 
 */
package com.peaceofmind.algorithms.backtracking;

import java.util.ArrayList;

/**
 * @author StarLord
 *
 */
public class Reachability {

    /* Given array of Integers with the hops possible from that point */
    public boolean canReachEnd(ArrayList<Integer> num) {
        if (num.size() == 1) {
            return (num.get(0) > 0);
        }
        int currSteps = num.get(0);
        int index = 0;
        while (currSteps != 0) {
            currSteps--;
            index++;
            if (index == num.size() - 1)
                return true;
            int possibleSteps = num.get(index);
            if (possibleSteps > currSteps) {
                currSteps = possibleSteps;
            }
        }
        return false;
    }

}
