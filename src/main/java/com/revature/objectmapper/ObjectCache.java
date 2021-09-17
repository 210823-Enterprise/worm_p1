package com.revature.objectmapper;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This class handles caching objects stored in database tables.
 * Singleton so that there's only one instance.
 *
 */
public class ObjectCache {
	
	static final  ObjectCache obj_cache = new ObjectCache();
	private final HashMap<Class<?>, HashSet<Object>> cache;

	private ObjectCache() {
		super();
		cache = new HashMap<>();
	}
	
	public static ObjectCache getInstance() {
		
		return obj_cache;
		
	}
	
	
	
	public boolean insertIntoCache(HashSet<Object> obj)
	{
		
		cache.put(obj.getClass() , obj);
		
		
		return false;
		
	}
	
	
	
	
	// method: putObj in cache()
	
	// remove()

	
	
	
}