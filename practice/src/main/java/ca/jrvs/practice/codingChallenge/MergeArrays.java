package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/Merge-Sorted-Array-56ba139dce7f4f928635543ea6892cc8
 */
public class MergeArrays {

    /**
     * Big-O Analysis: O(n + m) time worst-case, O(1) space worst-case.
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1, p3 = n + m - 1;

        while(p2 >= 0) {
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p3] = nums1[p1];
                p1--;
            } else {
                nums1[p3] = nums2[p2];
                p2--;
            }
            p3--;
        }
    }
}
