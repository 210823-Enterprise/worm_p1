package com.revature;

import java.sql.Connection;
import com.revature.connection.ConnectionFactory;
import com.revature.dummymodels.Test;
import com.revature.objectmapper.ObjectGetter;
import com.revature.objectmapper.ObjectRemover;
import com.revature.objectmapper.ObjectSaver;
import com.revature.util.Configuration;

public class OrmDriver {
	
	public static void main(String[] args) {
		
		Configuration cfg = new Configuration();
		// IN our configuration object we want to add annotated class, without having to instantiate them
		
		cfg.addAnnotatedClass(Test.class);
		ConnectionFactory Cf = ConnectionFactory.getInstance();
		Connection conn = Cf.getConnection();
		// this is just to prove that we successfully transformed it to a metamodel, readable by our framework
		// let's iterate over all meta models that exist in the config object
ObjectGetter obG = new ObjectGetter();
obG.getObjects();
				
//			ObjectRemover objR = new ObjectRemover();
//			
//			
//			
//			ObjectSaver objS =  new ObjectSaver();
//			Test something = new Test();
//			Test something2 = new Test();
//			// change it however you want 
//			something.id = 1000;
//			something2.testPass = "Some PassWord";
//			something2.testUsername = "Some username";
//			
//			//objR.removeObjectFromDb(something, conn);
//			//objR.removeObjectFromDb(something2, conn);
//			
//		
//    		objS.addObjectToDb(something, conn);
//			objS.addObjectToDb(something2, conn);
//			
			
		
		}

	}
	
	
	

	
	
