package edu.brown.cs.student.project1;

import edu.brown.cs.student.bloomfilter.BFCommands;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public final class REPL {

  private HashMap<String, Command> commandDatabase;
  String[] tokenList; //tokenized array list to be passed in with command and terminal entries
  static String[] testtokenList; //only to be passed thru for testing

  public REPL() {
    this.commandDatabase = new HashMap<String, Command>();
    this.buildCommandDatabase();
  }

  /**
   * This method creates a key value store of commands mapping from inputted commands (parsed from the terminal
   * by the repl to an instance of a command class which can then perform the appropriate run method.
   */

  public void buildCommandDatabase() {
    BFCommands bfComm = new BFCommands();
    this.commandDatabase.put("load_bf", bfComm);
    this.commandDatabase.put("similar_bf", bfComm);
    this.commandDatabase.put("create_bf", bfComm);
    this.commandDatabase.put("insert_bf", bfComm);
    this.commandDatabase.put("query_bf", bfComm);

  }


  /**
   * The repl simply parses terminal input and tokenized this input into an array of strings which can then
   * be passed into other methods and utilized
   * @param bufferedReader
   * @throws IOException
   */

  public void REPLFunctionality(BufferedReader bufferedReader) throws IOException, NoSuchAlgorithmException { //error checking for .readLine method

    String line = "";

    while ((line = bufferedReader.readLine()) != null) {

      tokenList = line.split("\\s+(?=((\\\\[\\\\\"]|[^\\\\\"])*\"(\\\\[\\\\\"]|[^\\\\\"])*\")*(\\\\[\\\\\"]|[^\\\\\"])*$)"); //site //"\\s+(?=((\\\\[\\\\\"]|[^\\\\\"])*\"(\\\\[\\\\\"]|[^\\\\\"])*\")*(\\\\[\\\\\"]|[^\\\\\"])*$)"
      testtokenList = line.split("\\s+(?=((\\\\[\\\\\"]|[^\\\\\"])*\"(\\\\[\\\\\"]|[^\\\\\"])*\")*(\\\\[\\\\\"]|[^\\\\\"])*$)");

      if (this.commandDatabase.containsKey(tokenList[0])) {
        this.commandDatabase.get(tokenList[0]).run(tokenList);
      } else {
        System.out.println("ERROR:");
      }

    }

  }

  /**
   * this method is only used for testing to verify that the REPL is correctly parsing
   * commmand input and tokenizing
   */

  public String[] returnTokenList(){
    return testtokenList;
  }

}
