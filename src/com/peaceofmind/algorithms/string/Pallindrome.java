/**
 * 
 */
package com.peaceofmind.algorithms.string;

/**
 * @author StarLord
 *
 */
public class Pallindrome {

    /* Write code to check pallindrome for only letters and ignore nonletters */
    public boolean isPallindrome(String str) {
        int i = 0;
        int j = str.length() - 1;
        while (i <= j) {
            char ci = str.charAt(i);
            char cj = str.charAt(j);
            if (Character.isAlphabetic(ci) && Character.isAlphabetic(cj)) {
                ci = Character.toLowerCase(ci);
                cj = Character.toLowerCase(cj);
                if (ci != cj)
                    return false;
                i++;
                j--;
            } else if (!Character.isAlphabetic(ci)) {
                i++;
            } else if (!Character.isAlphabetic(cj)) {
                j--;
            }
        }
        return true;
    }
}
