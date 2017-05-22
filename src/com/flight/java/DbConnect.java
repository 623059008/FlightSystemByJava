package com.flight.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
	private static final String url = "jdbc:mysql://127.0.0.1/test?useUnicode=true&characterEncoding=utf-8";
	private static final String name = "com.mysql.jdbc.Driver";
	private static final String user = "root";
	private static final String password = "������ݿ�����";

	private Connection conn = null;

	public DbConnect() {
		try {
			Class.forName(name);// ָ����������
			this.conn = DriverManager.getConnection(url, user, password);// ��ȡ����
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection Get_Connection() {
		if (conn != null) {
			return this.conn;
		} else {
			System.err.println("Null Connection!");
			return this.conn;
		}

	}

	public void close() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
