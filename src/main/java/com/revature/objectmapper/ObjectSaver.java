package com.revature.objectmapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import com.revature.util.ColumnField;
import com.revature.util.IdField;
import com.revature.util.MetaModel;

public class ObjectSaver extends ObjectMapper{
	
	public boolean addObjectToDb(Object obj, Connection conn) {
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
		System.out.println(model.toString());
		IdField Pk = model.getIdField();
		
		System.out.println(Pk.getName()+" , "+Pk.getType()+" , "+Pk.getColumnName()+" , "+Pk.getValue());
		Statement stmt;
		
		boolean Update = false;
		
		String check = "SELECT * FROM "+props.getProperty("DBschema")+"."+model.getTableName()+" WHERE "+Pk.getColumnName()+" = "+Pk.getValue();
				     
		try {
			stmt = conn.createStatement(); 
			stmt.execute(check); 
			ResultSet rs = stmt.getResultSet();
		if (rs.next()) 
		  {
			System.out.println("found "+rs.next());
			System.out.println("Record Already Exists");
			Update = true;
		  }
		else
		{
			System.out.println("New Record ");
			Update = false;
		}
		} catch (Exception e) {
			System.out.println("New Record Detected , Creating new table.");
		}	
		List<ColumnField> Cols = model.getColumns();
if(Update == false)
{		String sql = "CREATE TABLE IF NOT EXISTS "+props.getProperty("DBschema")+"."+model.getTableName()+" ("+Pk.getColumnName()+" "+Pk.getType()+" ,";
		try
		{
			   for(int i =0; i < Cols.size(); i++)
			   {
				   System.out.println(Cols.size()+ "   "+ i);
				   if(i < Cols.size() -1)
				   {
				      sql += Cols.get(i).getColumnName()+" "+getRDBDataType(Cols.get(i).getStringType())+" , ";
				   }
				   else
				   {
					   sql += Cols.get(i).getColumnName()+" "+getRDBDataType(Cols.get(i).getStringType())+" )";
				   }
			   }
			   System.out.println(sql);
			   
			    stmt  = conn.createStatement();
		       
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
		String sql2 = "INSERT INTO "+props.getProperty("DBschema")+"."+model.getTableName()+" ( id , ";
		
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
		
		
				sql2 += " VALUES("+Pk.getValue()+" , ";
				
				for(int i =0; i < Cols.size(); i++)
		        {
		    	   System.out.println(Cols.size()+ "   "+ i);
				   if(i < Cols.size() -1)
				   {
				      sql2 += "'"+Cols.get(i).getValue()+"' , ";
				   }
				   else
				   {
					   sql2 += "'"+Cols.get(i).getValue()+"' )";
				   }
		        }
				
			
				System.out.println(sql2);
				   
				   stmt  = conn.createStatement();
			       
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
	return false;		
}
else
{
	Update = false;
		try
		{
          String sql3 = "Update "+props.getProperty("DBschema")+"."+model.getTableName()+" SET ";

       for(int i =0; i < Cols.size(); i++)
        {
    	   System.out.println(Cols.size()+ "   "+ i);
		   if(i < Cols.size() -1)
		   {
		      sql3 += Cols.get(i).getColumnName()+" = '"+Cols.get(i).getValue() +"' , ";
		   }
		   else
		   {
			   sql3 += Cols.get(i).getColumnName()+" = '"+Cols.get(i).getValue() +"'  ";
		   }
        }

	sql3 += " WHERE id ='"+Pk.getValue()+"';";	
	
		System.out.println(sql3);
		   
		   stmt  = conn.createStatement();
	       
			if (stmt.execute(sql3)) 
			  {
				System.out.println("Successfully Updated!");
				return true;
			  }
}
catch(Exception e)
{
	e.printStackTrace();
}		// we want to grab meta data from this statement
		
		
		// instead of Method, maybe pass in a hashmap containing info about the object that you
		
		
		//ObjectCache class...
		
       
		
		
		// then call acustom setStatement method
		
		
		
	return false;
		
	}
	
	
 }	
}