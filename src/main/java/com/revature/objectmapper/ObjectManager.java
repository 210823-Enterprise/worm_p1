package com.revature.objectmapper;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.revature.connection.ConnectionFactory;
import com.revature.util.IdField;
import com.revature.util.MetaModel;

public class ObjectManager {
	static final ObjectManager OBM = new ObjectManager();
	
	static ObjectSaver ObjS = new ObjectSaver();
	static ObjectReader ObjR = new ObjectReader();
	
	static ConnectionFactory Cf = ConnectionFactory.getInstance();
	static Connection conn = Cf.getConnection();
	
	
	public ObjectManager() {
		super();
		
	}

	
	public Object GetObject(Object obj , Connection conn)
	{
		
		return ObjectCache.getObjectFromCache(obj , conn);
		
	}
	
	
}
