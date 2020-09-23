package Chp3_Stacks_and_Queues;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class Q3StackOfPlatesV1 {
    ArrayList<Stack> stacks = new ArrayList<>();
    public int capacity;

    public Q3StackOfPlatesV1(int capacity){
        this.capacity = capacity;
    }

    private Stack getLastStack(){
        if (stacks.size() == 0) return null;
        return stacks.get(stacks.size() - 1);
    }

    public void push(int v){
        Stack last = getLastStack();
        if (last != null && !(last.size() > capacity))
            last.push(v);
        else{ // must create new stack
            Stack stack = new Stack();
            stack.push(v);
            stacks.add(stack);
        }
    }

    public int pop(){
        Stack<Integer> last = getLastStack();
        if (last == null)
            throw new EmptyStackException();
        int v = last.pop();
        if (last.size() == 0)
            stacks.remove(stacks.size()-1);
        return v;
    }

    public static void main(String[] args) {
        int capacity_per_substack = 5;
        Q3StackOfPlatesV1 set = new Q3StackOfPlatesV1(capacity_per_substack);
        for (int i = 0; i < 34; i++) {
            set.push(i);
        }
        for (int i = 0; i < 35; i++) {
            System.out.println("Popped " + set.pop());
        }
    }


}
