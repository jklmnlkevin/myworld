package com.daxia.wy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateOrderNo {

	public static String getOrderNo() {
		String orderNo = "";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		orderNo = sdf.format(date);
		return orderNo;
	}
}
