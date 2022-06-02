package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClimbStairsTest {

  ClimbStairs climbStairs = new ClimbStairs();

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void climbStairs() {
    int stairs = climbStairs.climbStairs(1);
    assertEquals(stairs, 1);
    stairs = climbStairs.climbStairs(3);
    assertEquals(3, stairs);
    stairs = climbStairs.climbStairs(4);
    assertEquals(5, stairs);
  }

  @Test
  public void climbStairsRec() {
    int stairs = climbStairs.climbStairs(1);
    assertEquals(1, 1);
    stairs = climbStairs.climbStairsRec(3);
    assertEquals(3, stairs);
    stairs = climbStairs.climbStairsRec(4);
    assertEquals(5, stairs);
  }
}