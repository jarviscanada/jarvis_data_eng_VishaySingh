package ca.jrvs.practice.codingChallenge;

import static java.lang.Math.pow;

import java.math.BigInteger;

/**
 * https://www.notion.so/jarvisdev/String-to-Integer-atoi-1e09f7c0c1d744a0a717337c2d1eabf1
 * <p>
 * Big-O Analysis: O(n) time worst-case (O(2n) = O(n)). O(1) space.
 */
public class AToI {

  public int myAtoi(String s) {
    int sign = 1;
    int result = 0;
    int i = 0;
    int n = s.length();
    int power = 0;

    //Skip leading whitespace
    while (i < n && s.charAt(i) == ' ') {
      i++;
    }

    if (i == n) {
      return 0;
    }

    if (s.charAt(i) == '-') {
      sign = -1;
      i++;
    } else if (s.charAt(i) == '+') {
      i++;
    }

    //Get correct digit count
    while (power + i + 1 < n && Character.isDigit(s.charAt(power + i + 1))) {
      power++;
    }

    while (i < n && Character.isDigit(s.charAt(i))) {
      BigInteger curr = new BigInteger(String.valueOf(s.charAt(i)));
      curr = curr.multiply(new BigInteger(String.valueOf((int) pow(10, power)))).add(new BigInteger(
          String.valueOf(result)));
      if (curr.compareTo(new BigInteger(String.valueOf(Integer.MAX_VALUE))) > 0) {
        return Integer.MAX_VALUE;
      }
      if (curr.compareTo(new BigInteger(String.valueOf(Integer.MIN_VALUE))) < 0) {
        return Integer.MIN_VALUE;
      }
      result = curr.intValue();
      power--;
      i++;
    }

    return sign * result;
  }
}
