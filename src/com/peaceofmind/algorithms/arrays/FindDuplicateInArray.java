/**
 * 
 */
package com.peaceofmind.algorithms.arrays;

import java.util.ArrayList;

/**
 * To find duplicates in an array of size N and which contains numbers between 1 to n
 * 
 * @author StarLord
 *
 */
public class FindDuplicateInArray {

    /* Find the duplicates in an array where numbers are from 1-n-1 and size is n */
    public int findDuplicate(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            int index = Math.abs(numbers.get(i)) - 1;
            if (numbers.get(index) >= 0) {
                numbers.set(index, -numbers.get(index));
            } else {
                return index + 1;
            }
        }
        return -1;
    }

    /*
     * Given an integer array such that every element occurs 3 times except one element which occurs only once, how do I
     * find that single element in O(1) space and O(n) time complexity?
     */
    public int findNonTernaryOccurence(ArrayList<Integer> numbers) {
        int ones = 0;
        int twos = 0;
        for (int i = 0; i < numbers.size(); i++) {
            twos = twos | (ones & numbers.get(i));
            ones = (ones ^ numbers.get(i));
            int notThrees = ~(ones & twos);
            ones &= notThrees;
            twos &= notThrees;
        }
        return ones;
    }

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(1);
        numbers.add(11);
        numbers.add(2);
        numbers.add(2);
        numbers.add(2);
        FindDuplicateInArray obj = new FindDuplicateInArray();
        System.out.println(obj.findNonTernaryOccurence(numbers));
    }
}
