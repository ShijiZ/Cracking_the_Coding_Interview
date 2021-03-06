package Chp2_Linked_Lists;

import java.util.HashSet;
import CtCILibrary.LinkedListNode;

public class Q1RemoveDupsV1 {
    public static void deleteDups(LinkedListNode n){
        HashSet<Integer> set = new HashSet<>();
        LinkedListNode previous = null;

        while (n!=null){
            if (set.contains(n.data)){
                previous.next = n.next;
            }
            else{
                set.add(n.data);
                previous = n;
            }
            n = n.next;
        }
    }

    public static void main(String[] args){
        LinkedListNode first = new LinkedListNode(0, null, null);
        LinkedListNode head = first;
        LinkedListNode second;
        for (int i=1; i<8; i++){
            second = new LinkedListNode(i%2, null, null);
            first.setNext(second);
            second.setPrevious(first);
            first = second;
        }
        System.out.println(head.printForward());
        deleteDups(head);
        System.out.println(head.printForward());
    }
}
