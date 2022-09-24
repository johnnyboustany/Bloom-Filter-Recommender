package edu.brown.cs.student.bloomfilter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BloomFilterTest {

  @Test
  public void simpleBFTest() {

    BloomFilter bf = new BloomFilter(0.1,3);
    assertEquals(bf.insertBF("johnny"),"100001000000010000");
    assertTrue(bf.queryBF("johnny"));
    assertFalse(bf.queryBF("Johnny"));
  }

  @Test
  public void calculatingKandMTest() {

    double r = 0.1;
    int n = 10;
    int k = (int) Math.ceil(-1 * BloomFilter.log2(r));
    int m = (int) Math.ceil((k * n) / Math.log(2));

    // k and m are calculated by hand to compare
    assertEquals(k, 4);
    assertEquals(m, 58);
  }

  @Test
  public void BitStringGetterTest() {
    BloomFilter bf = new BloomFilter(0.1,3);
    assertEquals(bf.getBitSet().toString(), "000000000000000000");
  }

  @Test
  public void FixedBitSizeTest() {
    FixedSizeBitSet bits = new FixedSizeBitSet(4);
    bits.set(1);
    assertEquals(bits.toString(), "0100");
  }
}
