package edu.brown.cs.student.bloomfilter;

import edu.brown.cs.student.project1.Command;

/** Carries out the insert_bf command to
 * insert into the bloom filter.
 *
 */
public class InsertBF implements Command {
  private BloomFilter bf;

  public InsertBF() {
    bf = null;
  }

  /** Checks if the insert_bf command has been called correctly.
   *
   * @param commandInputs passed in from the BFCommands class
   */
  @Override
  public void run(String[] commandInputs) {
    if (bf != null && commandInputs.length == 2) {
      System.out.println(bf.insertBF(commandInputs[1]));
    } else {
      System.out.println("ERROR: Invalid insert_bf Command");
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
