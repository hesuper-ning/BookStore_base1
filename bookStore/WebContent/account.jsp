<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--<%@include file="index.jsp" %> --%>
<ul>
   <li><a href="${pageContext.request.contextPath }/edituser.jsp">修改用户信息</a></li>
   <li><a href="${pageContext.request.contextPath }/order.jsp">用户订单信息</a></li>   
</ul>

</body>
</html>