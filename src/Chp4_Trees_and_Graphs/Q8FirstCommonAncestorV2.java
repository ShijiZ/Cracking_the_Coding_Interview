package Chp4_Trees_and_Graphs;

import CtCILibrary.TreeNode;

public class Q8FirstCommonAncestorV2 {
    public static TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q){
        /* Check if either node is not in the tree, or if one covers the other. */
        if (!covers(root, p) || !covers(root, q)){
            return null;
        } else if (covers(p, q)){
            return p;
        } else if (covers(q, p)){
            return q;
        }

        /* Traverse upwards until you find a node that covers q. */
        TreeNode sibling = getSibling(p);
        TreeNode parent = p.parent;
        while (!covers(sibling, q)){
            sibling = getSibling(parent);
            parent = parent.parent;
        }
        return parent;
    }

    public static boolean covers(TreeNode root, TreeNode p){
        if (root == null) return false;
        if (root == p) return true;
        return covers(root.left, p) || covers(root.right, p);
    }

    public static TreeNode getSibling(TreeNode node){
        if (node == null || node.parent == null)
            return null;
        TreeNode parent = node.parent;
        return parent.left == node ? parent.right : parent.left;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TreeNode root = TreeNode.createMinimalBST(array);
        TreeNode n1 = root.find(1);
        TreeNode n7 = root.find(7);
        TreeNode ancestor = commonAncestor(root, n1, n7);
        System.out.println(ancestor.data);
    }

}
