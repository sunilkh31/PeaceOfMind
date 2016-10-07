package com.peaceofmind.algorithms.graph;

import java.util.*;

/**
 * Created by sukhand on 3/15/2016.
 */
public class ConnectedGraph {

    public static class Vertex {
        private int vertex;
        private List<Vertex> edges = new ArrayList<>();

        public Vertex(int vertexValue) {
            this.vertex = vertexValue;
        }

        public void addEdge(Vertex v) {
            edges.add(v);
        }

        public List<Vertex> getEdges(){
            return edges;
        }

        public int getValue() {
            return vertex;
        }
    }

    public List<Vertex> findConnectedComponents(List<Vertex> vertices) {
        Set<Vertex> allVertices = new HashSet<Vertex>(vertices);
        List<Vertex> connectedComponents = new ArrayList<Vertex>();

        for(Vertex v : vertices) {
            if(allVertices.contains(v)) {
                connectedComponents.add(v);
                Set<Vertex> allConnectedNodes = findConnectedNodes(v, allVertices);
                v.getEdges().clear();
                v.getEdges().addAll(allConnectedNodes);
            }
        }
        return connectedComponents;
    }

    public Set<Vertex> findConnectedNodes(Vertex v, Set<Vertex> remainingNodes) {
        Set<Vertex> connectedNodes = new HashSet<Vertex>();
        if(remainingNodes.contains(v)) {
            connectedNodes.add(v);
            remainingNodes.remove(v);
            for(Vertex edge : v.getEdges()) {
                connectedNodes.addAll(findConnectedNodes(edge, remainingNodes));
            }
        }
        return connectedNodes;
    }
}
