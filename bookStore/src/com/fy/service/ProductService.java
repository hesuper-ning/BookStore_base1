package com.fy.service;

import java.sql.SQLException;
import java.util.List;

import com.fy.dao.ProductDao;
import com.fy.domain.PageBean;
import com.fy.domain.Product;

public class ProductService {
	ProductDao dao=new ProductDao();

	public PageBean listPage(Integer curr, Integer pageSize) throws SQLException {
		Integer totalCount=dao.findCount();
		Integer totalPage=(totalCount%pageSize==0)?(totalCount/pageSize):(totalCount/pageSize+1);
		List<Product> products=dao.findByPage(curr,pageSize);
		PageBean pb=new PageBean(curr, pageSize, totalPage, totalCount, products);
		return pb;
	}

	public Product findProductById(String id) {
		try {
			return dao.findProductById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Product> findCondition(String name, String category, String minPrice, String maxPrice) {
		try {
			return dao.findCondition(name,category,minPrice,maxPrice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
