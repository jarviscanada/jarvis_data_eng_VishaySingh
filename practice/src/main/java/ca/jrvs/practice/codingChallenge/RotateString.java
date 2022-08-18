package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/Rotate-String-0a608a5c8249435c96829184510a12fd
 */
public class RotateString {

  /**
   * Big-O Analysis: O(n^2) time worst-case, O(n) space worst-case.
   *
   * @param s
   * @param goal
   * @return
   */
  public boolean rotateString(String s, String goal) {
    return s.length() == goal.length() && (s + s).contains(goal);
  }
}
