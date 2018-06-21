<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
</script>
</head>
<body>
<form action="${pageContext.request.contextPath }/order/createOrder">
	<table>
		<tr>
			<td>名称</td>
			<td>价格</td>
			<td>数量</td>
			<td>小计</td>
		</tr>
		<c:set var="count" value="0"></c:set>
		<c:forEach items="${cart }" var="c">
			<td>${c.key.name }</td>
			<td>${c.key.price }</td>
			<td>
				<input type="text" name="num" value="${c.value}" />
			</td>
			<td>${c.value*c.key.price }</td>
			<c:set var="count" value="${count+c.value*c.key.price }"></c:set>
		</c:forEach>
		<tr>
		  <td></td>
			<td></td>
			<td></td>
			<td>合计:${count }元 <input type="hidden" name="money"/></td>
		</tr>
	</table>
	地址：<input type="text" name="receiverAddress"/><br/>
	收货人：<input type="text" name="receiverName"/><br/>
	联系方式：<input type="text" name="receiverPhone"/><br/>
	<input type="submit" value="提交订单"/>
</form>
</body>
</html>