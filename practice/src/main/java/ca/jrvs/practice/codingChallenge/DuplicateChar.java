package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * https://www.notion.so/jarvisdev/Duplicate-Characters-1ab527455e744dea8cd4adc25f770fad
 */
public class DuplicateChar {

  /**
   * Big-O Analysis: O(n) worst-case time complexity, O(n) worst-case space complexity.
   *
   * @param str
   * @return
   */
  public List<String> findDuplicates(String str) {
    Map<String, Integer> map = new HashMap<>();
    List<String> output = new ArrayList<>();

    for (Character character : str.toCharArray()) {
      if (!character.toString().trim().equals("")) {
        if (!map.containsKey(character.toString().toLowerCase(Locale.ROOT))) {
          map.put(character.toString().toLowerCase(Locale.ROOT), 1);
        } else if (map.get(character.toString()) == 1) {
          output.add(character.toString());
          map.replace(character.toString().toLowerCase(Locale.ROOT), 2);
        }
      }
    }
    return output;
  }

}
