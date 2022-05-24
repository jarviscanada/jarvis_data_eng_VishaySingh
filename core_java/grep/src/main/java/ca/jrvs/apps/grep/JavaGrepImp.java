package ca.jrvs.apps.grep;

import ca.jrvs.apps.practice.RegexExcImp;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);
  static RegexExcImp reg = new RegexExcImp();

  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args) {
    //Check for valid # of args
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    //Initialize variables
    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process(); //run the process
    } catch (Exception e) {
      javaGrepImp.logger.error("Error: Unable to process", e);
    }
  }

  public Logger getLogger() {
    return logger;
  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  /**
   * Top level search workflow
   *
   * @throws IOException
   */
  @Override
  public void process() throws IOException {
    //declare vars
    List<String> matchedLines = new ArrayList<String>();
    List<File> files = this.listFiles(this.rootPath);
    List<String> lines;

    //check for error
    if (files == null) {
      throw new FileNotFoundException("ERROR: no files in path/file does not exist");
    }

    //start main loop over files
    for (File file1 : files) {
      //get lines from file, checking for errors
      try {
        lines = this.readLines(file1);
      } catch (Exception e) {
        throw new IOException("ERROR: file does not exist");
      }

      //loop over all lines in file
      for (String line : lines) {

        //check for a match
        if (this.containsPattern(line)) {
          matchedLines.add(line);
        }
      }
    }
    //finally, write to the out file
    this.writeToFile(matchedLines);
  }

  /**
   * Traverse a given directory and return all files
   *
   * @param rootDir input dir
   * @return files under the rootDir
   */
  @Override
  public List<File> listFiles(String rootDir) {
    List<File> files = new ArrayList<File>();
    File rootFile = new File(rootDir);

    //run the recursive traversal
    this.listFilesRecursive(rootFile, files);
    return files;
  }

  private void listFilesRecursive(File rootDir, List<File> files) {
    if (rootDir.isDirectory()) { //if not a directory, just stop
      File[] current_files = rootDir.listFiles();
      if (current_files == null) {
        return;
      }

      //main recursive loop
      for (File file1 : current_files) {
        if (file1.isFile()) {
          files.add(file1); //add the file to the list
        } else {
          this.listFilesRecursive(file1, files); //recurse on dir
        }
      }
    }
  }

  /**
   * Read a file and return all the lines
   *
   * @param inputFile file to be read
   * @return Lines
   * @throws IllegalArgumentException if inputFile is not a file
   */
  @Override
  public List<String> readLines(File inputFile) throws IllegalArgumentException, IOException {
    //check for correct file
    if (!inputFile.isFile()) {
      throw new IllegalArgumentException("ERROR: file does not exist");
    }

    //read the lines
    List<String> lines = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
      String line;
      while ((line = br.readLine()) != null) {
        lines.add(line);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return lines;
  }

  /**
   * Check if a line contains the regex pattern (passed by user)
   *
   * @param line input str
   * @return true if matching
   */
  @Override
  public boolean containsPattern(String line) {
    return reg.matchCheck(this.getRegex(), line, false);
  }

  /**
   * Write lines to a file
   *
   * @param lines
   * @throws IOException
   */
  @Override
  public void writeToFile(List<String> lines) throws IOException {
    Writer writer = null;
    File file = new File(this.getOutFile());

    if (!file.exists()) {
      file.createNewFile(); //create the out file if it does not exist
    }

    //write the lines
    for (String line : lines) {
      try {
        writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(file, true), "utf-8"));
        writer.write(line + "\n");
      } catch (IOException e) {
        throw new IOException(e);
      } finally {
        try {
          writer.close();
        } catch (NullPointerException e) {
          //do nothing
        }
      }
    }
  }
}
