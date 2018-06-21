<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/product/selectCondition" method="post">
  名称:<input type="text" name="name"/> 类别:<input type="text" name="category"/>
  最低价<input type="text" name="minPrice"/>~<input type="text" name="maxPrice"/>最高价
  <input type="submit" value="搜索"/>
</form>
<ul>
   <li><a href="${pageContext.request.contextPath }/cart.jsp">购物车</a></li>
   <li><a href="${pageContext.request.contextPath }/account.jsp">我的账户</a></li>
   <c:if test="${empty user }">
       <li><a href="">注册</a></li>
       <li><a href="">登录</a></li>
   </c:if>
   <c:if test="${not empty user}">
      <li><a href="">${user.username}</a></li>
      <li><a href="${pageContext.request.contextPath }/user/logout">登出</a></li>
   </c:if>
   <li><a href="${pageContext.request.contextPath }/product/listall">商品列表</a></li>
   
</ul>
<ul>
   <c:forEach items="${products}" var="b">
      <li><a href="${pageContext.request.contextPath }/product/findProductById?id=${b.id}"/>${b.name }</a></li>   
   </c:forEach>
</ul>

</body>
</html>