package edu.brown.cs.student.project1;

import java.util.ArrayList;
import java.util.List;

/**
 * This class details how Student objects can be created form the data structure returned by the csvreader
 * it implements the loadedDatabase interface because these objects have data structures which store
 * created instances
 */

public final class Fishes implements LoadedDatabase {

  static List<Fish> fishDatabase;

  public Fishes() {
    fishDatabase = new ArrayList<>();
  }

  @Override
  public void createObject(String[] line) {
    if(line[1].equals("fish")) {
      line[1] = "data/project1/fish.csv";
    }

    CSVGeneric genericCSV = new CSVGeneric();
    ArrayList<String[]> studentInfo = genericCSV.parseFile(line[1]);

    if(studentInfo != null) {
      for (String[] csvTokens : studentInfo) {

        if (csvTokens[0].equals("")) {
          continue;
        }
        //handling some regex issue specific to student csv entries
        csvTokens[3] = csvTokens[3].replaceAll("\"", "");
        csvTokens[5] = csvTokens[5].replaceAll("\"", "");
        csvTokens[6] = csvTokens[6].replaceAll("\"", "");

        Fish fish = new Fish(csvTokens);
        fishDatabase.add(fish);
      }
    } else {
      fishDatabase = null;
    }
  }

  @Override
  public String getDataType() {
    return null;
  }

  /**
   * This method can be used to access the static student database but is not necessary due to the static
   * structure.
   */

  @Override
  public List<Fish> getObjectDatabase() {
    return fishDatabase;
  }
}
