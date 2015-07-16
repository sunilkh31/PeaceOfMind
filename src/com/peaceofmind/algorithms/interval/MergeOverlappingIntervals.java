package com.peaceofmind.algorithms.interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

/**
 * Given a list of intervals, this class merges all the overlapping intervals
 * 
 * @author StarLord
 *
 */
public class MergeOverlappingIntervals {

    public static class Interval {
        public int startTime;
        public int endTime;

        public Interval(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    /**
     * Sorts the Intervals by Start time and uses Stack to merge the intervals
     * 
     * @param intervals
     * @return
     */
    public ArrayList<Interval> mergeOverlappingIntervals(ArrayList<Interval> intervals) {
        sortIntervalsWithStartTime(intervals);

        Stack<Interval> result = new Stack<Interval>();
        result.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            Interval top = result.peek();
            Interval curr = intervals.get(i);
            if (curr.startTime <= top.endTime) {
                if (curr.endTime > top.endTime)
                    top.endTime = curr.endTime;
            } else {
                result.push(curr);
            }
        }

        ArrayList<Interval> res = new ArrayList<>();
        res.addAll(result);
        return res;
    }

    private void sortIntervalsWithStartTime(ArrayList<Interval> intervals) {
        Comparator<Interval> comp = new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.startTime, o2.startTime);
            }
        };
        Collections.sort(intervals, comp);
    }

    public ArrayList<Interval> addAndMergeIntervals(ArrayList<Interval> intervals, Interval interval) {
        ArrayList<Interval> result = new ArrayList<>();
        for (int i = 0; i < intervals.size(); i++) {
            if ((interval.startTime >= intervals.get(i).startTime && interval.startTime <= intervals.get(i).endTime)
                    || (interval.endTime >= intervals.get(i).startTime && interval.startTime <= intervals.get(i).startTime)) {
                interval.startTime = Math.min(interval.startTime, intervals.get(i).startTime);
                interval.endTime = Math.max(interval.endTime, intervals.get(i).endTime);
                if (i == intervals.size() - 1) {
                    result.add(interval);
                }
            } else if (interval.endTime < intervals.get(i).startTime) {
                result.add(interval);
                result.addAll(intervals.subList(i, intervals.size()));
                break;
            } else {
                result.add(intervals.get(i));
                if (i == intervals.size() - 1) {
                    result.add(interval);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MergeOverlappingIntervals object = new MergeOverlappingIntervals();
        ArrayList<Interval> input = new ArrayList<>();
        input.add(new Interval(0, 10));
        input.add(new Interval(4, 20));
        input.add(new Interval(5, 9));
        input.add(new Interval(21, 25));
        input.add(new Interval(26, 29));
        input.add(new Interval(29, 40));
        input.add(new Interval(41, 42));
        ArrayList<Interval> intervals = object.mergeOverlappingIntervals(input);
        printIntervals(intervals);
        intervals = object.addAndMergeIntervals(intervals, new Interval(0, 20));
        printIntervals(intervals);
    }

    public static void printIntervals(ArrayList<Interval> intervals) {
        System.out.println("----New Print----");
        for (int i = 0; i < intervals.size(); i++) {
            System.out.println(intervals.get(i).startTime + " " + intervals.get(i).endTime);
        }
    }
}
