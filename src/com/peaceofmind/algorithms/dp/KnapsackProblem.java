package com.peaceofmind.algorithms.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by StarLord on 2016-12-10.
 */
public class KnapsackProblem {

    public static void main(String[] args){
        List<Integer> values = new ArrayList<>();
        List<Integer> weights = new ArrayList<>();

        values.add(2);
        values.add(5);
        values.add(7);
        values.add(8);

        weights.add(1);
        weights.add(2);
        weights.add(3);
        weights.add(4);

        System.out.println(ZeroOneKnapSack(values, weights, 5));
    }

    private static int ZeroOneKnapSack(List<Integer> values, List<Integer> weights, int targetWeight){
        int N = values.size();
        int[][] dp = new int[N+1][targetWeight+1];

        for(int i=0;i<=N;i++){
            dp[i][0] = 0;
        }

        for(int i=0;i<=targetWeight;i++){
            dp[0][i] = 0;
        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=targetWeight;j++){
                if(j>=weights.get(i-1)){
                    int fValue = values.get(i-1) + dp[i-1][j-weights.get(i-1)];
                    int sValue = dp[i-1][j];
                    dp[i][j] = Math.max(fValue, sValue);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[N][targetWeight];
    }

    private static int RepetitiveKnapSack(List<Integer> values, List<Integer> weights, int targetWeight){
        int N = values.size();
        int[][] dp = new int[N+1][targetWeight+1];

        for(int i=0;i<=N;i++){
            dp[i][0] = 0;
        }

        for(int i=0;i<=targetWeight;i++){
            dp[0][i] = 0;
        }

        for(int i=1;i<=N;i++){
            for(int j=1;j<=targetWeight;j++){
                if(j>=weights.get(i-1)){
                    int fValue = values.get(i-1) + dp[i][j-weights.get(i-1)];
                    int sValue = dp[i-1][j];
                    dp[i][j] = Math.max(fValue, sValue);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[N][targetWeight];
    }

}
