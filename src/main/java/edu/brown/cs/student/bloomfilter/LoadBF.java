package edu.brown.cs.student.bloomfilter;

import edu.brown.cs.student.project1.Command;
import edu.brown.cs.student.project1.Fish;
import edu.brown.cs.student.project1.Fishes;

import java.util.Collection;
import java.util.List;

/** Carries out the load_bf command to
 * load the student data.
 *
 */
public class LoadBF implements Command {

  private List<Fish> fishesList;

  /** Initializes students and csvReader.
   *
   */
  public LoadBF() {
    fishesList = null;
  }

  /** Checks if the load_bf command has been called correctly.
   *
   * @param commandInputs passed in from the BFCommands class
   */
  @Override
  public void run(String[] commandInputs) {
    if (commandInputs.length == 2) {

      Fishes fishes = new Fishes();
      fishes.createObject(commandInputs);

      fishesList = fishes.getObjectDatabase();

      if (fishesList != null) {
        int nMax = 0;

        for (Fish fish : fishesList) {
          Collection<String> studentAttributes = fish.getAttributes();
          if (studentAttributes.size() > nMax) {
            nMax = studentAttributes.size();
          }
        }

        System.out.println("Nanofish options: \n");

        for (Fish fish : fishesList) {
          BloomFilter studentBF = new BloomFilter(0.1, nMax);
          for (String attr : fish.getAttributes()) {
            studentBF.insertBF(attr);
          }
          fish.setBF(studentBF);
          System.out.println(fish.getName());
        }

        int numFishes = fishesList.size();
        System.out.println("Read " + numFishes + " fish from " + commandInputs[1]);
        System.out.println("To find a match (or matches) for a specific fish refer to this template command:");
        System.out.println("similar_bf [num of matches] [first part of fish name] [desired tank size in gallons] ");
        System.out.println("Warning: no matches will appear if tank size is not suitable for the chosen fish \n" );
      }
    }
  }
  /** Loads bfs for each fish in a list.
   *
   * @param fishes passed in
   */
  public void loadFromFishList(List<Fish> fishes) {
    this.fishesList = fishes;

    if (fishesList != null) {
      int nMax = 0;

      for (Fish fish : fishesList) {
        Collection<String> fishtAttributes = fish.getAttributes();
        if (fishtAttributes.size() > nMax) {
          nMax = fishtAttributes.size();
        }
      }

      for (Fish fish : fishesList) {
        BloomFilter fishBF = new BloomFilter(0.1, nMax);
        for (String attr : fish.getAttributes()) {
          fishBF.insertBF(attr);
        }
        fish.setBF(fishBF);
      }
    }
  }

  /** Returns the students.
   *
   * @return studentsList<Student> students
   */
  public List<Fish> getFishes() {
    return fishesList;
  }
}
