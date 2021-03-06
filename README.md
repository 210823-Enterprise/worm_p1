# WORM



## Project Description
Project 1 - WORM (Worming Object-Relational Mapping) - Custom Object Relational Mapping Format\
A java based ORM providing a simple way to connect to a database, eliminating the need for SQL and Connection management.

Creators: Erick Johnson, Matthew Erikson (Group 5)

## Technologies Used

* PostgreSQL - version 42.2.18  
* Java - version 8.0  
* Apache commons - version 2.1.1
* JUnit - version 4.13.1
* log4j - version 1.2.17

## Features

List of features ready and TODOs for future development  
* Easy to use and straightforward user API.
* No need for any SQL by the user 
* Straightforward and simple Annotation based for ease of use. 
* Allow ORM to build table based on Annotations in Entities. 
* Caching system

To-do list: [`for future iterations`]
  
* Add additional database support aside SQL  
* Improve database interaction speed

## Getting Started  
Currently project must be included as local dependency. to do so:
```shell
  git clone https://github.com/210517-Enterprise/worm_p1.git
  cd worm_p1
  mvn install
```
Next, place the following inside your project pom.xml file:
```XML
  <dependency>
    <groupId>com.revature</groupId>
    <artifactId>worm_p1</artifactId>
    <version>0.0.1-SNAPSHOT</version>
  </dependency>

```

Finally, inside your project structure you need a application.proprties file. 
 (typically located in src/main/resources/)
 ``` 
  url=path/to/database
  username=username/of/database
  password=password/of/database  
  ```
  
## Usage  
  ### Annotating classes  
  All classes which represent objects in database must be annotated.\
  Each class must also contain a full argument constructor.
   - #### @Entity(tableName = "table_name")  
      - Indicates that this class is associated with entity 'table_name' \
      `@Entity(tableName="table_name")`\
`public class Goblin`
   - #### @Id(columnName = "Id")  
      - Indicates that this value is the objects id field  
      `	@Id(columnName="Id")`\
`public int id;`
   - #### @Column(columnName = "column_name")  
      - Indicates that this class is associated with table 'table_name'\
  `	@Column(columnName="name")`\
`public String goblinName;`


  ### Connection and Class configuration
  - #### `public Configuration cfg = new Configuration();`
     - Initializing the configuration reads through annotated classes, creating a database that is ready for the ORM to use.
  - #### `public static final ConnectionFactory Cf = ConnectionFactory.getInstance();`
     - Sets up the Connection Factory.
  - #### `public static Connection conn = Cf.getConnection();`
     - Returns a connection to the database specified above to be used to execute User API methods.
  
  ### User API  
  - #### `public boolean addObjectToDb(Object, Connection)` (ObjectSaver objS = new ObjectSaver() -- objS.addObjectToDb(Object, Connection))
     - Used to add an instance of an annotated object to the database.
     - Pass in an object and a connection. Returns true if successful, false if unsuccessful.
     - To update an object, pass in the Object with the ID of the object that needs to be updated, along with updated values.
  - #### `public boolean removeObjectFromDb(Object, Connection)` (ObjectRemover objR = new ObjectRemover() -- objR.removeObjectFromDb(Object, Connection))
     - Used to remove an instance of an annotated object from the database.
     - Pass in an object and a connection. Returns true if successful, false if unsuccessful.
     - If there are no more objects in the database from the specified class, the table will be deleted.
  - #### `public List<Object> getObjectsFromDB(Object obj, Connection conn)`  (ObjectReader objRe = new ObjectReader() -- (*your object class*) objRe.getObjectsFromDB(Object, Connection))
     - Used to retrieve all objects of one annotated object class from the database
     - Returned list must be cast as your object.
  - #### `public static boolean putObjInCache(final Object obj)` (ObjectCache.putObjInCaache(Object))
     - Used to put an object into the cache
     - Returns true if the object was successfully added to the cache
  - #### `public static boolean removeObjFromCache(final Object obj)` (ObjectCache.removeObjFromCache(Object))
     - Used to remove an object from the cache
     - Returns true if the object was successfully removed from the cache
  - #### `public static HashMap<String , MetaModel<?>> getCache()` (ObjectCache.getCache())
     - Used to get the current cache
     - Returns a HashMap of the current cache


## License

This project uses the following license: [GNU Public License 3.0](https://www.gnu.org/licenses/gpl-3.0.en.html).
