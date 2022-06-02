package ca.jrvs.practice.codingChallenge;

/**
 * Ticket_URL:
 * https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-5430c8ca8ac94ee6852325312d19f63c
 * Online Judge: https://leetcode.com/problems/climbing-stairs/
 */
public class ClimbStairs {

  public ClimbStairs() {
  }

  /**
   * Dynamic Programming Climb Stairs Implementation (See Fibonacci for details)
   *
   * @param n
   * @return
   */
  public int climbStairs(int n) {
    Fibonacci fibonacci = new Fibonacci();
    return fibonacci.fib(n + 1);
  }

  /**
   * Recursive Climb Stairs Implementation
   *
   * @param n
   * @return
   */
  public int climbStairsRec(int n) {
    Fibonacci fibonacci = new Fibonacci();
    return fibonacci.fibRec(n + 1);
  }
}
