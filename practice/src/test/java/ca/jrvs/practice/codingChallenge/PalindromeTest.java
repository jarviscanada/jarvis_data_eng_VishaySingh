package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PalindromeTest {

  private final Palindrome palindrome = new Palindrome();

  @Test
  public void isPalindrome() {
    assertTrue(palindrome.isPalindrome("A man, a plan, a canal: Panama"));
    assertFalse(palindrome.isPalindrome("race a car"));
  }

  @Test
  public void isPalindromeRecursion() {
    assertTrue(palindrome.isPalindromeRecursion("A man, a plan, a canal: Panama"));
    assertFalse(palindrome.isPalindromeRecursion("race a car"));
  }
}