package com.mycompany.test;

import java.util.Date;

import com.mycompany.service.DataService;
import com.mycompany.utils.FileUtil;

public class CsvTest {
	public static void main(String[] args) {
		System.err.println(new Date());
		CsvTest csvTest = new CsvTest();
		
//		 csvTest.read();
		 csvTest.insertDb();
			System.err.println(new Date());
	}

/**
 *  调用工具类读取原文件并生成临时csv文件
 */
	public void read() {
		FileUtil fileUtil = new FileUtil();

		
	

		for (int i = 26; i < 27; i++) {
			String str2 = "";
			String str1 = i + "";
			if (i < 10) {
				str2 = "0" + i + "";
			} else {
				str2 = i + "";
			}
			fileUtil.readAndCopy("C:/Users/yuchunchao/Desktop/data/watcherlab-botnet-2016-12-" + str2 + ".txt",
					"a" + str1);
			fileUtil.readAndCopy("C:/Users/yuchunchao/Desktop/data/watcherlab-c2-2016-12-" + str2 + ".txt", "b" + str1);
			fileUtil.readAndCopy("C:/Users/yuchunchao/Desktop/data/watcherlab-cn-ipv4-2016-12-" + str2 + ".txt",
					"c" + str1);
			fileUtil.readAndCopy("C:/Users/yuchunchao/Desktop/data/watcherlab-email-2016-12-" + str2 + ".txt",
					"d" + str1);
			fileUtil.readAndCopy("C:/Users/yuchunchao/Desktop/data/watcherlab-fastflux-2016-12-" + str2 + ".txt",
					"e" + str1);
			fileUtil.readAndCopy("C:/Users/yuchunchao/Desktop/data/watcherlab-fqdn-2016-12-" + str2 + ".txt",
					"f" + str1);
			fileUtil.readAndCopy("C:/Users/yuchunchao/Desktop/data/watcherlab-ipv4-2016-12-" + str2 + ".txt",
					"g" + str1);
			fileUtil.readAndCopy("C:/Users/yuchunchao/Desktop/data/watcherlab-ipv6-2016-12-" + str2 + ".txt",
					"h" + str1);
			fileUtil.readAndCopy("C:/Users/yuchunchao/Desktop/data/watcherlab-proxy-2016-12-" + str2 + ".txt",
					"i" + str1);
			fileUtil.readAndCopy("C:/Users/yuchunchao/Desktop/data/watcherlab-tor-2016-12-" + str2 + ".txt",
					"j" + str1);
			fileUtil.readAndCopy("C:/Users/yuchunchao/Desktop/data/watcherlab-url-2016-12-" + str2 + ".txt",
					"k" + str1);

		}

	}
/**
 * watcher数据写入数据库
 */
	public void insertDb() {

		// 对应表插入sql语句
		String sql1 = "insert into watcherlab_botnet(botnet_relate_tag,botnet_ip,botnet_discovery_time,botnet_update_time,botnet_evil_behavior,botnet_protocols,botnet_source,botnet_credibility,botnet_viability,botnet_first_find_time,botnet_country_code,botnet_status,botnet_createtime,crc)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		// 11+2

		String sql2 = "insert into watcherlab_c2(c2_relate_tag,c2_ip,c2_dns,c2_discovery_time,c2_flag,c2_source,c2_location,c2_viability,c2_os_identification,c2_port,c2_status,c2_createtime,crc) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		// 10+2

		String sql3 = "insert into watcherlab_cn_ipv4(cn_relate_tag,cn_evil_ip,cn_discovery_time,cn_update_time,cn_evil_behavior,cn_protocols,cn_source,cn_credibility,cn_cumulative_credibility,cn_first_find_time,cn_location,cn_country_code,cn_viability,cn_os_identification,cn_port,cn_status,cn_createtime,crc) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		// 15+2

		String sql4 = "insert into watcherlab_email(email_relate_tag,email_evil_email,email_discovery_time,email_update_time,email_evil_behavior,email_source,email_credibility,email_status,email_createtime,crc) values(?,?,?,?,?,?,?,?,?,?)";
		// 7+2

		String sql5 = "insert into watcherlab_fastflux(fastflux_relate_tag,fastflux_ip,fastflux_discovery_time,fastflux_flag,fastflux_source,fastflux_location,fastflux_country_code,fastflux_viability,fastflux_os_identification,fastflux_port,fastflux_status,fastflux_createtime,crc) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		// 10+2

		String sql6 = "insert into watcherlab_fqdn(fqdn_relate_tag,fqdn_evil_dns,fqdn_discovery_time,fqdn_update_time,fqdn_evil_behavior,fqdn_protocols,fqdn_source,fqdn_credibility,fqdn_status,fqdn_createtime,crc) values(?,?,?,?,?,?,?,?,?,?,?)";
		// 8+2

		String sql7 = "insert into watcherlab_ipv4(ip_relate_tag,ip_evil_ip,ip_discovery_time,ip_update_time,ip_evil_behavior,ip_protocols,ip_source,ip_credibility,ip_viability,ip_first_find_time,ip_country_code,ip_status,ip_createtime,crc) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		// 11+2

		String sql8 = "insert into watcherlab_ipv6(ipv6_relate_tag,ipv6_evil_ip,ipv6_discovery_time,ipv6_update_time,ipv6_evil_behavior,ipv6_protocols,ipv6_source,ipv6_credibility,ipv6_status,ipv6_createtime,crc) values(?,?,?,?,?,?,?,?,?,?,?)";
		// 8+2

		String sql9 = "insert into watcherlab_proxy(proxy_relate_tag,proxy_doubtful_proxy,proxy_discovery_time,proxy_flag,proxy_source,proxy_location,proxy_country_code,proxy_viability,proxy_os_identification,proxy_port,proxy_status,proxy_createtime,crc) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		// 10+2

		String sql10 = "insert into watcherlab_tor(tor_relate_tag,tor_anonymous_ip,tor_discovery_time,tor_flag,tor_source,tor_location,tor_country_code,tor_status,tor_createtime,crc) values(?,?,?,?,?,?,?,?,?,?)";
		// 7+2

		String sql11 = "insert into watcherlab_url(url_relate_tag,url_evil_url,url_discovery_time,url_update_time,url_evil_behavior,url_source,url_viability,url_credibility,url_status,url_createtime,crc) values(?,?,?,?,?,?,?,?,?,?,?)";
		// 8+2

		
		DataService dataService=new DataService();
		for (int i = 1; i < 26; i++) {
			String str = i + "";
		
			 dataService.saveData("a"+str, 11, sql1);// botnet
			 dataService.saveData("b"+str, 10,sql2);//c2
			 dataService.saveData("c"+str, 15,sql3);//cn_ipv4
			 dataService.saveData("d"+str, 7,sql4);//email
			 dataService.saveData("e"+str, 10,sql5);//fastflux
			 dataService.saveData("f"+str, 8,sql6);//fqdn
			 dataService.saveData("g"+str, 11,sql7);//ipv4
			 dataService.saveData("h"+str, 8,sql8);//ipv6
			 dataService.saveData("i"+str, 10,sql9);//proxy
			 dataService.saveData("j"+str, 7,sql10);//tor
			dataService.saveData("k" + str, 8, sql11);// url
		}
		
		
		
		
		
		
		
		
		
		
		
		
//		DataDao datadao = new DataDao();
//		// 读取临时文件并存入数据库
//
//		for (int i = 1; i < 2; i++) {
//			String str = i + "";
//			 datadao.saveData("a"+str, 11, sql1);// botnet
//			 datadao.saveData("b"+str, 10,sql2);//c2
//			 datadao.saveData("c"+str, 15,sql3);//cn_ipv4
//			 datadao.saveData("d"+str, 7,sql4);//email
//			 datadao.saveData("e"+str, 10,sql5);//fastflux
//			 datadao.saveData("f"+str, 8,sql6);//fqdn
//			 datadao.saveData("g"+str, 11,sql7);//ipv4
//			 datadao.saveData("h"+str, 8,sql8);//ipv6
//			 datadao.saveData("i"+str, 10,sql9);//proxy
//			 datadao.saveData("j"+str, 7,sql10);//tor
//			datadao.saveData("k" + str, 8, sql11);// url
//		}

	

	}

}
