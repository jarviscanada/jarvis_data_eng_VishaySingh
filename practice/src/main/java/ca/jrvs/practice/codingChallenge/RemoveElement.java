package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/Remove-Element-8e6ecc4c991d44c6adfc9963d6278df3
 */
public class RemoveElement {

    /**
     * Big-O Analysis: O(n) time worst-case, O(1) space worst-case
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}
