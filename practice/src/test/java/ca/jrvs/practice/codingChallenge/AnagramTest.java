package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AnagramTest {

  private final Anagram anagram = new Anagram();

  @Test
  public void isAnagramSort() {
    assertTrue(anagram.isAnagramSort("anagram", "nagaram"));
    assertFalse(anagram.isAnagramSort("banagram", "nagaram"));
  }

  @Test
  public void isAnagramFast() {
    assertTrue(anagram.isAnagramFast("anagram", "nagaram"));
    assertFalse(anagram.isAnagramFast("banagram", "nagaram"));
  }
}