package Chp4_Trees_and_Graphs;

import java.util.Random;

public class Q11RandomNodeV1 {
    public static void main(String[] args) {
        int[] counts = new int[10];
        for (int i = 0; i < 1000000; i++) {
            Tree tree = new Tree();
            int[] array = {1, 0, 6, 2, 3, 9, 4, 5, 8, 7};
            for (int x : array) {
                tree.insert(x);
            }
            int d = tree.getRandomNode().data();
            counts[d]++;
        }

        for (int i = 0; i < counts.length; i++) {
            System.out.println(i + ": " + counts[i]);
        }
    }
}

class TreeNode{
    private int data;
    public TreeNode left;
    public TreeNode right;
    private int size;

    public TreeNode(int d){
        this.data = d;
        size = 1;
    }

    public int size(){
        return size;
    }

    public int data(){
        return data;
    }

    public void insert(int d){
        /* Note this is a binary search tree. */
        if (d <= data){
            if (left == null)
                left = new TreeNode(d);
            else
                left.insert(d);
        }
        else{
            if (right == null)
                right = new TreeNode(d);
            else
                right.insert(d);
        }
        size++;
    }

    public TreeNode find(int d){
        if (d == data)
            return this;
        else if (d <= data)
           return left == null ? null : left.find(d);
        else if (d > data)
            return right != null ? right.find(d) : null;
        return null;
    }

    public TreeNode getRandomNode(){
        int leftSize = left == null ? 0 : left.size();
        Random random = new Random();
        int index = random.nextInt(size);
        if (index < leftSize)
            return left.getRandomNode();
        else if (index == leftSize)
            return this;
        else
            return right.getRandomNode();
    }

}

class Tree{
    TreeNode root = null;

    public int size(){
        return root == null ? 0 : root.size();
    }

    public void insert(int value){
        if (root == null)
            root = new TreeNode(value);
        else
            root.insert(value);
    }

    public TreeNode find(int value){
        if (root == null)
            return null;
        return root.find(value);
    }

    public TreeNode getRandomNode(){
        if (root == null)
            return null;
        return root.getRandomNode();
    }


}
