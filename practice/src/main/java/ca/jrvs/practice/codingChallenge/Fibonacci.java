package ca.jrvs.practice.codingChallenge;

/**
 * Ticket_URL:
 * https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-5430c8ca8ac94ee6852325312d19f63c
 * Online Judge: https://leetcode.com/problems/fibonacci-number/
 */
public class Fibonacci {

  public Fibonacci() {
  }

  /**
   * Dynamic Programming Fibonacci Implementation
   * <p>
   * Big-O Time Complexity: This is bounded by O(n) time since we loop n - 1 times, and all other
   * operations can be considered to be O(1).
   * <p>
   * Big-O Space Complexity: We do not store n elements at all here, so we can say that we are
   * bounded by O(1) space complexity.
   *
   * @param n
   * @return
   */
  public int fib(int n) {
    if (n == 0) {
      return 0;
    }
    int prev = 0;
    int curr = 1;
    int newFib;
    for (int i = 0; i < n - 1; i++) {
      newFib = prev + curr;
      prev = curr;
      curr = newFib;
    }
    return curr;
  }

  /**
   * Recursive Fibonacci Implementation
   * <p>
   * Big-O Time Complexity: This is bounded by O(2^n) time due to repeated recursive calls
   * <p>
   * Big-O Space Complexity: This is bounded by O(n) space due to the height of the recursion tree
   *
   * @param n
   * @return
   */
  public int fibRec(int n) {
    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }
    return fibRec(n - 1) + fibRec(n - 2);
  }
}
