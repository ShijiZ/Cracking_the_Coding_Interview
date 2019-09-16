import java.util.Stack;

public class Q2StackMinV2 extends Stack<Integer> {
    Stack<Integer> s2;

    public Q2StackMinV2(){
        s2 = new Stack<>();
    }

    public void push(int value){
        if (value <= min()){
            s2.push(value);
        }
        super.push(value);
    }

    public Integer pop(){
        int value = super.pop();
        if (value == min()){
            s2.pop();
        }
        return value;
    }

    public int min(){
        if (s2.isEmpty()){
            return Integer.MAX_VALUE;
        } else {
            return s2.peek();
        }
    }

    public static void main(String[] args) {
        Q2StackMinV2 stack = new Q2StackMinV2();
        int[] array = {2, 1, 3, 1};
        for (int value : array) {
            stack.push(value);
            System.out.print(value + ", ");
        }
        System.out.println('\n');
        for (int i = 0; i < array.length; i++) {
            System.out.println("Popped " + stack.pop());
            System.out.println("New min is " + stack.min());
        }
    }
}
