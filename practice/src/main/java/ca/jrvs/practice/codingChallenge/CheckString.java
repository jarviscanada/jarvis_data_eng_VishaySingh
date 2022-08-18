package ca.jrvs.practice.codingChallenge;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://www.notion.so/jarvisdev/Check-if-a-String-contains-only-digits-5aa614f30e954339b01b95aec67814aa
 */
public class CheckString {

    /**
     * Big-O Analysis: O(n) time worst-case, O(1) space worst-case.
     * @param str
     * @return
     */
    public boolean isDigitAscii(String str) {
        for (char c: str.toCharArray()){
            if (((int) c) < 47 || ((int) c) > 58){
                return false;
            }
        }
        return true;
    }

    /**
     * Big-O Analysis: O(n) time worst-case, O(1) space worst-case.
     * @param str
     * @return
     */
    public boolean isDigitApi(String str) {
        try {
            Integer.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Big-O Analysis: O(n) time worst-case, O(1) space worst-case.
     * @param str
     * @return
     */
    public boolean isDigitRegex(String str) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
