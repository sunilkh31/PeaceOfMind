/**
 * 
 */
package com.peaceofmind.algorithms.linkedlists;

/**
 * @author StarLord
 *
 */
public class MergeSortDoublyLinkedList {

    static class DLLNode {
        public int val;
        public DLLNode next;
        public DLLNode prev;

        public DLLNode(int val) {
            this.val = val;
        }
    }

    public static DLLNode mergeSort(DLLNode root) {
        if (root == null || root.next == null)
            return root;

        DLLNode mid = getMid(root);
        if (mid.prev != null)
            mid.prev.next = null;
        mid.prev = null;

        DLLNode firstHalf = mergeSort(root);
        DLLNode secondHalf = mergeSort(mid);

        return merge(firstHalf, secondHalf);
    }

    public static DLLNode merge(DLLNode first, DLLNode second) {
        if (first == null)
            return second;
        if (second == null)
            return first;

        DLLNode fake = new DLLNode(0);
        DLLNode curr = fake;
        while (first != null || second != null) {
            if (first == null) {
                curr.next = second;
                second.prev = curr;
                break;
            } else if (second == null) {
                curr.next = first;
                first.prev = curr;
                break;
            }

            DLLNode smaller = null;
            if (first.val > second.val) {
                smaller = second;
                second = second.next;
            } else {
                smaller = first;
                first = first.next;
            }
            curr.next = smaller;
            smaller.prev = curr;
            curr = curr.next;
        }
        fake.next.prev = null;
        return fake.next;
    }

    public static DLLNode getMid(DLLNode node) {
        if (node == null || node.next == null)
            return node;
        DLLNode slow = node;
        DLLNode fast = node;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        DLLNode root = new DLLNode(11);
        DLLNode curr = root;
        curr = addNode(3, curr);
        curr = addNode(31, curr);
        curr = addNode(23, curr);
        curr = addNode(33, curr);
        curr = addNode(3, curr);
        curr = addNode(352, curr);
        curr = addNode(31, curr);
        curr = addNode(73, curr);

        printLinkedList(root);
        root = mergeSort(root);
        printLinkedList(root);
    }

    private static void printLinkedList(DLLNode root) {
        DLLNode curr = root;
        while (curr != null) {
            System.out.print(curr.val);
            System.out.println(" prev " + ((curr.prev != null) ? curr.prev.val : 0));
            curr = curr.next;
        }
    }

    public static DLLNode addNode(int val, DLLNode curr) {
        curr.next = new DLLNode(val);
        curr.next.prev = curr;
        curr = curr.next;
        return curr;
    }
}
