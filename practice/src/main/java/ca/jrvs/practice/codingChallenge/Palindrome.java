package ca.jrvs.practice.codingChallenge;

import java.util.Locale;

/**
 * https://www.notion.so/jarvisdev/Valid-Palindrome-aa8cec668b894904b2af3eb06c9b2f92
 */
public class Palindrome {

  /**
   * Big-O Analysis: O(n) time worst-case, O(1) space worst-case.
   *
   * @param s
   * @return
   */
  public boolean isPalindrome(String s) {
    int start = 0;
    int end = s.length() - 1;

    while (start < end) {
      Character startChar = s.charAt(start);
      Character endChar = s.charAt(end);
      if (!Character.isLetterOrDigit(startChar)) {
        start++;
        continue;
      }
      if (!Character.isLetterOrDigit(endChar)) {
        end--;
        continue;
      }
      if (!startChar.toString().toLowerCase(Locale.ROOT)
          .equals(endChar.toString().toLowerCase(Locale.ROOT))) {
        return false;
      }
      start++;
      end--;
    }
    return true;
  }

  /**
   * Big-O Analysis: O(n) time worst-case, O(n) space worst-case.
   *
   * @param s
   * @return
   */
  public boolean isPalindromeRecursion(String s) {
    return helper(s, 0, s.length() - 1, false);
  }

  private boolean helper(String s, int lo, int hi, boolean used) {
    if (lo >= hi) {
      return true;
    }

    if (s.charAt(lo) != s.charAt(hi)) {
      if (!used) {
        if (helper(s, lo + 1, hi, true)) {
          return true;
        }
        return helper(s, lo, hi - 1, true);
      } else {
        return false;
      }
    }

    return helper(s, lo + 1, hi - 1, used);
  }
}
