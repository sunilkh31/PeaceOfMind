package com.peaceofmind.algorithms.greedy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Algorithm to generate Huffman codes for different characters
 * 
 * @author StarLord
 *
 */
public class HuffmanCodes {

    class Node {
        public Character ch;
        public Integer count;
        public Node left;
        public Node right;

        public Node(Character ch, Integer count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public Map<Character, String> getHuffmanCodes(Map<Character, Integer> charFrequencies) {
        HashMap<Character, String> huffmanCodes = new HashMap<>();

        Comparator<Node> comp = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.count.compareTo(o2.count);
            }
        };
        PriorityQueue<Node> maxHeap = new PriorityQueue<Node>(comp);

        for (Map.Entry<Character, Integer> entry : charFrequencies.entrySet()) {
            maxHeap.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (maxHeap.size() > 1) {
            Node first = maxHeap.poll();
            Node second = maxHeap.poll();
            Node aggregate = new Node(null, first.count + second.count);
            aggregate.left = first;
            aggregate.right = second;
            maxHeap.add(aggregate);
        }

        if (maxHeap.size() == 1) {
            Node root = maxHeap.poll();
            populateHuffmanCodes(root, huffmanCodes, "");
        }
        return huffmanCodes;
    }

    private void populateHuffmanCodes(Node root, HashMap<Character, String> huffManCodes, String res) {
        // Leaf node
        if (root != null && root.ch != null) {
            huffManCodes.put(root.ch, res);
            return;
        }

        populateHuffmanCodes(root.left, huffManCodes, res + "0");
        populateHuffmanCodes(root.right, huffManCodes, res + "1");
    }

    public static void main(String[] args) {
        HashMap<Character, Integer> freq = new HashMap<>();
        freq.put('a', 5);
        freq.put('b', 9);
        freq.put('c', 122);
        freq.put('d', 132);
        freq.put('e', 162);
        freq.put('f', 45);
        HuffmanCodes obj = new HuffmanCodes();
        Map<Character, String> codes = obj.getHuffmanCodes(freq);
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}
