/**
 * 
 */
package com.peaceofmind.algorithms.sortedarrays;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains algorithmic code related to Sorted Rotated Arrays
 * 
 * @author StarLord
 *
 */
public class RotatedSortedArray {

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

    public static int search(List<Integer> list, int n) {
        if (list.isEmpty()) {
            return -1;
        }

        int start = 0;
        int end = list.size() - 1;
        int mid = (start + end) / 2;

        if (list.get(start) == n)
            return start;
        if (list.get(end) == n)
            return end;
        if (list.get(mid) == n)
            return mid;

        if (list.get(start) >= list.get(end)) {
            // Rotation is here
            if (list.get(start) >= list.get(mid)) {
                // Rotation in first half
                if (n > list.get(start) || n < list.get(mid)) {
                    return search(list.subList(start, mid), n);
                }
            } else if (n < list.get(mid) && n > list.get(start)) {
                return search(list.subList(start, mid), n);
            }

            if (list.get(mid) >= list.get(end)) {
                // Rotation in second half
                if (n > list.get(mid) || n < list.get(end)) {
                    return mid + search(list.subList(mid, end), n);
                }
            } else if (n > list.get(mid) && n < list.get(end)) {
                return mid + search(list.subList(mid, end), n);
            }
        } else {
            if (n < list.get(mid)) {
                return search(list.subList(start, mid), n);
            } else {
                return mid + search(list.subList(mid, end), n);
            }
        }
        return -1;
    }

}
