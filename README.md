
<p align="center"> 
  <img height = "200" src="https://i.imgur.com/0I4tcRT.png" alt="SeerJSON Logo">
  <h1 align="center">SeerJSON</h1>
</p>

Simple JSON parser for Java with intuitive value retrieval.

[![MIT Licensing](https://img.shields.io/github/license/Dajeki/JavaSeerJSONParser)](https://img.shields.io/github/license/Dajeki/JavaSeerJSONParser)
[![IssueTracker](https://img.shields.io/github/issues/Dajeki/JavaSeerJSONParser "IssueTracker")](https://img.shields.io/github/issues/Dajeki/JavaSeerJSONParser "IssueTracker")
[![Version](https://img.shields.io/github/v/tag/Dajeki/JavaSeerJSONParser?label=Version "Version")](https://img.shields.io/github/v/tag/Dajeki/JavaSeerJSONParser?label=Version "Version")

The language design of JAVA is not able to handle JSON natively from the lack of dynamically created objects. This simple library allows you to access values from JSON strings intuitively and in as close as possible to represent dot `.property` notation by instead using `.$("property")`.


## Installation

You may also download and use the Jar by clicking [[here!]][JARFile]<br/>
The jar file is packaged only with the `SeerJSON` package name. So use `import SeerJSON.*` with the .jar added to your classpath.<br/>

**Git Command Line**

This will download the current version as source. The classes are available in `SeerJSON.SeerDataType` package
and comes with an example of retrieving data from a JSON file.

```sh
git clone https://github.com/Dajeki/JavaSeerJSONParser new_directory_name
```
**note - the new directory name is optional. If left out will place it in current directory.* <br/>


<br/>


## Usage Examples
  

#### JSON for Examples

```json
[
  [
    {
      "value1" : null,
      "value2" : "Hello from Array @ index 0, Object @ index 0"
    },
    {
      "value1" : 2,
      "value2" : "Hello from Array @ index 0, Object @ index 1"
    }
  ],
  [
    {
      "value1" : 3.453,
      "value2" : "Hello from Array @ index 1, Object @ index 0"
    },
    {
      "value1" : true,
      "value2" : "Hello from Array @ index 1, Object @ index 1"
    }
  ]
]
``` 
<br/>
  
#### Creating Object

```java
SeerStringifiedJSON JSONString = new SeerStringifiedJSON( JSONFileAsString ); 
SeerJSON JSONObject = JSONString.getJSON();
```
`SeerStringifiedJSON` is how JSON Objects are created. You must use the constructor with a string version of valid JSON. Here you have basic functionality like pretty printing back the JSON like in the screenshot example above.

`SeerJSON` is the type of object that is used to access the object using Seer JS object notation `.$("propertyName");`<br/><br/><br/>


  
#### Retrieving Information From SeerJSON
##### SeerJSON Object

```java
SeerJSON objectInArray = JSONObject.$(0).$(0);  //Selects Array at 0 index then the Object at index 0 in that Array
```
The `.$("property")` call will return the underlying SeerJSON representation of the value back and have access to the methods that class provides. </br>
Arrays - Can use either the Int (`0`) or the String representation of a number (`"0"`)</br>
Objects - Call the properties in String format (`"property"`)

##### Underlying Value as Type

```java
int objectInArray = JSONObject.$(0).$(1).$("value1").$();  //Selects the value 2 from the SeerJSON object
```
To return the value as the underlying type, you only need set it to a variable of an appropriate type without an argument `.$()`.
Java object type primitives like `Integer`,`Double`,`Boolean` and primitive types like `int`, `double`, and `boolean` are accepted as valid value stores but must abide to the type of the underlying value. 
For example, a string can not be saved for an underlying double type. <br/><br/><br/>


  
#### Runtime Information of Object
##### Runtime Type of the Underlying Value

```java
JSONObject.getType();
```
`JSONObject.getType()` is a method of `SeerJSON` objects. This method returns the `ESeerType` enumerable corresponding to the valid JSON types with the **exception of FLOAT which is always a DOUBLE type instead.** ~~PUT LINK TO ENUM CLASS HERE~~
<p align="center"> 
	<img src="https://i.imgur.com/GnmEnxK.png" alt="Type of underlying value">
</p>

##### String Representation of the JSON

```java
JSONObject.toString();
```
`toString()` has been overriden to return the string representation of the object to be used to create more objects or send as data.
The SeerJSON object above produces the below output.
<p align="center">
		<img src="https://i.imgur.com/pdghdTq.png" alt="toString"/>
</p> <br/>


  
#### Pretty Print Formatting

```java
JSONObject.printObject();
```
When invoked by any SeerJSON object, will print out the object in formatted JSON form to allow for easy observation of the underlying object. When run on something like a String, it will include the `" "` in the output to differenciate numbers from Strings that are numbers. 
<p align="center"> 
		<img style="width:40%" src="https://i.imgur.com/eIrLThY.png" alt="Prettified JSON Output">
</p>


  
#### Sample Usage and Output
This program is retreiving values from the above JSON Object as a single string.

```java
SeerStringifiedJSON JSONString = new SeerStringifiedJSON(JSONFileAsString); 
SeerJSON JSONObject = JSONString.getJSON();

//Objects inside main array JSON object
SeerJSON objectInArray = JSONObject.$(0).$(0);
System.out.println(objectInArray.$("value1").getType());
System.out.println(objectInArray.$("value1"));
System.out.println(objectInArray.$("value2"));
System.out.println("-------------------------------------");
objectInArray = JSONObject.$(0).$(1);
System.out.println(objectInArray.$("value1").getType());
System.out.println(objectInArray.$("value1"));
System.out.println(objectInArray.$("value2"));
System.out.println("-------------------------------------");
objectInArray = JSONObject.$(1).$(0);
System.out.println(objectInArray.$("value1").getType());
System.out.println(objectInArray.$("value1"));
System.out.println(objectInArray.$("value2"));
System.out.println("-------------------------------------");
objectInArray = JSONObject.$(1).$(1);
System.out.println(objectInArray.$("value1").getType());
System.out.println(objectInArray.$("value1"));
System.out.println(objectInArray.$("value2"));
System.out.println("-------------------------------------");
```
<p align="center"> 
    <img style="width:40%" src="https://i.imgur.com/ua9jhTv.png" alt="Prettified JSON Output">
 </p>


## Release History

* 0.1.0
    * The first proper release
    * Includes all retrieving functionality for JSON. Does not include proper SeerJSON construction by altering properties.


## Contributing

Comming Soon!


[JARFile]: https://github.com/Dajeki/JavaSeerJSONParser/releases/download/0.1.0/SeerJSON-0.1.0.jar "JarFile"
