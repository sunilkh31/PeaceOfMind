package com.peaceofmind.algorithms.dp;

/**
 * Created by StarLord on 2016-12-11.
 */
public class EggDropping {

    // Find the min attempts req to find the floor from which egg will break
    public static void main(String[] args){
        System.out.println(minAttempts(2, 6));
    }

    private static int minAttempts(int numOfEggs, int maxFloors){
        int[][] dp = new int[numOfEggs+1][maxFloors+1];
        for(int i=1;i<=maxFloors;i++){
            dp[1][i] = i;
        }

        for(int i=2;i<=numOfEggs;i++){
            for(int j=1;j<=maxFloors;j++){
                if(i>j){
                    dp[i][j] = dp[i-1][j];
                } else {
                    int val = Integer.MAX_VALUE;
                    for(int k=1;k<=j;k++){
                        val = Math.min(val, Math.max(dp[i-1][k-1], dp[i][j-k]));
                    }
                    dp[i][j] = val + 1;
                }
            }
        }

        for(int i=1;i<=numOfEggs;i++){
            for(int j=1;j<=maxFloors;j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[numOfEggs][maxFloors];
    }
}
