<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="zh">
<head>
<meta charset="UTF-8"></meta>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> </meta>
<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
<title>金瑞帆人才系统-登录</title>
<link rel="stylesheet" type="text/css" href="/css/login.css"></link>
<script src="/js/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="/js/jquery/jquery.form.js" type="text/javascript"></script>
<script src="/js/layer-v3.1.1/layer/layer.js" type="text/javascript"></script>
</head>
<body>
	<div class="login_area">
		<input class="login_input login_user" type="text"  id="user_name">
		<input class="login_input login_pswd" type="password" id="pass_word">
		<a class="login_btn" href="javascript:void(0);" onclick="loginFun()"></a>
	</div>
<script type="text/javascript">
document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];
    if(e && e.keyCode==13){
    	loginFun();
	}
}; 
function loginFun(){
	var name = $("#user_name").val();
	var psw = $("#pass_word").val();
	if(name == "" || psw == ""){
		layer.msg("请输入用户名/密码");
		return
	}
	$.ajax({
		url:'login.do',
		type:'post',
		dataType:'json',
		data:{'userName':name,'passWord':psw},
		success:function(data){
			console.log(data)
			if(data.code == 1){
				layer.msg("登录成功",{time:1000},function(){
					window.location.href="choose_upload.do";
				});
			}else{
				layer.msg(data.message);
			}
		},
	});
}
</script>
</body>
</html>