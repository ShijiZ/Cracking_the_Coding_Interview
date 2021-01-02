package Chp4_Trees_and_Graphs;

import CtCILibrary.TreeNode;

public class Q8FirstCommonAncestorV1 {
    public static TreeNode commonAncestor(TreeNode p, TreeNode q){
        int delta = depth(p) - depth(q); // get difference in depths
        TreeNode first = delta > 0 ? q : p; // get shallower node
        TreeNode second = delta > 0 ? p : q; // get deeper node
        second = goUpBy(second, Math.abs(delta)); // move deeper node up

        /* Find where paths intersect. */
        while (first != second && first != null && second != null){
            first = first.parent;
            second = second.parent;
        }
        return first == null || second == null ? null : first;
    }

    public static TreeNode goUpBy(TreeNode node, int delta){
        while (delta > 0 && node != null){
            node = node.parent;
            delta--;
        }
        return node;
    }

    public static int depth(TreeNode node){
        int depth = 0;
        while (node != null){
            node = node.parent;
            depth++;
        }
        return depth;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TreeNode root = TreeNode.createMinimalBST(array);
        TreeNode n1 = root.find(1);
        TreeNode n7 = root.find(7);
        TreeNode ancestor = commonAncestor(n1, n7);
        System.out.println(ancestor.data);
    }
}
