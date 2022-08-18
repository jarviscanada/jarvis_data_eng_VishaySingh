package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/Sample-Check-if-a-number-is-even-or-odd-485a5b53728040b9b44311db7fc32d29
 */
public class EvenOdd {

  /**
   * Modulo Implementation Big-O Analysis: O(1) time worst-case since we can consider modulo as
   * O(1), and same for == operator. Also, O(1) space worst-case, since we always create a constant
   * amount of space (single string).
   *
   * @param i
   * @return
   */
  public String evenOdd(Integer i) {
    if (i == null) {
      throw new IllegalArgumentException("Input is null");
    }
    if (i % 2 == 0) {
      //even
      return "even";
    }
    return "odd";
  }

  public String evenOddBit(Integer i) {
    if (i == null) {
      throw new IllegalArgumentException("Input is null");
    }
    if ((i & 1) == 1) {
      return "odd";
    }
    return "even";
  }
}
