package com.revature.objectmapper;


import java.util.HashMap;
import com.revature.util.IdField;
import com.revature.util.MetaModel;

public class ObjectCache 
{
    private final static ObjectCache obj_cache = new ObjectCache();
    private static HashMap<String , MetaModel<?>> cache;

    private ObjectCache() 
    {
        super();
        cache = new HashMap<>();
    }

    public static ObjectCache getInstance()
    {
        return obj_cache;
    }

    
    public static HashMap<String , MetaModel<?>> getCache() 
    {
        return cache;
    }

   
    public static boolean putObjInCache(final Object obj) 
    {
    	MetaModel<?> model = MetaModel.of(obj.getClass());
    	IdField Pk = model.getIdField();
    	String id = (String) Pk.getValue(obj);
        if(!cache.containsKey(id)) 
        {
            cache.put(id, model);
            return true;
        }
        else
        {
        	return false;
        }
    }
    public static boolean removeObjFromCache(final Object obj) 
    {
    	MetaModel<?> model = MetaModel.of(obj.getClass());
    	IdField Pk = model.getIdField();
    	String id = (String) Pk.getValue(obj);
        if(cache.containsKey(id)) 
        {
            cache.remove(id, model);
            return true;
        }
        else
        {
        	return false;
        }
    }

    

    
/////

}