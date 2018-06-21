package com.fy.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.fy.domain.Order;
import com.fy.util.ThreadLocalManager;

public class OrderDao {

	public void addOrder(Order order) throws SQLException {
		QueryRunner qr=new QueryRunner();
		qr.update(ThreadLocalManager.getConnection(), "insert into order values(?,?,?,?,?,?)",order.getId(),order.getMoney(),order.getReceiverAddress(),order.getReceiverName(),order.getReceiverPhone(),order.getUser().getId());
		
	}

}
