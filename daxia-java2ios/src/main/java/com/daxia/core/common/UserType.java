package com.daxia.core.common;

public enum UserType {
	Normal(0),
	Disabled(1),
	StoreManager(2);

	private int value;

	private UserType(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}
}
