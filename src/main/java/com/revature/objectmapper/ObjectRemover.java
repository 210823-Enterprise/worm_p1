package com.revature.objectmapper;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;

import com.revature.util.IdField;
import com.revature.util.MetaModel;

public class ObjectRemover extends ObjectMapper{
	Properties props = new Properties();
	public boolean removeObjectFromDb(Object obj, Connection conn) {
		
		
		try {
			props.load(new FileReader("src/main/resources/application.properties"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		MetaModel<?> model = MetaModel.of(obj.getClass()); 
		IdField Pk = model.getIdField();
		Statement stmt = null;
		
		
		
		String sql  = "DELETE FROM "+props.getProperty("DBschema")+"."+ model.getTableName() +" WHERE id = '"+Pk.getValue(obj)+"'";
		System.out.println(sql);
		Statement pstmt;
		try {
			pstmt = conn.createStatement();
			pstmt.execute(sql);
			
		} catch (SQLException e) {
			// add an exception here
			e.printStackTrace();
		}
	

		String sql2 = "SELECT * FROM "+props.getProperty("DBschema")+"."+model.getTableName();
	     
		try {
			stmt = conn.createStatement(); 
			stmt.execute(sql2); 
			ResultSet rs = stmt.getResultSet();
			
		if (rs.next())
		  {
			System.out.println("Table not empty");
			//log.info("Table still contains objects.");
			return true;
		  }
		else
		{
			System.out.println("Table empty");
			//log.info("Table is empty Deleting Table");
			deleteTable(conn , model);
			return false;
		}
		} catch (Exception e) {
			//log.info("Something went wrong when checking the Table with Objectremover()");
		}
		return false;
		
		
			
		
	}
	
	public void deleteTable(Connection conn , MetaModel<?> model )
	{
		
		
	  	String sql = "DROP TABLE IF EXISTS "+props.getProperty("DBschema")+"."+model.getTableName();
		System.out.println(sql);
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}