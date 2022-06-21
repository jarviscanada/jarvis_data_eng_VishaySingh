package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class TwoSumTest {

  private TwoSum twoSum = new TwoSum();
  @Test
  public void twoSum() {
    int[] arr = {3,2,4};
    assertArrayEquals(new int[] {1,2}, twoSum.twoSum(arr, 6));
  }

  @Test
  public void twoSumSort() {
    int[] arr = {3,2,4};
    assertArrayEquals(new int[] {1,2}, twoSum.twoSumSort(arr, 6));
  }

  @Test
  public void twoSumBest() {
    int[] arr = {3,2,4};
    assertArrayEquals(new int[] {1,2}, twoSum.twoSumBest(arr, 6));
  }
}