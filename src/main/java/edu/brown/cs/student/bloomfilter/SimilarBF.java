package edu.brown.cs.student.bloomfilter;

import edu.brown.cs.student.project1.Command;
import edu.brown.cs.student.project1.Fish;

import java.util.Collection;
import java.util.List;

/** Carries out the similar_bf command to
 * find k similar students.
 *
 */
public class SimilarBF implements Command {
  private List<Fish> fishes;
  private final SimilarityMeasure sim;

  public SimilarBF() {
    fishes = null;
    sim = new XNORSimilarity();
  }

  public void setFishes(List<Fish> studs) {
    fishes = studs;
  }

  /** Checks if the similar_bf command has been called correctly.
   *
   * @param commandInputs passed in from the BFCommands class
   */
  @Override
  public void run(String[] commandInputs) {

    if (fishes != null && isInteger(commandInputs[commandInputs.length-1])) {
      int k = Integer.parseInt(commandInputs[1]);
      String name  = commandInputs[2];
      int tankSize  = Integer.parseInt(commandInputs[commandInputs.length-1]);

      BFRecommender recommender = new BFRecommender(fishes, sim);
      Collection<Fish> similarFishes = recommender.findSimilarFishes(k, name, tankSize);

      if (similarFishes != null) {
        for (Fish similarFish : similarFishes) {
          System.out.println(similarFish.getName());
        }
      } else {
        System.out.println("ERROR: Invalid userID");
      }
    } else {
      System.out.println("ERROR: Invalid similar_bf Command");
    }
  }

  /** Checks if a string can be parsed into an integer.
   *
   * @param str a string
   * @return boolean
   */
  public boolean isInteger(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
