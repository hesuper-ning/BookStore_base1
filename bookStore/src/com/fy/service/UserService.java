package com.fy.service;

import java.sql.SQLException;

import com.fy.dao.UserDao;
import com.fy.domain.User;

public class UserService {
	UserDao dao=new UserDao();
	public void regist(User user) throws SQLException {
		dao.addUser(user);
	}
	public User findUserByName(String username) {
		try {
			return dao.findUserByName(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public User login(String username, String pwd){
		try {
			return dao.findUser(username,pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void updateUser(User user) {
		try {
			dao.updateUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
