package com.daxia.generator.model;

public abstract class BaseModel {
	
	public String getTableName() {
		return this.getClass().getSimpleName();
	}
}
