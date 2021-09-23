package com.revature;

import java.sql.Connection;
import java.util.List;
import com.revature.connection.ConnectionFactory;
import com.revature.dummymodels.Goblin;
import com.revature.dummymodels.Test;
import com.revature.objectmapper.ObjectReader;
import com.revature.objectmapper.ObjectRemover;
import com.revature.objectmapper.ObjectSaver;
import com.revature.util.ColumnField;
import com.revature.util.Configuration;
import com.revature.util.MetaModel;

public class OrmDriver {

	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		// IN our configuration object we want to add annotated class, without having to
		// instantiate them

		cfg.addAnnotatedClass(Test.class);
		cfg.addAnnotatedClass(Goblin.class);
		ConnectionFactory Cf = ConnectionFactory.getInstance();
		Connection conn = Cf.getConnection();
		// this is just to prove that we successfully transformed it to a metamodel,
		// readable by our framework
		// let's iterate over all meta models that exist in the config object

		ObjectSaver objS = new ObjectSaver();
		ObjectRemover objR = new ObjectRemover();
		ObjectReader objRe = new ObjectReader();
		
		Goblin gob1 = new Goblin(4, "Gobby", 172, "Bow");
		Goblin gob2 = new Goblin(5, "Gormo", 172, "Airplane");
		Goblin gob3 = new Goblin(6, "Blanka", 172, "Tazer");
		//objS.addObjectToDb(gob1, conn);
		//objS.addObjectToDb(gob2, conn);
		//objS.addObjectToDb(gob3, conn);
		Goblin gobDel = new Goblin(1);
		boolean result =  objR.removeObjectFromDb(gobDel, conn);
		System.out.println(result);
		
		List<Goblin> gobLins =  (List<Goblin>) (Object) objRe.getObjectsFromDB(gob1, conn);
		
		System.out.println("");
		for (Goblin gobby : gobLins) {
			System.out.println("------------------------------");
			System.out.println(gobby.id + " - " + gobby.goblinName + " - " 
			+ gobby.weapon + " - " + gobby.powerLevel);
		}
		System.out.println("------------------------------");

		
		
		//ObjectSaver objS = new ObjectSaver();
		//Test something = new Test();
		//Test something2 = new Test();
		// change it however you want
		//something.id = 1000;
		//something2.testPass = "Some PassWord";
		//something2.testUsername = "Some username";

		// objR.removeObjectFromDb(something, conn);
		// objR.removeObjectFromDb(something2, conn);

		//objS.addObjectToDb(something, conn);
		// objS.addObjectToDb(something2, conn);

	}

}
