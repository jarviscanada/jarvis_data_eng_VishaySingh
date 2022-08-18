package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://www.notion.so/jarvisdev/Find-the-Duplicate-Number-b092228364f04408ba9b55c243a589fb
 */
public class DuplicateNumber {

  /**
   * Big-O Analysis: O(nlogn) worst-case time complexity due to sorting first. O(1) space worst-case
   * as we are only overwriting the array, and making a constant amount of space with prev.
   *
   * @param nums
   * @return
   */
  public int findDuplicate(int[] nums) {
    Arrays.sort(nums);
    int prev = nums[0];
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == prev) {
        return prev;
      }
      prev = nums[i];
    }
    return prev;
  }

  /**
   * Big-O Analysis: O(n) worst-case time complexity. O(n) worst-case space complexity.
   *
   * @param nums
   * @return
   */
  public int findDuplicateSet(int[] nums) {
    Set<Integer> hashSet = new HashSet<>();

    for (int i = 0; i < nums.length; i++) {
      if (hashSet.contains(nums[i])) {
        return nums[i];
      }
      hashSet.add(nums[i]);
    }
    return nums[0];
  }

}
