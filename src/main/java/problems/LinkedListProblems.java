package problems;

import model.ListNode;

public class LinkedListProblems {

    public int getListLength(ListNode listNode) {
        int size = 0;

        ListNode temp = listNode;

        while (temp != null) {
            temp = temp.next;
            size++;
        }

        return size;
    }

    // A -> B -> C -> D
    ListNode reverse(ListNode listNode) {
        if (listNode == null || listNode.next == null) {
            return listNode;
        }

        ListNode temp = listNode;
        ListNode previous = null;

        while (temp != null) {
            if (previous != null) {
                ListNode swap = temp.next;
                temp.next = previous;
                previous = temp;
                temp = swap;
            } else {
                previous = temp;
                temp = temp.next;
                previous.next = null;
            }
        }

        return previous;
    }

    ListNode merge(ListNode list1, ListNode list2) {
        ListNode smaller;
        ListNode bigger;

        if (list1.value > list2.value) {
            smaller = list2;
            bigger = list1;
        } else {
            smaller = list1;
            bigger = list2;
        }

        while (smaller != null) {

            

        }

        return smaller;
    }
}
