package Chp4_Trees_and_Graphs;

import CtCILibrary.TreeNode;

import java.util.HashMap;

public class Q12PathsWithSumV2 {
    public static int countPathsWithSum(TreeNode root, int targetSum){
        HashMap<Integer, Integer> pathCount = new HashMap<>();
        return countPathsWithSum(root, targetSum,0, pathCount);
    }

    public static int countPathsWithSum(TreeNode node, int targetSum, int runningSum,
                                        HashMap<Integer, Integer> pathCount){
        if (node == null) return 0; // Base case

        /* Count paths with sums ending at the current node. */
        runningSum += node.data;
        int sumNeeded = runningSum - targetSum;
        int totalPaths = pathCount.getOrDefault(sumNeeded, 0);

        /* If running sum equals targetSum, then one additional path starts at root.
        * Add in this path. */
        if (runningSum == targetSum)
            totalPaths++;

        /* Increment pathCount, recurse, then decrement pathCount. */
        incrementHashTable(pathCount, runningSum, 1); // increment pathCount
        totalPaths += countPathsWithSum(node.left, targetSum, runningSum, pathCount);
        totalPaths += countPathsWithSum(node.right, targetSum, runningSum, pathCount);
        incrementHashTable(pathCount, runningSum, -1); // decrement pathCount

        return totalPaths;
    }

    public static void incrementHashTable(HashMap<Integer, Integer> hashMap, int key, int delta){
        int newCount = hashMap.getOrDefault(key, 0) + delta;
        if (newCount == 0) // Remove when zero to reduce space usage
            hashMap.remove(key);
        else
            hashMap.put(key, newCount);
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(1);
        root.right = new TreeNode(-3);
        root.right.right = new TreeNode(11);

        System.out.println(countPathsWithSum(root, 8));
    }
}
