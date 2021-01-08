package Chp8_Recursion_and_Dynamic_Programming;

import java.util.Stack;

public class Q6TowerOfHanoi {
    private static class Tower{
        private Stack<Integer> disks;
        private int index;

        public Tower(int i){
            disks = new Stack<>();
            index = i;
        }

        public int index(){
            return this.index;
        }

        public void add(int d){
            if (!this.disks.isEmpty() && this.disks.peek() <= d){
                System.out.println("Error placing disk " + d);
            } else {
                disks.push(d);
            }
            return;
        }

        public void moveDisks(int n, Tower destination, Tower buffer){
            if (n > 0){
                // move top n - 1 disks from origin to buffer, using destination as a buffer.
                this.moveDisks(n - 1, buffer, destination);

                // move top from origin to destination.
                destination.add(disks.pop());

                // move top n - 1 disks from buffer to destination, using origin as a buffer.
                buffer.moveDisks(n - 1, destination, this);
            }
            return;
        }

        public void print(){
            System.out.println("Contents of Tower " + index() + ": " + disks.toString());
        }
    }

    public static void main(String[] args){
        Tower[] towers = new Tower[3];
        for (int i=0; i < 3; i++){
            towers[i] = new Tower(i);
        }

        int n = 7;
        for (int i=n-1; i >= 0; i--){
            towers[0].add(i);
        }

        towers[0].print();
        towers[0].moveDisks(n, towers[2], towers[1]);
        towers[2].print();
    }
}
