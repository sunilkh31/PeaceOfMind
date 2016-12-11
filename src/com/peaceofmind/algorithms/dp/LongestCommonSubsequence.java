package com.peaceofmind.algorithms.dp;

/**
 * Created by StarLord on 2016-12-11.
 */
public class LongestCommonSubsequence {

    public static void main(String[] args){
        String s1 = "abcdaf";
        String s2 = "acbcf";
        System.out.println(longestCommonSubSeq(s1, s2));
    }

    private static String longestCommonSubSeq(String s1, String s2){
        int[][] dp = new int[s1.length()+1][s2.length()+1];

        for(int i=1;i<=s1.length();i++){
            for(int j=1;j<=s2.length();j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                } else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        System.out.println(dp[s1.length()][s2.length()]);

        String result = "";
        int i = s1.length();
        int j = s2.length();
        while(i>0 && j>0){
            if(s1.charAt(i-1) == s2.charAt(j-1)){
                result = s1.charAt(i-1) + result;
                i--;
                j--;
            } else {
                if(dp[i-1][j] == dp[i][j]){
                    i = i-1;
                } else {
                    j= j-1;
                }
            }
        }

        return result;
    }
}
