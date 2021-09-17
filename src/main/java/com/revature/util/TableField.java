package com.revature.util;

import java.lang.reflect.Field;

import com.revature.annotations.Table;

public class TableField {

	
private Field field;
	
	public TableField(Field field) {
		
		if (field.getAnnotation(Table.class)== null) {
			// if the field object aht we pass thorugh DOESN't have the column annotation, then it returns null
			throw new IllegalStateException("Cannot create TableField Object! Provided field " + getName() + " is not a Table");
		}
		
		this.field = field;
	}
	
	public String getName() {
		return field.getName();
	}
	
	public Class<?> getType() {
		return field.getType();
	}
	
	public String getTableName() {
		return field.getAnnotation(Table.class).tableName();
	}
	
	
	
}