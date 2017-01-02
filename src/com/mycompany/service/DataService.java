package com.mycompany.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.CRC32;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;

import com.mycompany.dao.GeneriDaoImpl;
import com.mycompany.utils.CsvUtil;
import com.mycompany.utils.DataSource;

public class DataService {
	int a = 0;
	int errproms = 0;

	static Logger logger = Logger.getLogger(DataService.class);

	CSVParser parser = null;
	List<String> list = new ArrayList<>();
	

	DataSource datasource = new DataSource();

	/**
	 * 
	 * @param filename
	 *            临时文件
	 * @param rsize
	 *            参数个数
	 * @param sql
	 *            插入sql语句
	 * @return
	 */
	public boolean saveData(String filename, int rsize, String sql) {
		CRC32 crc32 = new CRC32();
		GeneriDaoImpl<Object> daoImpl = new GeneriDaoImpl<>();
		CsvUtil csvUtil = new CsvUtil();
		daoImpl.before();
		parser = csvUtil.parseCsvFile("source/" + filename + ".txt");// 参数

		for (CSVRecord csvRecord : parser) {
			crc32.update(csvRecord.toString().getBytes());
			a++;
			List<Object> objects = new ArrayList<>();
			if (csvRecord.size() == rsize) {

				for (int i = 0; i < csvRecord.size(); i++) {
					objects.add(csvRecord.get(i));

					
					if (i + 1 == csvRecord.size()) {

						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						objects.add(1);
						objects.add(sdf.format(new Date()).toString());
						objects.add(crc32.getValue());
					}

				}

				daoImpl.save(objects, sql);

			} else {
				errproms++;
				logger.warn(csvRecord.getRecordNumber() + "   " + csvRecord);
				
			}

		}
		daoImpl.after();
		logger.error("filename=" + filename + "  " + "总条数a=" + a + "  " + "errproms=" + errproms);
		return false;

	}

}
