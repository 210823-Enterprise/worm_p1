package com.revature;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.revature.util.ColumnField;
import com.revature.util.Configuration;
import com.revature.util.MetaModel;

public class TestWormDriver {

	public static void main(String[] args) {
		/**
		 * Driver for testing different functionality
		 * 
		 */
		//Initialize Worm
		System.out.println("Welcome to the WORM Demo, initializing the Worm..");
		initializeWorm();
		
		//////////////////////////////////////////////////////////
		//testing retrieving classes from application.properties//
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
			System.out.println(clazz); //prints out every class with path that is an object
		}
		//////////////////////////////////////////////////////////
		
		//////////////////////////////////////////////////////////
		//testing adding multiple annotated classes to a model list
		Configuration cfg = new Configuration();
		// IN our configuration object we want to add annotated class, without ever having to instantiate them
		
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
		
		//print out the classes with details here as a test	
		for (MetaModel<?> metamodel : cfg.getMetaModels()) {
			System.out.printf("Printing metamodel for class: %s\n ", metamodel.getClassName()); // %s is a place holder
			
			List<ColumnField> columnFields = metamodel.getColumns();
			
			for (ColumnField cf : columnFields) {
				
				System.out.printf("Found a column field named %s of type %s, which maps to the DB column %s\n", cf.getName(), cf.getType(), cf.getColumnName());		
				System.out.println();
			}
		
		}
		
	}
	
	private static void initializeWorm() {
		
		//Method to startup, put in another class...
		
	}

}
