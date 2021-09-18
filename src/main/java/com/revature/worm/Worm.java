package com.revature.worm;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import com.revature.connection.ConnectionFactory;
import com.revature.util.ColumnField;
import com.revature.util.Configuration;
import com.revature.util.MetaModel;

public class Worm {

	// instantiate Worm class as a static object
	final private static Worm worm = new Worm();
	private final Connection conn;
	// private final ConnectionFactory connFactory = new ConnectionFactory();
	// private final ObjectDestroyer object_destroyer;

	private Configuration cfg = new Configuration();

	private Worm() {
		// instantiates Connection Factory..
		conn = ConnectionFactory.getInstance().getConnection();
		// object_destroyer = ObjectDestroyer.getInstance();

		/**
		 * Capture the objects to be put into the database.....
		 */
		System.out.println("Capturing objects");
		captureObjects();

		// Call the DatabaseGenerator to instantiate objects into the Database

	}

	public static Worm getInstance() {
		return worm;
	}

	private void captureObjects() {
		Properties props = new Properties();
		try {
			props.load(new FileReader("src/main/resources/application.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String[] tokens=s.split(",");
		String[] classes = (props.getProperty("classes")).split(",");
		System.out.println("Number of classes: " + classes.length);
		for (String clazz : classes) {
			// cfg.addAnnotatedClass(clazz.getClass());
			try {
				// System.out.println(Class.forName(clazz)); // prints out the class
				cfg.addAnnotatedClass(Class.forName(clazz));

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		
		//outputObjectsFromAppProp();

		// TODO: Check if the table already exists, if not create it.
		// 		 OTHERWISE, leave it intact.

		// grab all of the data here -->>>>
		for (MetaModel<?> metamodel : cfg.getMetaModels()) {

			String sql ="DROP TABLE IF EXISTS wormdemo."
					+ metamodel.getTableName().tableName() + ";"
				 //	+ "CASCADE;"
					+ "CREATE TABLE wormdemo."
					// add table name
					+ metamodel.getTableName().tableName() + "(" + metamodel.getPrimaryKey().getPrimaryKey()
					+ " SERIAL PRIMARY KEY,\n";

			// add each of the columns
			List<ColumnField> columnFields = metamodel.getColumns();
			
			int counter = 0;
			int length = columnFields.size();
			System.out.println("number of columns: " + length);
			for (ColumnField cf : columnFields) {
				counter++;
				// name
				sql += cf.getColumnName() + " ";
				// type
				if (cf.getType().getCanonicalName().equals("java.lang.String")) {
					sql += "VARCHAR(50)";
					if (counter < length)
						sql += ",\n";
				}
			}
			//close the sql query
			sql += ");";
			
			try {
				//prepare the statement
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				System.out.println("Executing the query..");
				stmt.executeUpdate();
				conn.commit();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(sql);
			
			
		}
	}

	// In constructor, deploy objects into the database?

	// API functions that can be called by the user to do the CRUD here...
	/**
	 * delete object, add object, etc
	 */
	public boolean destroyObject(Object obj) {

		// return object_destroyer.destroyObject(obj, conn)
		return false;
	}

	//WARNING: This function doubles all objects... probably has to do with the way MetaModel works
	public void outputObjectsFromAppProp() {
		// see if the objects were captured
		System.out.println("Classes stripped from the applications.properties: ");
		System.out.println();

		for (MetaModel<?> metamodel : cfg.getMetaModels()) {
			System.out.printf("Printing metamodel for class: %s\n ", metamodel.getClassName()); // %s is a place holder

			List<ColumnField> columnFields = metamodel.getColumns();
			String className = metamodel.getSimpleClassName();
			String table = metamodel.getTableName().tableName();
			String primaryKey = metamodel.getPrimaryKey().getPrimaryKey();
			System.out.println("Table name: " + table);
			System.out.println("Primary: " + primaryKey);
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
}
