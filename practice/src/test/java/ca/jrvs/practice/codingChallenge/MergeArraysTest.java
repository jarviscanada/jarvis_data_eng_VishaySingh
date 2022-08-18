package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class MergeArraysTest {

    private MergeArrays merge = new MergeArrays();

    @Test
    public void merge() {
        int[] nums = new int[] {1,2,3,0,0,0};
        merge.merge(nums, 3, new int[] {2,5,6}, 3);
        assertArrayEquals(new int[] {1,2,2,3,5,6}, nums);
    }
}