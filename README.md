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
* None yet
* etc...

To-do list: [`for future iterations`]
* Easy to use and straightforward user API.  
* No need for SQL, HQL, or any databse specific language.  
* Straightforward and simple Annotation based for ease of use. 
* Mapping of join columns inside of entities.    
* Implement of aggregate functions.  
* Allow ORM to build table based on Annotations in Entities.  
* etc...

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
 (typically located src/main/resources/)
 ``` 
  url=path/to/database
  admin-usr=username/of/database
  admin-pw=password/of/database  
  ```
  
## Usage  
  ### Annotating classes  
  All classes which represent objects in database must be annotated.\
  TODO: Fill out these annotations with our annotations EX:
   - #### @Table(name = "table_name)  
      - Indicates that this class is associated with table 'table_name'  

  ### User API  
  TODO: Fill out this API with our functions EX:
  - #### `public static Something getInstance()`  
     - returns the singleton instance of the class. It is the starting point to calling any of the below methods.  \
 



## License

This project uses the following license: [GNU Public License 3.0](https://www.gnu.org/licenses/gpl-3.0.en.html).
