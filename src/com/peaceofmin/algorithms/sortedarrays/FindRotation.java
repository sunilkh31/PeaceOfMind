/**
 * 
 */
package com.peaceofmin.algorithms.sortedarrays;

import java.util.ArrayList;

/**
 * This class contains algorithmic code related to Sorted Arrays
 * 
 * @author StarLord
 *
 */
public class FindRotation {

    public int findRotation(ArrayList<Integer> array) {
        if (array.size() <= 1)
            return -1;

        int start = 0;
        int end = array.size() - 1;
        int mid = 0;
        if (array.get(start) < array.get(end))
            return -1;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if ((mid == 0 && array.get(mid + 1) < array.get(mid))
                    || (mid == array.size() - 1 && array.get(mid - 1) > array
                            .get(mid))
                    || (array.get(mid) < array.get(mid - 1) && array.get(mid) < array
                            .get(mid + 1))) {
                return mid;
            } else if (array.get(mid) > array.get(end)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

}
