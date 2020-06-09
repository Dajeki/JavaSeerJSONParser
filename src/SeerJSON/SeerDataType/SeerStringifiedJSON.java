package SeerJSON.SeerDataType;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeerStringifiedJSON {

    final private String regexStr = "[:,\\{\\}\\[\\]]|(\\\".*?\\\")|('.*?')|[-\\w.]+";
    
    final private Pattern regexPattern = Pattern.compile(regexStr);
    private Matcher regexMatcher;

    private ArrayList<String> regexArray;

    private void turnIntoArray(){
        while(regexMatcher.find()){
            regexArray.add(regexMatcher.group());
        }
    }

    public SeerStringifiedJSON(String JSONString){
        regexMatcher = regexPattern.matcher(JSONString);
        regexArray = new ArrayList<String>();
        
        turnIntoArray();
    }

    public boolean isProperty(String propertyName){
        int bracketCount = 0;
        String currentInfo;

        for( int regexIter = 0 ; regexIter < regexArray.size(); regexIter++ ){
            
            currentInfo = regexArray.get(regexIter);

            if ( currentInfo.equals("{") ){
                bracketCount++;
                continue;
            }
            else if ( currentInfo.equals("}") ){
                bracketCount--;
                continue;
            }
            else if ( currentInfo.equals("\"" + propertyName + "\"") && bracketCount == 0 ){
                return true;
            }
        }
        return false;
    }


    /* PRINTING OF A JSON STRINGIFIED*/
    private void printTabs(int numTimes){
        for(int tabnum = 0; tabnum < numTimes; ++tabnum){
            System.out.print("  ");
        }
    }

    public void printObject(){
        int bracketCount = 0;
        int squareBraketCount = 0;
        String stringInformation = "";

        for( int regexIter = 0 ; regexIter < regexArray.size(); regexIter++ ){
            stringInformation = regexArray.get(regexIter);

            /*
                Checks to make sure not first element for index out of bounds then can continue to check that string is '{' and that the value before is '['
                We do this so that brackets after '[' will be on a new line and will have to have tabs before the output.
              */
            if (regexIter - 1 >= 0 && (stringInformation.equals("{")|stringInformation.equals("[")) && regexArray.get(regexIter - 1).equals("[") ){
                
                printTabs(bracketCount);
                System.out.println(stringInformation);

                bracketCount++;
            }
            /*
                Checks to make sure not first element for index out of bounds then can continue to check that string is '{' and that the value before is ','
                Normal '{' will be put on a new line however it will not include the tabls because the ',' value is the one creating the newline. 
                This makes sure the tabs are in the right spot.
              */
            else if (regexIter - 1 >= 0 && (stringInformation.equals("{")|stringInformation.equals("[")) && regexArray.get(regexIter - 1).equals(",") ){
                
                printTabs(bracketCount);
                System.out.println(stringInformation);

                bracketCount++;
            }
            else if ( stringInformation.equals("{") ){
                bracketCount++;

                System.out.println(stringInformation);
            }
            else if ( stringInformation.equals("}") ){
                bracketCount--;

                System.out.println();
                printTabs(bracketCount);
                System.out.print(stringInformation);
            }
            else if ( stringInformation.equals("[") ){
                bracketCount++;
                squareBraketCount++;
                System.out.println(stringInformation);
            }
            else if ( stringInformation.equals("]") ){
                bracketCount--;
                squareBraketCount--;
                System.out.println();
                printTabs(bracketCount);
                System.out.print(stringInformation);
            }
            else if ( stringInformation.equals(":")) {
                System.out.print(stringInformation + " ");
            }
            /*
                checks to make sure the iterator + 1 doesnt go out of bounds then you can check that the next element is ':'
                This is so we can make sure properties get the tabs before them. Properties have the next return of ':' while normal values to just print
                will not want those tabs if the value we are printing is just a value.
              */  
            else if ( regexIter + 1 < regexArray.size() && regexArray.get(regexIter + 1).equals(":") ) {
                printTabs(bracketCount);
                System.out.print(stringInformation );
            }
            else if ( stringInformation.equals(",")) {
                System.out.println(stringInformation);
            }
            else if (regexIter - 1 >= 1  && (squareBraketCount > 0 && regexArray.get(regexIter - 1).equals(":"))){
                
                System.out.print(stringInformation);
            }
            else if (regexIter - 1 >= 1  && (regexArray.get(regexIter - 1).equals(",") || regexArray.get(regexIter - 1).equals("[")) ) {
                printTabs(bracketCount);
                System.out.print(stringInformation);
            }  
            else {
                System.out.print(stringInformation);
            }
        } 
        System.out.println();
    }

    public ArrayList<String> getSeperatedJSON(){
        return regexArray;
    }

    public SeerJSON getJSON(){

        String backToRegex = "";
        for(String strings: regexArray){
            backToRegex += strings;
        }
        if( backToRegex.charAt(0) == '{'){
            return new SeerJSON("Base", backToRegex , this, ESeerType.OBJECT);
        }
        else {
            return new SeerJSON("Base", backToRegex , this, ESeerType.ARRAY);
        }
        
    }
}
