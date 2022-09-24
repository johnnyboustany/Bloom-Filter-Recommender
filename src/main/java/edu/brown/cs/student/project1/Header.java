package edu.brown.cs.student.project1;

/**
 * details header objects and attributes
 */

public class Header implements Parsable<Header> {

    private String headerName;
    private String type;

    /**
     * to create a header object we need the name as well as the type (qualitative or quantitative)
     * @param headerName
     * @param type
     */

    public Header(String headerName, String type) {
        this.headerName = headerName;
        this.type = type;
    }

    @Override
    public boolean createObject(String[] csvLine) {
        return false;
    }

    @Override
    public boolean dataTypeCheck(String header) {
        return false;
    }

    /**
     * getter methods for header info
     */

    public String getHeaderName(){
        return headerName;
    }

    public String getHeaderType() {
        return type;
    }



}
