package com.daxia.wy.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 积分兑换
 *
 */
public enum PointChangeType {
    Exchange(0, "积分兑奖"), 
    Sign(1, "签到积分"),
    Consume(2,"消费积分");

    private int value;
    private String remark;

    private PointChangeType(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public PointChangeType getByValue(int value) {
        for (PointChangeType o : PointChangeType.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }
    
    public static String getRemarkByValue(int value) {
        for (PointChangeType o : PointChangeType.values()) {
            if (o.getValue() == value) {
                return o.remark;
            }
        }
        return null;
    }
    
    public static Map<Integer, String> toMap() {
    	Map<Integer, String> map = new HashMap<Integer, String>();
    	for (PointChangeType o : PointChangeType.values()) {
    		map.put(o.value, o.remark);
    	}
    	return map;
    }
    
    
}
