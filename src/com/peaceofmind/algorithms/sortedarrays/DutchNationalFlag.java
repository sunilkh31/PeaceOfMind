package com.peaceofmind.algorithms.sortedarrays;

import java.util.ArrayList;

/**
 * Standard Dutch National Flag problem
 * 
 * @author StarLord
 *
 */
public class DutchNationalFlag {

    public void dutchNationalFlagSort(ArrayList<Integer> A) {
        int mid = 1;
        for (int i = 0, j = A.size() - 1, k = 0; k < j;) {
            if (A.get(k) < mid) {
                A.set(k, A.get(i));
                A.set(i, 0);
                i++;
                k++;
            } else if (A.get(k) > mid) {
                A.set(k, A.get(j));
                A.set(j, 2);
                j--;
            } else {
                k++;
            }
        }
    }
}
