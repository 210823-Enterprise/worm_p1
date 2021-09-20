package com.revature.util;

import java.lang.reflect.Field;

import com.revature.annotations.Column;

public class ColumnField {

	/**
	 *@Column
	 *private String name; // how do I determine if this is a VARCHAR or NUMERIC or SERIAL PRIMARY KEY?
	 */
	
	private Field field;
	
	public ColumnField(Field field) {
		
		if (field.getAnnotation(Column.class)== null) {
			// if the field object aht we pass thorugh DOESN't have the column annotation, then it returns null
			throw new IllegalStateException("Cannot create ColumnField Object! Provided field " + getName() + " is not annotated with @Column");
		}
		
		this.field = field;
	}
	
	public String getName() {
		return field.getName();
	}
	
	public Class<?> getType() {
		return field.getType();
	}
	
	public String getStringType()
	{
		return field.getType().getSimpleName();
		
	}
	
	public String getColumnName() {
		return field.getAnnotation(Column.class).columnName();
	}
public Object getValue() {
	try {
		Class<?> clazz = field.getDeclaringClass();
		
		Object value = field.get(clazz.newInstance());
		return value;
	} catch (IllegalArgumentException | SecurityException | IllegalAccessException | InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 0;
	
	
		
	
	}
	
}



