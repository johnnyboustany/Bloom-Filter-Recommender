package edu.brown.cs.student.bloomfilter;

import edu.brown.cs.student.project1.Fish;
import java.util.Comparator;
import java.util.Random;


/** Compares two fishes based on their bloom filters */

public class BFComparator implements Comparator<Fish> {

   /** If the first bf is more similar to the given bf than the second, then 1 is returned.
   * -1 is returned if the opposite holds true. If it is a tie, 1 or -1
   * is randomly chosen.
   * @param s1 first fish
   * @param s2 second fish
   * @return int 1 or -1
   */

  @Override
  public int compare(Fish s1, Fish s2) {
    int numTrueBits1 = s1.getNumTrueBits();
    int numTrueBits2 = s2.getNumTrueBits();

    if (numTrueBits1 < numTrueBits2) {
      return 1;
    } else if (numTrueBits1 > numTrueBits2) {
      return -1;
    }

    Random random = new Random();
    // return number between 0 and 9
    if (random.nextInt(10) < 5) {
      return 1;
    } else {
      return -1;
    }
  }
}

