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
<script type="text/javascript" language="javascript" src="/js/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript" language="javascript" src="/layui/layui.all.js"></script>
</head>
<body>
<div class="g-wrap">
	<div class="c-title-box">
		<div class="c-fr c-edit-btns">
			<a href="/choose_upload.do">首页</a>
			<a href="javascript:void(0);" onclick="addFun()">添加</a>
		</div>
		<div class="c-main-title">岗位管理 </div>
	</div>
	<div class="c-content-box">	
		<div class="c-search-all">
			<div class="c-searchbox layui-input-inline">
				<input type="text" placeholder="岗位名称" value="${name}">
				<a href="javascript:void(0);" class="c-btn-search c-btn" onclick="searchFun(this)">搜索</a>
			</div>
		</div>
		<table class="c-table-fixed c-table">
			<thead>
				<tr>
					<td class="c-align-left">岗位名称</td>
					<td>岗位编号</td>
					<td>招聘人数</td>
					<td>招聘企业</td>
					<td class="c-width03">操作</td>
				</tr>
			</thead>
			<tbody id="data_body">
				<c:forEach items="${page.list}" var="p">
					<tr>
						<td class="c-align-left">${p.name}</td>
						<td >${p.number}</td>
						<td >${p.recruitNum}</td>
						<td >${p.customer}</td>
						<td>
							<a class="layui-btn layui-btn-primary layui-btn-sm" href="javascript:void(0);" onclick="updateFun(this,${p.id})">编辑</a>
							<a class="layui-btn layui-btn-primary layui-btn-sm" href="javascript:void(0)"  onclick="delFun(${p.id},'${p.name}')">删除</a>
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
<form action="../post/find_list.do" id="list_form" method="post" >
<input type="hidden" value="${page.pageNo}" name="pageNo" id="page_no">
<input type="hidden" value="${page.pageSize}" name="pageSize" id="page_size">
<input type="hidden" value="${name}" name="name" id="f_name">
</form>
<input type="hidden" value="${page.totalCount}" id="total_count">
<div class="c-add-container" style="display:none" id="add_page"></div>
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
	layer.confirm("确定删除'"+name+"'么",{
		btn:['确定','取消']
	},function(){
		$.ajax({
			url:'../post/delete.do',
			type:'post',
			dataType:'json',
			data:{"id":id},
			success:function(data){
				if(data.code == 1){
					layer.msg("删除成功",{time:1000},function(){
						window.location.reload();
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
	$.ajax({
		url:'/cus/layer_page.do',
		type:'post',
		dataType:"html",
		success:function(data){
			$("#add_page").empty();
			var html = $("#add_page").append(data);
			addShow(html);
		}
	});
}
function addShow(html){
	layer.open({
		  type: 1,
		  skin: 'layui-layer-demo', //样式类名
		  anim: 2,
		  //shadeClose: true, //开启遮罩关闭
		  btnAlign: 'c' ,
		  content: html,
		  area: ['600px', '440px'],
		  btn: ['确定', '取消'],
		  yes: function(){
			  var name = $("#p_name").val();
			  if(name == ""){
				  layer.msg("请输入岗位名称");
				  return
			  }else if(getLength(name) > 50){
				  layer.msg("岗位名称最多可输入50个汉字/100个字符");
				  return
			  }
			  var number = $("#p_number").val();
			  if(number == "" || isNaN(number)){
				  layer.msg("请输入3位数字的岗位编号");
				  return
			  }else if(getLength(number) != 3){
				  layer.msg("请输入3位数字的岗位编号");
			  }
			  var num = $("#recruit_num").val();
			  if(num == ""){
				  layer.msg("请输入招聘人数，最多四位");
				  return
			  }else if(getLength > 4){
				  layer.msg("招聘人数最多可输入4位数");
				  return
			  }
			  var html = editor.txt.html();
			  $("#h_duty").val(html);
			  var text = editor.txt.text();
			  if(text == ""){
				  layer.msg("请输入岗位职责");
				  return
			  }else if(getLength(text) > 500){
				  layer.msg("岗位职责最多可输入500个汉字");
				  return
			  }
		      $.ajax({
		    	 url:'../post/save.do',
		    	 type:'post',
		    	 dataType:'json',
		    	 data:$("#add_position").serialize(),
		    	 success:function(data){
		    		 if(data.code == 1){
						 layer.closeAll();
						 $("#add_page").empty();
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
		    },
		    end:function(){ // 未点击确定按钮，点击关闭按钮  
		  　　　　$("#add_page").empty();
		　　　}
		});
}

function updateFun(obj,id){
	$.ajax({
		url:'/cus/layer_page.do',
		type:'post',
		dataType:"html",
		data:{'id':id},
		success:function(data){
			$("#add_page").empty();
			var html = $("#add_page").append(data);
			updateShow(html);
		}
	});
}

function updateShow(html){
	layer.open({
		  type: 1,
		  skin: 'layui-layer-demo', //样式类名
		  anim: 2,
		  //shadeClose: true, //开启遮罩关闭
		  btnAlign: 'c' ,
		  content: html,
		  area: ['600px', '440px'],
		  btn: ['确定', '取消'],
		  yes: function(){
			  var name = $("#p_name").val();
			  if(name == ""){
				  layer.msg("请输入岗位名称");
				  return
			  }else if(getLength(name) > 50){
				  layer.msg("岗位名称最多可输入50个汉字/100个字符");
				  return
			  }
			  var number = $("#p_number").val();
			  if(number == "" || isNaN(number)){
				  layer.msg("请输入3位数字的岗位编号");
				  return
			  }else if(getLength(number) != 3){
				  layer.msg("请输入3位数字的岗位编号");
			  }
			  var num = $("#recruit_num").val();
			  if(num == ""){
				  layer.msg("请输入招聘人数，最多四位");
				  return
			  }else if(getLength > 4){
				  layer.msg("招聘人数最多可输入4位数");
				  return
			  }
			  var html = editor.txt.html();
			  $("#h_duty").val(html);
			  var text = editor.txt.text();
			  if(text == ""){
				  layer.msg("请输入岗位职责");
				  return
			  }else if(getLength(text) > 500){
				  layer.msg("岗位职责最多可输入500个汉字");
				  return
			  }
	          $.ajax({
	        	 url:'../post/update.do',
	        	 type:'post',
	        	 dataType:'json',
	        	 data:$("#add_position").serialize(),
	        	 success:function(data){
	        		 if(data.code == 1){
	        			 $("#add_page").hide();
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
	        },
	        end:function(){ // 未点击确定按钮，点击关闭按钮  
			  　　　　$("#add_page").hide();
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