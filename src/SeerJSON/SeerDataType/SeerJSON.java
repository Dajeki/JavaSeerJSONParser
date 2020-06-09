package SeerJSON.SeerDataType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Wrapper class for easy naming on objects for getting values
 */
public class SeerJSON {

    protected SeerStringifiedJSON objectCreator;

    protected String propertyName;
    protected String propertyValue;
    protected ESeerType valueType;

    protected HashMap<String, SeerJSON> JSONHashMap = null;

    public SeerJSON( String propertyName, String propertyValue, SeerStringifiedJSON creator, ESeerType valueType ) {
        
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
        this.valueType = valueType;
        this.objectCreator = creator;

        if ( valueType == ESeerType.OBJECT ) {
            JSONHashMap = new HashMap<String, SeerJSON>();
            JSONHashMap.put("0", this);
            setupObject(creator.getSeperatedJSON());
        }
        else if ( valueType == ESeerType.ARRAY ) {
            JSONHashMap = new HashMap<String, SeerJSON>();
            setupArray(creator.getSeperatedJSON());
        } 
    }

    protected void setupObject ( ArrayList<String> JSONStringArray ) {

        //*Debugging Output - Uncomment to whatch program run.
        // System.out.println("Printing out Object");

        String stringBefore = "";
        String stringInformation = "";
        String stringAfter = "";
        String strContainingObj = "";
        
        for ( int listIndex = 1; listIndex < JSONStringArray.size() - 1; ++listIndex ) { //-1 to account for last bracket

            //Must have a selection each time so we can skip past Object and Array blocks.
            stringBefore = JSONStringArray.get(listIndex - 1);
            stringInformation = JSONStringArray.get(listIndex);
            stringAfter = JSONStringArray.get(listIndex + 1);

            // System.out.println();
            // System.out.println("before " + stringBefore);
            // System.out.println("current " + stringInformation);
            // System.out.println("after " + stringAfter);
            
            //This means that it is a property of the object and not a new object or array
            if ( stringInformation.equals(":") && !stringAfter.equals("{") && !stringAfter.equals("[")  ) {
                System.out.println("Found object property.");
                System.out.println();

                listIndex += 3;
                //remove "" from strings so it can return the actual value
                JSONHashMap.put( stringBefore.replaceAll("[\"\']", ""),new SeerJSON(stringBefore.replaceAll("[\"\']", ""), stringAfter.replaceAll("[\"\']", ""), new SeerStringifiedJSON(stringAfter), determineType(stringAfter)) );
            
            }
            //Boolean to check if this property is a new object
            else if ( stringInformation.equals(":") && stringAfter.equals("{") ) {

                System.out.println("Found object property that is an object.");
                System.out.println();
    
                strContainingObj = getContainerString(new ArrayList<String>(JSONStringArray.subList(listIndex + 1, JSONStringArray.size())));
                SeerStringifiedJSON stringifiedContainer = new SeerStringifiedJSON( strContainingObj );
                
                //skip past what we just read in for the next go around by removing
                listIndex += stringifiedContainer.getSeperatedJSON().size() + 2;

                JSONHashMap.put(stringBefore.replaceAll("[\"\']", ""), new SeerJSON( stringBefore.replaceAll("[\"\']", ""), "{" + strContainingObj + "}", stringifiedContainer, ESeerType.OBJECT ) );
            
            }
            //Boolean to check if this property is a new object
            else if ( stringInformation.equals(":") && stringAfter.equals("[") ) {
                
                System.out.println("Found object property that is an array.");
                System.out.println();

                strContainingObj = getContainerString(new ArrayList<String>(JSONStringArray.subList(listIndex + 1, JSONStringArray.size())));
                SeerStringifiedJSON stringifiedContainer = new SeerStringifiedJSON( strContainingObj );
                
                //skip past what we just read in for the next go around by removing
                listIndex += stringifiedContainer.getSeperatedJSON().size() + 2;
                
                JSONHashMap.put(stringBefore.replaceAll("[\"\']", ""), new SeerJSON( stringBefore.replaceAll("[\"\']", ""), "[" + strContainingObj + "]", stringifiedContainer, ESeerType.ARRAY ) );

            }
        }
    }

    protected void setupArray ( ArrayList<String> JSONStringArray ){

        String stringInformation = "";
        String strContainingObj = "";

        int ArrayIndex = 0;

        System.out.println("Array contains values " + JSONStringArray); //this will include the , as its own index. Ignore on output.
        System.out.println();
        
        for ( int listIndex = 0; listIndex < JSONStringArray.size(); ++listIndex ) {

            stringInformation = JSONStringArray.get(listIndex);
            
            if ( !stringInformation.equals(",") && !stringInformation.equals("{") && !stringInformation.equals("[")) {

                JSONHashMap.put( Integer.toString(ArrayIndex), new SeerJSON( Integer.toString(ArrayIndex++), stringInformation, new SeerStringifiedJSON( stringInformation ), determineType( stringInformation ) ) );
                listIndex += 2;

            }
            //This means we are about to have an array of objects.
            else if ( stringInformation.equals("{") ) {

                strContainingObj = getContainerString(new ArrayList<String>(JSONStringArray.subList(listIndex, JSONStringArray.size())));
                SeerStringifiedJSON stringifiedContainer = new SeerStringifiedJSON( strContainingObj );
                
                //skip past what we just read in for the next go around (+2 for the brackets that were removed.)
                listIndex += stringifiedContainer.getSeperatedJSON().size() + 2;

                JSONHashMap.put(Integer.toString(ArrayIndex), new SeerJSON( Integer.toString(ArrayIndex++), "{" + strContainingObj + "}", stringifiedContainer, ESeerType.OBJECT ) );
            
            }
            else if ( stringInformation.equals("[") && listIndex != 0) {

                strContainingObj = getContainerString(new ArrayList<String>(JSONStringArray.subList(listIndex, JSONStringArray.size())));
                SeerStringifiedJSON stringifiedContainer = new SeerStringifiedJSON( strContainingObj );
                
                //skip past what we just read in for the next go around by removing
                listIndex += stringifiedContainer.getSeperatedJSON().size() + 2;

                JSONHashMap.put(Integer.toString(ArrayIndex), new SeerJSON( Integer.toString(ArrayIndex++), "[" + strContainingObj + "]", stringifiedContainer, ESeerType.ARRAY ) );

            }
        }
    }

    public String getContainerString( ArrayList<String> ArrayListToCheck ) {

        int bracketDepth = 0;
        String strReturn = "";
        for (String string : ArrayListToCheck) {
            
            if ( (string.equals("{") || string.equals("[")) && bracketDepth == 0 ) {
                bracketDepth++;
            }
            else if ( (string.equals("}") || string.equals("]")) && bracketDepth - 1 == 0 ) {
                bracketDepth--;
            }
            else if ( string.equals("{") || string.equals("[") ) {
                strReturn += string;
                bracketDepth++;
            }
            else if ( string.equals("}") || string.equals("]") ) {
                strReturn += string;
                bracketDepth--;
            }
            else {   
                strReturn += string;
            }

            if (bracketDepth == 0) {
                return strReturn;
            }
        }
        return strReturn;
    }

    private ESeerType determineType(String stringChecking) {

        stringChecking = stringChecking.toLowerCase();

        if (stringChecking.contains("\"") || stringChecking.contains("\'")) {
            return ESeerType.STRING;
        }
        else if(stringChecking.equals("true") || stringChecking.equals("false")) {
            return ESeerType.BOOLEAN;
        }
        else if(stringChecking.equals("null")) {
            return ESeerType.NULL;
        }
        else if( stringChecking.contains(".")) {
            return ESeerType.DOUBLE;
        }
        else {
            return ESeerType.INTEGER;
        }
    }

    private String interpretEscapeCharacters(String stringToChange) {
        stringToChange = stringToChange.replace("\\r", "\r");
        stringToChange = stringToChange.replace("\\n", "\n");
        return stringToChange;
    }

    @SuppressWarnings("unchecked")
    public <ReturnType> ReturnType $() {
        if (valueType == ESeerType.INTEGER) {
            return (ReturnType)Integer.valueOf(Integer.parseInt(propertyValue));
        }
        else if (valueType == ESeerType.DOUBLE) {
            return (ReturnType)Double.valueOf(Double.parseDouble(propertyValue));
        }
        else if (valueType == ESeerType.BOOLEAN) {
            return (ReturnType)Boolean.valueOf(Boolean.parseBoolean(propertyValue));
        }
        else if (valueType == ESeerType.STRING) {
            return (ReturnType)interpretEscapeCharacters(propertyValue);
        }
        else if (valueType == ESeerType.OBJECT || valueType == ESeerType.ARRAY) {
            return (ReturnType)this;
        }
        else {
            return null;
        }
    }

    public SeerJSON $(String propertyName){
        return JSONHashMap.get( propertyName );
    }
    
    public SeerJSON $(Integer arrayIndex){
        return JSONHashMap.get( arrayIndex.toString() );
    }

    public void printObject(){
        objectCreator.printObject();
    }

    public ESeerType getType(){
        return this.valueType;
    }

    @Override
    public String toString(){
        return propertyValue;
    }
}

