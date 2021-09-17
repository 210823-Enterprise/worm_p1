package com.revature.util;

import java.lang.reflect.Field;

import com.revature.annotations.Version;

public class VersionField {

	
private Field field;
	
	public VersionField(Field field) {
		
		if (field.getAnnotation(Version.class)== null) {
			// if the field object aht we pass thorugh DOESN't have the column annotation, then it returns null
			throw new IllegalStateException("Cannot create Version Object! Provided field " + getName() + " is not a valid Version");
		}
		
		this.field = field;
	}
	
	public String getName() {
		return field.getName();
	}
	
	public Class<?> getType() {
		return field.getType();
	}
	
	public String getVersion() {
		return field.getAnnotation(Version.class).version();
	}
	
	
	
}