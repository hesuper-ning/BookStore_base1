<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/product/addCart">
  <input type="hidden" name="id" value="${p.id}"/><br/>
  名称:<input type="text" name="name" value="${p.name}" readonly="readonly"/><br/>
  价格:<input type="text" name="price" value="${p.price}" readonly="readonly"/><br/>
  库存:<input type="text" name="pnum" value="${p.pnum}" readonly="readonly"/><br/>
  类别:<input type="text" name="category" value="${p.category}" readonly="readonly"/><br/>
  描述:<input type="text" name="description" value="${p.description}" readonly="readonly"/><br/>
  <input type="submit" value="添加购物车"/>
</form>

</body>
</html>