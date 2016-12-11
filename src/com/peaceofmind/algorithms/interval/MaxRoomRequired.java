/**
 * 
 */
package com.peaceofmind.algorithms.interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author StarLord
 *
 */
public class MaxRoomRequired {

    /* Maximum meeting scheduler */
    public static int maximumMeetingScheduler(ArrayList<Interval> intervals) {
        ArrayList<Integer> convertedIntervals = new ArrayList<Integer>();
        for (Interval interval : intervals) {
            convertedIntervals.add(interval.startTime);
            convertedIntervals.add(-interval.endTime);
        }

        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.valueOf(Math.abs(o1)).compareTo(Math.abs(o2));
            }
        };

        Collections.sort(convertedIntervals, comp);

        int max = 0;
        int result = max;
        for (int i = 0; i < convertedIntervals.size(); i++) {
            if (convertedIntervals.get(i) >= 0) {
                max++;
            } else {
                max--;
            }
            if (result < max)
                result = max;
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<Interval> input = new ArrayList<>();
        input.add(new Interval(0, 10));
        input.add(new Interval(4, 20));
        input.add(new Interval(5, 9));
        input.add(new Interval(21, 25));
        input.add(new Interval(26, 29));
        input.add(new Interval(29, 40));
        input.add(new Interval(41, 42));
        System.out.println(maximumMeetingScheduler(input));
    }
}
