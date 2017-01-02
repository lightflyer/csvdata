package com.mycompany.test;

import com.mycompany.service.UrlService;

public class BlackUrlTest {
public static void main(String[] args) {
	BlackUrlTest blackUrlTest=new BlackUrlTest();
	blackUrlTest.insertURL();
}



/**
 * 黑白名单全部数据写入
 */
	public void insertURL() {
		UrlService urlservice = new UrlService();

		// 对应表插入sql语句

		String sql11 = "insert into black_url_old(relate_tag,evil_url,discovery_time,update_time,evil_behavior,source,viability,credibility,status,create_time) values(?,?,?,?,?,?,?,?,?,?)";

		for (int i = 1; i < 26; i++) {
			String str = i + "";

			urlservice.url("k" + str, 8, sql11);// url
		}
	}
}
