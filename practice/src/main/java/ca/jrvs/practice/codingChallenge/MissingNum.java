package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.Set;

public class MissingNum {

    /**
     * Big-O Analysis: O(n) time worst-case, O(1) space worst-case
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int expectation = 0;
        int runningSum = 0;
        int index = 1;
        for (int i: nums) {
            expectation = expectation + index;
            runningSum = runningSum + i;
            index++;
        }
        return expectation - runningSum;
    }

    /**
     * Big-O Analysis: O(n) time worst-case, O(n) space worst-case
     * @param nums
     * @return
     */
    public int missingNumberSet(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i: nums) {
            set.add(i);
        }
        for (int i = 0; i <= nums.length; i++) {
            if(!set.contains(i)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Big-O Analysis: O(n) time worst-case, O(1) space worst-case.
     * @param nums
     * @return
     */
    public int missingNumberXor(int[] nums) {
        int result = nums.length;
        for(int i = 0; i < nums.length; i++) {
            result ^= i;
            result ^= nums[i];
        }
        return result;
    }
}
