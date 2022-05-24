package ca.jrvs.apps.grep;

import com.sun.org.slf4j.internal.Logger;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {

  /**
   * Top level search workflow
   *
   * @throws IOException
   */
  void process() throws IOException;

  /**
   * Traverse a given directory and return all files
   *
   * @param rootDir input dir
   * @return files under the rootDir
   */
  List<File> listFiles(String rootDir);

  /**
   * Read a file and return all the lines
   *
   * @param inputFile file to be read
   * @return Lines
   * @throws IllegalArgumentException if inputFile is not a file
   */
  List<String> readLines(File inputFile) throws IllegalArgumentException, IOException;

  /**
   * Check if a line contains the regex pattern (passed by user)
   *
   * @param line input str
   * @return true if matching
   */
  boolean containsPattern(String line);

  /**
   * Write lines to a file
   *
   * @param lines
   * @throws IOException
   */
  void writeToFile(List<String> lines) throws IOException;

  String getRootPath();

  void setRootPath(String rootPath);

  String getRegex();

  void setRegex(String regex);

  String getOutFile();

  void setOutFile(String outFile);

  Logger getLogger();
}
