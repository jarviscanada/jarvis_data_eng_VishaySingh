package ca.jrvs.apps.grep;

import ca.jrvs.apps.practice.LambdaStreamExcImp;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp {

  LambdaStreamExcImp lambda = new LambdaStreamExcImp();

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
   *
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
    Stream<String> strStream = lambda.createStrStream();
    try {
      strStream = Files.lines(Paths.get(inputFile.getAbsolutePath()));
    } catch (IOException e) {
      throw e;
    }
    return lambda.toList(strStream);
  }

  /**
   * Implement using lambda/stream APIs
   *
   * @param rootDir input dir
   * @return
   */
  @Override
  public List<File> listFiles(String rootDir) throws IOException {
    try {
      return lambda.toList(Files.walk(Paths.get(rootDir))
          .filter(Files::isRegularFile)
          .map(path -> path.toFile()));
    } catch (IOException e) {
      throw e;
    }
  }

  /**
   * Implement using lambda/stream APIs
   * @param lines
   * @throws IOException
   */
  @Override
  public void writeToFile(List<String> lines) throws IOException {
    final Writer[] writer = {null};
    File file = new File(this.getOutFile());

    if (!file.exists()) {
      file.createNewFile(); //create the out file if it does not exist
    }

    Stream<String> stringStream = lines.stream();

    stringStream.forEach(line -> {
      try {
        writer[0] = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(file, true), "utf-8"));
        writer[0].write(line + "\n");
      } catch (IOException e) {
        try {
          throw new IOException(e);
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      } finally {
        try {
          writer[0].close();
        } catch (NullPointerException | IOException e) {
          //do nothing
        }
    }});
  }
}
