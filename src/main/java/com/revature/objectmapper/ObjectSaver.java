package com.revature.objectmapper;


import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.util.IdField;
import com.revature.util.MetaModel;

public class ObjectSaver extends ObjectMapper{
	
	public boolean addObjectToDb(Object obj, Connection conn) {
		
		MetaModel<?> model = MetaModel.of(obj.getClass()); // use this to creaet an instance of the object
		
		IdField Pk = model.getIdField();
		String sql 		  = "DELETE from " + model.getSimpleClassName() + " where " + Pk.getName() + "= "+Pk.getPrimaryKey(); // create some type of method that returns the table name in MetaModel;
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ParameterMetaData pd = pstmt.getParameterMetaData();
			setStatement(pstmt, pd, null, obj, 1);
			
			
			pstmt.executeUpdate();
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