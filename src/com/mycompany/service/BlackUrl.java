package com.mycompany.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;

import com.mycompany.utils.DataSource;

public class BlackUrl {

	static Logger logger = Logger.getLogger(BlackUrl.class);

	public static void main(String[] args) {
		
		BlackUrl blackurl=new BlackUrl();
		blackurl.latestdata();
	}
	
	
	/**
	 * 提取历史数据写入URL历史表
	 */
	public void olddata(){

System.out.println(new Date());
		DataSource dataSource = new DataSource();
		Connection connection = dataSource.getConnection();
		String sql1 = "select b.evil_url  url, count(*) number FROM black_url_old b GROUP BY b.evil_url ORDER BY 2 DESC";
		String sql2 = "SELECT * from black_url_old WHERE evil_url = ? ORDER BY discovery_time DESC;";
		String sql3 = "INSERT INTO black_url(relate_tag,evil_url, discovery_time,update_time,evil_behavior,source,viability,credibility,status,create_time,evil_url_crc,version,evil_url_create_time)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			Statement statement = connection.createStatement();
			PreparedStatement ps = connection.prepareStatement(sql2);
			PreparedStatement ps2 = connection.prepareStatement(sql3);

			ResultSet resultSet = statement.executeQuery(sql1);
			while (resultSet.next()) {
				int number = resultSet.getInt("number");
				String url = resultSet.getString("url");

				// 取得数据库中的数据
				// System.out.println(" " + number + " " + url + " " );
				// logger.info(" " + number + " " + url + " " );
				// System.out.println("---");
				if (number>1) {
					ps.setString(1, url);
					ResultSet rs = ps.executeQuery();

					int i = 7;
					while (rs.next()) {

						if (i < 7) {
							// int id=rs.getInt("id");
							String relatetag = rs.getString("relate_tag");
							String evilurl = rs.getString("evil_url");
							String discoverytime = rs.getString("discovery_time");
							String updatetime = rs.getString("update_time");
							String evilbehavior = rs.getString("evil_behavior");
							String source = rs.getString("source");
							int viability = rs.getInt("viability");
							int credibility = rs.getInt("credibility");
							int status = rs.getInt("status");
							String createtime = rs.getString("create_time");
							String evil_crc = rs.getString("evil_url_crc");

							ps2.setObject(1, relatetag);
							ps2.setObject(2, evilurl);
							ps2.setObject(3, discoverytime);
							ps2.setObject(4, updatetime);
							ps2.setObject(5, evilbehavior);
							ps2.setObject(6, source);
							ps2.setObject(7, viability);
							ps2.setObject(8, credibility);
							ps2.setObject(9, status);
							ps2.setObject(10, createtime);
							ps2.setObject(11, evil_crc);
							ps2.setObject(12, i);
							ps2.setObject(13, new Timestamp(System.currentTimeMillis()));
							ps2.execute();
							// System.out.println(a);
						}
						i--;
					}

				} else {

				}

			}

			// 关闭连接和声明
			ps2.close();
			ps.close();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println(new Date());
	
	}
	
	
	
	/**
	 * 提取最新版数据写入black_URL表
	 */
	public void latestdata(){

		System.out.println(new Date());
				DataSource dataSource = new DataSource();
				Connection connection = dataSource.getConnection();
				String sql1 = "select b.evil_url  url, count(*) number FROM black_url_old b GROUP BY b.evil_url ORDER BY 2 DESC";
				String sql2 = "SELECT * from black_url_old WHERE evil_url = ? ORDER BY discovery_time DESC;";
				String sql3 = "INSERT INTO black_url(relate_tag,evil_url, discovery_time,update_time,evil_behavior,source,viability,credibility,status,create_time,evil_url_crc,version,evil_url_create_time)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				try {
					Statement statement = connection.createStatement();
					PreparedStatement ps = connection.prepareStatement(sql2);
					PreparedStatement ps2 = connection.prepareStatement(sql3);

					ResultSet resultSet = statement.executeQuery(sql1);
					while (resultSet.next()) {
						int number = resultSet.getInt("number");
						String url = resultSet.getString("url");

						// 取得数据库中的数据
						// System.out.println(" " + number + " " + url + " " );
						// logger.info(" " + number + " " + url + " " );
						// System.out.println("---");
						if (number>0) {
							ps.setString(1, url);
							ResultSet rs = ps.executeQuery();

							int i = 7;
							while (rs.next()) {

								if (i == 7) {
									// int id=rs.getInt("id");
									String relatetag = rs.getString("relate_tag");
									String evilurl = rs.getString("evil_url");
									String discoverytime = rs.getString("discovery_time");
									String updatetime = rs.getString("update_time");
									String evilbehavior = rs.getString("evil_behavior");
									String source = rs.getString("source");
									int viability = rs.getInt("viability");
									int credibility = rs.getInt("credibility");
									int status = rs.getInt("status");
									String createtime = rs.getString("create_time");
									String evil_crc = rs.getString("evil_url_crc");

									ps2.setObject(1, relatetag);
									ps2.setObject(2, evilurl);
									ps2.setObject(3, discoverytime);
									ps2.setObject(4, updatetime);
									ps2.setObject(5, evilbehavior);
									ps2.setObject(6, source);
									ps2.setObject(7, viability);
									ps2.setObject(8, credibility);
									ps2.setObject(9, status);
									ps2.setObject(10, createtime);
									ps2.setObject(11, evil_crc);
									ps2.setObject(12, i);
									ps2.setObject(13, new Timestamp(System.currentTimeMillis()));
									ps2.execute();
									// System.out.println(a);
								}
								i--;
							}

						} else {

						}

					}

					// 关闭连接和声明
					ps2.close();
					ps.close();
					statement.close();
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
					try {
						connection.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				System.out.println(new Date());
			
			}
			
	
}
