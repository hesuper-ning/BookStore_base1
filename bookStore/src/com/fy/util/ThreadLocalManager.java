package com.fy.util;

import java.sql.Connection;
import java.sql.SQLException;

public class ThreadLocalManager {
	private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
	public static Connection getConnection() throws SQLException {
		Connection conn=tl.get();
		if(conn==null) {
			conn=C3P0Util.getConnection();
			tl.set(conn);
		}
		return conn;
	}
	
	public static void startTransaction() throws SQLException {
		getConnection().setAutoCommit(false);
	}
	
	public static void commit() throws SQLException {
		getConnection().commit();
	}
	
	public static void rollback() throws SQLException {
		getConnection().rollback();
	}
	
	public static void close() throws SQLException {
		getConnection().close();
	}

}
