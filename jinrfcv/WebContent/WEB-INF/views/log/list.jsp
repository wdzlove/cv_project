<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>简历操作记录</title>
<link rel="stylesheet" type="text/css" href="/css/info.css">
<link rel="stylesheet" type="text/css" href="/css/layui.css">
<script type="text/javascript" language="javascript" src="/js/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" language="javascript" src="/js/jquery/jquery.form.js"></script>
<script type="text/javascript" language="javascript" src="/layui/layui.all.js"></script>
<script type="text/javascript" language="javascript" src="/js/layer-v3.1.1/layer/layer.js"></script>
</head>
<body>
<div class="g-wrap">
	<div class="c-title-box">
		<div class="c-main-title">简历操作记录 </div>
	</div>
	<div class="c-content-box">	
		<table class="c-table-fixed c-table">
			<thead>
				<tr>
					<td class="c-align-left">操作人</td>
					<td>操作时间</td>
					<td>所属企业</td>
					<td class="c-align-left">操作记录</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="c">
					<tr>
						<td class="c-align-left">${c.userName}</td>
						<td><fmt:formatDate value="${c.updateTime}" pattern="yyyy-MM-dd HH:mm"/> </td>
						<td>
							<c:if test="${!empty c.customer}">
								${c.customer}
							</c:if>
							<c:if test="${empty c.customer}">
								--
							</c:if>
						</td>
						<td class="c-align-left">${c.record}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${page.totalCount gt 0}">
			<div id="page_div"></div>
		</c:if>
	</div>
</div>
<form action="/find_log.do" id="list_form" method="post" >
	<input type="hidden" value="${page.pageNo}" name="pageNo" id="page_no">
	<input type="hidden" value="${page.pageSize}" name="pageSize" id="page_size">
	<input type="hidden" value="${cvId}" name="id" id="source">
</form>
<input type="hidden" value="${page.totalCount}" id="total_count">
<script type="text/javascript">
$(function(){
	layui.use(['laypage', 'layer'], function(){
		  var laypage = layui.laypage
		  ,layer = layui.layer;
		  //完整功能
		  laypage.render({
		    elem: 'page_div'
		    ,count: $("#total_count").val()
			,curr:  $("#page_no").val()
			,limit: $("#page_size").val()
			,theme: '#00ccff'
		    ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
		    ,jump: function(obj,first){
		      	var pageNo = obj.curr;
		      	var pageSize = obj.limit;
		      	$("#page_no").val(pageNo);
		      	$("#page_size").val(pageSize);
		      	if(!first){
		      		$("#list_form").submit();
		      	}
		    }
		  });
	});
});
</script>
</body>
</html>