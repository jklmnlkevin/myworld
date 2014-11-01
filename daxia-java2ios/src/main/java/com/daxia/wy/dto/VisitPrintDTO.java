package com.daxia.wy.dto;

import java.util.Date;

public class VisitPrintDTO {
        private Date date;
        private String address;
        private int merchantCount;
        private String merchantNames;
        private String day;
        public Date getDate() {
            return date;
        }
        public void setDate(Date date) {
            this.date = date;
        }
        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }
        public int getMerchantCount() {
            return merchantCount;
        }
        public void setMerchantCount(int merchantCount) {
            this.merchantCount = merchantCount;
        }
        public String getMerchantNames() {
            return merchantNames;
        }
        public void setMerchantNames(String merchantNames) {
            this.merchantNames = merchantNames;
        }
        public String getDay() {
            return day;
        }
        public void setDay(String day) {
            this.day = day;
        }
        
        
    }