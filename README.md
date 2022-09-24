# README

NanoTank-Recommender:

--Overview--

The high-level modules defining the application are:

- A generic CSV Reader and REPL that can be applied to the other high-level modules in the program.
- The ability to load bloom filters for every fish in a dataset and make recommendations for
 similar fishes by finding similar bloom filters.

The user will interact with the program primarily through the code terminal. It will be the responsibility of the user to input commands.
There will not be a high tolerance for typing or formatting errors. Eventually, it may become a goal to create options for the program to handle certain types
of input, however the initial goal is to simply have a functional algorithm that works with correct inputs.

--How To Run--

mvn package
./run --gui

Then in the REPL:
1. Load the bloom filters using "load_bf fish", which will output a list of fish.
2. Find matches for a specific fish from the list using
 "similar_bf similar_bf [num of matches] [first part of fish name] [desired tank size in gallons]"

--Testing--
JUnit tests were written to test the functionality of the bloom filter implemented
and test the commands.

System tests were written to test the functionality of our bloom filter, to make sure that it can be created, inserted into and also queried.
This makes sure that the bloom filter will perform optimally once used to store fish information.
