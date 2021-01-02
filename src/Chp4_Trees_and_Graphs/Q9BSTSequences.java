package Chp4_Trees_and_Graphs;

import CtCILibrary.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

public class Q9BSTSequences {
    public static ArrayList<LinkedList<Integer>> allSequences(TreeNode node){
        ArrayList<LinkedList<Integer>> result = new ArrayList<>();

        if (node == null){
            result.add(new LinkedList<>());
            return result;
        }

        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(node.data);

        /* Recurse on left and right subtrees */
        ArrayList<LinkedList<Integer>> leftSq = allSequences(node.left);
        ArrayList<LinkedList<Integer>> rightSq = allSequences(node.right);

        /* Weave together each list from the left and right sides. */
        for (LinkedList<Integer> left : leftSq)
            for (LinkedList<Integer> right : rightSq){
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<>();
                weaveLists(left, right, weaved, prefix);
                result.addAll(weaved);
            }
        return result;
    }

    /* Weave lists together in all possible ways. This algorithm works by removing the
    * head from one list, recursing, and then doing the same thing with the other list. */
    public static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second,
                                  ArrayList<LinkedList<Integer>> weaved, LinkedList<Integer> prefix){
        /* One list is empty. Add remainder to [a cloned] prefix and store result. */
        if (first.size() == 0 || second.size() == 0){
            LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
            result.addAll(first);
            result.addAll(second);
            weaved.add(result);
            return;
        }

        /* Recurse with head of first added to the prefix. Removing the head will damage
        * first, so we'll need to put it back where we found it afterwards. */
        int headFirst = first.removeFirst();
        prefix.addLast(headFirst);
        weaveLists(first, second, weaved, prefix);
        prefix.removeLast();      // revert the changes
        first.addLast(headFirst); // after done recursion

        /* Do the same thing with second, damaging and then restoring the list. */
        int headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weaveLists(first, second, weaved, prefix);
        prefix.removeLast();         // revert the changes
        second.addFirst(headSecond); // after done recursion
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(100);
        int[] array = {50, 20, 75, 150, 120, 170};
        for (int a : array) {
            node.insertInOrder(a);
        }
        ArrayList<LinkedList<Integer>> allSeq = allSequences(node);
        for (LinkedList<Integer> list : allSeq) {
            System.out.println(list);
        }
        System.out.println(allSeq.size());
    }
}
