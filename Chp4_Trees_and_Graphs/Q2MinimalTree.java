import CtCILibrary.TreeNode;

public class Q2MinimalTree {
    public static TreeNode createMinimalBST(int[] array){
        return createMinimalBST(array, 0, array.length-1);
    }

    public static TreeNode createMinimalBST(int[] arr, int start, int end){
        if (end < start) return null;

        int mid = (start + end)/2;
        TreeNode n = new TreeNode(arr[mid]);
        n.left = createMinimalBST(arr, start, mid-1);
        n.right = createMinimalBST(arr, mid+1, end);
        return n;
    }

    public static void main(String[] args){
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        TreeNode root = createMinimalBST(array);

        System.out.println("Root? " + root.data);
        System.out.println("Created BST? " + root.isBST());
        System.out.println("Height: " + root.height());
    }
}
