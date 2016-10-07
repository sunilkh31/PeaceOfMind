package com.peaceofmind.algorithms.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sukhand on 10/7/2016.
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        LongestIncreasingSubsequence obj = new LongestIncreasingSubsequence();
        System.out.print(obj.maximumElements(new int[]{13,5,4}));
    }

    /**
     * Variant of Longest Increasing Subsequence with different base condition
     * This one solves the following problem:
     * Given an array of elements, pick maximum number of elements with the following rule:
     * Cannot pick A[i] and A[j] if absolute value of (A[i]-A[j]) > absolute value of (i-j)
     * e.g. {13, 5, 4} -> ans =2 {4,5}
     * @param numbers
     * @return
     */
    private int maximumElements(int[] numbers) {
        int[] predecessors = new int[numbers.length];
        int[] indexOfLastElementOfIncreasingSubsequenceofLengthJ = new int[numbers.length + 1];
        int L = 0;
        for(int i=0; i<numbers.length;i++) {
            int lo = 1;
            int hi = L;
            int mid = 0;
            while(lo<= hi) {
                mid = (int) Math.ceil((lo+hi)/2);
                if(Math.abs(numbers[indexOfLastElementOfIncreasingSubsequenceofLengthJ[mid]]-numbers[i]) <= Math.abs(i-indexOfLastElementOfIncreasingSubsequenceofLengthJ[mid])) {
                    lo = mid+1;
                } else {
                    hi = mid-1;
                }
            }

            int newL = lo;

            predecessors[i] = indexOfLastElementOfIncreasingSubsequenceofLengthJ[newL-1];
            indexOfLastElementOfIncreasingSubsequenceofLengthJ[newL] = i;

            if(newL>L){
                L = newL;
            }
        }

        int k = indexOfLastElementOfIncreasingSubsequenceofLengthJ[L];
        for(int i=L-1;i>=0;i--) {
            System.out.println(numbers[k]);
            k = predecessors[k];
        }
        return L;
    }
}
