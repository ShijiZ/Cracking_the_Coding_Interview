import java.util.EmptyStackException;
import CtCILibrary.AssortedMethods;

public class Q1ThreeInOneV1 {
    private int numberOfStacks = 3;
    private int stackCapacity;
    private int[] values;
    private int[] sizes;

    public Q1ThreeInOneV1(int stackCapacity){
        this.stackCapacity = stackCapacity;
        this.values = new int[numberOfStacks*stackCapacity];
        this.sizes = new int[numberOfStacks];
    }

    /* Push value onto stack */
    public void push(int stackNum, int value) throws FullStackException{
        /* Check that we have space for the next element */
        if (isFull(stackNum)){
            throw new FullStackException();
        }

        /* Increment stack pointer and then update top value. */
        sizes[stackNum]++;
        values[indexOfTop(stackNum)] = value;
    }

    /* Pop item from top stack. */
    public int pop(int stackNum){
        if (isEmpty(stackNum)){
            throw new EmptyStackException();
        }

        int topIndex = indexOfTop(stackNum);
        int value = values[topIndex];
        values[topIndex] = 0;
        sizes[stackNum]--;
        return value;
    }

    /* Return top element. */
    public int peek(int stackNum){
        if (isEmpty(stackNum)){
            throw new EmptyStackException();
        }
        return values[indexOfTop(stackNum)];
    }

    /* Return if stack is empty. */
    public boolean isEmpty(int stackNum){
        return sizes[stackNum] == 0;
    }

    /* return if stack is full. */
    public boolean isFull(int stackNum){
        return sizes[stackNum] == stackCapacity;
    }

    /* returns index of the top of the stack. */
    private int indexOfTop(int stackNum){
        int offset = stackNum * stackCapacity;
        int size = sizes[stackNum];
        return offset + size - 1;
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

    public static void printStacks(Q1ThreeInOneV1 stacks) {
        System.out.println(AssortedMethods.arrayToString(stacks.getValues()));
    }

    public static void main(String [] args) throws Exception  {
        Q1ThreeInOneV1 stacks = new Q1ThreeInOneV1(4);
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
    }

}
