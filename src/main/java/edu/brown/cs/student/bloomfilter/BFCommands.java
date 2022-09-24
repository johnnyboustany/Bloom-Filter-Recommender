package edu.brown.cs.student.bloomfilter;

import edu.brown.cs.student.project1.Command;
import edu.brown.cs.student.project1.Fish;

import java.util.List;

/** Has all the bloom filter commands
 * at its direct reach.
 *
 */
public class BFCommands implements Command {
  private BloomFilter bf;
  private List<Fish> fishes;
  private CreateBF createBF;
  private InsertBF insertBF;
  private QueryBF queryBF;
  private LoadBF loadBF;
  private SimilarBF similarBF;

  /** Initializes instance variables and instantiates
   * commands.
   *
   */
  public BFCommands() {
    bf = null;
    fishes = null;
    createBF = new CreateBF();
    insertBF = new InsertBF();
    queryBF = new QueryBF();
    loadBF = new LoadBF();
    similarBF = new SimilarBF();
  }

  /** Calls the run method on a command
   * depending on the passed in string.
   * @param tokenList string
   */
  @Override
  public void run(String[] tokenList) {
    // checking if the line contains one of the many commands
    switch (tokenList[0]) {
      case "create_bf":
        createBF.run(tokenList);
        bf = createBF.getBloomFilter();
        break;
      case "insert_bf":
        insertBF.setBloomFilter(bf);
        insertBF.run(tokenList);
        break;
      case "query_bf":
        queryBF.setBloomFilter(bf);
        queryBF.run(tokenList);
        break;
      case "load_bf":
        loadBF.run(tokenList);
        fishes = loadBF.getFishes();
        break;
      case "similar_bf":
        similarBF.setFishes(fishes);
        similarBF.run(tokenList);
      default:
        break;
    }
  }

  /** Getter method for bf.
   * @return BloomFilter bf
   */
  public BloomFilter getBloomFilter() {
    return bf;
  }

}
