import CtCILibrary.LinkedListNode;

public class Q4PartitionV1 {
    /*Pass in the head of the linked list and the value to partition around */
    public static LinkedListNode partition(LinkedListNode node, int x){
        LinkedListNode beforeStart = null;
        LinkedListNode beforeEnd = null;
        LinkedListNode afterStart = null;
        LinkedListNode afterEnd = null;

        /* Partition list */
        while (node != null){
            LinkedListNode next = node.next;
            node.next = null;
            if (node.data < x){
                /* Insert node into end of before list */
                if (beforeStart == null){
                    beforeStart = node;
                    beforeEnd = beforeStart;
                }
                else {
                    beforeEnd.next = node;
                    beforeEnd = node;
                }
            }
            else {
                /* Insert node into end of after list */
                if (afterStart == null){
                    afterStart = node;
                    afterEnd = afterStart;
                }
                else {
                    afterEnd.next = node;
                    afterEnd = node;
                }
            }
            node = next;
        }

        if (beforeStart == null) // The before list is empty
            return afterStart;

        /* Merge before list and after list */
        beforeEnd.next = afterStart;
        return beforeStart;
    }

    public static void main(String[] args) {
        /* Create linked list */
        int[] vals = {33,9,2,3,10,10389,838,874578,5};
        LinkedListNode head = new LinkedListNode(vals[0], null, null);
        LinkedListNode current = head;
        for (int i = 1; i < vals.length; i++) {
            current = new LinkedListNode(vals[i], null, current);
        }
        System.out.println(head.printForward());

        /* Partition */
        LinkedListNode h = partition(head, 3);

        /* Print Result */
        System.out.println(h.printForward());
    }
}
