package com.daxia.wy.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum UserType {
	
	Normal(1, "消费者"),
	Merchant(2, "商户"),
//	Leader(3, "主管领导"),
//	Manager(4, "主任"),
//	Market(5, "市场员"),
//	CustomerManger(6, "客户经理"),
	Employee(7, "员工");
	

	private Integer type;
	private String value;
	
	private UserType(Integer type, String value) {
		this.type = type;
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public int getType() {
		return type;
	}
	
	public UserType getByType(Integer type) {
		UserType userType = null;
		for (UserType u : UserType.values()) {
			if (u.getType() == type) {
				userType = u;
			}
		}
		return userType;
	}
	
	public static String getValueByType(Integer type) {
		String value = "";
		for (UserType u : UserType.values()) {
			if (u.getType() == type) {
				value = u.getValue();
			}
		}
		return value;
	}
	
	public static Map<Integer, String> toMap() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (UserType u : UserType.values()) {
			map.put(u.type, u.value);
		}
		return map;
	}
	
	public static void main(String[] args) {
		Map<Integer, String> map = UserType.toMap();
		Set<Integer> keySet = map.keySet();
		for (Integer key : keySet) {
			System.out.println(map.get(key));
		}
		System.out.println(UserType.toMap().size());
	}
	
	
	
}
