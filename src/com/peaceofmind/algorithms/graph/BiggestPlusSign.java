package com.peaceofmind.algorithms.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Finding the biggest plus sign in a sparse matrix ( matrix with elements 0 and 1)
 * For example: The biggest plus sign for following matrix is located at (2,2), with length 1
 * for each edge (yes each edge should have same length)
 * 0 0 1 0 0 1 0
 * 1 0 1 0 1 0 1
 * 1 1 1 1 1 1 1
 * 0 0 1 0 0 0 0
 * 0 0 0 0 0 0 0
 *
 * Hint : Use DFS/BFS
 *
 * Created by StarLord on 2016-11-22.
 */
public class BiggestPlusSign {

    public static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        int[][] board = {
                {0,0,0,1,0,1,0},
                {1,0,1,1,1,0,1},
                {1,1,1,1,1,1,1},
                {0,0,1,1,0,0,0},
                {0,0,0,1,0,0,0}};
        Position val = getMaxPlusSignRoot(board);
        System.out.println(val.x + " " + val.y);
    }

    private static int getMaxPlusSign(Position pos, int[][] matrix, HashMap<String, Integer> map){
        int left = getMaxPlusSign(pos.x, pos.y-1, "LEFT", matrix, map);
        int right = getMaxPlusSign(pos.x, pos.y+1, "RIGHT", matrix, map);
        int up = getMaxPlusSign(pos.x-1, pos.y, "UP", matrix, map);
        int down = getMaxPlusSign(pos.x + 1, pos.y, "DOWN", matrix, map);

        return Math.min(Math.min(left, right), Math.min(up, down));
    }

    private static int getMaxPlusSign(int x, int y, String d, int[][] matrix, Map<String, Integer> map) {
        if(x<0 || x>=matrix.length || y<0 || y>=matrix[0].length || matrix[x][y] == 0) return 0;

        String key = x+""+y+""+d;
        if(map.containsKey(key)) return map.get(key);
        int size = 1;
        switch (d){
            case "LEFT":
                size+=getMaxPlusSign(x,y-1,"LEFT",matrix,map);
                break;
            case "RIGHT":
                size+=getMaxPlusSign(x,y+1,"RIGHT",matrix,map);
                break;
            case "UP":
                size+=getMaxPlusSign(x-1,y,"UP",matrix,map);
                break;
            case "DOWN":
                size+=getMaxPlusSign(x+1,y,"DOWN",matrix,map);
                break;
        }
        map.put(key, size);
        return size;
    }

    private static Position getMaxPlusSignRoot(int[][] matrix){
        List<Position> candidates = getExplorablePositions(matrix);
        HashMap<String, Integer> m = new HashMap<>();
        Position maxPos = null;
        int maxSize = -1;
        for(Position pos : candidates){
            int size = getMaxPlusSign(pos,matrix,m);
            if(size > maxSize) {
                maxPos = pos;
                maxSize = size;
            }
        }
        return maxPos;
    }

    private static List<Position> getExplorablePositions(int[][] matrix) {
        int rowSize = matrix.length;
        int colSize = matrix[0].length;

        List<Position> result = new ArrayList<>();
        for(int i=1;i<rowSize-1;i++) {
            for(int j=1; j<colSize-1; j++) {
                if(matrix[i][j] == 1 && matrix[i-1][j] == 1 && matrix[i+1][j] == 1 &&
                        matrix[i][j-1] == 1 && matrix[i][j+1] == 1){
                    result.add(new Position(i, j));
                }
            }
        }
        return result;
    }
}
