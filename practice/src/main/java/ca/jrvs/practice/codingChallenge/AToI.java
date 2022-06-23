package ca.jrvs.practice.codingChallenge;

import java.math.BigInteger;

/**
 * https://www.notion.so/jarvisdev/String-to-Integer-atoi-1e09f7c0c1d744a0a717337c2d1eabf1
 */
public class AToI {

  public int myAtoi(String s) {
    s = s.trim();
    if (s.equals("")){
      return 0;
    }
    StringBuilder builder = new StringBuilder();
    int len = s.length();
    int i = 0;
    int pos = 1;
    boolean seenInt = false;

    while(i < len){
      char curr = s.charAt(i);
      if (i == 0 && curr == '-'){
        pos = pos * -1;
      } else if (i == 0 && curr == '+') {
        //do nothing
      } else if (i == 0 && !seenInt && !Character.isDigit(curr)) {
        return 0;
      } else if (!seenInt && (curr == '-' || curr == '+')) {
        return 0;
      } else if (seenInt && !Character.isDigit(s.charAt(i))) {
        i = len;
      } else if (Character.isDigit(s.charAt(i))) {
        seenInt = true;
        builder.append(s.charAt(i));
      }
      i++;
    }
    if (builder.toString().equals("")){
      return 0;
    }
    try {
      BigInteger bigInt = new BigInteger(builder.toString()).multiply(BigInteger.valueOf(pos));
      if (bigInt.compareTo(new BigInteger(String.valueOf(Integer.MAX_VALUE))) > 0){
        return Integer.MAX_VALUE;
      }
      if (bigInt.compareTo(new BigInteger(String.valueOf(Integer.MIN_VALUE))) < 0) {
        return Integer.MIN_VALUE;
      }
      return bigInt.intValue();
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
