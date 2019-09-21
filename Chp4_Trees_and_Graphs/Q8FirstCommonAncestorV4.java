import CtCILibrary.TreeNode;

public class Q8FirstCommonAncestorV4 {
    public static TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q){
        Result r = commonAncestorHelper(root, p, q);
        if (r.isAncestor)
            return r.node;
        return null;
    }

    public static Result commonAncestorHelper(TreeNode root, TreeNode p, TreeNode q){
        if (root == null)
            return new Result(root, false);
        if (root == q && root == p)
            return new Result(root, true);

        Result x = commonAncestorHelper(root.left, p, q);
        if (x.isAncestor) // Common ancestor already found
            return x;

        Result y = commonAncestorHelper(root.right, p, q);
        if (y.isAncestor) // Common ancestor already found
            return y;

        if (x.node != null && y.node != null)
            return new Result(root, true); // This is the common ancestor
        else if (root == p || root == q){
            // If we're currently at p or q, and we also found one of those nodes in a
            // subtree, then this is truly an ancestor
            boolean isAncestor = x.node != null || y.node != null;
            return new Result(root, isAncestor);
        }
        else // return the non-null value
            return new Result(x.node != null ? x.node : y.node, false);
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

class Result{
    public TreeNode node;
    public boolean isAncestor;

    public Result(TreeNode n, boolean isAncestor){
        this.node = n;
        this.isAncestor = isAncestor;
    }
}
