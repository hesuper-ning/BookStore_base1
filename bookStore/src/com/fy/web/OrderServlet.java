package com.fy.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.fy.domain.Order;
import com.fy.domain.OrderItem;
import com.fy.domain.Product;
import com.fy.domain.User;
import com.fy.service.OrderService;
import com.fy.util.UUIDUtil;

public class OrderServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getRequestURI();
		String method=path.substring(path.lastIndexOf("/")+1);
		if(!("".equals(method))) {
			if("createOrder".equals(method)) {
				createOrder(req,resp);
			}
		}
	}

	private void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Order order=new Order();
		HttpSession session=req.getSession();
		try {
			BeanUtils.populate(order, req.getParameterMap());
			order.setId(UUIDUtil.getUUID());
			order.setUser((User)session.getAttribute("user"));			
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<Product,String> cart=(Map<Product, String>) session.getAttribute("cart");
		List<OrderItem> orderItems=new ArrayList<>();
		for (Product p : cart.keySet()) {
			OrderItem oi=new OrderItem();
			oi.setP(p);
			oi.setOrder(order);
			oi.setBuynum(Integer.parseInt(cart.get(p)));
			orderItems.add(oi);
		}
		order.setOrderItems(orderItems);
		OrderService os=new OrderService();
		os.addOrder(order);
		req.getRequestDispatcher("/end.jsp").forward(req, resp);
	}

}
