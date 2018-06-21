package com.fy.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.fy.domain.Order;
import com.fy.domain.Product;
import com.fy.util.C3P0Util;
import com.fy.util.ThreadLocalManager;

public class ProductDao {

	public Integer findCount() throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());
		long count=(Long) qr.query("select count(*) from books", new ScalarHandler());
		return (int)count;
	}

	public List<Product> findByPage(Integer curr, Integer pageSize) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());
		return qr.query("select * from books limit ?,?", new BeanListHandler<>(Product.class),(curr-1)*pageSize,pageSize);

	}

	public Product findProductById(String id) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());
		return qr.query("select * from books where id=?", new BeanHandler<>(Product.class),id);

	}

	public List<Product> findCondition(String name, String category, String minPrice, String maxPrice) throws SQLException {
		QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());
		String sql="select * from books where 1=1 ";
		List<String> list=new ArrayList<String>();
		if(!("".equals(name.trim()))) {
			sql+="and name like ?";
			list.add("%"+name.trim()+"%");
		}
		if(!("".equals(category.trim()))) {
			sql+=" and category like ?";
			list.add(category.trim());
		}
		if(!("".equals(maxPrice.trim()))) {
			sql+=" and price < ?";
			list.add(maxPrice.trim());
		}
		if(!("".equals(minPrice.trim()))) {
			sql+=" and price > ?";
			list.add(minPrice.trim());
		}
		return qr.query(sql, new BeanListHandler<>(Product.class),list.toArray());
	}

	public void updateNum(Order order) throws SQLException {
		QueryRunner qr=new QueryRunner();
		Object[][] params=new Object[order.getOrderItems().size()][];
		for (int i = 0; i < params.length; i++) {
			params[i]=new Object[] {order.getOrderItems().get(i).getBuynum(),order.getOrderItems().get(i).getP().getId()};			
		}
		qr.update(ThreadLocalManager.getConnection(), "update books set pnum=pnum-? where id=?",params);
		
	}

}
