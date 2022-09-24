package edu.brown.cs.student.project1;

public interface Parsable<T> {

  /**
   * parses the lines of the csv file according to specifications based on object type
   */

  boolean createObject(String[] csvLine);

  boolean dataTypeCheck(String header);

}
