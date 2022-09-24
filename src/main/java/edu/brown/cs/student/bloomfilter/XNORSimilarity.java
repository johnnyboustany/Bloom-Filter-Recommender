package edu.brown.cs.student.bloomfilter;

import java.util.BitSet;

/**
 * Uses XNOR Similarity to allow each bitset to be compared
 * by the BFComparator.
 */
public class XNORSimilarity implements SimilarityMeasure {

  /**
   * Finds the number of true bits after applying the XNOR algorithm.
   * @param bits bitset
   */
  @Override
  public int findNumTrueBits(BitSet given, BitSet bits) {
    BitSet currentBits = new BitSet(bits.length());
    currentBits.or(bits);

    currentBits.xor(given);
    // xnor is the complement of xor

    currentBits.flip(0, bits.length());

    return currentBits.cardinality();
  }
}
