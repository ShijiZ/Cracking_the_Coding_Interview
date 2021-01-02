package Chp4_Trees_and_Graphs;

import CtCILibrary.TreeNode;

public class Q12PathsWithSumV1 {
    public static int countPathsWithSum(TreeNode root, int targetSum){
        if (root == null) return 0;

        /* Count paths with sum starting from the root. */
        int pathsFromRoot = countPathsWithSumFromNode(root, targetSum, 0);

        /* Try the nodes on the left and right. */
        int pathsOnLeft = countPathsWithSum(root.left, targetSum);
        int pathsOnRight = countPathsWithSum(root.right, targetSum);

        return pathsFromRoot + pathsOnLeft + pathsOnRight;
    }

    /* Returns the number of paths with this sum starting from this node. */
    public static int countPathsWithSumFromNode(TreeNode node, int targetSum, int currentSum){
        if (node == null) return 0;

        currentSum += node.data;

        int totalPaths = 0;
        if (currentSum == targetSum) // Found a path from the root
            totalPaths++;

        totalPaths += countPathsWithSumFromNode(node.left, targetSum, currentSum);
        totalPaths += countPathsWithSumFromNode(node.right, targetSum, currentSum);
        return totalPaths;
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
