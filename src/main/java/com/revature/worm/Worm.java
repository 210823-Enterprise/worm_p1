package com.revature.worm;

import java.sql.Connection;

public class Worm {

	//instantiate Worm class as a static object
	final private static Worm worm = new Worm();
	
	/**
	 * private final Connection conn;
	 * private final ObjectDestroyer object_destroyer;
	 */
	
	private Worm() {
		//instantiates Connection Factory..
		/**
		 * conn = ConnectionFactory.getInstance().getConnection();
		 * object_destroyer = ObjectDestroyer.getInstance();
		 */
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
