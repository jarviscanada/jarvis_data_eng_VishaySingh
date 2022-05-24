package ca.jrvs.apps.grep;

import ca.jrvs.apps.practice.LambdaStreamExcImp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp{

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutFile(args[2]);

    try {
      javaGrepLambdaImp.process();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Implement using lambda/stream APIs
   * @param inputFile file to be read
   * @return
   */
  @Override
  public List<String> readLines(File inputFile) throws IOException {
    //check for correct file
    if (!inputFile.isFile()) {
      throw new IllegalArgumentException("ERROR: file does not exist");
    }

    //read the lines
    LambdaStreamExcImp lambda = new LambdaStreamExcImp();
    Stream<String> strStream = lambda.createStrStream();
    try{
       strStream = Files.lines(Paths.get(inputFile.getAbsolutePath()));
    } catch (IOException e) {
      throw e;
    }
    return lambda.toList(strStream);
  }
}
