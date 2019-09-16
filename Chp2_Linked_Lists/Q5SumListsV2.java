import CtCILibrary.LinkedListNode;

public class Q5SumListsV2 {
    private class PartialSum{
        public LinkedListNode sum = null;
        public int carry = 0;
    }

    private static int length(LinkedListNode l){
        if (l == null)
            return 0;
        else
            return 1 + length(l.next);
    }

    /* Helper function to insert node in the front of a linked list */
    private static LinkedListNode insertBefore(LinkedListNode l, int data){
        LinkedListNode node = new LinkedListNode(data);
        if (l != null)
            node.next = l;
        return node;
    }

    /* Pad the list with 0s*/
    private static LinkedListNode padList(LinkedListNode l, int padding){
        LinkedListNode head = l;
        for (int i=0; i<padding; i++)
            head = insertBefore(l, 0);
        return head;
    }

    private PartialSum addListHelper(LinkedListNode l1, LinkedListNode l2){
        if (l1 == null && l2 == null){
            PartialSum sum = new PartialSum();
            return sum;
        }

        /* Add smaller digits recursively */
        PartialSum sum = addListHelper(l1.next, l2.next);

        /* Add carry to current data */
        int val = sum.carry + l1.data + l2.data;

        /* Insert sum of current digits */
        LinkedListNode full_result = insertBefore(sum.sum, val % 10);

        /* Return sum so far, and the carry value */
        sum.sum = full_result;
        sum.carry = val /10;
        return sum;
    }

    public LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2){
        int len1 = length(l1);
        int len2 = length(l2);

        /* Pad the shorter list with 0s*/
        if (len1 < len2)
            l1 = padList(l1, len2-len1);
        else
            l2 = padList(l2, len1-len2);

        PartialSum sum = addListHelper(l1, l2);

        /* If there is a carry value left over, insert this at the front of the list.
        * Otherwise, just return the linked list. */
        if (sum.carry == 0)
            return sum.sum;
        else {
            LinkedListNode result = insertBefore(sum.sum, sum.carry);
            return result;
        }
    }

    public static void main(String[] args) {
        LinkedListNode lA1 = new LinkedListNode(3, null, null);
        LinkedListNode lA2 = new LinkedListNode(1, null, lA1);

        LinkedListNode lB1 = new LinkedListNode(5, null, null);
        LinkedListNode lB2 = new LinkedListNode(9, null, lB1);
        LinkedListNode lB3 = new LinkedListNode(1, null, lB2);

        Q5SumListsV2 addList = new Q5SumListsV2();
        LinkedListNode list3 = addList.addLists(lA1, lB1);

        System.out.println("  " + lA1.printForward());
        System.out.println("+ " + lB1.printForward());
        System.out.println("= " + list3.printForward());
    }
}
