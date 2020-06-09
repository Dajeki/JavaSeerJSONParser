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
				System.out.println("Please enter new file path to a .JSON file.");

				fileName = Path.of(systemInScanner.nextLine().replace("\n",""));
	
			}
		}
		systemInScanner.close();
        
		SeerStringifiedJSON JSONString = new SeerStringifiedJSON(JSONFileAsString); //Create a stringified JSON to create the object. !STILL NEED TO ADD CHECKING
		SeerJSON JSONObject = JSONString.getJSON(); //Get the actual json object from the StringifiedJSON

		//Main Array Object
		System.out.println(JSONObject.getType());
		System.out.println(JSONObject);
		System.out.println("\n");
		//Prints the object formatted
		JSONObject.printObject();
		System.out.println("\n");

		//Objects inside main array JSON object
		SeerJSON objectInArray = JSONObject.$(0).$(0);
		System.out.println(objectInArray.$("value1").getType());
		System.out.println(objectInArray.$("value1"));
		System.out.println(objectInArray.$("value2"));
		System.out.println("\n");

		objectInArray = JSONObject.$(0).$(1);
		System.out.println(objectInArray.$("value1").getType());
		System.out.println(objectInArray.$("value1"));
		System.out.println(objectInArray.$("value2"));
		System.out.println("\n");

		objectInArray = JSONObject.$(1).$(0);
		System.out.println(objectInArray.$("value1").getType());
		System.out.println(objectInArray.$("value1"));
		System.out.println(objectInArray.$("value2"));
		System.out.println("\n");

		objectInArray = JSONObject.$(1).$(1);
		System.out.println(objectInArray.$("value1").getType());
		System.out.println(objectInArray.$("value1"));
		System.out.println(objectInArray.$("value2"));
		System.out.println("\n");


    }
}