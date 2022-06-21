package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class CompareMapsTest {

  private final CompareMaps maps = new CompareMaps();

  @Test
  public void compareMaps() {
    Map<String, Integer> stringMap = new HashMap<>();
    stringMap.put("a", 1);
    stringMap.put("b", 2);
    Map<String, Integer> stringMap2 = new HashMap<>();
    stringMap2.put("a", 1);
    stringMap2.put("b", 2);
    assertTrue(maps.compareMaps(stringMap, stringMap2));
    stringMap2.put("c", 3);
    assertFalse(maps.compareMaps(stringMap, stringMap2));
  }

  @Test
  public void compareMaps2() {
    Map<String, Integer> stringMap = new HashMap<>();
    stringMap.put("a", 1);
    stringMap.put("b", 2);
    Map<String, Integer> stringMap2 = new HashMap<>();
    stringMap2.put("a", 1);
    stringMap2.put("b", 2);
    assertTrue(maps.compareMaps2(stringMap, stringMap2));
    stringMap2.put("c", 3);
    assertFalse(maps.compareMaps2(stringMap, stringMap2));
  }
}