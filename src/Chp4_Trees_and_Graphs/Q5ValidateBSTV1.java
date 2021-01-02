package Chp4_Trees_and_Graphs;

import CtCILibrary.TreeNode;

public class Q5ValidateBSTV1 {
    public static Integer last_printed = null;  // int cannot be set as null

    public static boolean checkBST(TreeNode n){
        if (n == null)
            return true;  // base case

        // Check/recurse left
        if (!checkBST(n.left))
            return false;

        // Check current
        if (last_printed != null && n.data <= last_printed)
            return false;
        last_printed = n.data;

        // Check/recurse right
        if (!checkBST(n.right))
            return false;

        return true; // All good
    }

    public static void main(String[] args) {
        int[] array = {Integer.MIN_VALUE, Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1, Integer.MAX_VALUE};
        TreeNode node = TreeNode.createMinimalBST(array);
        System.out.println(checkBST(node));
    }
}
