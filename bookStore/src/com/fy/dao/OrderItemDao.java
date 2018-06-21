package com.fy.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.fy.domain.Order;
import com.fy.util.ThreadLocalManager;

public class OrderItemDao {

	public void addOrderItem(Order order) throws SQLException {
		QueryRunner qr=new QueryRunner();
		Object[][] params=new Object[order.getOrderItems().size()][];
		for (int i = 0; i < params.length; i++) {
			params[i]=new Object[] {order.getId(),order.getOrderItems().get(i).getP().getId(),order.getOrderItems().get(i).getBuynum()};
			
		}
		qr.update(ThreadLocalManager.getConnection(), "insert into order_item values(?,?,?)",params);
	}

}
