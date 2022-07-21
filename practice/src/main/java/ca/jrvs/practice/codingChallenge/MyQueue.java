package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

/**
 * https://www.notion.so/jarvisdev/Implement-Queue-using-Stacks-5e136a0e13bf49a0b1b294c125539c3a
 */
class MyQueue {

  private final Stack<Integer> stack1 = new Stack<>();
  private final Stack<Integer> stack2 = new Stack<>();

  /**
   * Big-O Analysis: Push O(n) time worst-case, O(n) space worst-case, Pop O(1) time worst-case,
   * O(1) space worst-case
   */
  public MyQueue() {

  }

  public void push(int x) {
    while (!stack1.isEmpty()) {
      stack2.push(stack1.pop());
    }
    stack2.push(x);
    while (!stack2.isEmpty()) {
      stack1.push(stack2.pop());
    }
  }

  public int pop() {
    return stack1.pop();
  }

  public int peek() {
    return stack1.peek();
  }

  public boolean empty() {
    return stack1.isEmpty();
  }
}

/**
 * Your MyQueue object will be instantiated and called as such: MyQueue obj = new MyQueue();
 * obj.push(x); int param_2 = obj.pop(); int param_3 = obj.peek(); boolean param_4 = obj.empty();
 */
