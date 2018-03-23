<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户管理</title>
<link rel="stylesheet" type="text/css" href="/css/layui.css">
<link rel="stylesheet" type="text/css" href="/css/info.css">
<script type="text/javascript" language="javascript" src="/js/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" language="javascript" src="/js/jquery/jquery.form.js"></script>
<script type="text/javascript" language="javascript" src="/layui/layui.all.js"></script>
<script type="text/javascript" language="javascript" src="/js/layer-v3.1.1/layer/layer.js"></script>
</head>
<body>
<div class="g-wrap">
	<div class="c-title-box">
		<div class="c-fr c-edit-btns">
			<a href="/choose_upload.do">首页</a>
			<a href="javascript:void(0);" onclick="addFun()">添加</a>
		</div>
		<div class="c-main-title">客户管理 </div>
	</div>
	<div class="c-content-box">	
	<div class="c-search-all">
		<div class="c-searchbox layui-input-inline">
			<input type="text" placeholder="客户名称" value="${name}">
			<a href="javascript:void(0);" class="c-btn-search c-btn" onclick="searchFun(this)">搜索</a>
		</div>
		</div>
		<table class="c-table-fixed c-table">
			<thead>
				<tr>
					<td class="c-align-left">客户编号</td>
					<td>客户名称</td>
					<td class="c-width03">操作</td>
				</tr>
			</thead>
			<tbody id="data_body">
				<c:forEach items="${page.list}" var="c">
					<tr>
						<td class="c-align-left">${c.number}</td>
						<td>${c.name}</td>
						<td>
							<a class="layui-btn layui-btn-primary layui-btn-sm" href="javascript:void(0);" onclick="updateFun(${c.id},'${c.name}','${c.number}')">编辑</a>
							<a class="layui-btn layui-btn-primary layui-btn-sm" href="javascript:void(0)"  onclick="delFun(${c.id},'${c.name}')">删除</a>
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
<form action="../cus/find_list.do" id="list_form" method="post" >
	<input type="hidden" value="${page.pageNo}" name="pageNo" id="page_no">
	<input type="hidden" value="${page.pageSize}" name="pageSize" id="page_size">
	<input type="hidden" value="${name}" name="name" id="f_name">
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
function searchFun(obj){
	var val = $(obj).prev().val();
	$("#f_name").val(val);
	$("#page_no").val(1);
	$("#list_form").submit();
}
function delFun(id,name){
	layer.confirm("确定删除'"+name+"'么?,删除后对应的岗位数据也将删除",{
		btn:['确定','取消']
	},function(){
		$.ajax({
			url:'../cus/delete.do',
			type:'post',
			dataType:'json',
			data:{"id":id},
			success:function(data){
				if(data.code == 1){
					layer.msg("删除成功",{time:1000},function(){
						var len = $("#data_body").children("tr").length;
						if(len == 1){
							var pageNo = $("#page_no").val();
							$("#page_no").val(pageNo-1);
				      		$("#list_form").submit();
						}else{
							window.location.reload();
						}
					});
				}else if(data.code == 2){
					layer.msg("有简历推荐不能删除");
				}else{
					layer.msg("删除失败");
				}
			}
		});
	},function(){
		layer.msg("已取消");
	});
}

function addFun(){
	var html = '<div class="c-add-container c-two-middle">'+
					'<form action="" id="add_customer">'+
						'<dl>'+
							'<dd><span>企业编号：</span><input class="c-input-width02" type="text" name="number" value="" id="c_number" maxLength="3" onkeyup="value=value.replace(/[^\\d]/g,\'\') " ></dd>'+
							'<dd><span>企业名称：</span><input class="c-input-width02" type="text" name="name" value="" id="c_name"></dd>'+
						'</dl>'+
					'</form>'+
				'</div>';
	layer.open({
	  type: 1,
	  skin: 'layui-layer-demo', //样式类名
	  anim: 2,
	  shadeClose: true, //开启遮罩关闭
	  btnAlign: 'c' ,
	  content: html,
	  area: ['600px', '345px'],
	  btn: ['确定', '取消'],
	  yes: function(){
		  var name = $("#c_name").val();
		  if(name == ""){
			  layer.msg("请输入企业名称");
			  return
		  }else if(getLength(name) > 100){
			  layer.msg("企业名称最多可输入50个汉字/100个字符");
			  return
		  }
		  var number = $("#c_number").val();
		  if(number == ""  || isNaN(number)){
			  layer.msg("请输入3位数字的企业编号");
			  return
		  }else if(getLength(number) != 3){
			  layer.msg("请输入3位数的企业编号");
			  return
		  }
	      $.ajax({
	    	 url:'../cus/save.do',
	    	 type:'post',
	    	 dataType:'json',
	    	 data:$("#add_customer").serialize(),
	    	 success:function(data){
	    		 if(data.code == 1){
					 layer.closeAll();
	    			 layer.msg("添加成功",{time:1000},function(){
	    				 window.location.reload();
	    			 });
	    		 }else if(data.code == 2){
        			 layer.msg("'"+name+"'已存在");
        		 }else if(data.code == 3){
        			 layer.msg("'"+number+"'编号已被使用");
        		 }else{
	    			 layer.msg("添加失败");
	    		 }
	    	 }
	      });
	    }
	});
}

function updateFun(id,name,number){
	var html = '<div class="c-add-container c-two-middle">'+
					'<form action="" id="update_customer">'+
						'<input type="hidden" name="id" value="'+id+'">'+
						'<dl>'+
							'<dd><span>企业编号：</span><input class="c-input-width02" type="text" name="number" value="'+number+'" id="c_number" maxLength="3" onkeyup="value=value.replace(/[^/d]/g,\'\') " ></dd>'+
							'<dd><span>企业名称：</span><input class="c-input-width02" type="text" name="name" value="'+name+'" id="c_name" ></dd>'+
						'</dl>'+
					'</form>'+
				'</div>';
	layer.open({
		  type: 1,
		  skin: 'layui-layer-demo', //样式类名
		  anim: 2,
		  shadeClose: true, //开启遮罩关闭
		  btnAlign: 'c' ,
		  content: html,
		  area: ['600px', '345px'],
		  btn: ['确定', '取消'],
		  yes: function(){
			  var name = $("#c_name").val();
			  if(name == ""){
				  layer.msg("请输入企业名称");
				  return
			  }else if(getLength(name) > 100){
				  layer.msg("企业名称最多可输入50个汉字/100个字符");
				  return
			  }
			  var number = $("#c_number").val();
			  if(number == ""  || isNaN(number)){
				  layer.msg("请输入企业编号");
				  return
			  }else if(getLength(number) != 3){
				  layer.msg("请输入3位数的企业编号");
				  return
			  }
	          $.ajax({
	        	 url:'../cus/update.do',
	        	 type:'post',
	        	 dataType:'json',
	        	 data:$("#update_customer").serialize(),
	        	 success:function(data){
	        		 if(data.code == 1){
	    				 layer.closeAll();
	        			 layer.msg("修改成功",{time:1000},function(){
	        				 window.location.reload();
	        			 });
	        		 }else if(data.code == 2){
	        			 layer.msg("'"+name+"'已存在");
	        		 }else if(data.code == 3){
	        			 layer.msg("'"+number+"'编号已被使用");
	        		 }else{
	        			 layer.msg("修改失败");
	        		 }
	        	 }
	          });
	        }
		});
}

/**
 * 长度取得
 * 汉字算2位
 */
function getLength(varStr){
	var length = varStr.length;
  	var regC = /^[\u4E00-\u9FA5]/;
	for(var i=0;i<varStr.length;i++){
		if(regC.test(varStr.charAt(i))){
			length+=1;
		}
	}
	return length;
}
</script>
</body>
</html>