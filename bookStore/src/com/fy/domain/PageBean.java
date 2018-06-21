package com.fy.domain;

import java.util.List;

public class PageBean {
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalPage;
	private Integer totalCount;
	private List<Product> products;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public PageBean(Integer currentPage, Integer pageSize, Integer totalPage, Integer totalCount,
			List<Product> products) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
		this.setProducts(products);
	}
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	

}
