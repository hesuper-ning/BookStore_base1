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

<ul>
   <c:forEach items="${pb.products }" var="b">
      <li><a href="${pageContext.request.contextPath }/product/findProductById?id=${b.id}"/>${b.name }</a></li>   
   </c:forEach>
</ul>
<ul>
  <li><a href="${pageContext.request.contextPath }/product/listall?currentPage=${pb.currentPage==1?1:pb.currentPage-1}">上一页</a></li>
  <li>${pb.currentPage }/${pb.totalPage}</li>
  <li><a href="${pageContext.request.contextPath }/product/listall?currentPage=${pb.currentPage==pb.totalPage?pb.totalPage:pb.currentPage+1}">下一页</a></li>
</ul>
</body>
</html>