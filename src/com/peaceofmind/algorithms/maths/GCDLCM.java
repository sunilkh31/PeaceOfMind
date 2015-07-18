package com.peaceofmind.algorithms.maths;

/**
 * Class includes methods to calculate GCD and LCM of two integers
 * 
 * @author StarLord
 *
 */
public class GCDLCM {

    /**
     * Returns GCD of two non-zero positive integers.
     * 
     * @param a
     * @param b
     * @return
     */
    public int GCD(int a, int b) {
        if (a < b)
            return GCD(b, a);
        if (b == 0)
            return a;
        return GCD(b, a % b);
    }

    /**
     * Returns the LCM of two non-zero positive integers
     * 
     * @param a
     * @param b
     * @return
     */
    public int LCM(int a, int b) {
        return (a * b) / GCD(a, b);
    }

    public static void main(String[] args) {
        GCDLCM obj = new GCDLCM();
        System.out.println(obj.GCD(234, 724));
        System.out.println(obj.LCM(343, 832));
    }
}
