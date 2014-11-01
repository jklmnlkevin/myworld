package com.daxia.wy.common;

import java.util.HashMap;
import java.util.Map;

public enum ProductRecommendType {
	RecommendOn(0, "推荐"),
    RecommendOff(1, "不推荐");

    private int value;
    private String remark;

    private ProductRecommendType(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public ProductRecommendType getByValue(int value) {
        for (ProductRecommendType o : ProductRecommendType.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }
    
    public static String getRemark(int value) {
    	 for (ProductRecommendType o : ProductRecommendType.values()) {
             if (o.getValue() == value) {
                 return o.remark;
             }
         }
    	 return null;
    }
    
    public static Map<Integer, String> toMap() {
    	Map<Integer, String> map = new HashMap<Integer, String>();
    	for (ProductRecommendType o : ProductRecommendType.values()) {
    		map.put(o.value, o.remark);
    	}
    	return map;
    }
}
