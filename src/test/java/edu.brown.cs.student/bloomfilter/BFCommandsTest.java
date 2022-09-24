package edu.brown.cs.student.bloomfilter;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BFCommandsTest {

  @Test
  public void simpleBFCommandsTest() {

    BFCommands commands = new BFCommands();
    assertTrue(commands.getBloomFilter() == null);
    String command1 = "create_bf 0.1 3";
    String[] lineArr1 = command1.split(" ");
    commands.run(lineArr1);

    assertTrue(commands.getBloomFilter() != null);

    BloomFilter bf = commands.getBloomFilter();
    assertEquals(bf.getBitSet().toString(), "000000000000000000");

    String command2 = "insert_bf dog";
    String[] lineArr2 = command2.split(" ");
    commands.run(lineArr2);
    assertEquals(bf.getBitSet().toString(), "000000110000110000");

    String command3 = "query_bf dog";
    String[] lineArr3 = command3.split(" ");
    commands.run(lineArr3);
    assertTrue(bf.queryBF("dog"));
  }


  @Test
  public void CommandClassesTest() {

    CreateBF createBF = new CreateBF();
    String command1 = "create_bf 0.1 3";
    String[] lineArr1 = command1.split(" ");
    createBF.run(lineArr1);
    BloomFilter bf = createBF.getBloomFilter();
    assertNotNull(bf);

    InsertBF insertBF = new InsertBF();
    String command2 = "insert_bf dog";
    String[] lineArr2 = command2.split(" ");
    // didn't set the bf
    insertBF.run(lineArr2);
    assertEquals(bf.getBitSet().toString(), "000000000000000000");

    // now setting the bf
    insertBF.setBloomFilter(bf);
    insertBF.run(lineArr2);
    assertEquals(bf.getBitSet().toString(), "000000110000110000");

    QueryBF queryBF = new QueryBF();
    String command3 = "query_bf dog";
    String[] lineArr3 = command3.split(" ");

    // didn't set the bf
    queryBF.run(lineArr3);
    assertNull(queryBF.getBloomFilter());

    // now setting the bf
    queryBF.setBloomFilter(bf);
    queryBF.run(lineArr3);
    assertTrue(bf.queryBF("dog"));
  }





}
