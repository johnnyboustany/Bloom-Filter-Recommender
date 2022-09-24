package edu.brown.cs.student.project1;

import edu.brown.cs.student.bloomfilter.BloomFilter;
import edu.brown.cs.student.bloomfilter.SimilarityMeasure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Fish implements Parsable {

  //all the attributes needed to create a student object
  private int id;
  private String name;
  private String water;
  private String behavior;
  private String habitat;
  private String substrate;
  private String aquarium_preference;
  private double min_pH;
  private double max_pH;
  private double min_hardness;
  private double max_hardness;
  private double min_temp;
  private double max_temp;
  private int min_tank_size;
  private String image;
  private String[] fishAttributes;
  private BloomFilter bf;
  private int numTrueBits;

  //in order to construct a student from the csv file, i will pass in all the attributes as an arraylist of strings which can then be
  //converted into other object types as necessary (ie strings)
  //this closely mirrors the structure of my star object class from project0

  public Fish(String[] csvattributes) {

    fishAttributes = csvattributes;
    this.id = Integer.parseInt(csvattributes[0]); //in order to streamline passing in attributes the integer conversion happens
    //here rather than within the constructor parameters or later on down the line
    this.name = csvattributes[1];
    this.water = csvattributes[2];
    this.behavior = csvattributes[3];
    this.habitat = csvattributes[4];
    this.substrate = csvattributes[5];
    this.aquarium_preference = csvattributes[6];
    this.min_pH = Double.parseDouble(csvattributes[7]);
    this.max_pH = Double.parseDouble(csvattributes[8]);
    this.min_hardness = Double.parseDouble(csvattributes[9]);
    this.max_hardness = Double.parseDouble(csvattributes[10]);
    this.min_temp = Double.parseDouble(csvattributes[11]);
    this.max_temp = Double.parseDouble(csvattributes[12]);
    this.min_tank_size = Integer.parseInt(csvattributes[13]);
    this.image = csvattributes[14];
    numTrueBits = 0;
  }

  // methods for bloom filter

  public Collection<String> getAttributes() {
    Collection<String> attributes = new ArrayList<>();
    String comma = ", ";
    for (int i = 2; i <= 6; i++) {
      attributes.addAll(Arrays.asList(fishAttributes[i].split(comma)));
    }
    return attributes;
  }

  /** Setter method for bf.
   * @param bloomFilter bf
   */
  public void setBF(BloomFilter bloomFilter) {
    bf = bloomFilter;
  }

  /** Getter method for bf.
   * @return BloomFilter bf
   */
  public BloomFilter getBF() {
    return bf;
  }

  /** Getter method for ID.
   * @return int id
   */
  public int getId() {
    return id;
  }

  public double getMin_pH() {
    return min_pH;
  }

  public double getMax_pH() {
    return max_pH;
  }

  public double getMin_hardness() {
    return min_hardness;
  }

  public double getMax_hardness() {
    return max_hardness;
  }

  public double getMin_temp() {
    return min_temp;
  }

  public double getMax_temp() {
    return max_temp;
  }

  public int getMin_tank_size() {
    return min_tank_size;
  }

  public String getName() {
    return name;
  }

  /** Sets the number
   * of true bits with respect
   * to a given student.
   * @param givenFish student
   * @param sim similarity measure
   */
  public void setNumTrueBits(Fish givenFish, SimilarityMeasure sim) {
    numTrueBits = sim.findNumTrueBits(givenFish.getBF().getBitSet(), this.getBF().getBitSet());
  }
  /** Gets the number
   * of true bits with respect
   * to a given student.
   */
  public int getNumTrueBits() {
    return numTrueBits;
  }

  @Override
  public boolean createObject(String[] csvLine) {
    return false;
  }

  @Override
  public boolean dataTypeCheck(String header) {
    return false;
  }

  public int id() {
    return id;
  }
}
