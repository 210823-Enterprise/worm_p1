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

	public Goblin(int id, String goblinName, int powerLevel, String weapon) {
		super();
		this.id = id;
		this.goblinName = goblinName;
		this.powerLevel = powerLevel;
		this.weapon = weapon;
	}
	
	public Goblin(int id) {
		super();
		this.id = id;
		this.goblinName = "Stumpy";
		this.powerLevel = 1;
		this.weapon = "borken stick";
	}

	public Goblin() {
		super();
		this.id = 10;
		this.goblinName = "Stumpy";
		this.powerLevel = 1;
		this.weapon = "borken stick";
	}
	
	public void Roar() {
		System.out.println("RAAAAAAAAAWR");
	}
	
	
}