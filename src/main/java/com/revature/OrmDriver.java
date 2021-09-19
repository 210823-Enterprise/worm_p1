package com.revature;

import java.sql.Connection;
import java.util.List;
import com.revature.connection.ConnectionFactory;
import com.revature.dummymodels.Test;
import com.revature.objectmapper.ObjectRemover;
import com.revature.objectmapper.ObjectSaver;
import com.revature.util.ColumnField;
import com.revature.util.Configuration;
import com.revature.util.MetaModel;

public class OrmDriver {
	
	public static void main(String[] args) {
		
		Configuration cfg = new Configuration();
		// IN our configuration object we want to add annotated class, without having to instantiate them
		
		cfg.addAnnotatedClass(Test.class);
		ConnectionFactory Cf = ConnectionFactory.getInstance();
		Connection conn = Cf.getConnection();
		// this is just to prove that we successfully transformed it to a metamodel, readable by our framework
		// let's iterate over all meta models that exist in the config object
				
			ObjectRemover objR = new ObjectRemover();
			ObjectSaver objS =  new ObjectSaver();
			
			objS.addObjectToDb(new Test(), conn);
			objR.removeObjectFromDb(new Test(), conn);
			
		
		}
	}
	
	
	

	
	
