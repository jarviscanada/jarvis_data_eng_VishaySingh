package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.Set;

/**
 * https://www.notion.so/jarvisdev/Duplicate-LinkedList-Node-5350b017d7ed456b9dea15ab5e23ae09
 */
public class DuplicateNode {

    /**
     * Big-O Analysis: O(n) time worst-case, O(n) space worst-case.
     * @param node
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode node) {
        if (node.next == null) return node;
        Set<Integer> seen = new HashSet<>();
        ListNode head = node;

        seen.add(node.val);
        ListNode prev = node;
        node = node.next;
        while (node != null) {
            if (seen.contains(node.val)) {
                //remove duplicate
                ListNode next = node.next;
                node.next = null;
                prev.next = next;
                node = next;
            } else {
                seen.add(node.val);
                prev = prev.next;
                node = node.next;
            }
        }

        return head;
    }
}
