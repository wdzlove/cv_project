<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>智联简历上传</title>
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
		<div class="c-fr c-edit-btns">
			<a href="/choose_upload.do">首页</a>
			<a href="/find_list.do">列表</a>
		</div>
		<div class="c-main-title">导入智联简历（.xls)</div>
	</div>
	<div class="layui-upload" style="width: 200;height: 200">
	  <button type="button" class="layui-btn layui-btn-sm" id="upload_lp">选择多文件</button>
	  <div class="layui-upload-list">
	    <table class="layui-table">
	      <thead>
	        <tr><th>文件名</th>
	        <th>大小</th>
	        <th>状态</th>
	        <th class="c-align-center">操作</th>
	      </tr></thead>
	      <tbody id="show_list"></tbody>
	    </table>
	  </div>
	  <button type="button" class="layui-btn layui-btn-sm" id="begin_upload">开始上传</button>
	</div> 
</div>
<script type="text/javascript">
//多文件列表示例
$(function(){
	  var upload = layui.upload;
	  var demoListView = $('#show_list'),uploadListIns = upload.render({
	    elem: '#upload_lp'
	    ,url: '/upload_file.do'
	    ,accept: 'file'
	    ,exts:'xls'
	    ,multiple: true
	    ,auto: false
	    ,data:{'type':1}
	    ,number:10
	    ,bindAction: '#begin_upload'
	    ,choose: function(obj){
	      var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
	      //读取本地文件
	      obj.preview(function(index, file, result){
	        var tr = $(['<tr id="upload-'+ index +'">'
	          ,'<td>'+ file.name +'</td>'
	          ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
	          ,'<td>等待上传</td>'
	          ,'<td class="c-align-center">'
	            ,'<button class="layui-btn layui-btn-primary layui-btn-sm demo-delete">删除</button>'
	            ,'<button class="layui-btn layui-btn-primary layui-btn-sm demo-reload layui-hide">重传</button>'
	          ,'</td>'
	        ,'</tr>'].join(''));
	        
	        //单个重传
	        tr.find('.demo-reload').on('click', function(){
	          obj.upload(index, file);
	        });
	        
	        //删除
	        tr.find('.demo-delete').on('click', function(){
	          delete files[index]; //删除对应的文件
	          tr.remove();
	          uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
	        });
	        
	        demoListView.append(tr);
	      });
	    }
	    ,done: function(res, index, upload){
	      if(res.code == 1){ //上传成功
	        var tr = demoListView.find('tr#upload-'+ index)
	        ,tds = tr.children();
	        tds.eq(2).html('<span style="color: #5FB878;">'+res.message+'</span>');
	        tds.eq(3).html(''); //清空操作
	        return delete this.files[index]; //删除文件队列已经上传成功的文件
	      }else if(res.code == 2){
	    	  var tr = demoListView.find('tr#upload-'+ index)
		        ,tds = tr.children();
		        tds.eq(2).html('<span style="color: #FF5722;">'+res.message+'</span>');
		        return delete this.files[index]; //删除文件队列已经上传成功的文件
	      }
	      this.error(index, upload);
	    }
	    ,error: function(index, upload){
	      var tr = demoListView.find('tr#upload-'+ index)
	      ,tds = tr.children();
	      tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
	      tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
	    }
	  });
});
</script>
</body>
</html>