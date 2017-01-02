package com.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.mycompany.utils.DataSource;

public class GeneriDaoImpl<T> implements GenericDao<T> {
	Connection con = null;
	PreparedStatement ps = null;

	public  void before() {// 获取数据库连接
		DataSource datasource = new DataSource();
		con = datasource.getConnection();
	}

	public boolean save(List<T> list, String sql) {

		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				ps.setObject(i + 1, list.get(i));
			}

			ps.executeUpdate();
			con.commit();// 提交
//			System.out.println("true");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error");
			try {
				con.rollback();
			} catch (SQLException e1) {
				// 回滚失败
				e1.printStackTrace();
				System.out.println("error");
			}

			return false;
		}

	}

	public void after() {// 关闭相关的连接，释放资源
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	public GeneriDaoImpl() {
		super();
	}

}
