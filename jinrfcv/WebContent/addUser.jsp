<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
function addUser(){
	var form = document.forms[0];
	//form.action="/SpringMVC_ZJ/data/addUser";单参数传递
	form.action="/SpringMVC_ZJ/data/addUserVo";//对象传递
	form.method="post";
	form.submit();
}

</script>

</head>
<body>
<form action="">

	姓名：<input type="text" name="username">
	年龄：<input type="text" name="age">
	<input type="button" value="添加" onclick="addUser()">
</form>

</body>
</html>