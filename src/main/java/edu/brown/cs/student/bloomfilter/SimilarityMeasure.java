package edu.brown.cs.student.bloomfilter;

import java.util.BitSet;

/**
 * Interface that can implemented by a class
 * that carries out a similarity algorithm.
 */
public interface SimilarityMeasure {
  int findNumTrueBits(BitSet given, BitSet bits);
}
