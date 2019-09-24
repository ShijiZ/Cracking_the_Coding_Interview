import java.util.Random;

public class Q11RandomNodeV2 {
    public static void main(String[] args) {
        int[] counts = new int[10];
        for (int i = 0; i < 1000000; i++) {
            TreeV2 tree = new TreeV2();
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

class TreeNodeV2{
    private int data;
    public TreeNodeV2 left;
    public TreeNodeV2 right;
    private int size = 0;

    public TreeNodeV2(int d){
        this.data = d;
        size = 1;
    }

    public TreeNodeV2 getIthNode(int i){
        int leftSize = left == null ? 0 : left.size();
        if (i < leftSize)
            return left.getIthNode(i);
        else if (i == leftSize)
            return this;
        else
            return right.getIthNode(i - (leftSize + 1));
    }

    public void insert(int d){
        /* Note this is a binary search tree. */
        if (d <= data){
            if (left == null)
                left = new TreeNodeV2(d);
            else
                left.insert(d);
        }
        else{
            if (right == null)
                right = new TreeNodeV2(d);
            else
                right.insert(d);
        }
        size++;
    }

    public int size(){
        return size;
    }

    public int data(){
        return data;
    }

    public TreeNodeV2 find(int d){
        if (d == data)
            return this;
        else if (d <= data)
            return left == null ? null : left.find(d);
        else if (d > data)
            return right != null ? right.find(d) : null;
        return null;
    }

}

class TreeV2{
    TreeNodeV2 root = null;

    public void insert(int value){
        if (root == null)
            root = new TreeNodeV2(value);
        else
            root.insert(value);
    }

    public int size(){
        return root == null ? 0 : root.size();
    }

    public TreeNodeV2 getRandomNode(){
        if (root == null) return null;
        Random random = new Random();
        int i = random.nextInt(size());
        return root.getIthNode(i);
    }
}