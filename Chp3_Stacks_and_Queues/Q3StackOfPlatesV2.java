import java.util.ArrayList;
import java.util.EmptyStackException;

public class Q3StackOfPlatesV2 {
    ArrayList<Stack> stacks = new ArrayList<>();
    public int capacity;

    public Q3StackOfPlatesV2(int capacity){
        this.capacity = capacity;
    }

    public Stack getLastStack(){
        if (stacks.size() == 0) return null;
        return stacks.get(stacks.size() - 1);
    }

    public void push(int v){
        Stack last = getLastStack();
        if (last != null && !last.isFull())
            last.push(v);
        else{ // must create new stack
            Stack stack = new Stack(capacity);
            stack.push(v);
            stacks.add(stack);
        }
    }

    public int pop(){
        Stack last = getLastStack();
        if (last == null)
            throw new EmptyStackException();
        int v = (int) last.pop();
        if (last.isEmpty())
            stacks.remove(stacks.size()-1);
        return v;
    }

    public int popAt(int index){
        return leftShift(index, true);
    }

    public int leftShift(int index, boolean removeTop){
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
        Node next;
        Node prev;

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

    public void join(Node above, Node below){
        if (below != null) below.prev = above;
        if (above != null) above.next = below;
    }

    public boolean push(int v){
        if (size >= capacity) return false;
        size++;
        Node n = new Node(v);
        if (size == 1) bottom = n;
        join(n,top);
        top = n;
        return true;
    }

    public int pop(){
        Node t = top;
        top = top.next;
        top.prev = null;
        size--;
        return t.value;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int removeBottom(){
        Node b = bottom;
        bottom = bottom.prev;
        if (bottom != null) bottom.next = null;
        size--;
        return b.value;
    }
}
