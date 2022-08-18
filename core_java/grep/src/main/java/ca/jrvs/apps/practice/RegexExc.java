package ca.jrvs.apps.practice;

public interface RegexExc {

  /**
   * private helper method for this class
   *
   * @param regex
   * @param check
   * @param caseInsensitive
   * @return
   */
  public boolean matchCheck(String regex, String check, boolean caseInsensitive);

  /**
   * return true if filename extension is jpg or jpeg (case insensitive)
   *
   * @param filename
   * @return
   */
  public boolean matchJpeg(String filename);

  /**
   * return true if filename is csv (case insensitive)
   *
   * @param filename
   * @return
   */
  public boolean matchCsv(String filename);

  /**
   * return true if ip is valid assume IP addr range is from 0.0.0.0 to 999.999.999.999
   *
   * @param ip
   * @return
   */
  public boolean matchIp(String ip);

  /**
   * return true if line is empty (e.g. empty, white space, etc.)
   *
   * @param line
   * @return
   */
  public boolean isEmptyLine(String line);
}
