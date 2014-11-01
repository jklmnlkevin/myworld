package com.daxia.wy.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum TopicType {
	
	OnTop(1, "首页显示"),
	Normal(2, "侧边栏显示");
	

	private int type;
	private String value;
	
	private TopicType(int type, String value) {
		this.type = type;
		this.value = value;
	}
	
	private String getValue() {
		return value;
	}
	
	private int getType() {
		return type;
	}
	
	public TopicType getByType(int type) {
		TopicType userType = null;
		for (TopicType u : TopicType.values()) {
			if (u.getType() == type) {
				userType = u;
			}
		}
		return userType;
	}
	
	public static Map<Integer, String> toMap() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (TopicType u : TopicType.values()) {
			map.put(u.type, u.value);
		}
		return map;
	}
	
	public static void main(String[] args) {
		Map<Integer, String> map = TopicType.toMap();
		Set<Integer> keySet = map.keySet();
		for (Integer key : keySet) {
			System.out.println(map.get(key));
		}
		System.out.println(TopicType.toMap().size());
	}
	
	
	
}
