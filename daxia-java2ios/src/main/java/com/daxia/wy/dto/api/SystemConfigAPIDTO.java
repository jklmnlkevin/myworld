package com.daxia.wy.dto.api;

/**
 * 这个类的字段都是与返回给手机的json的数组里的内容相对应的，
 * 所有的字段都定义成字符串的形式，除了集合和数组，名称与model类里的字段名相同。
 */
public class SystemConfigAPIDTO {
	private String id;
	
	/**
     * @return the id
     */
    public String getId() {
    	return id;
    }
	/**
     * @param id the id to set
     */
    public void setId(String id) {
    	this.id = id;
    }
}
