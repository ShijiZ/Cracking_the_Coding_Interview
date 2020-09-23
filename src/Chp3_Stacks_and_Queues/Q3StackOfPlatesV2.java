package Chp3_Stacks_and_Queues;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class Q3StackOfPlatesV2 {
    ArrayList<Stack> stacks = new ArrayList<>();
    public int capacity;

    public Q3StackOfPlatesV2(int capacity){
        this.capacity = capacity;
    }

    private Stack getLastStack(){
        if (stacks.size() == 0) return null;
        return stacks.get(stacks.size() - 1);
    }

    public void push(int v){
        Stack last = getLastStack();
        if (last != null && !last.isFull())
            last.push(v);
        else { // must create new stack
            Stack stack = new Stack(capacity);
            stack.push(v);
            stacks.add(stack);
        }
    }

    public int pop(){
        Stack last = getLastStack();
        if (last == null)
            throw new EmptyStackException();
        int v = last.pop();
        if (last.isEmpty())
            stacks.remove(stacks.size()-1);
        return v;
    }

    public int popAt(int index){
        return leftShift(index, true);
    }

    private int leftShift(int index, boolean removeTop){
        Stack stack = stacks.get(index);
        int removed_item;

        if (removeTop)
            removed_item = stack.pop();
        else
            removed_item = stack.removeBottom();

        if (stack.isEmpty())
            stacks.remove(index);
        else if (stacks.size() > index+1){
            int v = leftShift(index+1, false);
            stack.push(v);
        }

        return removed_item;
    }

    public static void main(String[] args) {
        int capacity_per_substack = 5;
        Q3StackOfPlatesV2 set = new Q3StackOfPlatesV2(capacity_per_substack);
        for (int i = 0; i < 14; i++) {
            set.push(i);
        }

        set.popAt(1);

        for (int i = 0; i < 14; i++) {
            System.out.println("Popped " + set.pop());
        }
    }
}

class Stack{
    private class Node{
        int value;
        Node above;
        Node below;

        public Node(int value){
            this.value = value;
        }
    }

    private int capacity;
    public Node top, bottom;
    public int size = 0;

    public Stack(int capacity){
        this.capacity = capacity;
    }

    public boolean isFull(){
        return capacity == size;
    }

    public boolean isEmpty(){ return size==0; }

    private void join(Node above, Node below){
        if (below != null) below.above = above;
        if (above != null) above.below = below;
    }

    public void push(int v){
        if (size >= capacity) {
            System.out.println("Push failed.");
            return;
        }
        size++;
        Node n = new Node(v);
        if (size == 1) bottom = n;
        join(n, top);
        top = n;
    }

    public int pop(){
        if (top == null) throw new EmptyStackException();
        Node t = top;
        top = top.below;
        if (top != null) top.above = null;
        size--;
        return t.value;
    }

    public int removeBottom(){
        Node b = bottom;
        bottom = bottom.above;
        if (bottom != null) bottom.below = null;
        size--;
        return b.value;
    }
}
