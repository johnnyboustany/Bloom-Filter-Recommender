package edu.brown.cs.student.project1;

import java.security.NoSuchAlgorithmException;

public interface Command {

  void run(String[] commandInputs) throws NoSuchAlgorithmException;

}
