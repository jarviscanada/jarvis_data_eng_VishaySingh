package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

  /**
   * Big-O Analysis: O(n^2) worst-case time complexity (double for loop), O(1) space wc
   *
   * @param nums
   * @param target
   * @return
   */
  public int[] twoSum(int[] nums, int target) {
    int[] indices = {0, 0};
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          indices[0] = i;
          indices[1] = j;
          return indices;
        }
      }
    }
    return indices;
  }

  /**
   * Big-O Analysis: O(nlogn) worst-case due to sorting first, O(n) space average-case
   *
   * @param nums
   * @param target
   * @return
   */
  public int[] twoSumSort(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      map.put(nums[i], i);
    }
    int[] numCopy = Arrays.copyOf(nums, nums.length);
    Arrays.sort(numCopy);
    int i, j;
    i = 0;
    j = 1;
    while (j < numCopy.length) {
      if (numCopy[i] + numCopy[j] == target) {
        return new int[]{map.get(numCopy[i]), map.get(numCopy[j])};
      }
      if (i + 1 == j || target - numCopy[j] > 0) {
        j++;
      } else {
        i++;
      }
    }

    return new int[]{0, 0};
  }

  /**
   * Big-O Analysis: O(n) amortized time (technically O(n^2) worst-case due to hash-collision), O(n)
   * space average-case.
   *
   * @param nums
   * @param target
   * @return
   */
  public int[] twoSumBest(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      Object j = map.get(target - nums[i]);
      if (j != null) {
        int k = (int) j;
        if (k != i) {
          return new int[]{k, i};
        }
      }
      map.put(nums[i], i);
    }
    return new int[]{0, 0};
  }
}
