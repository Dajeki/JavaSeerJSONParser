package SeerJSON.SeerDataType;

import java.util.ArrayList;
import java.util.HashMap;


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

        String stringBefore = "";
        String stringInformation = "";
        String stringAfter = "";
        String strContainingObj = "";
        
        for ( int listIndex = 1; listIndex < JSONStringArray.size() - 1; ++listIndex ) { //-1 to account for last bracket

            stringBefore = JSONStringArray.get(listIndex - 1);
            stringInformation = JSONStringArray.get(listIndex);
            stringAfter = JSONStringArray.get(listIndex + 1);
            
            //This means that it is a "primitive" property of the object and not a new object or array
            if ( stringInformation.equals(":") && !stringAfter.equals("{") && !stringAfter.equals("[")  ) {

                listIndex += 3; //Skip to the next : . 1 for the value, one for the comma, one for the next property value. The loop of the index will put it back to the next :
            
                JSONHashMap.put( stringBefore.replaceAll("[\"\']", ""),new SeerJSON(stringBefore.replaceAll("[\"\']", ""), stringAfter.replaceAll("[\"\']", ""), new SeerStringifiedJSON(stringAfter), determineType(stringAfter)) );
            
            }
            //Check to see if property is a new object.
            else if ( stringInformation.equals(":") && stringAfter.equals("{") ) {
    
                strContainingObj = getContainerString(new ArrayList<String>(JSONStringArray.subList(listIndex + 1, JSONStringArray.size())));
                SeerStringifiedJSON stringifiedContainer = new SeerStringifiedJSON( strContainingObj );
                
                listIndex += stringifiedContainer.getSeperatedJSON().size() + 2; // +2 For removed braces

                JSONHashMap.put(stringBefore.replaceAll("[\"\']", ""), new SeerJSON( stringBefore.replaceAll("[\"\']", ""), "{" + strContainingObj + "}", stringifiedContainer, ESeerType.OBJECT ) );
            
            }
            //Checks to see if the property is an array.
            else if ( stringInformation.equals(":") && stringAfter.equals("[") ) {

                strContainingObj = getContainerString(new ArrayList<String>(JSONStringArray.subList(listIndex + 1, JSONStringArray.size())));
                SeerStringifiedJSON stringifiedContainer = new SeerStringifiedJSON( strContainingObj );
                
                listIndex += stringifiedContainer.getSeperatedJSON().size() + 2; // +2 For removed braces
                
                JSONHashMap.put(stringBefore.replaceAll("[\"\']", ""), new SeerJSON( stringBefore.replaceAll("[\"\']", ""), "[" + strContainingObj + "]", stringifiedContainer, ESeerType.ARRAY ) );

            }
        }
    }

    protected void setupArray ( ArrayList<String> JSONStringArray ){

        String stringInformation = "";
        String strContainingObj = "";

        int ArrayIndex = 0;
        
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
                
                listIndex += stringifiedContainer.getSeperatedJSON().size() + 2; // +2 For removed braces

                JSONHashMap.put(Integer.toString(ArrayIndex), new SeerJSON( Integer.toString(ArrayIndex++), "{" + strContainingObj + "}", stringifiedContainer, ESeerType.OBJECT ) );
            
            }
            //This is if it is an array of arrays. Must skip past the first index because it will always be [
            else if ( stringInformation.equals("[") && listIndex != 0) {

                strContainingObj = getContainerString(new ArrayList<String>(JSONStringArray.subList(listIndex, JSONStringArray.size())));
                SeerStringifiedJSON stringifiedContainer = new SeerStringifiedJSON( strContainingObj );
                
                listIndex += stringifiedContainer.getSeperatedJSON().size() + 2; // +2 For removed braces

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

