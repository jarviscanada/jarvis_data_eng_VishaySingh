package ca.jrvs.practice.search;

import static org.junit.Assert.assertEquals;

import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTest {

  BinarySearch binarySearch = new BinarySearch();

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void binarySearchRecursion() {
    Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    Optional<Integer> index = binarySearch.binarySearchRecursion(arr, 3);
    assertEquals(Optional.of(2), index);
  }

  @Test
  public void binarySearchIteration() {
    Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    Optional<Integer> index = binarySearch.binarySearchIteration(arr, 3);
    assertEquals(Optional.of(2), index);
  }
}