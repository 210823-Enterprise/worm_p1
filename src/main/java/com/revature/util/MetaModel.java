package com.revature.util;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import com.revature.annotations.Column;
import com.revature.annotations.Entity;

public class MetaModel<T> {
	
	private Class<T> clazz;
	// private IdField primarykeyField
	private List<ColumnField> columnFields;
	// private List<ForeignKeyField> foreignKeyFields
	
	// MetaModel Constructor here
	
	// of() method to take in a clas and transform it to a meta model
	
	public static <T> MetaModel<T> of(Class<T> clazz) {
		
		// first have to check that it's marked with the Entity annotation
		if (clazz.getAnnotation(Entity.class) == null) {
			throw new IllegalStateException("Cannot create MetaModel object! Provided class " + clazz.getName() + " is not annotated with @Entity");
		}
		
		return new MetaModel<>(clazz);
	}
	
	public MetaModel(Class<T> clazz) {
		this.clazz = clazz;
		this.columnFields = new LinkedList<>();
		
	}
	
	// class name is com.revature.MyClass
	public String getClassName() {
		
		return clazz.getName();
		
	}
	
	// simple class name is just MyClass
	public String getSimpleName() {
		
		return clazz.getSimpleName();
	
	}
	
	// public IdField getPrimaryKey() ... You would need to create the IdField class...
	
	public List<ColumnField> getColumns() {
		
		Field[] fields = clazz.getDeclaredFields();
		
		for (Field field : fields) {
			Column column = field.getAnnotation(Column.class);
			
			if (column != null) {
				columnFields.add(new ColumnField(field));
			}
		}
		
		return columnFields;
		
	}
	
	// public List<ForeignKeyField 
	
}