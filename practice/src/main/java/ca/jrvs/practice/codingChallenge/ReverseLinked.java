package ca.jrvs.practice.codingChallenge;


/**
 * https://www.notion.so/jarvisdev/Reverse-Linked-List-3e0460c4dd024e8bbb3e4c064942c971
 */
public class ReverseLinked {

    /**
     * Big-O Analysis: O(n) time worst-case, O(1) space worst-case.
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while(head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    /**
     * Big-O Analysis: O(n) time worst-case, O(n) space worst-case (due to callstack).
     * @param head
     * @return
     */
    public ListNode reverseListRec(ListNode head) {
        return helper(head, null);
    }

    private ListNode helper(ListNode head, ListNode prev) {
        if (head == null) return prev;

        ListNode next = head.next;
        head.next = prev;
        return helper(next, head);
    }
}
