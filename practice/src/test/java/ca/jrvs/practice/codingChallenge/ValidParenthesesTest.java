package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidParenthesesTest {

  private final ValidParentheses validParentheses = new ValidParentheses();

  @Test
  public void isValid() {
    assertTrue(validParentheses.isValid("((([])))"));
    assertFalse(validParentheses.isValid("([)]"));
  }
}