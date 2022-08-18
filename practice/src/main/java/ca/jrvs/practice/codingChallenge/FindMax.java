package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://www.notion.so/jarvisdev/Find-Largest-Smallest-ff1f5fb0ec1f4d8d9425adf5451a6db9
 */
public class FindMax {

    /**
     * Big-O Analysis: O(n) time worst-case, O(1) space worst-case
     * @param arr
     * @return
     */
    public int findMax(int[] arr) {
        int maxNum = 0;
        for (int i : arr) {
            if (i > maxNum) {
                maxNum = i;
            }
        }
        return maxNum;
    }

    /**
     * Big-O Analysis: O(n) time worst-case, O(1) space worst-case
     * @param arr
     * @return
     */
    public int findMaxStream(int[] arr) {
        return Arrays.stream(arr).max().getAsInt();
    }

    /**
     * Big-O Analysis: O(n) time worst-case, O(1) space worst-case
     * @param arr
     * @return
     */
    public int findMaxApi(int[] arr) {
        return Collections.max(Arrays.stream(arr).boxed().collect(Collectors.toList()));
    }
}
