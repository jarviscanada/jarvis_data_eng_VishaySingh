package ca.jrvs.practice.codingChallenge;

import static java.util.Arrays.sort;

/**
 * https://www.notion.so/jarvisdev/Valid-Anagram-9ab39a07666a42daa58352f05a8c7693
 */
public class Anagram {

  /**
   * Big-O Analysis: O(nlogn) time worst-case, O(n) space worst-case.
   *
   * @param s
   * @param t
   * @return
   */
  public boolean isAnagramSort(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }

    char[] arr = s.toCharArray();
    sort(arr);
    s = new String(arr);
    char[] arr2 = t.toCharArray();
    sort(arr2);
    t = new String(arr2);

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) != t.charAt(i)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Big-O Analysis: O(n) time worst-case (O(2n) = O(n)), O(1) space since arr is fixed-length
   *
   * @param s
   * @param t
   * @return
   */
  public boolean isAnagramFast(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }

    int[] arr = new int[26];
    for (int i = 0; i < s.length(); i++) {
      arr[s.charAt(i) - 'a']++;
      arr[t.charAt(i) - 'a']--;
    }

    for (int i : arr) {
      if (i != 0) {
        return false;
      }
    }
    return true;
  }
}
