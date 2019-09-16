import CtCILibrary.LinkedListNode;
import java.util.Stack;

public class Q6PalindromeV2 {
    public static boolean isPalindrome(LinkedListNode head){
        LinkedListNode fast = head;
        LinkedListNode slow = head;

        Stack<Integer> stack = new Stack<>();

        /* Push elements from first half of linked list onto stack. When fast runner
        * (which is moving at 2x speed) reaches the end of the linked list, then we
        * know we're at the middle*/
        while (fast != null && fast.next != null){
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }

        /* Has odd number of elements, so skip the middle element */
        if (fast != null){
            slow = slow.next;
        }

        while (slow != null){
            int top = stack.pop().intValue();

            /* If values are different, then it's not a palindrome */
            if (top != slow.data)
                return false;
            slow = slow.next;
        }
        return true;
    }

    public static void main(String[] args) {
        int length = 9;
        LinkedListNode[] nodes = new LinkedListNode[length];
        for (int i = 0; i < length; i++) {
            nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null, null);
        }

        for (int i = 0; i < length; i++) {
            if (i < length - 1) {
                nodes[i].setNext(nodes[i + 1]);
            }
            if (i > 0) {
                nodes[i].setPrevious(nodes[i - 1]);
            }
        }
        // nodes[length - 2].data = 9; // Uncomment to ruin palindrome

        LinkedListNode head = nodes[0];
        System.out.println(head.printForward());
        System.out.println(isPalindrome(head));
    }
}
