package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EvenOddTest {

  private final EvenOdd evenOdd = new EvenOdd();

  @Test
  public void evenOdd() {
    String result = evenOdd.evenOdd(23);
    assertEquals("odd", result);
    result = evenOdd.evenOdd(22);
    assertEquals("even", result);
  }

  @Test
  public void evenOddBit() {
    String result = evenOdd.evenOddBit(23);
    assertEquals("odd", result);
    result = evenOdd.evenOddBit(22);
    assertEquals("even", result);
  }
}