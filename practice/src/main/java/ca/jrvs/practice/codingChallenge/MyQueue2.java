package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

class MyQueue2 {

  private final Stack<Integer> stack1 = new Stack<>();
  private final Stack<Integer> stack2 = new Stack<>();
  private int front;

  /**
   * Big-O Analysis: Push O(1) time worst-case, O(1) space worst-case, Pop O(1) amortized time
   * worst-case, O(1) amortized space worst-case
   */
  public MyQueue2() {

  }

  public void push(int x) {
    if (stack1.isEmpty()) {
      front = x;
    }
    stack1.push(x);
  }

  public int pop() {
    if (stack2.isEmpty()) {
      while (!stack1.isEmpty()) {
        stack2.push(stack1.pop());
      }
    }
    return stack2.pop();
  }

  public int peek() {
    if (stack2.isEmpty()) {
      return front;
    }
    return stack2.peek();
  }

  public boolean empty() {
    if (stack2.isEmpty()) {
      return stack1.isEmpty();
    }
    return false;
  }
}

/**
 * Your MyQueue object will be instantiated and called as such: MyQueue obj = new MyQueue();
 * obj.push(x); int param_2 = obj.pop(); int param_3 = obj.peek(); boolean param_4 = obj.empty();
 */
