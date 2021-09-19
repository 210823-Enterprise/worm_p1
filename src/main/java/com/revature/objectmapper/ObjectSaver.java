package com.revature.objectmapper;


import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.revature.util.ColumnField;
import com.revature.util.IdField;
import com.revature.util.MetaModel;

public class ObjectSaver extends ObjectMapper{
	
	public boolean addObjectToDb(Object obj, Connection conn) {
		
		MetaModel<?> model = MetaModel.of(obj.getClass()); // use this to creaet an instance of the object
		System.out.println(model.toString());
		IdField Pk = model.getIdField();
		
		System.out.println(Pk.getName()+" , "+Pk.getType()+" , "+Pk.getValue());
		String sql = "CREATE TABLE IF NOT EXISTS erickj."+model.getSimpleClassName()+" ( ";
		List<ColumnField> Cols = model.getColumns();
			   for(int i =0; i < Cols.size(); i++)
			   {
				   System.out.println(Cols.size()+ "   "+ i);
				   if(i < Cols.size() -1)
				   {
				      sql += Cols.get(i).getName()+" "+getRDBDataType(Cols.get(i).getStringType())+" , ";
				   }
				   else
				   {
					   sql += Cols.get(i).getName()+" "+getRDBDataType(Cols.get(i).getStringType())+" ) ";
				   }
			   }
			   System.out.println(sql);
			   Statement pstmt ;
		       try {
				pstmt = conn.createStatement();
				pstmt.execute(sql);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		// we want to grab meta data from this statement
		
		
		// instead of Method, maybe pass in a hashmap containing info about the object that you
		
		
		//ObjectCache class...
		
       
		
		
		// then call acustom setStatement method
		
		
		
		return false;
		
	}
	
	
	

}