package com.revature.objectmapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.revature.util.ColumnField;
import com.revature.util.IdField;
import com.revature.util.MetaModel;

public class ObjectSaver extends ObjectMapper{
	
	
	private static Logger log = Logger.getLogger(ObjectSaver.class);

	 
	public ObjectSaver()
	{
		super();
	}
	
	

	
	
	
	public boolean addObjectToDb(Object obj, Connection conn) {
		ClassLoader classLoader = getClass().getClassLoader();
		Properties props = new Properties();
		
		try {
			
			props.load(new FileReader(classLoader.getResource("application.properties").getFile()));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MetaModel<?> model = MetaModel.of(obj.getClass()); // use this to creaet an instance of the object
		IdField Pk = model.getIdField();
		Statement stmt = null;
		
		boolean Update = false;
		
		String check = "SELECT * FROM "+props.getProperty("DBschema")+"."+model.getTableName()+" WHERE "+Pk.getColumnName()+" = "+Pk.getValue(obj);
				     
		try {
			stmt = conn.createStatement(); 
			stmt.execute(check); 
			ResultSet rs = stmt.getResultSet();
		if (rs.next()) 
		  {
			
			log.info("This Object Already Exists in the Database. Updating...");
			Update = true;
		  }
		else
		{
			log.info(model.getTableName()+" "+model.getIdField().getValue(obj)+" is being put in the Table : "+model.getTableName()+" That already exists in database.");
			Update = false;
		}
		} catch (Exception e) {
			log.info("The Table "+model.getTableName()+" Already exists , "+model.getTableName()+" "+model.getIdField().getValue(obj)+" Is being Inserted.");
		}	
		List<ColumnField> Cols = model.getColumns();
if(Update == false)
{		String sql = "CREATE TABLE IF NOT EXISTS "+props.getProperty("DBschema")+"."+model.getTableName()+" ("+Pk.getColumnName()+" "+Pk.getType()+" ,";
		try
		{
			   for(int i =0; i < Cols.size(); i++)
			   {
				  
				   if(i < Cols.size() -1)
				   {
				      sql += Cols.get(i).getColumnName()+" "+getRDBDataType(Cols.get(i).getStringType())+" , ";
				   }
				   else
				   {
					   sql += Cols.get(i).getColumnName()+" "+getRDBDataType(Cols.get(i).getStringType())+" )";
				   }
			   }
			  
			   
			    stmt  = conn.createStatement();
		       
				if (stmt.execute(sql)) 
				  {
					log.info("New Table created successfully.");
					
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
		    	  
				   if(i < Cols.size() -1)
				   {
				      sql2 += Cols.get(i).getColumnName()+" , ";
				   }
				   else
				   {
					   sql2 += Cols.get(i).getColumnName()+" )";
				   }
		        }
		
		
				sql2 += " VALUES("+Pk.getValue(obj)+" , ";
				
				for(int i =0; i < Cols.size(); i++)
		        {
		    	  
				   if(i < Cols.size() -1)
				   {
				      sql2 += "'"+Cols.get(i).getValue(obj)+"' , ";
				   }
				   else
				   {
					   sql2 += "'"+Cols.get(i).getValue(obj)+"' )";
				   }
		        }
				   
				   stmt  = conn.createStatement();
			       
					if (stmt.execute(sql2)) 
					  {
						log.info(model.getTableName()+" "+model.getIdField().getValue(obj)+" Inserted successfully.");
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
    	  
		   if(i < Cols.size() -1)
		   {
		      sql3 += Cols.get(i).getColumnName()+" = '"+Cols.get(i).getValue(obj) +"' , ";
		   }
		   else
		   {
			   sql3 += Cols.get(i).getColumnName()+" = '"+Cols.get(i).getValue(obj) +"'  ";
		   }
        }

	sql3 += " WHERE id ='"+Pk.getValue(obj)+"';";	
	
		   
		   stmt  = conn.createStatement();
	       
			if (stmt.execute(sql3)) 
			  {
				log.info(model.getTableName()+" "+model.getIdField().getValue(obj));
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
		
		
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	return false;
		
	}
	
	
 }	
	
}