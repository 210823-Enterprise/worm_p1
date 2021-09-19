package com.revature.dummymodels;

import com.revature.annotations.Column;
import com.revature.annotations.Entity;
import com.revature.annotations.Id;

@Entity(tableName="test_table")
public class Test {
	
	@Id(value = "1")
	private int id;
	
	@Column(columnName="Username")
	private String testUsername;
	
	@Column(columnName="Password")
	private String testPass;
}
