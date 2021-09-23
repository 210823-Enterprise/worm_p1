package com.revature;

import java.util.HashMap;

import com.revature.objectmapper.ObjectCache;
import com.revature.util.IdField;
import com.revature.util.MetaModel;

public class Worm {

	
	public void save(Object obj)
	{
		MetaModel<?> model = MetaModel.of(obj.getClass());
		IdField Pk = model.getIdField();
    	String id = (String) Pk.getValue(obj);
		if(ObjectCache.getCache().containsKey(id))
		{
		  
		}
		else
		{
			ObjectCache.putObjInCache(obj);
		}
	}
	public void delete(Object obj)
	{
		MetaModel<?> model = MetaModel.of(obj.getClass());
		IdField Pk = model.getIdField();
    	String id = (String) Pk.getValue(obj);
		if(ObjectCache.getCache().containsKey(id))
		{
			ObjectCache.removeObjFromCache(obj);
		}
		else
		{
			
		}
	}
	
	public void ListSavedObjects()
	{
		
	}
	public void ListCache()
	{
		
	}
	
	
}
