package edu.brown.cs.student.bloomfilter;

import edu.brown.cs.student.project1.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

/** Recommends fishes based on the given fish
 * using a similarity algorithm.
 */
public class BFRecommender {

  private final List<Fish> fishes;
  private final SimilarityMeasure similarity;

  /** Stores the fishes data structure and similarity measure
   * for use in the similar fishes algorithm.
   * @param fishies fishes
   * @param sim similarity algorithm
   */
  public BFRecommender(List<Fish> fishies, SimilarityMeasure sim) {
    fishes = fishies;
    similarity = sim;
  }

  /** Finds the student based on their userID.
   *
   * @param name of fish
   * @return Fish that the id belongs to
   */
  public Fish findFish(String name) {
    for (Fish fish : fishes) {
      if (fish.getName().split(" ")[0].equals(name)) {
        return fish;
      }
    }
    return null;
  }
  /** Runs the similar students algorithm to find a k number of most similar
   * students to a specific given fish.
   *
   * @param k integer number of similar fish
   * @param name of given fish
   * @return Collection<Fish> an arraylist containing the k most similar
   * students to the given student
   */
  public Collection<Fish> findSimilarFishes(int k, String name, int tank_size) {
    Fish givenFish = findFish(name);
    if (givenFish != null) {

      // return empty list if tank size is not compatible
      if (tank_size < givenFish.getMin_tank_size()) {
        return new ArrayList<>();
      }

      PriorityQueue<Fish> pq = new PriorityQueue<>(new BFComparator());

      // if a name was given, then that student has to be skipped and not
      // considered as a similar student
      boolean skippedAFish = false;

      for (Fish fish : fishes) {
        if (!skippedAFish && fish.getName().equals(name)) {
          skippedAFish = true;
          continue;
        }
        if(!fish.getName().equals(name) && (givenFish.getMin_pH() <= fish.getMax_pH() && fish.getMin_pH() <= givenFish.getMax_pH())
        && (givenFish.getMin_hardness() <= fish.getMax_hardness() && fish.getMin_hardness() <= givenFish.getMax_hardness())
        && (givenFish.getMin_temp() <= fish.getMax_temp() && fish.getMin_temp() <= givenFish.getMax_temp())
        && tank_size >= fish.getMin_tank_size()) {
          fish.setNumTrueBits(findFish(name), similarity);
          pq.add(fish);
        }
      }

      Collection<Fish> similarFishes = new ArrayList<>();

      // if k is larger than the number of stars being stored,
      // then all of them are added to the similarStudents arrayList,
      // except the student that a userID was given for

      for (int i = 0; i < k; i++) {
        // removing the top of the queue
        // and storing it in s
        Fish s = pq.poll();
        if (s != null) {
          similarFishes.add(s);
        }
      }
      return similarFishes;

    } else {
      return null;
    }
  }
}
