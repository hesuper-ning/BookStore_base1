<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function changeNum(id, num, totalCount) {
		num = parseInt(num);
		totalCount = parseInt(totalCount);

		if (num < 1) {
			if (confirm("是否确认要删除此商品？")) {
				num = 0;
			} else {
				num = 1;
			}
		}

		if (num > totalCount) {
			alert("购买数量不能大于库存数量！");
			num = totalCount;
		}
		location.href="${pageContext.request.contextPath}/product/changeNum?id="+id+"&num="+num;

	}
</script>
</head>
<body>
	<table>
		<tr>
			<td>名称</td>
			<td>价格</td>
			<td>数量</td>
			<td>库存</td>
			<td>小计</td>
			<td>取消</td>
		</tr>
		<c:forEach items="${cart }" var="c">
			<td>${c.key.name }</td>
			<td>${c.key.price }</td>
			<td><input type="button" value="-"
				onclick="changeNum('${c.key.id}','${c.value-1 }','${c.key.pnum }')" />
				<input type="text" name="num" value="${c.value}" /> <input
				type="button" value="+"
				onclick="changeNum('${c.key.id}','${c.value+1 }','${c.key.pnum }')" />
			</td>
			<td>${c.key.pnum }</td>
			<td>${c.value}*${c.key.price }</td>
			<td><a href="${pageContext.request.contextPath}/product/changeNum?id=${c.key.id}&num=0">取消</a></td>
		</c:forEach>
	</table>

</body>
</html>