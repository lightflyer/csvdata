package com.mycompany.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
	
	/**
	 * 处理标准格式日期字符串，记录异常格式日期数据
	 * @param str 日期字符串
	 * @return
	 */
	public  String date(String str) {

		String reg1 = "[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))";// 2016-12-01
		String reg2 = "(0[0-9]{1}|1[0-9]{1}|2[0-3]):[0-5][0-9]{1}:([0-5][0-9]{1})";// 00:00:00
		String reg3 = "(0[0-9]{1}|1[0-9]{1}|2[0-3]):([0-5][0-9]{1})";// 00:00
		Pattern p1 = Pattern.compile(reg1);
		Pattern p2 = Pattern.compile(reg2);
		Pattern p3 = Pattern.compile(reg3);

		if (str != null && str != "") {
			if (str.length() == 19) {
				String date = str.substring(0, 10);
				String time1 = str.substring(11, 19);
				Matcher m1 = p1.matcher(date);
				Matcher m2 = p2.matcher(time1);

				if (m1.matches()&& m2.matches()) {
//					System.out.println(str+" *19");
					return str;
				
				} else {
					System.out.println("19");
					return "2016-01-01 00:00:00";
				}
			} else if (str.length() == 10) {
				String date = str.substring(0, 10);
				Matcher m1 = p1.matcher(date);
				if (m1.matches()) {
//					System.out.println(str+" *10");
					return str + " 00:00:00";
				} else {
					System.out.println("10");
					return "2016-01-01 00:00:00";
				}

			} else if (str.length() == 16) {
				String date = str.substring(0, 10);
				String time2 = str.substring(11, 16);
				Matcher m1 = p1.matcher(date);
				Matcher m3 = p3.matcher(time2);
				if (m1.matches() && m3.matches()) {
//					System.out.println(str+" *16");
					return str + ":00";
				} else {
					System.out.println("16");
					return "2016-01-01 00:00:00";
				}
			}else if (str.length()>10&&str.length()<16) {
				String date = str.substring(0, 10);
				Matcher m1 = p1.matcher(date);
				if (m1.matches()) {
					return str.substring(0,10)+" 00:00:00";
				}
				System.out.println(str+"  长度大于10小于16");
				return "2016-01-01 00:00:00";
			}

		} else {
			System.out.println("日期为空");
			return "2016-01-01 00:00:00";
		}
		System.out.println(str+"  其他未知长度?");
		 	return "2016-01-01 00:00:00";
	

	}

}
