<img src="https://drive.google.com/uc?export=view&id=1K2KyNA28sUfDo2EpO8uus-yyPtFzPNfs" title="Click for the larger version." alt="Program Output"/>

```java
SeerStringifiedJSON JSONString =  new  SeerStringifiedJSON(string);  //Create a stringified JSON to create the object. !STILL NEED TO ADD CHECKING
SeerJSON JSONObject = JSONString.getJSON();  //Get the actual json object from the StringifiedJSON

//NAVIGATING JSON AND GETTING TYPE
JSONObject.printObject();  //Printing original JSON Object
System.out.println();
System.out.println(JSONObject.$(5).$("friends").$(2).$("id"));  //Print out the value when used in a Print
System.out.println(JSONObject.$(5).$("friends").$(2).$("id").getType());  //(This is going to be an int type in the form of an ESeerType Enumerator)

//RETURNING VALUES
System.out.println(JSONObject.$(5).$("about").<String>$());  //Get the actual return value from the object. Requires type casting without variable being saved into.
String gettingString = JSONObject.$(5).$("about").$();  //Will actual cast if the right type (Will give an error if they are different unfortunately)
System.out.println(gettingString);
  
//PROGRAMMING VALUES  String Arrays and Objects all print with appropriate characters before and after them.
JSONObject.$(5).$("greeting").printObject();  //This will print out the object in its actual form. Example strings will be printed with ""

//Just to show runtime values
System.out.println(JSONObject.$(5).$("greeting").getType());  //Get the ESeerType for the underlying value. (This is going to be a string as shown above.)
```

## JSON Object
<details><summary>Large JSON Beware</summary>
<p>

```js
[
  {
    "_id": "5edf15b8b581f7481701f135",
    "index": 0,
    "guid": "dc9f66c4-9903-4a01-8ee0-0f08a80ce564",
    "isActive": true,
    "balance": "$2,176.82",
    "picture": "http://placehold.it/32x32",
    "age": 39,
    "eyeColor": "brown",
    "name": "Robin Rosario",
    "gender": "female",
    "company": "ZENTIX",
    "email": "robinrosario@zentix.com",
    "phone": "+1 (939) 402-2417",
    "address": "710 Beayer Place, Richville, Florida, 4159",
    "about": "Voluptate est qui elit laborum. Officia sit pariatur consectetur commodo cillum consequat consequat. Ipsum sit aliqua eiusmod qui.\r\n",
    "registered": "2014-01-20T06:01:24 +05:00",
    "latitude": 71.942021,
    "longitude": 104.800266,
    "tags": [
      "elit",
      "minim",
      "cillum",
      "nostrud",
      "non",
      "consectetur",
      "pariatur"
    ],
    "friends": [
      {
        "id": 0,
        "name": "Araceli Gonzales"
      },
      {
        "id": 1,
        "name": "Lora Hardy"
      },
      {
        "id": 2,
        "name": "Sheena Odonnell"
      }
    ],
    "greeting": "Hello, Robin Rosario! You have 10 unread messages.",
    "favoriteFruit": "apple"
  },
  {
    "_id": "5edf15b83f007afcd2cfb900",
    "index": 1,
    "guid": "0a65c738-3445-40db-8a48-7c646ff9c5e8",
    "isActive": true,
    "balance": "$2,596.26",
    "picture": "http://placehold.it/32x32",
    "age": 32,
    "eyeColor": "brown",
    "name": "James Mcpherson",
    "gender": "female",
    "company": "QUIZMO",
    "email": "jamesmcpherson@quizmo.com",
    "phone": "+1 (949) 562-3044",
    "address": "507 Fleet Place, Barronett, Mississippi, 8421",
    "about": "Consequat culpa deserunt aliqua id. Quis reprehenderit et culpa et ex excepteur id sunt ut excepteur aliqua minim nulla elit. Et incididunt exercitation est pariatur ad tempor non laborum deserunt nulla ullamco. Reprehenderit consequat eiusmod aliquip consectetur. Nulla aliqua duis in consectetur nostrud consequat culpa fugiat aute proident.\r\n",
    "registered": "2018-07-08T12:14:54 +04:00",
    "latitude": 2.08105,
    "longitude": -69.56807,
    "tags": [
      "irure",
      "sunt",
      "aute",
      "amet",
      "adipisicing",
      "non",
      "aliquip"
    ],
    "friends": [
      {
        "id": 0,
        "name": "Jackie Berg"
      },
      {
        "id": 1,
        "name": "Adrian Adkins"
      },
      {
        "id": 2,
        "name": "Curtis Meyer"
      }
    ],
    "greeting": "Hello, James Mcpherson! You have 7 unread messages.",
    "favoriteFruit": "banana"
  },
  {
    "_id": "5edf15b8ad9cb1d56433cfe5",
    "index": 2,
    "guid": "0937fb1d-da2e-4246-a783-26645583968e",
    "isActive": true,
    "balance": "$1,616.58",
    "picture": "http://placehold.it/32x32",
    "age": 32,
    "eyeColor": "blue",
    "name": "Evelyn Crane",
    "gender": "female",
    "company": "TRIPSCH",
    "email": "evelyncrane@tripsch.com",
    "phone": "+1 (888) 558-2658",
    "address": "350 Montauk Court, Roeville, Minnesota, 866",
    "about": "Commodo fugiat do voluptate eu. Excepteur sit dolor fugiat laboris dolor fugiat amet pariatur proident non. Irure proident aliqua ex commodo ipsum id non consectetur cillum qui non non.\r\n",
    "registered": "2018-11-02T10:50:22 +04:00",
    "latitude": -63.777345,
    "longitude": -0.680827,
    "tags": [
      "deserunt",
      "amet",
      "qui",
      "adipisicing",
      "nisi",
      "proident",
      "et"
    ],
    "friends": [
      {
        "id": 0,
        "name": "Jensen Wood"
      },
      {
        "id": 1,
        "name": "Emilia Hall"
      },
      {
        "id": 2,
        "name": "Johnston Mercer"
      }
    ],
    "greeting": "Hello, Evelyn Crane! You have 3 unread messages.",
    "favoriteFruit": "apple"
  },
  {
    "_id": "5edf15b89456f8cb58c2ec31",
    "index": 3,
    "guid": "89a3ac38-a058-4a96-a823-bc60445b7aeb",
    "isActive": false,
    "balance": "$3,953.29",
    "picture": "http://placehold.it/32x32",
    "age": 37,
    "eyeColor": "brown",
    "name": "Mckenzie Fernandez",
    "gender": "male",
    "company": "ACIUM",
    "email": "mckenziefernandez@acium.com",
    "phone": "+1 (931) 447-3257",
    "address": "537 Williams Place, Cowiche, Texas, 7157",
    "about": "Velit culpa dolore officia sint minim laborum sint esse. Elit et eiusmod do est exercitation anim consequat velit adipisicing laborum pariatur excepteur aliqua. Ad labore in pariatur aliquip velit enim laboris quis sit. Est reprehenderit tempor deserunt amet adipisicing Lorem ea non.\r\n",
    "registered": "2018-06-07T04:18:45 +04:00",
    "latitude": 46.806302,
    "longitude": 17.827977,
    "tags": [
      "sunt",
      "ex",
      "commodo",
      "est",
      "velit",
      "qui",
      "ut"
    ],
    "friends": [
      {
        "id": 0,
        "name": "Tracey Barker"
      },
      {
        "id": 1,
        "name": "Addie Burke"
      },
      {
        "id": 2,
        "name": "Ware Orr"
      }
    ],
    "greeting": "Hello, Mckenzie Fernandez! You have 3 unread messages.",
    "favoriteFruit": "strawberry"
  },
  {
    "_id": "5edf15b834e10579667d7c16",
    "index": 4,
    "guid": "8bda3c89-1f97-4fbc-90ff-a0d12334c170",
    "isActive": false,
    "balance": "$2,367.87",
    "picture": "http://placehold.it/32x32",
    "age": 31,
    "eyeColor": "blue",
    "name": "Ladonna David",
    "gender": "female",
    "company": "ENTHAZE",
    "email": "ladonnadavid@enthaze.com",
    "phone": "+1 (996) 579-3891",
    "address": "158 Meserole Street, Edgar, Ohio, 2187",
    "about": "In proident consequat et magna incididunt aute mollit adipisicing est est dolore. Magna qui enim amet nulla enim ipsum anim minim commodo aliqua laboris cupidatat. Veniam ipsum deserunt deserunt non dolor pariatur laborum est deserunt voluptate sint laboris reprehenderit proident. Aliquip commodo enim ullamco ipsum Lorem amet culpa consequat sunt. Amet tempor enim consequat consectetur ullamco id fugiat.\r\n",
    "registered": "2016-02-07T09:34:06 +05:00",
    "latitude": -3.700648,
    "longitude": -172.81412,
    "tags": [
      "incididunt",
      "tempor",
      "est",
      "Lorem",
      "eiusmod",
      "cillum",
      "eiusmod"
    ],
    "friends": [
      {
        "id": 0,
        "name": "Shepherd Ray"
      },
      {
        "id": 1,
        "name": "Mcclain Osborne"
      },
      {
        "id": 2,
        "name": "Holmes Mitchell"
      }
    ],
    "greeting": "Hello, Ladonna David! You have 9 unread messages.",
    "favoriteFruit": "banana"
  },
  {
    "_id": "5edf15b822199749f44e9ff6",
    "index": 5,
    "guid": "933a5685-08a6-4ab9-b548-10dca69b61d8",
    "isActive": true,
    "balance": "$1,768.26",
    "picture": "http://placehold.it/32x32",
    "age": 28,
    "eyeColor": "brown",
    "name": "Louisa Richard",
    "gender": "female",
    "company": "SPLINX",
    "email": "louisarichard@splinx.com",
    "phone": "+1 (877) 524-2046",
    "address": "781 Bainbridge Street, Lindisfarne, Maryland, 1002",
    "about": "Enim exercitation est duis eu qui adipisicing ipsum. Aliquip sit pariatur aliquip esse voluptate nostrud. Proident consequat exercitation anim aliquip veniam esse tempor sint veniam incididunt irure officia. Exercitation dolor veniam cillum adipisicing. Fugiat esse nisi officia proident velit dolore anim labore ut ut ea mollit in.\r\n",
    "registered": "2014-05-17T07:57:00 +04:00",
    "latitude": -87.102861,
    "longitude": 7.065177,
    "tags": [
      "aliquip",
      "exercitation",
      "sint",
      "incididunt",
      "nisi",
      "minim",
      "dolor"
    ],
    "friends": [
      {
        "id": 0,
        "name": "Moreno Dalton"
      },
      {
        "id": 1,
        "name": "Valarie Payne"
      },
      {
        "id": 2,
        "name": "Nikki Cherry"
      }
    ],
    "greeting": "Hello, Louisa Richard! You have 6 unread messages.",
    "favoriteFruit": "banana"
  },
  {
    "_id": "5edf15b8c5135ad1562600a8",
    "index": 6,
    "guid": "09e6a9a1-7b9e-48dd-b25c-100be8594874",
    "isActive": true,
    "balance": "$3,764.97",
    "picture": "http://placehold.it/32x32",
    "age": 32,
    "eyeColor": "blue",
    "name": "Raymond Walton",
    "gender": "male",
    "company": "EBIDCO",
    "email": "raymondwalton@ebidco.com",
    "phone": "+1 (878) 413-3957",
    "address": "587 Rogers Avenue, Cassel, Maine, 3335",
    "about": "Cupidatat esse excepteur ea fugiat cupidatat do Lorem in deserunt proident ad sint ullamco incididunt. Aliquip anim eiusmod non dolore enim Lorem Lorem aliquip amet pariatur est. Cillum dolor deserunt nostrud ex dolore anim. Qui elit duis duis esse adipisicing in voluptate aliqua esse officia mollit.\r\n",
    "registered": "2014-02-06T11:32:43 +05:00",
    "latitude": -65.113274,
    "longitude": 54.321192,
    "tags": [
      "mollit",
      "aliquip",
      "enim",
      "nostrud",
      "excepteur",
      "et",
      "consectetur"
    ],
    "friends": [
      {
        "id": 0,
        "name": "Love Santiago"
      },
      {
        "id": 1,
        "name": "Nellie Petersen"
      },
      {
        "id": 2,
        "name": "Hillary Schwartz"
      }
    ],
    "greeting": "Hello, Raymond Walton! You have 1 unread messages.",
    "favoriteFruit": "banana"
  }
]
```
</p>
</details>
