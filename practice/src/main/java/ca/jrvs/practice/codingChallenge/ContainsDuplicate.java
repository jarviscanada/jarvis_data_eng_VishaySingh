package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://www.notion.so/jarvisdev/Contains-Duplicate-e0be87bca067408b821bd332144b81a1
 */
public class ContainsDuplicate {

    /**
     * Big-O Analysis: O(nlogn) worst-case time complexity due to sorting first. O(1) space worst-case
     * as we are only overwriting the array, and making a constant amount of space with prev.
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        int prev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == prev) {
                return true;
            }
            prev = nums[i];
        }
        return false;
    }

    /**
     * Big-O Analysis: O(n) worst-case time complexity. O(n) worst-case space complexity.
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicateSet(int[] nums) {
        Set<Integer> hashSet = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (hashSet.contains(nums[i])) {
                return true;
            }
            hashSet.add(nums[i]);
        }
        return false;
    }
}
