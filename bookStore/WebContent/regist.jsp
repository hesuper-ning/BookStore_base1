<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
  function changeImg(){
  	var im=document.getElementsByTagName("img")[0];
  	im.src="${pageContext.request.contextPath}/servlet/ckcode?time="+new Date().getTime();
  }
  function testUsername(){
	  var username=document.getElementsByName("username")[0].value;
	  var xhr=new XMLHttpRequest();
	  xhr.onreadystatechange=function(){
		  if(xhr.readyState==4){
			  if(xhr.status==200){
				  var msg=document.getElementById("msg");
				  if(xhr.responseText=="true"){
					  msg.innerHTML="<font color='red'>用户名已存在</font>";
				  }else{
					  msg.innerHTML="<font color='green'>用户名可用</font>";
				  }
			  }
		  }
	  }
	  xhr.open("get", "${pageContext.request.contextPath}/user/ckUsername?username="+username);
	  xhr.send();
  }
</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/regist">
   用户名:<input type="text" name="username" onblur="testUsername()"/><span id="msg"></span><br/>
   密码:<input type="password" name="pwd"/><br/>
   <img  src="${pageContext.request.contextPath }/servlet/ckcode" onclick="changeImg()"/><a href="javascript:changeImg()">看不清楚，请重换一张</a><br/>
   <input type="text" name="ckCode"><br/>
   <input type="submit" value="注册"/>

</form>
</body>
</html>