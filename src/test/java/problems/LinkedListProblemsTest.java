package problems;

import model.ListNode;
import org.junit.Assert;
import org.junit.Test;

public class LinkedListProblemsTest {

    LinkedListProblems linkedListProblems = new LinkedListProblems();
    @Test
    public void testReverse() {
        ListNode listNode = new ListNode(10, null);
        ListNode listNode1 = new ListNode(5, listNode);
        ListNode listNode2 = new ListNode(7, listNode1);
        ListNode listNode3 = new ListNode(8, listNode2);
        ListNode listNode4 = new ListNode(9, listNode3);

        ListNode listNode5 = linkedListProblems.reverse(listNode4);

        Assert.assertTrue(listNode5.value == 10);
    }
}
