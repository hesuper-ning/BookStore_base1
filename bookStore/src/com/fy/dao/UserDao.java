package com.fy.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.fy.domain.User;
import com.fy.util.C3P0Util;

public class UserDao {
	public void  addUser(User user) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());
		qr.update("insert into user(id,username,pwd) values(?,?,?)",user.getId(),user.getUsername(),user.getPwd());
	}

	public User findUserByName(String username) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());
		return qr.query("select * from user where username=?", new BeanHandler<>(User.class),username);
	}

	public User findUser(String username, String pwd) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());
		return qr.query("select * from user where username=? and pwd=?", new BeanHandler<>(User.class),username,pwd);
	}

	public void updateUser(User user) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());
		qr.update("update user set username=?,pwd=? where id=?", user.getUsername(),user.getPwd(),user.getId());
	}

}
