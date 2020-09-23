package Chp3_Stacks_and_Queues;

import CtCILibrary.AssortedMethods;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Q4QueueViaStacks<Item> {
    Stack<Item> stackNewest, stackOldest;

    public Q4QueueViaStacks(){
        stackNewest = new Stack<>();
        stackOldest = new Stack<>();
    }

    public int size(){
        return stackOldest.size() + stackNewest.size();
    }

    /* Push onto stackNewest, which always has the newest elements on top.*/
    public void add(Item value){
        stackNewest.push(value);
    }

    /* Move elements from stackNewst into stackOldest. This is usually done so that
    * we can do operations on stackOldest. */
    private void shiftStacks(){
        if (stackOldest.isEmpty()){
            while (!stackNewest.isEmpty()){
                stackOldest.push(stackNewest.pop());
            }
        }
    }

    public Item peek(){
        shiftStacks(); // Ensure stackOldest has the current elements
        return stackOldest.peek();
    }

    public Item remove(){
        shiftStacks();
        return stackOldest.pop();
    }

    public static void main(String[] args) {
        Q4QueueViaStacks<Integer> my_queue = new Q4QueueViaStacks<>();

        // Let's test our code against a "real" queue
        Queue<Integer> test_queue = new LinkedList<>();

        for (int i = 0; i < 100; i++) {
            int choice = AssortedMethods.randomIntInRange(0, 10);
            if (choice <= 5) { // enqueue
                int element = AssortedMethods.randomIntInRange(1, 10);
                test_queue.add(element);
                my_queue.add(element);
                System.out.println("Enqueued " + element);
            } else if (test_queue.size() > 0) {
                int top1 = test_queue.remove();
                int top2 = my_queue.remove();
                if (top1 != top2) { // Check for error
                    System.out.println("******* FAILURE - DIFFERENT TOPS: " + top1 + ", " + top2);
                }
                System.out.println("Dequeued " + top1);
            }

            if (test_queue.size() == my_queue.size()) {
                if (test_queue.size() > 0 && test_queue.peek() != my_queue.peek()) {
                    System.out.println("******* FAILURE - DIFFERENT TOPS: " + test_queue.peek() + ", " + my_queue.peek() + " ******");
                }
            } else {
                System.out.println("******* FAILURE - DIFFERENT SIZES ******");
            }
        }
    }


}
