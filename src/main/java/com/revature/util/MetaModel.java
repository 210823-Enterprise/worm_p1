package com.revature.util;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import com.revature.annotations.Column;
import com.revature.annotations.Entity;
import com.revature.annotations.Id;


public class MetaModel<T> {
	
	private Class<T> clazz;
	private IdField primarykeyField;
	private List<ColumnField> columnFields;
	
	
	
	
	// of() method to take in a clas and transform it to a meta model
	
	public MetaModel(Class<T> clazz, IdField primarykeyField, List<ColumnField> columnFields) {
		super();
		this.clazz = clazz;
		this.primarykeyField = primarykeyField;
		this.columnFields = columnFields;
	}

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
	public String getSimpleClassName() {
		
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
	
	public String getTableName() {
		String name = clazz.getAnnotation(Entity.class).tableName();
		
		if (name != null) {
			return name;
		}
		return null;
	}

	public IdField getIdField() {
		
Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Id Id = field.getAnnotation(Id.class);
			
			if (Id != null) {
				return new IdField(field);
			}
		}
		

		return null;
	}

	public IdField getPrimaryKey() {
		
		Field[] fields = clazz.getDeclaredFields();
		
		for (Field field : fields) {
			Id id = field.getAnnotation(Id.class);
			
			if (id != null) {
				primarykeyField = new IdField(field);
			}
		}

		
		return primarykeyField;
	}
	
	
}
