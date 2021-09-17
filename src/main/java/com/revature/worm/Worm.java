package com.revature.worm;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import com.revature.connection.ConnectionFactory;
import com.revature.util.ColumnField;
import com.revature.util.Configuration;
import com.revature.util.MetaModel;

public class Worm {

	//instantiate Worm class as a static object
	final private static Worm worm = new Worm();
	private final Connection conn;
	//private final ConnectionFactory connFactory = new ConnectionFactory();
	 //private final ObjectDestroyer object_destroyer;
	
	private Configuration cfg = new Configuration();
	
	private Worm() {
		//instantiates Connection Factory..
		conn = ConnectionFactory.getInstance().getConnection();
		//object_destroyer = ObjectDestroyer.getInstance();
		
		
		/**
		 * Capture the objects to be put into the database.....
		 */
		System.out.println("Capturing objects");
		captureObjects();
		
		
		//Call the DatabaseGenerator to instantiate objects into the Database
		
	}
	


	public static Worm getInstance() {
		return worm;
	}
	
	private void captureObjects() {
		Properties props = new Properties();
		try {
			props.load(new FileReader("src/main/resources/application.properties"));
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String[] tokens=s.split(",");  
		String[] classes = (props.getProperty("classes")).split(",");

		for (String clazz : classes) {
			//cfg.addAnnotatedClass(clazz.getClass());
			try {
				//System.out.println(Class.forName(clazz)); // prints out the class
				cfg.addAnnotatedClass(Class.forName(clazz));
				
			} catch (ClassNotFoundException e) {		
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		// see if the objects were captured
		System.out.println("Classes stripped from the applications.properties: ");
		System.out.println();
		
		for (MetaModel<?> metamodel : cfg.getMetaModels()) {
		System.out.printf("Printing metamodel for class: %s\n ", metamodel.getClassName()); // %s is a place holder
		
		List<ColumnField> columnFields = metamodel.getColumns();
		String className = metamodel.getSimpleClassName();
		String table = metamodel.getTableName().tableName();
		System.out.println("Table name: " + table);
		
		System.out.println("Class: " + className);
		System.out.println("====================");
		
		for (ColumnField cf : columnFields) {
			String columnName = cf.getColumnName();
			String type = cf.getType().toString();
			String name = cf.getName();
			
			System.out.println("Column name: " + columnName);
			System.out.println("Class type: " + type);
			System.out.println("Name: " + name);
			
			
			}
		System.out.println();
	}
		
		
	}
	
	//In constructor, deploy objects into the database?
	
	//API functions that can be called by the user to do the CRUD here...
	/**
	 * delete object,
	 * add object,
	 * etc
	 */
	public boolean destroyObject(Object obj) {
		
		//return object_destroyer.destroyObject(obj, conn)
		return false;
	}
}
