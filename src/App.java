import SeerJSON.SeerDataType.SeerJSON;
import SeerJSON.SeerDataType.SeerStringifiedJSON;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Scanner;

public class App {
    
    public static void main(String[] args){

		String JSONFileAsString = "";
		Path fileName = Path.of("src/ExampleJSON.JSON");
		Scanner systemInScanner = new Scanner(System.in);
		
		while (true) {
			try { 
				JSONFileAsString = Files.readString(fileName);

				break;
			}
			catch (IOException Exception) {
				
				System.out.println("Could not find file " + JSONFileAsString);
				System.out.println("Please enter new file path to a JSON file.");

				fileName = Path.of(systemInScanner.nextLine().replace("\n",""));
	
			}
		}
		systemInScanner.close();
        
        

        
        SeerStringifiedJSON JSONString = new SeerStringifiedJSON(JSONFileAsString); //Create a stringified JSON to create the object. !STILL NEED TO ADD CHECKING
        SeerJSON JSONObject = JSONString.getJSON(); //Get the actual json object from the StringifiedJSON

        JSONObject.printObject(); //Printing origional JSON Object
		System.out.println();
		
		System.out.println(JSONObject.$(5).$("friends").$(2).$("id")); //Print out the value when used in a Print
		System.out.println(JSONObject.$(5).$("friends").$(2).$("id").getType()); //(This is going to be an int type in the form of an ESeerType Enumerator)

		System.out.println(JSONObject.$(5).$("about").<String>$()); //Get the actual return value from the object.
		
		String gettingString = JSONObject.$(5).$("about").$(); //Will actual cast if the right type (Will give an error if they are different unfortunately)
		System.out.println(gettingString);

		JSONObject.$(5).$("greeting").printObject();  //This will print out the object in its actual form. Example strings will be printed with ""
		System.out.println(JSONObject.$(5).$("greeting").getType()); //Get the ESeerType for the underlying value. (This is going to be a string as shown above.)
		
    }
}