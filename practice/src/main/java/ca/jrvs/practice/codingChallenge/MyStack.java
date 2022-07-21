package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.notion.so/jarvisdev/Implement-Stack-using-Queue-e185fbe6a0b642289db9dc1bed9824c9
 */
class MyStack {

  private Queue<Integer> q1 = new LinkedList<>();
  private Queue<Integer> q2 = new LinkedList<>();
  private int top;

  /**
   * Big-O Analysis: Push O(1) time worst-case, O(1) space worst-case. Pop O(n) time worst-case,
   * O(n) space worst-case.
   */
  public MyStack() {

  }

  public void push(int x) {
    q1.add(x);
    top = x;
  }

  public int pop() {
    while (q1.size() > 1) {
      top = q1.remove();
      q2.add(top);
    }
    int x = q1.remove();
    Queue<Integer> temp = q1;
    q1 = q2;
    q2 = temp;
    return x;
  }

  public int top() {
    return top;
  }

  public boolean empty() {
    return q1.isEmpty();
  }
}

/**
 * Your MyStack object will be instantiated and called as such: MyStack obj = new MyStack();
 * obj.push(x); int param_2 = obj.pop(); int param_3 = obj.top(); boolean param_4 = obj.empty();
 */
