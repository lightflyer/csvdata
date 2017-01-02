package com.mycompany.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;




import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;


public class CsvUtil {



	InputStream fileStream;
	Reader decoder = null;
	CSVParser parser = null;

/**
 * 将临时csv文件解析成CSVParser对象
 * @param tmpfilename 读取的临时csv文件名
 * @return
 */
public	CSVParser parseCsvFile(String tmpfilename) {

		File csvFile = new File(tmpfilename);

		try {
			fileStream = new FileInputStream(csvFile);
			decoder = new InputStreamReader(fileStream, Charset.forName("UTF-8"));

			parser = new CSVParser(decoder, CSVFormat.DEFAULT);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	
		return parser;
		
	}

}
