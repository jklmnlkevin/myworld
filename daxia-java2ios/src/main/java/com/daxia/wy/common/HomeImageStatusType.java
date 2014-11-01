package com.daxia.wy.common;

import java.util.HashMap;
import java.util.Map;

public enum HomeImageStatusType {
	Disabled(0, "禁用"),
    Enabled(1, "启用");

    private int value;
    private String remark;

    private HomeImageStatusType(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public HomeImageStatusType getByValue(int value) {
        for (HomeImageStatusType o : HomeImageStatusType.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }
    
    public static String getRemark(int value) {
    	 for (HomeImageStatusType o : HomeImageStatusType.values()) {
             if (o.getValue() == value) {
                 return o.remark;
             }
         }
    	 return null;
    }
    
    public static Map<Integer, String> toMap() {
    	Map<Integer, String> map = new HashMap<Integer, String>();
    	for (HomeImageStatusType o : HomeImageStatusType.values()) {
    		map.put(o.value, o.remark);
    	}
    	return map;
    }
}
