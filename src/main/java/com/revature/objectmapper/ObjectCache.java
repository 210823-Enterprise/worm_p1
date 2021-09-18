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
		if(obj != null)
		{
		cache.put(obj.getClass() , obj);
		return true;
		}
		else
		{
		return false;
		}
		
	}
	public boolean removeFromCache(HashSet<Object> obj)
	{
		if(cache.containsKey(obj.getClass()))
		{
			cache.remove(obj.getClass());
			return true;
		}
		return false;
		
		
	}
	public boolean updateObjectInCache(HashSet<Object> obj)
	{
		try
		{
		removeFromCache(obj);
		insertIntoCache(obj);
		return true;
		}
		catch(Exception e)
		{
		return false;
		}
		
		
	}
	
	
	// method: putObj in cache()
	
	// remove()

	
	
	
}