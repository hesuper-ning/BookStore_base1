package com.fy.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fy.domain.PageBean;
import com.fy.domain.Product;
import com.fy.service.ProductService;

public class ProductServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getRequestURI();
		String method=path.substring(path.lastIndexOf("/")+1);
		if(!("".equals(method))) {
			if("listall".equals(method)) {
				try {
					listall(req,resp);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if("findProductById".equals(method)) {
				findProductById(req,resp);
			}
			if("addCart".equals(method)) {
				addCart(req,resp);
			}
			if("changeNum".equals(method)) {
				changeNum(req,resp);
			}
			if("selectCondition".equals(method)) {
				selectCondition(req,resp);
			}
		}
	}

	private void selectCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String category=req.getParameter("category");
        String minPrice=req.getParameter("minPrice");
        String maxPrice=req.getParameter("maxPrice");
		ProductService ps=new ProductService();
		List<Product> products= ps.findCondition(name,category,minPrice,maxPrice);
        req.setAttribute("products", products);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	private void changeNum(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		String num=req.getParameter("num");
		ProductService ps=new ProductService();
		Product p=ps.findProductById(id);
		HttpSession session=req.getSession();
		Map<Product, String> cart=(Map<Product, String>) session.getAttribute("cart");
		if("0".equals(num)) {
			cart.remove(p);
		}
		if(cart.containsKey(p)) {
			cart.put(p, num);
		}
		req.getRequestDispatcher("/cart.jsp").forward(req, resp);
	}

	private void addCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		ProductService ps=new ProductService();
		Product p=ps.findProductById(id);
		HttpSession session=req.getSession();
		Map<Product,String> cart=(Map<Product, String>) session.getAttribute("cart");
		int num=1;
		if(cart==null) {
			cart=new HashMap<Product, String>();
		}
		if(cart.containsKey(p)) {
			num=Integer.parseInt(cart.get(p))+1;
		}
		cart.put(p, num+"");
		session.setAttribute("cart", cart);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);		
	}

	private void findProductById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		ProductService ps=new ProductService();
		Product p=ps.findProductById(id);
		req.setAttribute("p", p);
		req.getRequestDispatcher("/product_detail.jsp").forward(req, resp);
		
	}

	private void listall(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		Integer pageSize=4;
		Integer currentpage=1;
		String curr=req.getParameter("currentPage");
		if(curr==null) {
			curr=currentpage+"";
		}
		ProductService ps=new ProductService();
		PageBean pb=ps.listPage(Integer.parseInt(curr),pageSize);
		req.setAttribute("pb", pb);
		req.getRequestDispatcher("/product_list.jsp").forward(req, resp);
	}

}
