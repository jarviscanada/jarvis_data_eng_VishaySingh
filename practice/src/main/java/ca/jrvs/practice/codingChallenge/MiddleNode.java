package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/Middle-of-the-Linked-List-8019684c09e144b983e97485a3613de1
 */
public class MiddleNode {

  /**
   * Big-O Analysis: O(n) time worst case. O(1) space worst case.
   * @param head
   * @return
   */
  public ListNode middleNode(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode slow = head;
    ListNode fast = head;

    while (fast != null) {
      fast = fast.next;
      if (fast != null) {
        fast = fast.next;
        slow = slow.next;
      }
    }
    return slow;
  }
}
