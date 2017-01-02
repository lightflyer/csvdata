package com.mycompany.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.CRC32;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;


import com.mycompany.utils.CsvUtil;
import com.mycompany.utils.DataSource;

public class DataDao {
	
	static Logger logger=Logger.getLogger(DataDao.class);
	int a = 0;
	int b=0;
	int errproms = 0;
	int errordata = 0;


	CRC32 crc32 = new CRC32();
	CSVParser parser = null;
	List<String> list = new ArrayList<>();
	PreparedStatement ps;

	DataSource datasource = new DataSource();
	/**
	* 
	* @param filename   临时文件
	* @param rsize      参数个数
	* @param sql        插入sql语句
	* @return
	*/
	public boolean saveData(String filename, int rsize, String sql) {
		Connection con = datasource.getConnection();
		CsvUtil csvUtil = new CsvUtil();
		System.out.println(new Date());
		parser = csvUtil.parseCsvFile("source/" + filename + ".txt");// 参数
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			// 不自动提交
			e.printStackTrace();
		}

	
		try {
			ps = con.prepareStatement(sql);
		} catch (SQLException e) {
			// SQL预处理
			e.printStackTrace();
			System.out.println("sql预处理出错");
		}
		for (CSVRecord csvRecord : parser) {
			a++;
//			crc32.update(csvRecord.toString().getBytes());

			if (csvRecord.size() == rsize) {

				for (int i = 0; i < csvRecord.size(); i++) {
						try {
//							
							ps.setObject(i + 1, csvRecord.get(i));
						} catch (SQLException e) {
							// 添加字段值出错
							e.printStackTrace();
						}
						if (i + 1 == csvRecord.size()) {

							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

							try {
								ps.setInt(i+2, 1);
//	ps.setObject(i + 2, crc32.getValue());
								ps.setObject(i + 3, sdf.format(new Date()).toString());
							} catch (SQLException e) {
								// 添加额外记录信息出错
								e.printStackTrace();
							}
						}
						
						
						
//					}

					
					
				}

				try {
					ps.executeUpdate();
					con.commit();
			} catch (SQLException e) {
					// 执行一行数据插入操作
					e.printStackTrace();
					try {
						con.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
					
			
			} else {
				errproms++;
				logger.warn(csvRecord.getRecordNumber()+"   "+csvRecord);
				// System.out.println("参数个数不正确");
				list.add(b + errordata + errproms + "");
			}

		}
		
	

//		for (String string : list) {
// 			System.out.print(string+" ");
//		}
		
		System.out.println("filename=" + filename + "  " + "总条数a=" + a + "  "  + "errproms="
				+ errproms + "  " + "errordata=" + errordata + "  " + "错误个数" + list.size());
		System.out.println(new Date());
		if (b > (errordata + errproms)) {
			return true;
		} else {

			return false;
		}

	}
}
