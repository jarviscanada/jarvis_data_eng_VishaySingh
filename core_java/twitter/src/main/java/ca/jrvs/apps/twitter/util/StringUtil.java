package ca.jrvs.apps.twitter.util;

public class StringUtil {

  public static boolean isEmpty(String text) {
    return text == null || text.trim().isEmpty();
  }
}
