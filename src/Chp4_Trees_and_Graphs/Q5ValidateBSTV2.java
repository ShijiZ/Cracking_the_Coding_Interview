package Chp4_Trees_and_Graphs;

import CtCILibrary.TreeNode;

public class Q5ValidateBSTV2 {
    public static boolean checkBST(TreeNode n){
        return checkBST(n, null, null);
    }

    private static boolean checkBST(TreeNode n, Integer min, Integer max){
        if (n == null)
            return true;

        if ((min != null && n.data <= min) || (max != null && n.data >= max))
            return false;

        if (!checkBST(n.left, min, n.data) || !checkBST(n.right, n.data, max))
            return false;

        return true;
    }

    public static void main(String[] args) {
        int[] array = {Integer.MIN_VALUE, Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1, Integer.MAX_VALUE};
        TreeNode node = TreeNode.createMinimalBST(array);
        System.out.println(checkBST(node));
    }
}
