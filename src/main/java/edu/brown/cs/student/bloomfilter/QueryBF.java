package edu.brown.cs.student.bloomfilter;

import edu.brown.cs.student.project1.Command;

/** Carries out the query_bf command to
 * query the bloom filter.
 *
 */
public class QueryBF implements Command {
  private BloomFilter bf;

  public QueryBF() {
    bf = null;
  }
  /** Checks if the query_bf command has been called correctly.
   *
   * @param commandInputs passed in from the BFCommands class
   */
  @Override
  public void run(String[] commandInputs) {
    if (bf != null && commandInputs.length == 2) {

      if (bf.queryBF(commandInputs[1])) {
        System.out.println("\"" + commandInputs[1] + "\" " + "might be in the set.");
      } else {
        System.out.println("\"" + commandInputs[1] + "\" " + "is definitely not in the set.");
      }
    } else {
      System.out.println("ERROR: Invalid query_bf Command");
    }
  }

  /** Stores a bloomFilter in the bf instance variable.
   *
   * @param bloomFilter passed in from the BFCommands class
   */
  public void setBloomFilter(BloomFilter bloomFilter) {
    bf = bloomFilter;
  }

  /** Getter method for bf.
   * @return BloomFilter bf
   */
  public BloomFilter getBloomFilter() {
    return bf;
  }

}
