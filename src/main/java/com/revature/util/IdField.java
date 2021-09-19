package com.revature.util;

import java.lang.reflect.Field;

import com.revature.annotations.Id;

public class IdField {

	
private Field field;
	
	public IdField(Field field) {
		
		if (field.getAnnotation(Id.class)== null) {
			// if the field object aht we pass thorugh DOESN't have the column annotation, then it returns null
			throw new IllegalStateException("Cannot create IdField Object! Provided field " + getName() + " is not a primaryKey");
		}
		
		this.field = field;
	}
	
	public String getName() {
		return field.getName();
	}
	
	public Class<?> getType() {
		return field.getType();
	}
	
	public String getValue() {
		return field.getAnnotation(Id.class).value();
	}
	
	
	
}
