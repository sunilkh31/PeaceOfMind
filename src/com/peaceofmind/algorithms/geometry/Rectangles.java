package com.peaceofmind.algorithms.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author StarLord
 *
 */
public class Rectangles {

    public static class Pair {
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * A rectangle is represented by BottomLeft and topRight coordinate pair. If a intersection is not found then null
     * is returned.
     * 
     * @param first
     * @param second
     * @return
     */
    public List<Pair> findRectangleIntersection(List<Pair> first, List<Pair> second) {
        Pair bottomLeft = new Pair(Math.max(first.get(0).x, second.get(0).x), Math.max(first.get(0).y, second.get(0).y));
        Pair topRight = new Pair(Math.max(first.get(1).x, second.get(1).x), Math.max(first.get(1).y, second.get(1).y));
        if (bottomLeft.x > topRight.x || bottomLeft.y > topRight.y) {
            return null;
        }
        List<Pair> result = new ArrayList<>();
        result.add(bottomLeft);
        result.add(topRight);
        return result;
    }

    public static void main(String[] args) {
        List<Pair> first = new ArrayList<>();
        first.add(new Pair(0, 0));
        first.add(new Pair(5, 6));

        List<Pair> second = new ArrayList<>();
        second.add(new Pair(1, 4));
        second.add(new Pair(5, 9));

        Rectangles obj = new Rectangles();
        List<Pair> intersection = obj.findRectangleIntersection(first, second);
        System.out.println(intersection.get(0).x + " " + intersection.get(0).y);
        System.out.println(intersection.get(1).x + " " + intersection.get(1).y);

    }
}
