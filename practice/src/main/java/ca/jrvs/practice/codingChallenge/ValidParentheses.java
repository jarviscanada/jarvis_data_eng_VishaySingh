package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

public class ValidParentheses {

  /**
   * Big-o Analysis: O(n) time worst-case. O(n) space worst-case.
   * @param s
   * @return
   */
  public boolean isValid(String s) {
    if ((s.length() & 1) == 1) {
      return false;
    }

    Stack<Character> stack = new Stack();
    for (char c : s.toCharArray()) {
      switch (c) {
        case '(':
          stack.push(')');
          break;
        case '{':
          stack.push('}');
          break;
        case '[':
          stack.push(']');
          break;
        default:
          if (stack.isEmpty() || stack.pop() != c) {
            return false;
          }
          break;
      }
    }

    if (!stack.isEmpty()) {
      return false;
    }
    return true;
  }
}
