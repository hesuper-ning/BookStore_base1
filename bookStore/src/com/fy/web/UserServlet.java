package com.fy.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fy.domain.User;
import com.fy.service.UserService;
import com.fy.util.UUIDUtil;

public class UserServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getRequestURI();
		String method=path.substring(path.lastIndexOf("/")+1);
		if(!("".equals(method))) {
			if("ckUsername".equals(method)) {
				ckUsername(req,resp);
			}
			if("regist".equals(method)) {
				regist(req,resp);
			}
			if("login".equals(method)) {
				try {
					login(req,resp);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if("logout".equals(method)) {
				logout(req,resp);
			}
			if("updateUser".equals(method)) {
				updateUser(req,resp);
			}
		}		
	}

	private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user=new User();
        try {
			BeanUtils.populate(user, req.getParameterMap());
			UserService us=new UserService();
			us.updateUser(user);
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath()+"/login.jsp");
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.getSession().invalidate();
		resp.sendRedirect(req.getContextPath()+"/index.jsp");		
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		String username=req.getParameter("username");
		String pwd=req.getParameter("pwd");
		UserService us=new UserService();
		User user=us.login(username,pwd);
		if(user!=null) {
			req.getSession().setAttribute("user", user);
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		
	}

	private void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ckCode=req.getParameter("ckCode");
		String checkcode=(String) req.getSession().getAttribute("checkcode");
		if(!(checkcode.equals(ckCode))) {
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			return ;
		}
		User user=new User();
		try {
			BeanUtils.populate(user, req.getParameterMap());
			user.setId(UUIDUtil.getUUID());
			UserService us=new UserService();
			us.regist(user);
			req.getRequestDispatcher("/registsuccess.jsp").forward(req, resp);
		} catch (Exception e) {
			req.getRequestDispatcher("/regist.jsp").forward(req, resp);
			e.printStackTrace();
		}	
		
	}

	private void ckUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username=req.getParameter("username");
		UserService us=new UserService();
		User u=us.findUserByName(username);
		if(u==null) {
			resp.getWriter().print("false");
		}else {
			resp.getWriter().print("true");

		}
	}

}
