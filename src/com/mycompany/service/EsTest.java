package com.mycompany.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.mycompany.utils.CsvUtil;
import com.mycompany.utils.DateUtil;

public class EsTest {
	public static void main(String[] args) throws IOException {

		EsTest test02 = new EsTest();

		 for (int i = 1; i <26; i++) {
	
			 test02.resBotnet("a"+i);
			 test02.resC2("b"+i);
			 test02.resCn_ipv4("c"+i);
			 test02.resEmail("d"+i);
			 test02.resFastflux("e"+i);
			 test02.resFqdn("f"+i);
			 test02.resIpv4("g"+i);
			 test02.resIpv6("h"+i);
			 test02.resProxy("i"+i);
			 test02.resTor("j"+i);
			 test02.resUrl("k"+i);
	
		
		
		 }

	}

	 DateUtil dateUtil=new DateUtil();
	public void resIpv4(String filename) {

		CsvUtil csvUtil = new CsvUtil();
		CSVParser parser = csvUtil.parseCsvFile("source/" + filename + ".txt");// 参数

		FileWriter fileWritter;
		try {
			fileWritter = new FileWriter("esdata/" + "ipv4.txt", true);
			BufferedWriter bfWriter = new BufferedWriter(fileWritter);

			for (CSVRecord csvRecord : parser) {
				StringBuffer buffer = new StringBuffer();
				if (csvRecord.size() == 11) {
					String tag = null;
					String ip = null;
					String dtime = null;
					String utime = null;
					String evil = null;
					int viability = 0;
					int credibility = 0;
					String ftimeg = null;
					String county = null;

					if (csvRecord.get(0).contains("=")) {
						tag = "E";
					} else {
						tag = "A";
					}

					ip = csvRecord.get(1).toLowerCase();
					dtime =dateUtil.date(csvRecord.get(2)) .replace(" ", "T") + ".000Z";
					utime = dateUtil.date(csvRecord.get(3)).replace(" ", "T") + ".000Z";
					evil = csvRecord.get(4);
					viability = (int) Double.parseDouble(csvRecord.get(8));
					credibility = (int) Double.parseDouble(csvRecord.get(7));
					ftimeg = dateUtil.date(csvRecord.get(9)).replace(" ", "T") + ".000Z";
					
						county = csvRecord.get(10);
//String str=(dtime.length()+","+utime.length()+","+ftimeg.length());	bfWriter.write(str+"\r\n");
				
					
					buffer.append(tag + " " + ip + " " + dtime + " " + utime + " " + evil + " " + viability + " "
							+ credibility + " " + ftimeg + " " + county);

					bfWriter.write(buffer.toString() + "\r\n");

				} else {
					continue;
				}

			}
		
			bfWriter.flush();
			bfWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void resC2(String filename) {

		CsvUtil csvUtil = new CsvUtil();
		CSVParser parser = csvUtil.parseCsvFile("source/" + filename + ".txt");// 参数

		FileWriter fileWritter;
		try {
			fileWritter = new FileWriter("esdata/" + "c2.txt", true);
			BufferedWriter bfWriter = new BufferedWriter(fileWritter);

			for (CSVRecord csvRecord : parser) {
				StringBuffer buffer = new StringBuffer();
				if (csvRecord.size() == 10) {
					String tag = null;
					String ip = null;
					String dtime = null;
					String utime = null;
					String evil = null;
					int viability = 0;
					int credibility = 0;
					String ftimeg = null;
					String county = null;

					if (csvRecord.get(0).contains("=")) {
						tag = "E";
					} else {
						tag = "A";
					}

					ip = csvRecord.get(1).toLowerCase();
					dtime = dateUtil.date(csvRecord.get(3)).replace(" ", "T")+ ".000Z";
					utime = dtime;
					evil = csvRecord.get(4);
					viability = (int) Double.parseDouble(csvRecord.get(7));
					// credibility=(int)Double.parseDouble(csvRecord.get(#));
					ftimeg = dtime;

					county = "nu";

					buffer.append(tag + " " + ip + " " + dtime + " " + utime + " " + evil + " " + viability + " "
							+ credibility + " " + ftimeg + " " + county);

//					String str=(dtime.length()+","+utime.length()+","+ftimeg.length());	bfWriter.write(str+"\r\n");
					bfWriter.write(buffer.toString() + "\r\n");

				} else {
					continue;
				}

			}
			bfWriter.flush();
			bfWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void resCn_ipv4(String filename) {

		CsvUtil csvUtil = new CsvUtil();
		CSVParser parser = csvUtil.parseCsvFile("source/" + filename + ".txt");// 参数

		FileWriter fileWritter;
		try {
			fileWritter = new FileWriter("esdata/" + "cnipv4.txt", true);
			BufferedWriter bfWriter = new BufferedWriter(fileWritter);

			for (CSVRecord csvRecord : parser) {
				StringBuffer buffer = new StringBuffer();
				if (csvRecord.size() == 15) {
					String tag = null;
					String ip = null;
					String dtime = null;
					String utime = null;
					String evil = null;
					int viability = 0;
					int credibility = 0;
					String ftimeg = null;
					String county = null;

					if (csvRecord.get(0).contains("=")) {
						tag = "E";
					} else {
						tag = "A";
					}

					ip = csvRecord.get(1).toLowerCase();
					dtime = dateUtil.date(csvRecord.get(2)).replace(" ", "T") + ".000Z";
					utime = dateUtil.date(csvRecord.get(3)).replace(" ", "T") + ".000Z";
					evil = csvRecord.get(4);
					viability = (int) Double.parseDouble(csvRecord.get(12));
					credibility = (int) Double.parseDouble(csvRecord.get(7));
					ftimeg =dateUtil.date(csvRecord.get(9)).replace(" ", "T") + ".000Z";
				
						county = csvRecord.get(11);


					buffer.append(tag + " " + ip + " " + dtime + " " + utime + " " + evil + " " + viability + " "
							+ credibility + " " + ftimeg + " " + county);

					bfWriter.write(buffer.toString() + "\r\n");
//					String str=(dtime.length()+","+utime.length()+","+ftimeg.length());	bfWriter.write(str+"\r\n");

				} else {
					continue;
				}

			}
			bfWriter.flush();
			bfWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void resEmail(String filename) {

		CsvUtil csvUtil = new CsvUtil();
		CSVParser parser = csvUtil.parseCsvFile("source/" + filename + ".txt");// 参数

		FileWriter fileWritter;
		try {
			fileWritter = new FileWriter("esdata/" + "email.txt", true);
			BufferedWriter bfWriter = new BufferedWriter(fileWritter);

			for (CSVRecord csvRecord : parser) {
				StringBuffer buffer = new StringBuffer();
				if (csvRecord.size() == 7) {
					String tag = null;
					String ip = null;
					String dtime = null;
					String utime = null;
					String evil = null;
					int viability = 0;
					int credibility = 0;
					String ftimeg = null;
					String county = null;

					if (csvRecord.get(0).contains("=")) {
						tag = "E";
					} else {
						tag = "A";
					}

					ip = csvRecord.get(1).toLowerCase();
					dtime = dateUtil.date(csvRecord.get(2)).replace(" ", "T") + ".000Z";
					utime = dateUtil.date(csvRecord.get(3)).replace(" ", "T") + ".000Z";
					evil = csvRecord.get(4);
					// viability=(int) Double.parseDouble(csvRecord.get(#));
					credibility = (int) Double.parseDouble(csvRecord.get(6));
					ftimeg =dtime;

					county = "nu";

					buffer.append(tag + " " + ip + " " + dtime + " " + utime + " " + evil + " " + viability + " "
							+ credibility + " " + ftimeg + " " + county);

					bfWriter.write(buffer.toString() + "\r\n");
					
//					String str=(dtime.length()+","+utime.length()+","+ftimeg.length());	bfWriter.write(str+"\r\n");

				} else {
					continue;
				}

			}
			bfWriter.flush();
			bfWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void resFastflux(String filename) {

		CsvUtil csvUtil = new CsvUtil();
		CSVParser parser = csvUtil.parseCsvFile("source/" + filename + ".txt");// 参数

		FileWriter fileWritter;
		try {
			fileWritter = new FileWriter("esdata/" + "fastflux.txt", true);
			BufferedWriter bfWriter = new BufferedWriter(fileWritter);

			for (CSVRecord csvRecord : parser) {
				StringBuffer buffer = new StringBuffer();
				if (csvRecord.size() == 10) {
					String tag = null;
					String ip = null;
					String dtime = null;
					String utime = null;
					String evil = null;
					int viability = 0;
					int credibility = 0;
					String ftimeg = null;
					String county = null;

					if (csvRecord.get(0).contains("=")) {
						tag = "E";
					} else {
						tag = "A";
					}

					ip = csvRecord.get(1).toLowerCase();
					dtime =dateUtil.date(csvRecord.get(2)).replace(" ", "T") + ".000Z";
					utime =dtime;
					evil = csvRecord.get(3);
					viability = (int) Double.parseDouble(csvRecord.get(7));
					// credibility=(int)Double.parseDouble(csvRecord.get(#));
					ftimeg =dtime;
					
						county = csvRecord.get(6);

					buffer.append(tag + " " + ip + " " + dtime + " " + utime + " " + evil + " " + viability + " "
							+ credibility + " " + ftimeg + " " + county);

					bfWriter.write(buffer.toString() + "\r\n");
//					String str=(dtime.length()+","+utime.length()+","+ftimeg.length());	bfWriter.write(str+"\r\n");

				} else {
					continue;
				}

			}
			bfWriter.flush();
			bfWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void resFqdn(String filename) {

		CsvUtil csvUtil = new CsvUtil();
		CSVParser parser = csvUtil.parseCsvFile("source/" + filename + ".txt");// 参数

		FileWriter fileWritter;
		try {
			fileWritter = new FileWriter("esdata/" + "fqdn.txt", true);
			BufferedWriter bfWriter = new BufferedWriter(fileWritter);

			for (CSVRecord csvRecord : parser) {
				StringBuffer buffer = new StringBuffer();
				if (csvRecord.size() == 8) {
					String tag = null;
					String ip = null;
					String dtime = null;
					String utime = null;
					String evil = null;
					int viability = 0;
					int credibility = 0;
					String ftimeg = null;
					String county = null;

					if (csvRecord.get(0).contains("=")) {
						tag = "E";
					} else {
						tag = "A";
					}

					ip = csvRecord.get(1).toLowerCase();
					dtime = dateUtil.date(csvRecord.get(2)).replace(" ", "T") + ".000Z";
					utime = dateUtil.date(csvRecord.get(3)).replace(" ", "T") + ".000Z";
					evil = csvRecord.get(4);
					// viability=(int) Double.parseDouble(csvRecord.get(#));
					credibility = (int) Double.parseDouble(csvRecord.get(7));
					ftimeg = dtime;

					county = "nu";

					buffer.append(tag + " " + ip + " " + dtime + " " + utime + " " + evil + " " + viability + " "
							+ credibility + " " + ftimeg + " " + county);

					bfWriter.write(buffer.toString() + "\r\n");
//					String str=(dtime.length()+","+utime.length()+","+ftimeg.length());	bfWriter.write(str+"\r\n");	// System.out.println(buffer.toString());

				} else {
					continue;
				}

			}
			bfWriter.flush();
			bfWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void resBotnet(String filename) {

		CsvUtil csvUtil = new CsvUtil();
		CSVParser parser = csvUtil.parseCsvFile("source/" + filename + ".txt");// 参数

		FileWriter fileWritter;
		try {
			fileWritter = new FileWriter("esdata/" + "botnet.txt", true);
			BufferedWriter bfWriter = new BufferedWriter(fileWritter);

			for (CSVRecord csvRecord : parser) {
				StringBuffer buffer = new StringBuffer();
				if (csvRecord.size() == 11) {
					String tag = null;
					String ip = null;
					String dtime = null;
					String utime = null;
					String evil = null;
					int viability = 0;
					int credibility = 0;
					String ftimeg = null;
					String county = null;

					if (csvRecord.get(0).contains("=")) {
						tag = "E";
					} else {
						tag = "A";
					}

					ip = csvRecord.get(1).toLowerCase();
					dtime = dateUtil.date(csvRecord.get(2)).replace(" ", "T") + ".000Z";
					utime = dateUtil.date(csvRecord.get(3)).replace(" ", "T") + ".000Z";
					evil = csvRecord.get(4);
					viability = (int) Double.parseDouble(csvRecord.get(8));
					credibility = (int) Double.parseDouble(csvRecord.get(7));
					ftimeg = dateUtil.date(csvRecord.get(9)).replace(" ", "T") + ".000Z";
				
						county = csvRecord.get(10);

					buffer.append(tag + " " + ip + " " + dtime + " " + utime + " " + evil + " " + viability + " "
							+ credibility + " " + ftimeg + " " + county);

					bfWriter.write(buffer.toString() + "\r\n");
//					String str=(dtime.length()+","+utime.length()+","+ftimeg.length());	bfWriter.write(str+"\r\n");

				} else {
					continue;
				}

			}
			bfWriter.flush();
			bfWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void resIpv6(String filename) {

		CsvUtil csvUtil = new CsvUtil();
		CSVParser parser = csvUtil.parseCsvFile("source/" + filename + ".txt");// 参数

		FileWriter fileWritter;
		try {
			fileWritter = new FileWriter("esdata/" + "ipv6.txt", true);
			BufferedWriter bfWriter = new BufferedWriter(fileWritter);

			for (CSVRecord csvRecord : parser) {
				StringBuffer buffer = new StringBuffer();
				if (csvRecord.size() == 8) {
					String tag = null;
					String ip = null;
					String dtime = null;
					String utime = null;
					String evil = null;
					int viability = 0;
					int credibility = 0;
					String ftimeg = null;
					String county = null;

					if (csvRecord.get(0).contains("=")) {
						tag = "E";
					} else {
						tag = "A";
					}

					ip = csvRecord.get(1).toLowerCase();
					dtime = dateUtil.date(csvRecord.get(2)).replace(" ", "T") + ".000Z";
					utime = dateUtil.date(csvRecord.get(3)).replace(" ", "T") + ".000Z";
					evil = csvRecord.get(4);
					// viability = (int) Double.parseDouble(csvRecord.get(#));
					credibility = (int) Double.parseDouble(csvRecord.get(7));
					ftimeg =dtime;

					county = "nu";

					buffer.append(tag + " " + ip + " " + dtime + " " + utime + " " + evil + " " + viability + " "
							+ credibility + " " + ftimeg + " " + county);

					bfWriter.write(buffer.toString() + "\r\n");
//					String str=(dtime.length()+","+utime.length()+","+ftimeg.length());	bfWriter.write(str+"\r\n");

				} else {
					continue;
				}

			}
			bfWriter.flush();
			bfWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void resProxy(String filename) {

		CsvUtil csvUtil = new CsvUtil();
		CSVParser parser = csvUtil.parseCsvFile("source/" + filename + ".txt");// 参数

		FileWriter fileWritter;
		try {
			fileWritter = new FileWriter("esdata/" + "proxy.txt", true);
			BufferedWriter bfWriter = new BufferedWriter(fileWritter);

			for (CSVRecord csvRecord : parser) {
				StringBuffer buffer = new StringBuffer();
				if (csvRecord.size() == 10) {
					String tag = null;
					String ip = null;
					String dtime = null;
					String utime = null;
					String evil = null;
					int viability = 0;
					int credibility = 0;
					String ftimeg = null;
					String county = null;

					if (csvRecord.get(0).contains("=")) {
						tag = "E";
					} else {
						tag = "A";
					}

					ip = csvRecord.get(1).toLowerCase();
					dtime = dateUtil.date(csvRecord.get(2)).replace(" ", "T") + ".000Z";
					utime = dtime;
					evil = csvRecord.get(3);
					viability = (int) Double.parseDouble(csvRecord.get(7));
					// credibility = (int) Double.parseDouble(csvRecord.get(#));
					ftimeg = dtime;
				
						county = csvRecord.get(6);

					buffer.append(tag + " " + ip + " " + dtime + " " + utime + " " + evil + " " + viability + " "
							+ credibility + " " + ftimeg + " " + county);

					bfWriter.write(buffer.toString() + "\r\n");
//					String str=(dtime.length()+","+utime.length()+","+ftimeg.length());	bfWriter.write(str+"\r\n");

				} else {
					continue;
				}

			}
			bfWriter.flush();
			bfWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void resUrl(String filename) {

		CsvUtil csvUtil = new CsvUtil();
		CSVParser parser = csvUtil.parseCsvFile("source/" + filename + ".txt");// 参数

		FileWriter fileWritter;
		try {
			fileWritter = new FileWriter("esdata/" + "url.txt", true);
			BufferedWriter bfWriter = new BufferedWriter(fileWritter);

			for (CSVRecord csvRecord : parser) {
				StringBuffer buffer = new StringBuffer();
				if (csvRecord.size() == 8) {
					String tag = null;
					String ip = null;
					String dtime = null;
					String utime = null;
					String evil = null;
					int viability = 0;
					int credibility = 0;
					String ftimeg = null;
					String county = null;

					if (csvRecord.get(0).contains("=")) {
						tag = "E";
					} else {
						tag = "A";
					}

					ip = csvRecord.get(1).toLowerCase();
					dtime = dateUtil.date(csvRecord.get(2)).replace(" ", "T") + ".000Z";
					utime = dateUtil.date(csvRecord.get(3)).replace(" ", "T") + ".000Z";
					evil = csvRecord.get(4);
					viability = (int) Double.parseDouble(csvRecord.get(6));
					credibility = (int) Double.parseDouble(csvRecord.get(7));
					ftimeg = dtime;

					county = "nu";

					buffer.append(tag + " " + ip + " " + dtime + " " + utime + " " + evil + " " + viability + " "
							+ credibility + " " + ftimeg + " " + county);

					bfWriter.write(buffer.toString() + "\r\n");
//					String str=(dtime.length()+","+utime.length()+","+ftimeg.length());	bfWriter.write(str+"\r\n");

				} else {
					continue;
				}

			}
			bfWriter.flush();
			bfWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void resTor(String filename) {

		CsvUtil csvUtil = new CsvUtil();
		CSVParser parser = csvUtil.parseCsvFile("source/" + filename + ".txt");// 参数

		FileWriter fileWritter;
		try {
			fileWritter = new FileWriter("esdata/" + "tor.txt", true);
			BufferedWriter bfWriter = new BufferedWriter(fileWritter);

			for (CSVRecord csvRecord : parser) {
				StringBuffer buffer = new StringBuffer();
				if (csvRecord.size() == 7) {
					String tag = null;
					String ip = null;
					String dtime = null;
					String utime = null;
					String evil = null;
					int viability = 0;
					int credibility = 0;
					String ftimeg = null;
					String county = null;

					if (csvRecord.get(0).contains("=")) {
						tag = "E";
					} else {
						tag = "A";
					}

					ip = csvRecord.get(1).toLowerCase();
				
						dtime = dateUtil.date(csvRecord.get(2)).replace(" ", "T") + ".000Z";
						utime =dtime;
						ftimeg = dtime;
					
					
					evil = csvRecord.get(3);
					// viability = (int) Double.parseDouble(csvRecord.get(#));
					// credibility = (int) Double.parseDouble(csvRecord.get(#));
					
				
						county = csvRecord.get(6);

					buffer.append(tag + " " + ip + " " + dtime + " " + utime + " " + evil + " " + viability + " "
							+ credibility + " " + ftimeg + " " + county);

					bfWriter.write(buffer.toString() + "\r\n");
//					String str=(dtime.length()+","+utime.length()+","+ftimeg.length());	bfWriter.write(str+"\r\n");

				} else {
					continue;
				}

			}
			bfWriter.flush();
			bfWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
