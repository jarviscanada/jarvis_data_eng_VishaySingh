package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/Swap-two-numbers-916493ba4aae4283b092ca1375241e6b
 */
public class SwapTwo {

    /**
     * Big-O Analysis: O(1) time worst-case, O(1) space worst-case.
     * @param arr
     * @return
     */
    public int[] swapTwo(int[] arr) {
        arr[0] = arr[0] + arr[1];
        arr[1] = arr[0] - arr[1];
        arr[0] = arr[0] - arr[1];
        return arr;
    }

    /**
     * Big-O Analysis: O(1) time worst-case, O(1) space worst-case.
     * @param arr
     * @return
     */
    public int[] swapTwoBitWise(int[] arr) {
        arr[0] = arr[0] ^ arr[1];
        arr[1] = arr[0] ^ arr[1];
        arr[0] = arr[0] ^ arr[1];
        return arr;
    }
}
