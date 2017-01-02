package com.mycompany.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.mycompany.dao.GeneriDaoImpl;
import com.mycompany.utils.CsvUtil;
import com.mycompany.utils.DateUtil;

public class UrlService {

	// 1.解析csv - 异常数据记录保存到日志
	// 2.调用save

	CsvUtil csvUtil = new CsvUtil();
	CSVParser parser = null;

	public void url(String filename, int rsize,String sql) {

		parser = csvUtil.parseCsvFile("source/" + filename + ".txt");// 参数
		String relateTag = "+";
		String evilUrl = "";
		String discoveryTime;
		String updateTime;
		String evilBehavior;

		String source = null;
		int viability=1;
		int credibility=0;
		
		int status=1;
		
		DateUtil dateUtil=new DateUtil();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 GeneriDaoImpl<Object>dao=new GeneriDaoImpl<Object>();
		dao.before();//获取连接

		for (CSVRecord csvRecord : parser) {
		
			if (csvRecord != null && csvRecord.size() == rsize) {
				if (csvRecord.get(0).toString().equals("+")) {
					relateTag = "+";
				} else if (csvRecord.get(0).toString().equals("=")) {

					relateTag = "=";

				}

				else {
					
					System.out.println("------A------"+csvRecord.getRecordNumber() + "  " + csvRecord.getComment());
					break;
				}

				if (csvRecord.get(1).length() > 10000) {
					System.out.println("------B------"+csvRecord.getRecordNumber() + "  " + csvRecord.getComment());
					break;
				} else {
					evilUrl = csvRecord.get(1);
				}

				if (csvRecord.get(2)!=null&&csvRecord.get(2)!="") {
					discoveryTime=dateUtil.date(csvRecord.get(2));
				} else {
				
					discoveryTime=sdf.format(new Date()).toString();
				}
				if (csvRecord.get(3)!=null&&csvRecord.get(3)!="") {
					updateTime=dateUtil.date(csvRecord.get(3));
				} else {
				
					updateTime=discoveryTime;
				}
				
				if (csvRecord.get(4)!=null&&csvRecord.get(4)!="") {
					evilBehavior=csvRecord.get(4);
				}else{
					evilBehavior="url";
				}
				if (csvRecord.get(5)!=null&&csvRecord.get(5)!="") {
					source=csvRecord.get(5);
				}else{
					source="url";
				}
				
				if (csvRecord.get(6)!=null&&csvRecord.get(6)!="") {
					if (csvRecord.get(6).toString().equals("0")) {
						viability=0;
					} else {
						viability=1;
					}
				}else{
					viability=1;
				}
				if (csvRecord.get(7)!=null&&csvRecord.get(7)!="") {
					credibility = (int) Double.parseDouble(csvRecord.get(7));
						
					}
				else{
					credibility =0;
				}
				String createtime=sdf.format(new Date()).toString();
				List<Object>list=new ArrayList<>();
				list.add(relateTag);
				list.add(evilUrl);
				list.add(discoveryTime);
				list.add(updateTime);
				list.add(evilBehavior);
				list.add(source);
				list.add(viability);
				list.add(credibility);
				list.add(status);
				list.add(createtime);
				
				dao.save(list, sql);
			}
			else {
				System.out.println("------C------"+csvRecord.getRecordNumber() + "  " + csvRecord.getComment());

			}

			
			
		}
		dao.after();//关闭资源
	}

	
}
