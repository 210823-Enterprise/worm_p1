package com.revature.objectmapper;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;

import com.revature.util.IdField;
import com.revature.util.MetaModel;

public class ObjectRemover extends ObjectMapper{
	
	public boolean removeObjectFromDb(Object obj, Connection conn) {
		
		Properties props = new Properties();
		try {
			props.load(new FileReader("src/main/resources/application.properties"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		MetaModel<?> model = MetaModel.of(obj.getClass()); // use this to creaet an instance of the object
		
		IdField Pk = model.getIdField();
		String sql  = "DELETE FROM "+props.getProperty("DBschema")+"."+ model.getTableName() + " WHERE EXISTS (SELECT * FROM erickj."+model.getTableName()+" WHERE "+Pk.getName()+" = "+Pk.getColumnName()+")";
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