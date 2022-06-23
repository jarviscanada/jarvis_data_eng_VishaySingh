package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class AToITest {

  private AToI aToI = new AToI();

  @Test
  public void myAtoi() {
    assertEquals(-42, aToI.myAtoi("   -42"));
    assertEquals(4193, aToI.myAtoi("4193 with words"));
    assertEquals(0, aToI.myAtoi("words and 987"));
    assertEquals(3, aToI.myAtoi("3.14159"));
    assertEquals(0, aToI.myAtoi("+-12"));
    assertEquals(1, aToI.myAtoi("+1-2"));
    assertEquals(0, aToI.myAtoi("  "));
  }
}