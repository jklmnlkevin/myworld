package com.daxia.wy.common;

import java.util.HashMap;
import java.util.Map;

public enum NoticeSourceType {
    System(0, "系统消息");

    private int value;
    private String remark;

    private NoticeSourceType(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public NoticeSourceType getByValue(int value) {
        for (NoticeSourceType o : NoticeSourceType.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }
    
    public static String getRemark(int value) {
    	 for (NoticeSourceType o : NoticeSourceType.values()) {
             if (o.getValue() == value) {
                 return o.remark;
             }
         }
    	 return null;
    }
    
    public static Map<Integer, String> toMap() {
    	Map<Integer, String> map = new HashMap<Integer, String>();
    	for (NoticeSourceType o : NoticeSourceType.values()) {
    		map.put(o.value, o.remark);
    	}
    	return map;
    }
}
