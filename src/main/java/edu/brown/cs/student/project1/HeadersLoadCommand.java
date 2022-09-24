package edu.brown.cs.student.project1;

import java.util.*;

/**
 * class handles loading headers into a map
 */

public final class HeadersLoadCommand implements Command{

    static Map<String, String> headersAndTypes;
    private CSVGeneric<Header> csvReader;

    public HeadersLoadCommand(){
        headersAndTypes = new HashMap<>();
    }

    /**
     * this method is called in the generic repl and executes the given header command
     * @param commandInputs terminal commands put in by the user
     */

    @Override
    public void run(String[] commandInputs) {

        if (commandInputs.length > 1 ) {

            if (commandInputs[0].equals("headers_load")) {
                this.LoadMap(commandInputs);
                System.out.println(headersAndTypes.toString());
            } else {
                System.out.println("ERROR: Command does not exist.");
            }

        } else {
            System.out.println("ERROR: Insufficient Commands");
        }

    }

    /**
     * loads header info into a key value store
     * @param commandInputs
     */

    public void LoadMap(String[] commandInputs){

        csvReader = new CSVGeneric<>();
        ArrayList<String[]> HeadersCreationArrayList = new ArrayList<>();
        HeadersCreationArrayList =  csvReader.parseFile(commandInputs[1]);

        for (int i = 0; i < HeadersCreationArrayList.size() ; i++){

            headersAndTypes.put(HeadersCreationArrayList.get(i)[0].toLowerCase(Locale.ROOT).trim(), HeadersCreationArrayList.get(i)[1].toLowerCase(Locale.ROOT).trim());
            //new Header(HeadersCreationArrayList.get(i)[0], HeadersCreationArrayList.get(i)[1]);
        }
        System.out.println("Loaded header types from " + commandInputs[1]);
        //System.out.println(headersAndTypes.toString()); //testing

    }

    /**
     * This method returns the type of an inputted header
     * @param header
     * @return
     */

    public String getHeaderType(String header) {
        return headersAndTypes.get(header);
    }

}

