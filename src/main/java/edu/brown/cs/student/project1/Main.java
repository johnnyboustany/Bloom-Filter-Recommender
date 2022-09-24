package edu.brown.cs.student.project1;

// look into using these imports for your REPL!

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.Spark;

import java.io.*;
import java.security.NoSuchAlgorithmException;

/**
 * The Main class of our project. This is where execution begins.
 */
public final class Main {

  // use port 4567 by default when running server
  private static final int DEFAULT_PORT = 4567;

  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }


  private String[] args;

  public Main(String[] args) {
    this.args = args;
  }

  public void run() {
    // set up parsing of command line flags
    OptionParser parser = new OptionParser();

    // "./run --gui" will start a web server
    parser.accepts("gui");

    // use "--port <n>" to specify what port on which the server runs
    parser.accepts("port").withRequiredArg().ofType(Integer.class)
        .defaultsTo(DEFAULT_PORT);

    OptionSet options = parser.parse(args);
    if (options.has("gui")) {

      runSparkServer((int) options.valueOf("port"));

    }

    REPL repl = new REPL();
    try {
      //added
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      repl.REPLFunctionality(bufferedReader);
    } catch (IOException | NoSuchAlgorithmException e) {
      System.out.println("ERROR: no more entries"); //testing for io exception

    }
  }


  private void runSparkServer(int port) {
    // set port to run the server on
    Spark.port(port);

    // specify location of static resources (HTML, CSS, JS, images, etc.)
    Spark.externalStaticFileLocation("src/main/resources/static");

  }

}
