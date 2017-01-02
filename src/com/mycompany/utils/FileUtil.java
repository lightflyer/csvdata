package com.mycompany.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileUtil {
/**
 * 处理原文件中非标准格式数据，写入临时csv文件
 * @param filePath
 * @param tmpfilename
 */
	public void readAndCopy(String filePath, String tmpfilename) {
		String encoding = "utf-8";

		File csvfile;
		FileWriter fileWritter;
		InputStreamReader read;
		BufferedReader bufferedReader;
		String lineTxt = null;
		String sublinetxt = null;
		BufferedWriter bfdWriter;
		StringBuffer buffer;

		File tmpfile = clearTmpFile(tmpfilename);

		try {
			fileWritter = new FileWriter(tmpfile, true);

			csvfile = new File(filePath);

			if (csvfile.isFile() && csvfile.exists()) { // 判断文件是否存在
				read = new InputStreamReader(new FileInputStream(csvfile),encoding);// 考虑到编码格式
				bufferedReader = new BufferedReader(read);
				bfdWriter = new BufferedWriter(fileWritter);

				while ((lineTxt = bufferedReader.readLine()) != null) {

					sublinetxt = lineTxt;
					buffer = new StringBuffer();

					while (sublinetxt.contains("['")) {
						buffer.append(sublinetxt.substring(0, sublinetxt.indexOf("['")));// 获取第一个"['"之前的内容
						String field = sublinetxt.substring(sublinetxt.indexOf("['"), sublinetxt.indexOf("']") + 2);// 获取包含"['']"的字段内容
						if (field.contains(",")) {
							field = field.replace(",", "_");// 替换"['']"中包含的","
							field =field.replace("'", "");//去掉"'"
							field=field.replace(" ", "");//去掉""
							buffer.append(field.substring(1, field.length()-1));// 替换后去掉"[]"

						} else {
							buffer.append(field.substring(2, field.length()-2));// 直接去掉"['']"
						}

						sublinetxt = sublinetxt.substring(sublinetxt.indexOf("']") + 2);//更新一行字符串的内容后继续判断是否有需要替换的
					}
					buffer.append(sublinetxt);

					// System.out.println(buffer.toString());//一行内容替换完成

					bfdWriter.write(""+buffer.toString().replaceAll("22016-", "2016-") + "\r\n");
					bfdWriter.flush();

				}

				read.close();
				bfdWriter.close();

			} else {
				System.out.println("找不到指定的文件");
			}

		} catch (IOException e) {
			System.out.println("文件读写io错误");
			e.printStackTrace();
		}

	}

	private File clearTmpFile(String tmpfilename) {
		BufferedWriter bufferedWriter;
		FileWriter fileWritter;
		File tmpfile = new File("source/" + tmpfilename + ".txt");
		if (tmpfile.exists()) {

			try {
				fileWritter = new FileWriter(tmpfile, false);
				bufferedWriter = new BufferedWriter(fileWritter);
				bufferedWriter.write("");
				bufferedWriter.flush();
			} catch (IOException e) {
				// 清空临时文件内容失败
				e.printStackTrace();
			}
		} else {
			try {
				tmpfile.createNewFile();
			} catch (IOException e) {
				// 创建临时文件失败
				e.printStackTrace();
			}

		}
		return tmpfile;
	}

	public  void getFiles(String filePath) {
		  ArrayList<String> filelist = new ArrayList<String>();
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				/*
				 * 递归调用
				 */
				getFiles(file.getAbsolutePath());
				filelist.add(file.getAbsolutePath());
				System.out.println("显示" + filePath + "下所有子目录及其文件" + file.getAbsolutePath());
			} else {
				System.out.println("显示" + filePath + "下所有子目录" + file.getAbsolutePath());
			}
		}
	}
	
	
	
}
