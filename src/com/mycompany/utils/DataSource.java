package com.mycompany.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
	Connection con = null;
/**
 * 获取数据库连接
 * @return
 */
	public Connection getConnection() {
		InputStream inputStream;
		try {
			inputStream = new BufferedInputStream(new FileInputStream("config/jdbc.properties"));
			Properties p = new Properties();
			p.load(inputStream);
			String driver=p.getProperty("driver");
			String url=p.getProperty("url");
			String username=p.getProperty("username");
			String password=p.getProperty("password");
			
		
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// 找不到驱动
			e.printStackTrace();
		} catch (SQLException e) {
			// 获取连接失败
			e.printStackTrace();
		}
		catch (FileNotFoundException e1) {
			//找不到配置文件
			e1.printStackTrace();
		} catch (IOException e) {
		//读取配置文件出错
			e.printStackTrace();
		}
		return con;
	}
}
