package com.peaceofmind.algorithms.dp;

import com.peaceofmind.algorithms.interval.Interval;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by StarLord on 2016-12-11.
 */
public class WeightedJobScheduling {

    public static void main(String[] args){
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1 ,3));
        intervals.add(new Interval(7 ,9));
        intervals.add(new Interval(2 ,5));
        intervals.add(new Interval(5 ,8));
        intervals.add(new Interval(6 ,7));
        intervals.add(new Interval(4 ,6));

        List<Integer> cost = new ArrayList<>();
        cost.add(5);
        cost.add(2);
        cost.add(6);
        cost.add(11);
        cost.add(4);
        cost.add(5);

        System.out.println(maxProfit(intervals, cost));

    }

    private static int maxProfit(List<Interval> intervals, List<Integer> cost){
        Comparator<Interval> comp = new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.endTime, o2.endTime);
            }
        };

        intervals.sort(comp);

        int[] dp = new int[intervals.size()];

        for(int i=0;i<intervals.size();i++){
            dp[i] = cost.get(i);
        }

        int maxProfit = dp[0];
        for(int i=1;i<intervals.size();i++){
            for(int j=0;j<i;j++){
                if(intervals.get(j).endTime <= intervals.get(i).startTime){
                    dp[i] = Math.max(dp[j]+cost.get(i), dp[i]);
                }
            }
            if(dp[i]>maxProfit){
                maxProfit = dp[i];
            }
        }

        return maxProfit;
    }
}
