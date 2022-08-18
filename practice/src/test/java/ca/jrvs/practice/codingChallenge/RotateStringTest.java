package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RotateStringTest {

  private final RotateString rotateString = new RotateString();

  @Test
  public void rotateString() {
    assertTrue(rotateString.rotateString("abcde", "cdeab"));
  }
}