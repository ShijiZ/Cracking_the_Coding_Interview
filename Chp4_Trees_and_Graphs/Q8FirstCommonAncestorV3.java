import CtCILibrary.TreeNode;

public class Q8FirstCommonAncestorV3 {
    public static TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q){
        /* Error check, one node is not in the tree. */
        if (!covers(root, p) || !covers(root, q))
                return null;
        return ancestorHelper(root, p, q);
    }

    public static TreeNode ancestorHelper(TreeNode root, TreeNode p, TreeNode q){
        if (root == null || root == p || root == q)
            return root;

        boolean pIsOnLeft = covers(root.left, p);
        boolean qIsOnLeft = covers(root.left, q);
        if (pIsOnLeft != qIsOnLeft) // Nodes are on different sides
            return root;
        TreeNode childSide = pIsOnLeft ? root.left : root.right;
        return ancestorHelper(childSide, p, q);
    }

    public static boolean covers(TreeNode root, TreeNode node){
        if (root == node)
            return true;
        else if (root == null)
            return false;
        return covers(root.left, node) || covers(root.right, node);
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TreeNode root = TreeNode.createMinimalBST(array);
        TreeNode n3 = root.find(1);
        TreeNode n7 = root.find(7);
        TreeNode ancestor = commonAncestor(root, n3, n7);
        System.out.println(ancestor.data);
    }
}
