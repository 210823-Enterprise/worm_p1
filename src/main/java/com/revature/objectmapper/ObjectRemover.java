package com.revature.objectmapper;


import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import com.revature.util.IdField;
import com.revature.util.MetaModel;

public class ObjectRemover extends ObjectMapper{
	
	public boolean removeObjectFromDb(Object obj, Connection conn) {
		
		MetaModel<?> model = MetaModel.of(obj.getClass()); // use this to creaet an instance of the object
		
		IdField Pk = model.getIdField();
		String sql  = "DELETE FROM erickj." + model.getTableName() + " WHERE EXISTS (SELECT * FROM erickj."+model.getTableName()+" WHERE "+Pk.getName()+" = "+Pk.getValue()+")";
		// create some type of method that returns the table name in MetaModel;
		Statement pstmt;
		try {
			pstmt = conn.createStatement();
			pstmt.execute(sql);
			return true;
		} catch (SQLException e) {
			// add an exception here
			e.printStackTrace();
		}
		// we want to grab meta data from this statement
		
		
		// instead of Method, maybe pass in a hashmap containing info about the object that you
		
		
		//ObjectCache class...
		
		
		
		
		// then call acustom setStatement method
		
		
		
		return false;
		
	}
	
	
	

}