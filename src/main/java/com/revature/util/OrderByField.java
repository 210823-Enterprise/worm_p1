package com.revature.util;

import java.lang.reflect.Field;

import com.revature.annotations.OrderBy;

public class OrderByField {

	
private Field field;
	
	public OrderByField(Field field) {
		
		if (field.getAnnotation(OrderBy.class)== null) {
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
	
	public String getOrder() {
		return field.getAnnotation(OrderBy.class).order();
	}
	
	
	
}