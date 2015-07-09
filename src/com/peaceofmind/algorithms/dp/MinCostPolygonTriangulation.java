package com.peaceofmind.algorithms.dp;

/**
 * A triangulation of a convex polygon is formed by drawing diagonals between non-adjacent vertices (corners) such that
 * the diagonals never intersect. The problem is to find the cost of triangulation with the minimum cost. The cost of a
 * triangulation is sum of the weights of its component triangles. Weight of each triangle is its perimeter (sum of
 * lengths of all sides)
 * 
 * 
 * @author StarLord
 *
 */
public class MinCostPolygonTriangulation {

    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    // Recursive solution
    public static double mtc(Point[] points, int start, int end) {
        if (end - start < 2)
            return 0;
        double result = Double.MAX_VALUE;

        for (int k = start + 1; k < end; k++) {
            result = Math.min(result, (mtc(points, start, k) + mtc(points, k, end) + cost(points, start, k, end)));
        }
        return result;
    }

    // Dynamic Programming solution
    public static double mtcDP(Point[] points) {
        int size = points.length;
        if (size < 3)
            return 0;

        double[][] dp = new double[size][size];

        for (int gap = 0; gap < size; gap++) {
            for (int i = 0, j = gap; j < size; i++, j++) {
                if (j - i < 2) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Double.MAX_VALUE;
                    for (int k = i + 1; k < j; k++) {
                        double val = dp[i][k] + dp[k][j] + cost(points, i, j, k);
                        if (val < dp[i][j])
                            dp[i][j] = val;
                    }
                }
            }
        }
        return dp[0][size - 1];
    }

    /**
     * Returns the cost i.e. perimeter of the triangle
     * 
     * @param points
     * @param start
     * @param k
     * @param end
     * @return
     */
    private static double cost(Point[] points, int start, int k, int end) {
        Point p1 = points[start];
        Point p2 = points[k];
        Point p3 = points[end];

        return distance(p1, p2) + distance(p2, p3) + distance(p3, p1);
    }

    /**
     * Returns the distance between two points
     * 
     * @param p3
     * @param p1
     * @return
     */
    private static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    public static void main(String[] args) {
        Point[] points = new Point[5];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(2, 1);
        points[3] = new Point(1, 2);
        points[4] = new Point(0, 2);

        System.out.println(mtc(points, 0, points.length - 1));
        System.out.println(mtcDP(points));
    }
}
