package ca.jrvs.apps.practice;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class LambdaStreamExcImpTest {

  static LambdaStreamExcImp tester;

  @BeforeClass
  public static void testSetup() {
    tester = new LambdaStreamExcImp();
  }

  @AfterClass
  public static void testCleanup() {
    // Do cleanup here
  }

  @Test
  public void testCreateStrStream() {
    String[] array = {"mark", "anthony", "richard"};
    Stream<String> new_stream = tester.createStrStream(array);
    assertArrayEquals(new_stream.toArray(), array);
  }

  @Test
  public void testToUpper() {
    String[] array = {"mark", "anthony", "richard"};
    String[] arrayUpper = {"MARK", "ANTHONY", "RICHARD"};
    Stream<String> new_stream = tester.toUpperCase(array);
    assertArrayEquals(new_stream.toArray(), arrayUpper);
  }

  @Test
  public void testFilter() {
    String[] array = {"mark", "anthony", "chris"};
    String[] arrayPattern = {"chris"};
    Stream<String> new_stream = tester.filter(tester.createStrStream(array), "a");
    assertArrayEquals(new_stream.toArray(), arrayPattern);
  }

  @Test
  public void testCreateIntStream() {
    int[] array = {1, 2, 3};
    IntStream new_stream = tester.createIntStream(array);
    assertArrayEquals(new_stream.toArray(), array);
  }

  @Test
  public void testToList() {
    List<String> array1 = Arrays.asList("mark", "anthony", "chris");
    String[] array = {"mark", "anthony", "chris"};
    Stream<String> new_stream = tester.createStrStream(array);
    assertEquals(tester.toList(new_stream), array1);
  }

  @Test
  public void testIntToList() {
    List<Integer> array1 = Arrays.asList(1, 2, 3);
    int[] array = {1, 2, 3};
    IntStream new_stream = tester.createIntStream(array);
    assertEquals(tester.toList(new_stream), array1);
  }

  @Test
  public void testCreateFromRange() {
    int[] array = {1, 2, 3};
    IntStream new_stream = tester.createIntStream(1, 3);
    assertArrayEquals(new_stream.toArray(), array);
  }

  @Test
  public void testSquareRoot() {
    int[] array = {4, 9, 25};
    List<Double> array1 = Arrays.asList(2.0, 3.0, 5.0);
    IntStream new_stream = tester.createIntStream(array);
    DoubleStream doubleStream = tester.squareRootIntStream(new_stream);
    assertEquals(tester.toList(doubleStream), array1);
  }

  @Test
  public void testGetOdd() {
    int[] array = {1, 3};
    IntStream new_stream = tester.createIntStream(array);
    IntStream stream2 = tester.createIntStream(1, 4);
    stream2 = tester.getOdd(stream2);
    assertArrayEquals(new_stream.toArray(), stream2.toArray());
  }

  @Test
  public void testPrinter() {
    Consumer<String> printer = tester.getLambdaPrinter("Testing Printer: ", " and it works!");
    printer.accept("printer,");
  }

  @Test
  public void testPrintMessages() {
    Consumer<String> printer = tester.getLambdaPrinter("Testing Print Messages: ", " and it works!");
    String[] messages = {"test 1...", "test 2...", "test 3..."};
    tester.printMessages(messages, printer);
  }

  @Test
  public void testPrintInt() {
    Consumer<String> printer = tester.getLambdaPrinter("Testing Print Odd: ", " and it works!");
    IntStream new_stream = tester.createIntStream(1, 5);
    tester.printOdd(new_stream, printer);
  }

  @Test
  public void testNestedInt() {
    List<Integer> arr1 = Arrays.asList(1, 3, 5);
    List<Integer> arr2 = Arrays.asList(3, 4, 4);
    List<List<Integer>> arr3 = Arrays.asList(arr1, arr2);
    List<Integer> lst = Arrays.asList(1, 9, 25, 9, 16, 16);
    Stream<List<Integer>> nest_stream = arr3.stream();
    Stream<Integer> new_stream = tester.flatNestedInt(nest_stream);
    assertEquals(tester.toList(new_stream), lst);
  }
}
