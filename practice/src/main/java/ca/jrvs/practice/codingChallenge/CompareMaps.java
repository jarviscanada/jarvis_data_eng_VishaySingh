package ca.jrvs.practice.codingChallenge;

import java.util.Map;

/**
 * https://www.notion.so/jarvisdev/How-to-compare-two-maps-04729cac99ae463591eb14f67ec37178
 */
public class CompareMaps {

  /**
   * Big-O Analysis: if n is the # of K,V pairs in m1, and m is the # of K,V pairs in m2, then O(n)
   * when n>m, and O(m) when m>n in the worst-case, since each entry must be compared. Same for
   * space.
   *
   * @param m1
   * @param m2
   * @param <K>
   * @param <V>
   * @return
   */
  public <K, V> boolean compareMaps(Map<K, V> m1, Map<K, V> m2) {
    return m1.equals(m2);
  }

  /**
   * Big-O Analysis: if n is the # of K,V pairs in m1, and m is the # of K,V pairs in m2, then O(n)
   * when n>m, and O(m) when m>n in the worst-case, since each entry must be compared. Same for
   * space.
   *
   * @param m1
   * @param m2
   * @param <K>
   * @param <V>
   * @return
   */
  public <K, V> boolean compareMaps2(Map<K, V> m1, Map<K, V> m2) {
    if (m1.entrySet().stream().count() != m2.entrySet().stream().count()) {
      return false;
    }
    for (Map.Entry entry : m1.entrySet()) {
      V value = m2.get(entry.getKey());
      if (value == null) {
        return false;
      }
      if (!value.equals(entry.getValue())) {
        return false;
      }
    }
    return true;
  }
}
