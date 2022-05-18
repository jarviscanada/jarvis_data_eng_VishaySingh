package ca.jrvs.apps.grep;

import ca.jrvs.apps.practice.RegexExcImp;

public interface grep {

  /**
   * searches the root path for the matching lines within each file, according to the given regex,
   * and writes the lines to outFile
   *
   * @param rootPath
   * @param regex
   * @param outFile
   */
  public void searchPath(String rootPath, String regex, String outFile);

  /**
   * reads the file at filePath for matching lines with regex, and returns the corresponding lines
   *
   * @param filePath
   * @param regex
   * @return
   */
  public String readFile(String filePath, String regex);

  /**
   * writes the output of the search to the given file in outFile
   *
   * @param outFile
   */
  public void writeToFile(String outFile);
}
