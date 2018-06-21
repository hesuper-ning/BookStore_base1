package com.fy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dsna.util.images.ValidateCode;

public class CkcodeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ValidateCode vc=new ValidateCode(110,25,4,9);
		req.getSession().setAttribute("checkcode",vc.getCode());
		vc.write(resp.getOutputStream());		
	}

}
