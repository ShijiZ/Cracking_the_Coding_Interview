import java.util.EmptyStackException;
import CtCILibrary.AssortedMethods;

public class Q1ThreeInOneV2 {

    /* StackInfo is a simple class that holds a set of data about
     * each stack. It does not hold the actual items in the stack.
     * We could have done this with just a bunch of individual
     * variables, but that’s messy and doesn’t gain us much. */
    private class StackInfo{
        public int start, size, capacity;
        public StackInfo(int start, int capacity){
            this.start = start;
            this.capacity = capacity;
        }

        /* Check if an index on the full array is within the stack boundaries. The
        * stack can wrap around to the start of the array*/
        public boolean isWithinStackCapacity(int index){
            /* If outside of bounds of array, return false. */
            if (index < 0 || index >= values.length) return false;

            /* If index wraps around, adjust it. */
            int contiguousIndex = index < start ? index + values.length : index;
            int end = start + capacity;
            return start <= contiguousIndex && contiguousIndex < end;
        }

        public int lastCapacityIndex(){
            return adjustIndex(start + capacity - 1);
        }

        public int lastElementIndex(){
            return adjustIndex(start + size - 1);
        }

        public boolean isFull(){
            return size == capacity;
        }

        public boolean isEmpty(){
            return size == 0;
        }
    }

    private StackInfo[] info;
    private int[] values;

    public Q1ThreeInOneV2(int numberOfStacks, int defaultSize){
        /* Create metadata for all the stacks. */
        info = new StackInfo[numberOfStacks];
        for (int i=0; i<numberOfStacks; i++){
            info[i] = new StackInfo(defaultSize * i, defaultSize);
        }
        values = new int[numberOfStacks * defaultSize];
    }

    /* Push value onto stack num, shifting/expanding stacks as necessary. Throws
    * exception if all stacks are full. */
    public void push(int stackNum, int value) throws FullStackException{
        if (allStacksAreFull()){
            throw new FullStackException();
        }

        /* if this stack is full, expand it. */
        StackInfo stack = info[stackNum];
        if (stack.isFull()){
            expand(stackNum);
        }

        /* Find the index of the top element in the array + 1, and increment the
        * stack pointer */
        stack.size++;
        values[stack.lastElementIndex()] = value;
    }

    /* Remove value from stack. */
    public int pop(int stackNum) throws Exception{
        StackInfo stack = info[stackNum];
        if (stack.isEmpty()){
            throw new EmptyStackException();
        }

        /* Remove the last element. */
        int value = values[stack.lastElementIndex()];
        values[stack.lastElementIndex()] = 0;
        stack.size--;
        return value;
    }

    /* Get top element of stack.*/
    public int peek(int stackNum){
        StackInfo stack = info[stackNum];
        return values[stack.lastElementIndex()];
    }

    /* Shift items in stack over by one element. If we have available capacity, then
    * we'll end up shrinking the stack by one element. If we don't have available
    * capacity, then we'll need to shift the next stack over too. */
    private void shift(int stackNum){
        System.out.println("/// Shifting " + stackNum);
        StackInfo stack = info[stackNum];

        /* If this stack is at its full capacity, then you need to move the next
        * stack over by one element. This stack can now claim the freed index. */
        if (stack.size > stack.capacity){
            int nextStack = (stackNum + 1) % info.length;
            shift(nextStack);
            stack.capacity++; // claim inex that next stack lost
        }

        /* Shift all elements in stack over by one. */
        int index = stack.lastCapacityIndex();
        while (stack.isWithinStackCapacity(index)){
            values[index] = values[previousIndex(index)];
            index = previousIndex(index);
        }

        /* Adjust stack data. */
        values[stack.start] = 0;
        stack.start = nextIndex(stack.start);
        stack.capacity--;
    }

    /* Expand stack by shifting over other stacks */
    private void expand(int stackNum){
        shift((stackNum + 1) % info.length);
        info[stackNum].capacity++;
    }

    /* Return the number of items actually present in stack. */
    public int numberOfElements(){
        int size = 0;
        for (StackInfo sd : info){
            size += sd.size;
        }
        return size;
    }

    /* Returns true if all the stacks are full. */
    public boolean allStacksAreFull(){
        return numberOfElements() == values.length;
    }

    /* Adjust index to be within the range of 0 -> length - 1. */
    private int adjustIndex(int index){
        /* Java's mod operator can return neg values. */
        int max = values.length;
        return ((index % max) + max) % max;
    }

    /* Get index after this index. adjusted for wrap around. */
    private int nextIndex(int index){
        return adjustIndex(index+1);
    }

    /* Get index before this index, adjusted for wrap around. */
    private int previousIndex(int index){
        return adjustIndex(index-1);
    }

    public int[] getValues() {
        return values;
    }

    public class FullStackException extends Exception {
        private static final long serialVersionUID = 1L;

        public FullStackException(){
            super();
        }

        public FullStackException(String message){
            super(message);
        }
    }


    public static void printStacks(Q1ThreeInOneV2 stacks) {
        //System.out.println(stacks.stackToString(0));
        //System.out.println(stacks.stackToString(1));
        //System.out.println(stacks.stackToString(2));
        System.out.println(AssortedMethods.arrayToString(stacks.getValues()));
    }
    public static void main(String [] args) throws Exception  {
        Q1ThreeInOneV2 stacks = new Q1ThreeInOneV2(3, 4);
        printStacks(stacks);
        stacks.push(0, 10);
        printStacks(stacks);
        stacks.push(1, 20);
        printStacks(stacks);
        stacks.push(2, 30);
        printStacks(stacks);
        stacks.push(1, 21);
        printStacks(stacks);
        stacks.push(0, 11);
        printStacks(stacks);
        stacks.push(0, 12);
        printStacks(stacks);
        stacks.pop(0);
        printStacks(stacks);
        stacks.push(2, 31);
        printStacks(stacks);
        stacks.push(0, 13);
        printStacks(stacks);
        stacks.push(1, 22);
        printStacks(stacks);
        stacks.push(2, 31);
        printStacks(stacks);
        stacks.push(2, 32);
        printStacks(stacks);
        stacks.push(2, 33);
        printStacks(stacks);
        stacks.push(2, 34);
        printStacks(stacks);
        stacks.pop(1);
        printStacks(stacks);
        stacks.push(2, 35);
        printStacks(stacks);
        System.out.println("Final Stack: " + AssortedMethods.arrayToString(stacks.getValues()));
    }
}


