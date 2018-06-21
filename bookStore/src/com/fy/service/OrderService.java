package com.fy.service;

import java.sql.SQLException;

import com.fy.dao.OrderDao;
import com.fy.dao.OrderItemDao;
import com.fy.dao.ProductDao;
import com.fy.domain.Order;
import com.fy.util.ThreadLocalManager;

public class OrderService {
	OrderDao oDao=new OrderDao();
	OrderItemDao oiDao=new OrderItemDao();
	ProductDao pDao=new ProductDao();

	public void addOrder(Order order) {
		try {
			ThreadLocalManager.startTransaction();
			oDao.addOrder(order);
			oiDao.addOrderItem(order);
			pDao.updateNum(order);
			ThreadLocalManager.commit();
		} catch (SQLException e) {
			try {
				ThreadLocalManager.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				ThreadLocalManager.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
