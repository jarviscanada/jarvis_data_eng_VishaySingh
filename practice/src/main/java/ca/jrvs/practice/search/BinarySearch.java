package ca.jrvs.practice.search;

import java.util.Arrays;
import java.util.Optional;

public class BinarySearch {

  public BinarySearch() {
  }

  /**
   * find the target index in a sorted array
   *
   * @param arr input arry is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not ound
   */
  public <E extends Comparable> Optional<Integer> binarySearchRecursion(E[] arr, E target) {
    return binarySearchRecursionHelper(arr, target, arr.length / 2);
  }

  private <E extends Comparable> Optional<Integer> binarySearchRecursionHelper(E[] arr, E target, int i) {
    //base cases
    if (arr.length == 0){
      return Optional.empty();
    }
    if (arr.length == 1){
      if (arr[0] == target){
        return Optional.of(i);
      }
      return Optional.empty();
    }

    //recursive steps
    if (target.compareTo(arr[i]) < 0){ // target < arr[i]
      //recurse left
      return binarySearchRecursionHelper(Arrays.copyOfRange(arr, 0, i), target, i / 2);
    }
    if (target.compareTo(arr[i]) > 0){ //target > arr[i]
      return binarySearchRecursionHelper(Arrays.copyOfRange(arr, i, arr.length), target,i + (i / 2));
    }
    return Optional.of(i); //equal to target
  }

  /**
   * find the the target index in a sorted array
   *
   * @param arr input arry is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not ound
   */
  public <E extends Comparable> Optional<Integer> binarySearchIteration(E[] arr, E target) {
    int index = arr.length / 2;
    while(arr.length > 0){
      if (target.compareTo(arr[index]) < 0) {
        arr = Arrays.copyOfRange(arr, 0, index);
        index = index / 2;
      } else if (target.compareTo(arr[index]) > 0) {
        arr = Arrays.copyOfRange(arr, index, arr.length);
        index = index + (index / 2);
      } else {
        //found target
        return Optional.of(index);
      }
    }
    return Optional.empty();
  }
}