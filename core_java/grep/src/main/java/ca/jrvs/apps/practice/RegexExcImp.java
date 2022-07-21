package ca.jrvs.apps.practice;

import java.lang.reflect.Parameter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExcImp implements RegexExc {

  /**
   * private helper method for this class
   *
   * @param regex
   * @param check
   * @param caseInsensitive
   * @return
   */
  @Override
  public boolean matchCheck(String regex, String check, boolean caseInsensitive) {
    Pattern p;
    if (caseInsensitive) {
      p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    } else {
      p = Pattern.compile(regex);
    }
    Matcher m = p.matcher(check);
    return m.matches();
  }

  /**
   * return true if filename extension is jpg or jpeg (case insensitive)
   *
   * @param filename
   * @return
   */
  @Override
  public boolean matchJpeg(String filename) {
    return this.matchCheck("^.+\\.jpg$|^.+\\.jpeg$", filename, true);
  }

  /**
   * return true if filename is csv (case insensitive)
   *
   * @param filename
   * @return
   */
  @Override
  public boolean matchCsv(String filename) {
    return this.matchCheck("^.+\\.csv$", filename, true);
  }

  /**
   * return true if ip is valid assume IP addr range is from 0.0.0.0 to 999.999.999.999
   *
   * @param ip
   * @return
   */
  @Override
  public boolean matchIp(String ip) {
    return this.matchCheck("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}", ip, false);
  }

  /**
   * return true if line is empty (e.g. empty, white space, etc.)
   *
   * @param line
   * @return
   */
  @Override
  public boolean isEmptyLine(String line) {
    return this.matchCheck("^ *$", line, false);
  }
}
