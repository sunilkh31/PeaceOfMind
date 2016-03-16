package com.peaceofmind.algorithms.graph;

import java.util.*;

/**
 * Given a string and a pair of N swapping indices. Generate a lexicographically largest string.
 * Swapping indices can be reused any number of times.
 * E.g. "abdc" and indices : (0,3), (2,3)
 * Possible answers : cdba, cbad, dbac, dbca
 * We should print only "dbca"
 *
 * Created by sukhand on 3/15/2016.
 */
public class LexicographicSortWithSwappingRelation {

    public static void main(String[] args) {
        String word = "abdc";
        Map<Integer, Integer> edgeMap = new HashMap<Integer, Integer>();
        edgeMap.put(0,3);
        edgeMap.put(2,3);

        LexicographicSortWithSwappingRelation main = new LexicographicSortWithSwappingRelation();
        main.execute(word, edgeMap);
    }

    public void execute(String word, Map<Integer, Integer> edgeMap) {

        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<word.length();i++) {
            graph.add(new ArrayList<>());
        }

        //Graph created
        for(Integer j : edgeMap.keySet()) {
            graph.get(j).add(edgeMap.get(j));
            graph.get(edgeMap.get(j)).add(j);
        }

        Set<Integer> visited = new HashSet<>();
        char[] ch = word.toCharArray();

        for(int i=0;i<word.length();i++) {
            ArrayList<Integer> indices = new ArrayList<>();
            if(!visited.contains(i)) {
                doDfs(visited, graph, indices, i);
                List<Character> characters = new ArrayList<>();
                indices.forEach(k -> characters.add(word.charAt(k)));
                Collections.sort(characters, (o1, o2) -> o2.compareTo(o1));
                Collections.sort(indices);

                for(int j=0;j<indices.size();j++) {
                    System.out.println("Replacing " + indices.get(j) + " " + characters.get(j));
                    ch[indices.get(j)] = characters.get(j);
                }
            }
        }
        System.out.println(new String(ch));
    }

    private void doDfs(Set<Integer> visited, List<List<Integer>> aList, ArrayList<Integer> list, int i) {
        visited.add(i);
        list.add(i);
        aList.get(i).stream().filter(edge -> !visited.contains(edge)).forEach(edge -> {
            doDfs(visited, aList, list, edge);
        });
    }
}
