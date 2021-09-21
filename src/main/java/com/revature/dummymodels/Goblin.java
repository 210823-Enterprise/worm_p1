package com.revature.dummymodels;

import com.revature.annotations.Column;
import com.revature.annotations.Entity;
import com.revature.annotations.Id;

@Entity(tableName="goblin_table")
public class Goblin {
	
	@Id(columnName="Id" )
	public int id;
	
	@Column(columnName="name")
	public String goblinName;
	
	@Column(columnName="power_level")
	public int powerLevel;
	
	@Column(columnName="weapon")
	public String weapon;
	
}