package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack2 {

  private final Queue<Integer> q1 = new LinkedList<>();

  /**
   * Big-O Analysis: Push O(n) time worst-case, O(n) space worst-case. Pop O(1) time worst-case,
   * O(1) space worst-case.
   */
  public MyStack2() {

  }

  public void push(int x) {
    q1.add(x);
    int s = q1.size();
    while (s > 1) {
      q1.add(q1.remove());
      s--;
    }
  }

  public int pop() {
    return q1.remove();
  }

  public int top() {
    return q1.peek();
  }

  public boolean empty() {
    return q1.isEmpty();
  }
}
