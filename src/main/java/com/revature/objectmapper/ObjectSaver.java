package com.revature.objectmapper;

import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.revature.annotations.Entity;
import com.revature.util.ColumnField;
import com.revature.util.IdField;
import com.revature.util.MetaModel;

public class ObjectSaver extends ObjectMapper{
	
	public boolean addObjectToDb(Object obj, Connection conn) {
		
		
		
		
		MetaModel<?> model = MetaModel.of(obj.getClass()); // use this to creaet an instance of the object
		System.out.println(model.toString());
		IdField Pk = model.getIdField();
		
		System.out.println(Pk.getName()+" , "+Pk.getType()+" , "+Pk.getValue());
		String sql = "CREATE TABLE IF NOT EXISTS erickj."+model.getTableName()+" ( id int , ";
		List<ColumnField> Cols = model.getColumns();
		
		try
		{
			   for(int i =0; i < Cols.size(); i++)
			   {
				   System.out.println(Cols.size()+ "   "+ i);
				   if(i < Cols.size() -1)
				   {
				      sql += Cols.get(i).getName()+" "+getRDBDataType(Cols.get(i).getStringType())+" , ";
				   }
				   else
				   {
					   sql += Cols.get(i).getName()+" "+getRDBDataType(Cols.get(i).getStringType())+" )";
				   }
			   }
			   System.out.println(sql);
			   
			   Statement stmt  = conn.createStatement();
		       
				if (stmt.execute(sql)) 
				  {
					System.out.println("Table Created ,Success!");
					
				  }
			   
			   
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}   
		 
		
		try
		{
		String sql2 = "INSERT INTO erickj."+model.getTableName()+"( id , ";
		
		       for(int i =0; i < Cols.size(); i++)
		        {
		    	   System.out.println(Cols.size()+ "   "+ i);
				   if(i < Cols.size() -1)
				   {
				      sql2 += Cols.get(i).getName()+" , ";
				   }
				   else
				   {
					   sql2 += Cols.get(i).getName()+" )";
				   }
		        }
		
		
				sql2 += " VALUES("+Pk.getValue()+" , ";
				
				for(int i =0; i < Cols.size(); i++)
		        {
		    	   System.out.println(Cols.size()+ "   "+ i);
				   if(i < Cols.size() -1)
				   {
				      sql2 += Cols.get(i).getColumnName()+" , ";
				   }
				   else
				   {
					   sql2 += Cols.get(i).getColumnName()+" )";
				   }
		        }
				
			
				System.out.println(sql2);
				   
				   Statement stmt  = conn.createStatement();
			       
					if (stmt.execute(sql2)) 
					  {
						System.out.println("Successfully Inserted!");
						return true;
					  }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		// we want to grab meta data from this statement
		
		
		// instead of Method, maybe pass in a hashmap containing info about the object that you
		
		
		//ObjectCache class...
		
       
		
		
		// then call acustom setStatement method
		
		
		
	return false;
		
	}
	
	
	

}