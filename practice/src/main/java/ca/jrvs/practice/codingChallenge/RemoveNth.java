package ca.jrvs.practice.codingChallenge;

public class RemoveNth {

  /**
   * Big-O Analysis: O(n) time worst case. O(1) space worst case.
   *
   * @param head
   * @param n
   * @return
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null || head.next == null) {
      return null;
    }
    ListNode fast = head;
    ListNode slow = head;

    for (int i = 0; i < n; i++) {
      fast = fast.next;
    }

    if (fast == null) {
      return head.next;
    }

    while (fast.next != null) {
      fast = fast.next;
      slow = slow.next;
    }
    slow.next = slow.next.next;
    return head;
  }
}
