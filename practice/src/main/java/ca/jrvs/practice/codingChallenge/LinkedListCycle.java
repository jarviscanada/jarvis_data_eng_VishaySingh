package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/LinkedList-Cycle-bbdbfc8e4e8e4240a4c3180e4a7bbaa5
 */
public class LinkedListCycle {

    /**
     * Big-O Analysis: O(n) time worst-case, O(1) space worst-case.
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
