package com.revature.objectmapper;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.revature.util.IdField;
import com.revature.util.MetaModel;

public class ObjectCache 
{
    private final static ObjectCache obj_cache = new ObjectCache();
    private static HashMap< Integer  , Object> cache = new HashMap<>();
    static ObjectReader OBR = new ObjectReader();
    static ObjectSaver OBS = new ObjectSaver();
    private ObjectCache() 
    {
        super();
        
    }

    public static ObjectCache getInstance()
    {
        return obj_cache;
    }

    
    public static HashMap<Integer , Object> getCache() 
    {
        return cache;
    }

   
    public static boolean putObjInCache(Object obj , Connection conn) 
    {
    	MetaModel<?> model = MetaModel.of(obj.getClass()); // use this to creaet an instance of the object
		IdField Pk = model.getIdField();
        cache.put( Integer.getInteger( Pk.getValue(obj).toString()) , obj);
        saveCache(conn);    
            return true;  
    }
    public static Object getObjectFromCache(Object obj , Connection conn)
    {
    	MetaModel<?> model = MetaModel.of(obj.getClass()); // use this to creaet an instance of the object
		IdField Pk = model.getIdField();
    	
    	if(cache.containsKey(Pk.getValue(obj)))
    	{
    		return cache.get(Pk.getValue(obj));
    	}
    	else
    	{
    		List<Object> objs = OBR.getObjectsFromDB(obj, conn);
    		for (Object o : objs)
    		{
	    		if(o == obj)
	    		{
	    			cache.put( Integer.getInteger((String) Pk.getValue(o)) , o);
	    			return o;
	    		}
    		}
    	}
    		
		return null;
    	
    }
    public static boolean removeObjFromCache(final Object obj) 
    {
//    	
//        if(cache.contains(obj)) 
//        {
//            cache.remove(obj);
//            return true;
//        }
//        else
//        {
//        	return false;
//        }
        return false;
    }

    public static boolean saveCache(Connection conn)
	{
		//runs cache into db
		HashMap<Integer , Object> cache = ObjectCache.getCache();
		ObjectSaver ObjS = new ObjectSaver();
		cache.forEach((i , o) -> ObjS.addObjectToDb(o, conn));
		return true;
	}

    
/////

}