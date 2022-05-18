package ca.jrvs.apps.grep;

import ca.jrvs.apps.practice.RegexExcImp;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.nio.file.Path;
import java.nio.file.Paths;

public class grepImp implements grep {

  static private final Logger logger = LoggerFactory.getLogger(grep.class);
  static RegexExcImp reg = new RegexExcImp();

  public void main(String args[]) {

    //Check for valid # of args
    if (args.length != 3){
      logger.error("Incorrect number of arguments");
      return;
    }

    //note: no string validation yet
    //run searchPath with the correct args
    this.searchPath(args[1], args[0], args[2]);
  }

  /**
   * searches the root path for the matching lines within each file, according to the given regex,
   * and writes the lines to outFile
   *
   * @param rootPath
   * @param regex
   * @param outFile
   */
  @Override
  public void searchPath(String rootPath, String regex, String outFile) {
    Path dir = Paths.get(rootPath);
  }

  /**
   * reads the file at filePath for matching lines with regex, and returns the corresponding lines
   *
   * @param filePath
   * @param regex
   * @return
   */
  @Override
  public String readFile(String filePath, String regex) {
    return null;
  }

  /**
   * writes the output of the search to the given file in outFile
   *
   * @param outFile
   */
  @Override
  public void writeToFile(String outFile) {

  }
}
