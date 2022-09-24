package edu.brown.cs.student.bloomfilter;

import edu.brown.cs.student.project1.Command;

/** Carries out the create_bf command to
 * make the bloom filter.
 *
 */
public class CreateBF implements Command {
  private BloomFilter bf;

  public CreateBF() {
    bf = null;
  }

  /** Checks if the create_bf command has been called correctly.
   *
   * @param commandInputs passed in from the BFCommands class
   */
  @Override
  public void run(String[] commandInputs) {
    if (commandInputs.length == 3
        && isDouble(commandInputs[1]) && isInteger(commandInputs[2])) {
      double r = Double.parseDouble(commandInputs[1]);
      int n = Integer.parseInt(commandInputs[2]);

      bf = new BloomFilter(r, n);
      System.out.println(bf.getBitSet().toString());

    } else {
      System.out.println("ERROR: Invalid create_bf Command");
    }
  }

  public BloomFilter getBloomFilter() {
    return bf;
  }

  /** Checks if a string can be parsed into a double.
   *
   * @param str a string
   * @return boolean
   */
  public boolean isDouble(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
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
