package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FibonacciTest {

  Fibonacci fibonacci = new Fibonacci();

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void fib() {
    int fib0 = fibonacci.fib(0);
    assertEquals(0, fib0);
    int fib4 = fibonacci.fib(4);
    assertEquals(3, fib4);
    int fib31 = fibonacci.fib(31);
    assertEquals(1346269, fib31);
    int fib32 = fibonacci.fib(32);
    assertEquals(2178309, fib32);
  }

  @Test
  public void fibRec() {
    int fib0 = fibonacci.fibRec(0);
    assertEquals(0, fib0);
    int fib4 = fibonacci.fibRec(4);
    assertEquals(3, fib4);
    int fib31 = fibonacci.fibRec(31);
    assertEquals(1346269, fib31);
    int fib32 = fibonacci.fibRec(32);
    assertEquals(2178309, fib32);
  }
}