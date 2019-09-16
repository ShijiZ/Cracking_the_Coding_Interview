import CtCILibrary.LinkedListNode;

public class Q5SumListsV1 {
    public static LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2, int carry){
        if (l1 == null && l2 == null && carry == 0)
            return null;

        LinkedListNode result = new LinkedListNode();
        int value = carry;
        if (l1 != null){
            value += l1.data;
        }
        if (l2 != null){
            value += l2.data;
        }
        result.data = value % 10; /* Second digit of number */

        /* Recurse */
        if (l1 != null || l2 != null){
            LinkedListNode more = addLists(
                    l1 == null ? null : l1.next,
                    l2 == null ? null : l2.next,
                    value >= 10 ? 1 : 0);
            result.setNext(more);
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedListNode lA1 = new LinkedListNode(9, null, null);
        LinkedListNode lA2 = new LinkedListNode(9, null, lA1);
        LinkedListNode lA3 = new LinkedListNode(9, null, lA2);

        LinkedListNode lB1 = new LinkedListNode(1, null, null);
        LinkedListNode lB2 = new LinkedListNode(0, null, lB1);
        LinkedListNode lB3 = new LinkedListNode(0, null, lB2);

        LinkedListNode list3 = addLists(lA1, lB1, 0);

        System.out.println("  " + lA1.printForward());
        System.out.println("+ " + lB1.printForward());
        System.out.println("= " + list3.printForward());
    }
}
