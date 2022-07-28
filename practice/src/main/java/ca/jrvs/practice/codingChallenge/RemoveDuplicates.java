package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/Duplicates-from-Sorted-Array-99bbe782898a4e198725f2925f3ea1a9
 */
public class RemoveDuplicates {

  /**
   * Big-O Analysis: O(n) time worst-case, O(1) space worst-case
   *
   * @param nums
   * @return
   */
  public int removeDuplicates(int[] nums) {
    int i = 0;
    for (int n : nums) {
      if (i == 0 || n > nums[i - 1]) {
        nums[i++] = n;
      }
    }
    return i;
  }

}
