package edu.brown.cs.student.project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;


//source https://www.opencodez.com/java/generic-csv-file-reader-in-java.htm

public class CSVGeneric<Data extends Parsable> {


  public CSVGeneric() { }

  /**
   * the parseFile method takes in a file name and a new instance of an object implementing the loaded database
   * class.  the second parameter ended up being not utilized although it was intended to be used for object
   * creation. however, all the generic csv reader does is return a data structure and further object creation
   * is delegated to individual classes.
   * @param filename
   * @param object
   * @return
   */

  public ArrayList<String[]> parseFile(String filename) {

    ArrayList<String[]> itemCreationArrayList = new ArrayList<String[]>();
    String line = "";

    try {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      br.readLine(); //skip over first line

      while ((line = br.readLine()) != null) {

        String[] csvTokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);  // splits line by commas, check regex
        itemCreationArrayList.add(csvTokens);

      }
    } catch (IOException e1) {
      System.out.println("ERROR: Invalid File");
      return null;
    }
    return itemCreationArrayList;
  }
}
