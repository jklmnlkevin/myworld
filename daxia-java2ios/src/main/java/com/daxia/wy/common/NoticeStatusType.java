package com.daxia.wy.common;

public enum NoticeStatusType {
    UnRead(0, "未读"), 
    Readed(1, "已读");

    private int value;
    private String remark;

    private NoticeStatusType(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }

    public NoticeStatusType getByValue(int value) {
        for (NoticeStatusType o : NoticeStatusType.values()) {
            if (o.getValue() == value) {
                return o;
            }
        }
        return null;
    }
    
    public static String getRemark(int value) {
    	 for (NoticeStatusType o : NoticeStatusType.values()) {
             if (o.getValue() == value) {
                 return o.remark;
             }
         }
    	 return null;
    }
}
