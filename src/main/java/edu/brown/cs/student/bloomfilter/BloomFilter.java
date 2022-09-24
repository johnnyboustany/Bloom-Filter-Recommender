package edu.brown.cs.student.bloomfilter;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

/**
 * BloomFilter contains an array of bits
 * that it can call methods on.
 */
public class BloomFilter {

  private static MessageDigest HASH_FUNCTION = null;
  static {
    try {
      HASH_FUNCTION = MessageDigest.getInstance("SHA-1");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }
  private static final Charset CHARSET = StandardCharsets.UTF_8;
  private final int k;
  private final int m;
  private final FixedSizeBitSet bits;

  /**
   * Constructs the bloom filter.
   * @param r desired false positive rate
   * @param n maximum number of elements that will be inserted
   */
  public BloomFilter(double r, int n) {

    // # of hash functions
    k = (int) Math.ceil(-1 * log2(r));

    // m bit length
    m = (int) Math.ceil((k * n) / Math.log(2));

    bits = new FixedSizeBitSet(m);
  }

  /**
   * Inserts into BF.
   * @param element string
   * @return String
   */
  public String insertBF(String element) {
    byte[] b = element.getBytes(StandardCharsets.UTF_8);
    BigInteger[] hashes = createHashes(b, k);
    for (int i = 0; i < k; i++) {
      int key = hashes[i].mod(BigInteger.valueOf(m)).intValue();
      bits.set(key);
    }
    return bits.toString();
  }
  /**
   * Queries BF for the element.
   * @param element string
   * @return boolean
   */
  public boolean queryBF(String element) {
    byte[] b = element.getBytes(StandardCharsets.UTF_8);
    BigInteger[] hashes = createHashes(b, k);

    for (int i = 0; i < k; i++) {
      int key = hashes[i].mod(BigInteger.valueOf(m)).intValue();

      if (!bits.get(key)) {
        return false;
      }
    }
    return true;
  }
  /**
   * Function to calculate the
   * log base 2 of an integer.
   * @param x a double
   * @return a double (result)
   */
  public static double log2(double x) {
    // calculate log2 x indirectly
    // using log() method
    return Math.log(x) / Math.log(2);
  }

  /**
   * Generates hashes based on the contents of an array of bytes, converts the result into
   * BigIntegers, and stores them in an array. The hash function is called until the required number
   * of BigIntegers are produced.
   * For each call to the hash function a salt is prepended to the data. The salt is increased by 1
   * for each call.
   *
   * @param data input data.
   * @param numHashes number of hashes/BigIntegers to produce.
   * @return array of BigInteger hashes
   */
  public static BigInteger[] createHashes(byte[] data, int numHashes) {
    BigInteger[] result = new BigInteger[numHashes];

    int k = 0;
    BigInteger salt = BigInteger.valueOf(0);
    while (k < numHashes) {
      HASH_FUNCTION.update(salt.toByteArray());
      salt = salt.add(BigInteger.valueOf(1));
      byte[] hash = HASH_FUNCTION.digest(data);
      HASH_FUNCTION.reset();

      // convert hash byte array to hex string, then to BigInteger
      String hexHash = bytesToHex(hash);
      result[k] = new BigInteger(hexHash, 16);
      k++;
    }
    return result;
  }

  /**
   * Converts a byte array to a hex string.
   * Source: https://stackoverflow.com/a/9855338
   *
   * @param bytes the byte array to convert
   * @return the hex string
   */
  private static String bytesToHex(byte[] bytes) {
    byte[] hexArray = "0123456789ABCDEF".getBytes(CHARSET);
    byte[] hexChars = new byte[bytes.length * 2];
    for (int j = 0; j < bytes.length; j++) {
      int v = bytes[j] & 0xFF;
      hexChars[j * 2] = hexArray[v >>> 4];
      hexChars[j * 2 + 1] = hexArray[v & 0x0F];
    }
    return new String(hexChars, CHARSET);
  }


  /** Getter to return the bitset.
   *
   * @return bitSet
   */
  public BitSet getBitSet() {
    return bits;
  }
}

