package com.daxia.wy.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum MessageStatusType {
	
	Readed(1, "已读"),
	UnRead(7, "未读");
	

	private Integer type;
	private String value;
	
	private MessageStatusType(Integer type, String value) {
		this.type = type;
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public int getType() {
		return type;
	}
	
	public MessageStatusType getByType(Integer type) {
		MessageStatusType userType = null;
		for (MessageStatusType u : MessageStatusType.values()) {
			if (u.getType() == type) {
				userType = u;
			}
		}
		return userType;
	}
	
	public static String getValueByType(Integer type) {
		String value = "";
		for (MessageStatusType u : MessageStatusType.values()) {
			if (u.getType() == type) {
				value = u.getValue();
			}
		}
		return value;
	}
	
	public static Map<Integer, String> toMap() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (MessageStatusType u : MessageStatusType.values()) {
			map.put(u.type, u.value);
		}
		return map;
	}
	
	public static void main(String[] args) {
		Map<Integer, String> map = MessageStatusType.toMap();
		Set<Integer> keySet = map.keySet();
		for (Integer key : keySet) {
			System.out.println(map.get(key));
		}
		System.out.println(MessageStatusType.toMap().size());
	}
	
	
	
}
