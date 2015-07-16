/**
 * 
 */
package com.peaceofmind.algorithms.string;

import java.util.ArrayList;

/**
 * @author StarLord
 *
 */
public class LongestPrefix {

    /* Longest prefix in the given list of strings */
    public static String longestPrefix(String[] strings) {
        if (strings.length == 0)
            return "";
        if (strings[0].length() == 0)
            return "";
        char start = strings[0].charAt(0);

        int i = 0;
        boolean cont = true;
        while (cont) {
            if (i >= strings[0].length())
                break;
            start = strings[0].charAt(i);
            for (int j = 1; j < strings.length; j++) {
                if (i >= strings[j].length() || strings[j].charAt(i) != start) {
                    cont = false;
                    break;
                }
            }
            if (cont)
                i++;
        }
        return strings[0].substring(0, i);
    }

    public static void main(String[] args) {
        ArrayList<String> input = new ArrayList<>();
        input.add("i lovdogs");
        input.add("i love cats");
        System.out.println(longestPrefix(input.toArray(new String[input.size()])));
    }
}
