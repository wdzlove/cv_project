<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传首页</title>
<link rel="stylesheet" type="text/css" href="/css/info.css">
</head>
<body class="c-height">
<div class="exit-div" style="text-align: right">
	<h1>欢迎
		<c:if test="${!empty user.realName}">${user.realName}</c:if>
		<c:if test="${empty user.realName}">${user.userName}</c:if>
		用户
		<a href="exit.do" >【退出】</a>
	</h1>
</div>
<div class="g-wrap">
	<div class="c-links">
		<ul>
			<li>
				<a href="/upload_zl.do" class="c-big-ico i-zhilian"></a>
				<a href="/upload_zl.do" class="c-big-title">智联简历【Excel】</a>
			</li>
			<li>
				<a href="/upload_lp.do" class="c-big-ico i-liepin"></a>
				<a href="/upload_lp.do" class="c-big-title">猎聘简历【Excel】</a><a class="i-icons i-tip" href="/linepin_explain.do" target="_black" title="点击查看上传说明"></a>
			</li>
			<li>
				<a href="/find_list.do" class="c-big-ico i-ku"></a>
				<a href="/find_list.do" class="c-big-title">人才库</a>
			</li>
			<c:if test="${user.isAdmin == 1}">
				<li>
					<a href="/cus/find_list.do?pageNo=1&pageSize=10" class="c-big-ico i-kehu"></a>
					<a href="/cus/find_list.do?pageNo=1&pageSize=10" class="c-big-title">客户管理</a>
				</li>
				<li>
					<a href="/post/find_list.do?pageNo=1&pageSize=10" class="c-big-ico i-gangwei"></a>
					<a href="/post/find_list.do?pageNo=1&pageSize=10" class="c-big-title">岗位管理</a>
				</li>
			</c:if>
		</ul>
	</div>
	</div>
</body>
</html>