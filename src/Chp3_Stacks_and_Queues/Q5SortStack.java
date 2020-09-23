package Chp3_Stacks_and_Queues;

import CtCILibrary.AssortedMethods;

import java.util.Stack;

public class Q5SortStack {
    public static void sort(Stack<Integer> s){
        Stack<Integer> r = new Stack<>();
        while (!s.isEmpty()){
            /* Insert each element in s in sorted order into r. */
            int tmp = s.pop();
            while (!r.isEmpty() && (r.peek() > tmp))
                s.push(r.pop());

            r.push(tmp);
        }

        /* Copy the elements from r back into s. */
        while (!r.isEmpty())
            s.push(r.pop());
    }

    public static void main(String[] args){
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < 10; i++) {
            int r = AssortedMethods.randomIntInRange(0,  1000);
            s.push(r);
        }

        sort(s);

        while(!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }
}
