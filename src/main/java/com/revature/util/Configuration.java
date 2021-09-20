package com.revature.util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Configuration {
	
	// all jdbc properties needed to establish a connection with the database
	
	private List <MetaModel<Class<?>>> metaModelList;
	
	// maybe some connection pooling properties
	
	// this essentially does what the Hibernate.cfg.xml mapping property does!
	public Configuration addAnnotatedClass(Class annotatedClass) {
		
		if (metaModelList == null) {
			
			metaModelList = new LinkedList<>();
		
		}
		
		metaModelList.add(MetaModel.of(annotatedClass)); // we will make this inside our MetaModel class
		
		// Create the of() method inside MetaModel to transform a class
		// into an appropriate data model to be transposed into a relational db object.
		
		return this;
	}
	
	public List<MetaModel<Class<?>>> getMetaModels() {
		
		return (List<MetaModel<Class<?>>>) ((metaModelList == null) ? Collections.emptyList() : metaModelList);
		
	}
	
}
