package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DuplicateNodeTest {

    private DuplicateNode dup = new DuplicateNode();

    @Test
    public void removeDuplicateNodes() {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(3))));
        ListNode newHead = dup.removeDuplicateNodes(head);
        List<Integer> lst = new ArrayList<>();
        while(newHead != null) {
            lst.add(newHead.val);
            newHead = newHead.next;
        }
        List<Integer> lst2 = new ArrayList<>();
        lst2.add(1);
        lst2.add(2);
        lst2.add(3);
        assertArrayEquals(lst2.toArray(), lst.toArray());
    }
}