package com.revature.dummymodels;

import com.revature.annotations.Column;
import com.revature.annotations.Entity;
import com.revature.annotations.Id;

@Entity(tableName = "animal_table")
public class Test2 {

	@Id(columnName = "animal_id")
	private int id;

	@Column(columnName = "animal_color")
	private String animalColor;

	@Column(columnName = "animal_diet")
	private String animalDiet;
}
