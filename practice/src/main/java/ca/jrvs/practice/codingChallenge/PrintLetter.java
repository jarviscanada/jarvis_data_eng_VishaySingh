package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.notion.so/jarvisdev/Print-letter-with-number-0887285c896844e3b0690096f6bf6a4b
 */
public class PrintLetter {

    /**
     * Big-O Analysis: O(n) time worst-case, O(n) space worst-case.
     * @param inp
     * @return
     */
    public String printLetterNum(String inp) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Character, Integer> hashMap = new HashMap<>();
        int i = 1;
        for (char c : "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            hashMap.put(c, i);
            i++;
        }

        for (char c : inp.toCharArray()) {
            stringBuilder.append(c);
            stringBuilder.append(hashMap.get(c));
        }

        return stringBuilder.toString();
    }
}
