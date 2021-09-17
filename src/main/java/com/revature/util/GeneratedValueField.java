package com.revature.util;

import java.lang.reflect.Field;

import com.revature.annotations.GeneratedValue;

public class GeneratedValueField {

	
private Field field;
	
	public GeneratedValueField(Field field) {
		
		if (field.getAnnotation(GeneratedValue.class)== null) {
			// if the field object aht we pass thorugh DOESN't have the column annotation, then it returns null
			throw new IllegalStateException("Cannot create GeneratedValue Object! Provided field " + getName() + " is not a Generated Value");
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
		return field.getAnnotation(GeneratedValue.class).value();
	}
	
	
	
}