<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title dir="ltr">人才库</title>
<link rel="stylesheet" type="text/css" href="/css/info.css">
<link rel="stylesheet" type="text/css" href="/css/layui.css">
<script type="text/javascript" language="javascript" src="/js/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" language="javascript" src="/js/jquery/jquery.form.js"></script>
<script type="text/javascript" language="javascript" src="/layui/layui.all.js"></script>
<script type="text/javascript" language="javascript" src="/js/layer-v3.1.1/layer/layer.js"></script>
</head>
<body>
<div class="g-wrap">
	<div class="c-banner"><img src="/img/banner.jpg"></div>
	<!-- <div class="c-title-box">
		<div class="c-fr c-edit-btns">
			
		</div>
		<div class="c-main-title">人才库 </div>
	</div> -->
	<div class="c-content-box">	
	<div class="c-search-all">
		<a href="/find_list.do"  class="c-btn">全部</a>
		<a href="Javascript:void(0);" class="c-btn" onclick="sourceFun(2);">猎聘</a>
		<a href="Javascript:void(0);" class="c-btn" onclick="sourceFun(1);">智联</a>
		<a class="c-btn" href="/choose_upload.do">首页</a>
		<div class="layui-input-inline">
			<select lay-verify="required" lay-search="" onchange="stateFun(this);">
				<option value="" <c:if test="${empty states}">selected="selected"</c:if>>全部状态</option>
				<option value="0" <c:if test="${states == 0}">selected="selected"</c:if>>新简历</option>
				<option value="1" <c:if test="${states == 1}">selected="selected"</c:if>>已通知</option>
				<option value="2" <c:if test="${states == 2}">selected="selected"</c:if>>已面试</option>
				<option value="3" <c:if test="${states == 3}">selected="selected"</c:if>>已淘汰</option>
				<option value="4" <c:if test="${states == 4}">selected="selected"</c:if>>已入职</option>
				<option value="5" <c:if test="${states == 5}">selected="selected"</c:if>>已转正</option>
				<option value="6" <c:if test="${states == 6}">selected="selected"</c:if>>已离职</option>
				<option value="7" <c:if test="${states == 7}">selected="selected"</c:if>>已储备</option>
			</select>
		</div>
		<c:if test="${isAdmin == 1}">
			<div class="layui-input-inline">
				<select onchange="userFun(this);">
					<option value="0" <c:if test="${empty userId || userId == 0}">selected="selected"</c:if>>全部责任人</option>
					<c:forEach items="${users}" var="u">
						<option value="${u.userId}" <c:if test="${userId == u.userId}">selected="selected"</c:if>>${u.realName}</option>
					</c:forEach>
				</select>
			</div>
		</c:if>
		<div class="layui-input-inline">
			<select onchange="inputUserFun(this)">
				<option value="0" <c:if test="${empty inputUser || inputUser == 0}">selected="selected"</c:if>>全部录入人</option>
				<c:forEach items="${users}" var="u">
					<option value="${u.userId}" <c:if test="${inputUser == u.userId}">selected="selected"</c:if>>${u.realName}</option>
				</c:forEach>
			</select>
		</div>
		<div class="c-searchbox layui-input-inline">
			<input type="text" placeholder="姓名/手机/企业/编号" value="${value}">
			<a href="javascript:void(0);" class="c-btn-search c-btn" onclick="searchFun(this)">搜索</a>
		</div>
		</div>
		<table class="c-table-fixed c-table">
			<thead>
				<tr>
					<td class="c-align-left">姓名</td>
					<td>性别</td>
					<td>年龄</td>
					<td>手机</td>
					<td class="c-align-left">Email</td>
					<td class="c-align-left">录入人</td>
					<td>录入时间</td>
					<td class="c-align-left">负责人</td>
					<td>更新时间</td>
					<td>来源</td>
					<td>状态</td>
					<td>推荐企业</td>
					<td class="c-width03">操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="cv">
					<tr>
						<td class="c-align-left"><a href="/get_detail.do?id=${cv.id}" target="_blank">${cv.name}</a></td>
						<td>${cv.sex}</td>
						<td>${cv.age}</td>
						<td>${cv.mobile}</td>
						<td class="c-align-left">${cv.email}</td>
						<td class="c-align-left">${cv.inputReal}</td>
						<td><fmt:formatDate value="${cv.createTime}" pattern="yyyy-MM-dd"/></td>
						<td class="c-align-left">${cv.userReal}</td>
						<td><fmt:formatDate value="${cv.updateTime}" pattern="yyyy-MM-dd"/></td>
						<td>
							<c:if test="${cv.source == 1}">智联</c:if>
							<c:if test="${cv.source == 2}">猎聘</c:if>
						</td>
						<td>
							<a href="javascript:void(0);" onclick="updateState(${cv.id},${cv.state})">
								<c:if test="${cv.state == 0}">新简历</c:if>
								<c:if test="${cv.state == 1}">已通知</c:if>
								<c:if test="${cv.state == 2}">已面试</c:if>
								<c:if test="${cv.state == 3}">已淘汰</c:if>
								<c:if test="${cv.state == 4}">已入职</c:if>
								<c:if test="${cv.state == 5}">已转正</c:if>
								<c:if test="${cv.state == 6}">已离职</c:if>
								<c:if test="${cv.state == 7}">已储备</c:if>
							</a>
						</td>
						<td>
							<c:if test="${!empty cv.customer}">${cv.customer}</c:if>
							<c:if test="${empty cv.customer}">--</c:if>
						</td>
						<td>
							<a class="layui-btn layui-btn-primary layui-btn-sm" href="/cv_edit.do?id=${cv.id}" target="_blank">编辑</a>
							<a class="layui-btn layui-btn-primary layui-btn-sm" href="/export_doc.do?id=${cv.id}">导出</a>
							<div class="layui-btn layui-btn-primary layui-btn-sm c-btn-more" href="javascript:void(0)"><span>···</span>
							<ul class="c-more-box">
							<li>
							<a href="javascript:void(0)" onclick="delCV(this,${cv.id});">删除</a>
							</li>
							<li>
							<a href="javascript:void(0)" onclick="toUser(this,${cv.id});">指派</a>
							</li>
							<li>
							<a href="/find_log.do?id=${cv.id}" target="_blank">日志</a>
							</li>
							</ul>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${page.totalCount gt 0}">
			<div id="page_div"></div>
		</c:if>
	</div>
</div>
<form action="/find_list.do" id="list_form" method="post" >
	<input type="hidden" value="${page.pageNo}" name="pageNo" id="page_no">
	<input type="hidden" value="${page.pageSize}" name="pageSize" id="page_size">
	<input type="hidden" value="${sources}" name="sources" id="source">
	<input type="hidden" value="${states}" name="states" id="state">
	<input type="hidden" value="${userId}" name="userId" id="user_id">
	<input type="hidden" value="${inputUser}" name="inputUser" id="input_user">
	<input type="hidden" value="${value}" name="value" id="f_value">
</form>
<input type="hidden" value="${page.totalCount}" id="total_count">
<script type="text/javascript">
$(function(){
	$(".c-btn-more").mouseover(function(){
		$(this).css("z-index","1");
		$(this).find(".c-more-box").show();
		
	}).mouseout(function(){
		$(this).css("z-index","0");
		$(this).find(".c-more-box").hide();
	})
	
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
function delCV(obj,id){
	layer.confirm("确定删除么",{
		btn:['确定','取消']
	},function(){
		$.ajax({
			url:'/del_cv.do?id='+id,
			type:'post',
			dataType:'json',
			success:function(data){
				if(data.code == 1){
					layer.msg("删除成功",{time:1000},function(){
						window.location.reload();
					});
				}
			}
		});
	},function(){
		layer.msg("已取消");
	});
}

function sourceFun(source){
	$("#source").val(source);
	$("#page_no").val(1);
	$("#list_form").submit();
}
function stateFun(obj){
	var state = $(obj).val();
	$("#state").val(state);
	$("#page_no").val(1);
	$("#list_form").submit();
}
function userFun(obj){
	var userId = $(obj).val();
	$("#user_id").val(userId);
	$("#page_no").val(1);
	$("#list_form").submit();
}
function inputUserFun(obj){
	var userId = $(obj).val();
	$("#input_user").val(userId);
	$("#page_no").val(1);
	$("#list_form").submit();
}

function searchFun(obj){
	var val = $(obj).prev().val();
	$("#f_value").val(val);
	$("#page_no").val(1);
	$("#list_form").submit();
}

function updateState(id,state){
	var html = ' <div class="layui-layer-inner"><ul id="state_layer">'
				+'<li><i class="c-radio"><i></i></i><input type="radio" name="state" value="1" ><label for="" onclick="radioState(this,1,'+id+');">已通知</label></li>'
				+'<li><i class="c-radio"><i></i></i><input type="radio" name="state" value="2" ><label for="" onclick="radioState(this,2,'+id+');">已面试</label></li>'
				+'<li><i class="c-radio"><i></i></i><input type="radio" name="state" value="3" ><label for="" onclick="radioState(this,3,'+id+');">已淘汰</label></li>'
				+'<li><i class="c-radio"><i></i></i><input type="radio" name="state" value="4" ><label for="" onclick="radioState(this,4,'+id+');">已入职</label></li>'
				+'<li><i class="c-radio"><i></i></i><input type="radio" name="state" value="5" ><label for="" onclick="radioState(this,5,'+id+');">已转正</label></li>'
				+'<li><i class="c-radio"><i></i></i><input type="radio" name="state" value="6" ><label for="" onclick="radioState(this,6,'+id+');">已离职</label></li>'
				+'<li><i class="c-radio"><i></i></i><input type="radio" name="state" value="7" ><label for="" onclick="radioState(this,7,'+id+');">已储备</label></li></ul></div>';
	layer.open({
		  type: 1,
		  skin: 'layui-layer-demo', //样式类名
		  anim: 2,
		  shadeClose: true, //开启遮罩关闭
		  btnAlign: 'c' ,
		  content: html,
		  area: ['360px', '225px'],
		  btn: ['确定', '取消'],
		  yes: function(){
	          var val = $("input[checked=checked][name='state']").val();
	          $.ajax({
	        	 url:'update_state_user.do',
	        	 type:'post',
	        	 dataType:'json',
	        	 data:{'id':id,'state':val},
	        	 success:function(data){
    				 layer.closeAll();
	        		 if(data.code == 1){
	        			 layer.msg("修改成功",{time:1000},function(){
	        				 window.location.reload();
	        			 });
	        		 }else{
	        			 layer.msg("修改失败");
	        		 }
	        	 }
	          });
	        }
		});
	if(state > 0){
		var obj = $("#state_layer").find("li").eq(state-1);
		$(obj).find("i.c-radio").addClass("checked").find("input").attr("checked","checked");
	}
}

function radioState(obj,val,cvId){
	var checked = $(obj).prev().attr("checked");
	if(checked == "checked"){
		$(obj).prev().removeAttr("checked").prev().removeClass("checked");
	}else{
		$(obj).prev().attr("checked","checked").prev().addClass("checked");
		$(obj).parent().siblings("li").each(function(i,o){
			if($(o).find("i.checked").length > 0){
				$(o).find("i.checked").removeClass("checked").end().find("input").removeAttr("checked");
			}
		});
		if(val > 1 && val < 7){
			$.ajax({
				url:'/check_recommend.do',
				type:"post",
				dataType:'json',
				data:{'cvId':cvId},
				success:function(data){
					if(data.code == 1){
						
					}else if(data.code == 2){
						layer.msg("获取不到推荐企业，请编辑推荐信息重试",{time:2000},function(){
							$(obj).prev().removeAttr("checked").prev().removeClass("checked");
						});
						return
					}else if(data.code == 3){
						layer.msg("简历编号异常",{time:2000},function(){
							$(obj).prev().removeAttr("checked").prev().removeClass("checked");
						});
						return
					}
				}
			});
		}
	}
}

function changeUser(obj){
	var checked = $(obj).prev().attr("checked");
	if(checked == "checked"){
		$(obj).prev().removeAttr("checked").prev().removeClass("checked");
	}else{
		$(obj).prev().attr("checked","checked").prev().addClass("checked");
		$(obj).parent().siblings("li").each(function(i,o){
			if($(o).find("i.checked").length > 0){
				$(o).find("i.checked").removeClass("checked").end().find("input").removeAttr("checked");
			}
		});
	}
}

function toUser(obj,id){
	$.ajax({
		url:'find_users.do',
		type:'get',
		dataType:'json',
		success:function(data){
			var objs = eval(data.datas);
			if(typeof(objs) != "undefined" && objs.length > 0){
				showUser(objs,id);
			}else{
				layer.msg("无可用指派人数据");
			}
		}
	});
}

function showUser(objs,id){
	var html = ' <div class="layui-layer-inner"><ul>';
	$(objs).each(function(i,o){
		var userId = o.userId;
		var userName = o.userName;
		html +='<li><i class="c-radio"><i></i></i><input type="radio" name="user" value="'+userId+'" ><label for="" onclick="changeUser(this);">'+userName+'</label></li>';
	});
	html+='</ul></div>';
	layer.open({
		  type: 1,
		  skin: 'layui-layer-demo', //样式类名
		  anim: 2,
		  shadeClose: true, //开启遮罩关闭
		  btnAlign: 'c' ,
		  content: html,
		  area: ['360px', '220px'],
		  btn: ['确定', '取消'],
		  yes: function(){
		       var val = $("input[checked=checked]").val();
		       $.ajax({
			     	 url:'update_state_user.do',
			     	 type:'post',
			     	 dataType:'json',
			     	 data:{'id':id,'userId':val},
			     	 success:function(data){
						 layer.closeAll();
			     		 if(data.code == 1){
			     			 layer.msg("指派成功",{time:1000},function(){
			     				 window.location.reload();
			     			 });
			     		 }else{
			     			 layer.msg("指派失败");
			     		 }
			     	 }
		       });
	     	}
		});
}

</script>
</body>
</html>