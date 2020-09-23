package Chp3_Stacks_and_Queues;

import java.util.Stack;

public class Q2StackMinV1 extends Stack<NodeWithMin>{
    public void push(int value){
        int newMin = Math.min(value, min());
        super.push(new NodeWithMin(value, newMin));
    }

    public int min(){
        if (this.isEmpty()){
            return Integer.MAX_VALUE;
        } else {
            return peek().min;
        }
    }

    public static void main(String[] args) {
        Q2StackMinV1 stack = new Q2StackMinV1();
        int[] array = {2, 1, 3, 1};
        for (int value : array) {
            stack.push(value);
            System.out.print(value + ", ");
        }
        System.out.println('\n');
        for (int i = 0; i < array.length; i++) {
            System.out.println("Popped " + stack.pop().value);
            System.out.println("New min is " + stack.min());
        }
    }
}

class NodeWithMin{
    public int value;
    public int min;

    public NodeWithMin(int v, int min){
        this.value = v;
        this.min = min;
    }
}