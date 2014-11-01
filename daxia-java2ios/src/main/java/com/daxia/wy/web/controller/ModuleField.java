package com.daxia.wy.web.controller;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ModuleField {
    private String type;
    private String name;
    private boolean isObject;
    private boolean isArray;
    private boolean isDate;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isObject() {
        return isObject;
    }
    public void setObject(boolean isObject) {
        this.isObject = isObject;
    }
    public boolean isArray() {
        return isArray;
    }
    public void setArray(boolean isArray) {
        this.isArray = isArray;
    }
    public boolean isDate() {
        return isDate;
    }
    public void setDate(boolean isDate) {
        this.isDate = isDate;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
}
