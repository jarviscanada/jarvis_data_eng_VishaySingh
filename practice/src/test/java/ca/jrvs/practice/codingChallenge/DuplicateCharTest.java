package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class DuplicateCharTest {

  private DuplicateChar duplicateChar;

  @Before
  public void setUp() throws Exception {
    duplicateChar = new DuplicateChar();
  }

  @Test
  public void findDuplicates() {
    List<String> lst = Arrays.asList("a", "c");
    List<String> lst2 = duplicateChar.findDuplicates("A black cat");
    assertArrayEquals(lst.toArray(), lst2.toArray());
  }
}