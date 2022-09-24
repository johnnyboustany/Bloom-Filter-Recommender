package edu.brown.cs.student.bloomfilter;

import java.util.BitSet;
import java.util.stream.IntStream;

/**
 * Wrapper class for BitSet to
 * make it fixed size.
 */
public class FixedSizeBitSet extends BitSet {
  private final int nbits;

  /**
   * Storing the nbits value in an instance variable.
   */
  public FixedSizeBitSet(final int nbits) {
    super(nbits);
    this.nbits = nbits;
  }

  /**
   * Converts bits to a string that can be printed.
   * @return String of bits
   */
  @Override
  public String toString() {
    final StringBuilder buffer = new StringBuilder(nbits);
    IntStream.range(0, nbits).mapToObj(i -> get(i) ? '1' : '0').forEach(buffer::append);
    return buffer.toString();
  }
}
