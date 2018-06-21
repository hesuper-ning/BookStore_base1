package com.fy.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		MyRequest req=new MyRequest(httpRequest);
		HttpServletResponse resp=(HttpServletResponse) response;
		resp.setContentType("text/html;charset=UTF-8");
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

class MyRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;

	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request=request;
	}
	
	@Override
	public String getParameter(String name) {
		Map<String, String[]> map=getParameterMap();
		String[] values=map.get(name);
		if(values==null) {
			return null;
		}
		return values[0];
	}
	
	@Override
	public String[] getParameterValues(String name) {
		Map<String, String[]> map=getParameterMap();
		String[] values=map.get(name);
//		if(values==null) {
//			return null;
//		}
		return values;
	}
	private boolean flag=true;
	
	@Override
	public Map<String, String[]> getParameterMap() {
		String method=request.getMethod();
		if(method.equalsIgnoreCase("post")) {
			try {
				request.setCharacterEncoding("UTF-8");
				return request.getParameterMap();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equalsIgnoreCase("get")) {
			Map<String, String[]> parameterMap = request.getParameterMap();
			if(flag) {
				for (String parameterName: parameterMap.keySet()) {
					String[] values=parameterMap.get(parameterName);
					if(values!=null) {
						for (int i = 0; i < values.length; i++) {
							try {
								values[i]=new String(values[i].getBytes("iso-8859-1"),"UTF-8");
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				flag=false;
			}
			return parameterMap;

		}
		return super.getParameterMap();
	}
	
}
