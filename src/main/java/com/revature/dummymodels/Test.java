package com.revature.dummymodels;

import com.revature.annotations.Column;
import com.revature.annotations.Entity;
import com.revature.annotations.Id;

@Entity(tableName="test_table")
public class Test {
	
	@Id(columnName="Id" )
	public int id = 10;
	
	@Column(columnName="USERname")
	public String testUsername = "USer";
	
	@Column(columnName="PassWORD")
	public String testPass = "Pass";
	
	

}
