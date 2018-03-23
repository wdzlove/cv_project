<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$("#pro_isIt").click(function(){
		var name = $(this).attr("r_name");
		if(name == 0){
			 $(this).attr("r_name","1");
		}else{
			$(this).attr("r_name","0");
		}
	});
	$("#post_ul .i-checkbox").on('click',function(){
		if($(this).hasClass("checked")){
			$(this).removeClass("checked").prev().removeAttr("checked");
		}else{
			var len = $("#post_ul").find("input[checked='checked']").length;
			if(len < 5){
				$(this).addClass("checked").prev().attr("checked","checked");
				var val = $(this).text()
			}else{
				layer.msg("最多可选择5个岗位");
			}
		}
	});
	var postIds = $("#post_ids").val();
	if(postIds != ""){
		var ids = postIds.split(",");
		for(var k=0;k < ids.length; k++){
			var id = ids[k];
			$("#post_ul label").each(function(i,o){
				if($(o).prev().val() == id){
					$(o).addClass("checked").prev().attr("checked","checked");
				}
			});
		}
	}
	
});
function imgChange(obj){
	 var file = obj.files[0];
	 if (window.FileReader) {   
         var reader = new FileReader();    
         reader.readAsDataURL(file);    
         //监听文件读取结束后事件    
         reader.onloadend = function (e) {
	       	  if($(obj).parent().children("img").length > 0){
	       		  $(obj).parent().find("img").remove();
	       	  }
         	  $(obj).before("<img src='"+e.target.result+"'>");    //e.target.result就是最后的路径地址
         };    
		 $("#head_form").ajaxSubmit({
			 url:'save_head.do',
			 dataType:'json',
			 type:"post",
			 success:function(data){
				 if(data.code == 1){
					 layer.msg("保存成功");
				 }else{
					 layer.msg("保存失败");
				 }
			 }
		 });
    } 
}

function editEducation(obj){
	$(obj).parents("dl").next().show();
	$(obj).parents("dl").hide();
}

function updateEducation(id){
	$.ajax({
		url:'/save_education.do',
		type:'post',
		dataType:'json',
		data:$("#edu_form_"+id).serialize(),
		success:function(data){
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
function editJob(obj,id,cvId){
	$(obj).parents("dl").next().show();
	$(obj).parents("dl").hide();
}


function updateJob(id){
	$.ajax({
		url:'/update_job.do',
		type:'post',
		dataType:'json',
		data:$("#job_form_"+id).serialize(),
		success:function(data){
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
function editPro(obj){
	$(obj).parents("dl").next().show();
	$(obj).parents("dl").hide();
}

function updatePro(id){
	$.ajax({
		url:'/update_project.do',
		type:'post',
		dataType:'json',
		data:$("#pro_form_"+id).serialize(),
		success:function(data){
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


function closeEdit(obj){
	$(obj).parents(".c-edit-container").hide();
	$(obj).parents(".c-edit-container").prev().show();
}
function editHope(obj){
	$(obj).parents('.c-info-box').find(".c-edit-container").show();
	$(obj).parents(".c-title").next().hide();
}
function updateHope(){
	$.ajax({
		url:'/update_hope.do',
		type:'post',
		dataType:'json',
		data:$("#hope_form").serialize(),
		success:function(data){
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

function editTrain(obj){
	$(obj).parents("dl").next().show();
	$(obj).parents("dl").hide();
}

function updateTrain(id){
	$.ajax({
		url:'/update_train.do',
		type:'post',
		dataType:'json',
		data:$("#train_form_"+id).serialize(),
		success:function(data){
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

function editLanguage(obj){
	$(obj).parents("dl").next().show();
	$(obj).parents("dl").hide();
}

function updateLanguage(id){
	$.ajax({
		url:'/update_language.do',
		type:'post',
		dataType:'json',
		data:$("#language_form_"+id).serialize(),
		success:function(data){
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

function editSkill(obj){
	$(obj).parents("dl").next().show();
	$(obj).parents("dl").hide();
}
function updateSkill(id){
	$.ajax({
		url:'/update_skill.do',
		type:'post',
		dataType:'json',
		data:$("#skill_form_"+id).serialize(),
		success:function(data){
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

function editHobby(obj){
	$(obj).parents(".c-title").next().hide();
	$(obj).parents(".c-info-box").find(".c-edit-container").show();
}

function updateHobby(){
	$.ajax({
		url:'/update_hobby.do',
		type:'post',
		dataType:'json',
		data:$("#hobby_form").serialize(),
		success:function(data){
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
function editStatus(obj){
	$(obj).parents(".c-title").next().hide();
	$(obj).parents(".c-info-box").find('.c-edit-container').show();
}

function updateStatus(){
	$.ajax({
		url:'/update_status.do',
		type:'post',
		dataType:'json',
		data:$("#status_form").serialize(),
		success:function(data){
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

function editBase(obj){
	$("#base_div").hide();
	$("#base_edit").show();
}
function closeBase(obj){
	$("#base_div").show();
	$("#base_edit").hide();
}
function updateBase(){
	$.ajax({
		url:'/update_base.do',
		type:'post',
		dataType:'json',
		data:$("#base_form").serialize(),
		success:function(data){
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

/***********************删除*********************/
function delEdu(obj,id,cvId){
	layer.confirm('确定删除教育经历么？',{
		btn:['确定','取消']
	},function(){
		$.ajax({
			url:'/cv_del_edu.do',
			type:'post',
			dataType:'json',
			data:{'id':id,'cvId':cvId},
			success:function(data){
				if(data.code == 1){
					layer.msg("删除成功",{time:1000},function(){
						var len = $(obj).parents("div.c-info-box").children("dl").length;
						if(len == 1){
							window.location.reload();
						}else{
							$(obj).parents("dl").remove();
						}
					});
				}else{
					layer.msg("删除失败");
				}
			}
		});
	},function(){
		layer.msg("已取消",{time:1000});
	});
}

function delJobExp(obj,id,cvId){
	layer.confirm('确定删除工作经历么？',{
		btn:['确定','取消']
	},function(){
		$.ajax({
			url:'/cv_del_job_exp.do',
			type:'post',
			dataType:'json',
			data:{'id':id,'cvId':cvId},
			success:function(data){
				if(data.code == 1){
					layer.msg("删除成功",{time:1000},function(){
						var len = $(obj).parents("div.c-info-box").children("dl").length;
						if(len == 1){
							window.location.reload();
						}else{
							$(obj).parents("dl").remove();
						}
					});
				}else{
					layer.msg("删除失败");
				}
			}
		});
	},function(){
		layer.msg("已取消",{time:1000});
	});
}

function delPro(obj,id,cvId){
	layer.confirm('确定删除项目业绩么？',{
		btn:['确定','取消']
	},function(){
		$.ajax({
			url:'/cv_del_pro_exp.do',
			type:'post',
			dataType:'json',
			data:{'id':id,'cvId':cvId},
			success:function(data){
				if(data.code == 1){
					layer.msg("删除成功",{time:1000},function(){
						var len = $(obj).parents("div.c-info-box").children("dl").length;
						if(len == 1){
							window.location.reload();
						}else{
							$(obj).parents("dl").remove();
						}
					});
				}else{
					layer.msg("删除失败");
				}
			}
		});
	},function(){
		layer.msg("已取消",{time:1000});
	});
}

function delTrain(obj,id,cvId){
	layer.confirm('确定删除培训经历么？',{
		btn:['确定','取消']
	},function(){
		$.ajax({
			url:'/cv_del_train_exp.do',
			type:'post',
			dataType:'json',
			data:{'id':id,'cvId':cvId},
			success:function(data){
				if(data.code == 1){
					layer.msg("删除成功",{time:1000},function(){
						var len = $(obj).parents("div.c-info-box").children("dl").length;
						if(len == 1){
							window.location.reload();
						}else{
							$(obj).parents("dl").remove();
						}
					});
				}else{
					layer.msg("删除失败");
				}
			}
		});
	},function(){
		layer.msg("已取消",{time:1000});
	});
}

function delLanguage(obj,id,cvId){
	layer.confirm('确定删除么？',{
		btn:['确定','取消']
	},function(){
		$.ajax({
			url:'/cv_del_language.do',
			type:'post',
			dataType:'json',
			data:{'id':id,'cvId':cvId},
			success:function(data){
				if(data.code == 1){
					layer.msg("删除成功",{time:1000},function(){
						var len = $(obj).parents("div.c-info-box").children("dl").length;
						if(len == 1){
							window.location.reload();
						}else{
							$(obj).parents("dl").remove();
						}
					});
				}else{
					layer.msg("删除失败");
				}
			}
		});
	},function(){
		layer.msg("已取消",{time:1000});
	});
}
function delSkill(obj,id,cvId){
	layer.confirm('确定删除么？',{
		btn:['确定','取消']
	},function(){
		$.ajax({
			url:'/cv_del_skill.do',
			type:'post',
			dataType:'json',
			data:{'id':id,'cvId':cvId},
			success:function(data){
				if(data.code == 1){
					layer.msg("删除成功",{time:1000},function(){
						var len = $(obj).parents("div.c-info-box").children("dl").length;
						if(len == 1){
							window.location.reload();
						}else{
							$(obj).parents("dl").remove();
						}
					});
				}else{
					layer.msg("删除失败");
				}
			}
		});
	},function(){
		layer.msg("已取消",{time:1000});
	});
}

function addEducation(obj,id){
	var html = '<div class="c-add-container">'+
					'<form action="" id="edu_add">'+
						'<input type="hidden" name="cvId" value="'+id+'">'+
						'<dl>'+
						'<dd><span>开始时间：</span><input class="c-input-width01 Wdate" type="text" name="startTime" onclick="WdatePicker({dateFmt:\'yyyy.MM\',maxDate:\'%y-%M\',onpicked:function(){end_time.click();}})" id="start_time" readonly="readonly"><span>结束时间：</span><input class="c-input-width01 Wdate" type="text" name="endTime" id="end_time"  onclick="WdatePicker({dateFmt:\'yyyy.MM\',maxDate:\'%y-%M\',minDate:\'#F{$dp.$D(\\\'start_time\\\')}\'})" readonly="readonly"></dd>'+
							'<dd><span>学校名称：</span><input class="c-input-width02" type="text" name="school" value="" id="school_name"></dd>'+
							'<dd><span>专业名称：</span><input class="c-input-width02" type="text" name="majorName" value="" id="major_name"></dd>'+
							'<dd><span>学历：</span><input class="c-input-width02" type="text" name="education" value="" id="edu_cation"></dd>'+
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
		  area: ['600px', '285px'],
		  btn: ['确定', '取消'],
		  yes: function(){
			  var startTime = $("#start_time").val();
			  if(typeof(startTime) == "undefined" || startTime == "" ){
				  layer.msg("请选择开始时间");
				  return
			  }
			  var endTime = $("#end_time").val();
			  if(typeof(endTime) == "undefined" || endTime == "" ){
				  layer.msg("请选择结束时间");
				  return
			  }
			  var name = $("#school_name").val();
			  if(typeof(name) == "undefined" || name == "" ){
				  layer.msg("请选输入学校名称");
				  return
			  }
			  var majName = $("#major_name").val();
			  if(typeof(majName) == "undefined" || majName == "" ){
				  layer.msg("请选输入专业名称");
				  return
			  }
			  var edu = $("#edu_cation").val();
			  if(typeof(edu) == "undefined" || edu == "" ){
				  layer.msg("请选输入学历");
				  return
			  }
		       $.ajax({
			     	 url:'add_education.do',
			     	 type:'post',
			     	 dataType:'json',
			     	 data:$("#edu_add").serialize(),
			     	 success:function(data){
						 layer.closeAll();
			     		 if(data.code == 1){
			     			 layer.msg("添加成功",{time:1000},function(){
			     				 window.location.reload();
			     			 });
			     		 }else{
			     			 layer.msg("添加失败");
			     		 }
			     	 }
		       });
	     	}
		});
}

function addJobExp(obj,id){
	var html = '<div class="c-add-container">'+
					'<form action="" id="add_jobexp">'+
						'<input type="hidden" name="cvId" value="'+id+'">'+
						'<dl>'+
							'<dd>'+
								'<span>开始时间：</span><input class="c-input-width01  Wdate" type="text" name="starTime" onclick="WdatePicker({dateFmt:\'yyyy.MM\',maxDate:\'%y-%M\',onpicked:function(){end_time.click();}})" id="start_time" readonly="readonly"><span>结束时间：</span><input class="c-input-width01 Wdate" type="text" name="endTime" id="end_time"  onclick="WdatePicker({dateFmt:\'yyyy.MM\',maxDate:\'%y-%M\',minDate:\'#F{$dp.$D(\\\'start_time\\\')}\'})" readonly="readonly">'+
							'</dd>'+
							'<dd><span>公司名称：</span><input class="c-input-width02" type="text" name="entName" value="" id="ent_name"></dd>'+
							'<dd><span>职位名称：</span><input class="c-input-width02" type="text" name="jobName" value="" id="job_name"></dd>'+
							'<dd><span>职位薪资：</span><input class="c-input-width02" type="text" name="jobSalary" value="" id="job_salary"></dd>'+
							'<dd><span>公司行业：</span><input class="c-input-width02" type="text" name="industryType" value="" id="indu_type"></dd>'+
							'<dd><span>公司性质：</span><input class="c-input-width02" type="text" name="entNature" value="" id="ent_nature"></dd>'+
							'<dd><span>职责描述：</span><textarea class="c-input-width02" rows="5" cols="30" name="jobDescribe" id="job_desc"></textarea></dd>'+
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
		area: ['600px', '505px'],
		btn: ['确定', '取消'],
		yes: function(){
			 var startTime = $("#start_time").val();
			 if(typeof(startTime) == "undefined" || startTime == "" ){
				layer.msg("请选择开始时间");
			  	return
			 }
			 var endTime = $("#end_time").val();
			 if(typeof(endTime) == "undefined" || endTime == "" ){
				  layer.msg("请选择结束时间");
				  return
			 }
			 var name = $("#ent_name").val();
			 if(typeof(name) == "undefined" || name == "" ){
				  layer.msg("请输入公司名称");
				  return
			 }
			 var jobName = $("#job_name").val();
			 if(typeof(jobName) == "undefined" || jobName == "" ){
				  layer.msg("请输入职位名称");
				  return
			 }
			 var type = $("#indu_type").val();
			 if(typeof(type) == "undefined" || type == "" ){
				  layer.msg("请输入公司行业");
				  return
			 }
			 var nature = $("#ent_nature").val();
			 if(typeof(nature) == "undefined" || nature == "" ){
				  layer.msg("请输入公司性质");
				  return
			 }
			 var desc = $("#job_desc").val();
			 if(typeof(desc) == "undefined" || desc == "" ){
				  layer.msg("请输入职责描述");
				  return
			 }
			$.ajax({
			 	 url:'add_jobexp.do',
			 	 type:'post',
			 	 dataType:'json',
			 	 data:$("#add_jobexp").serialize(),
			 	 success:function(data){
					 layer.closeAll();
			 		 if(data.code == 1){
			 			 layer.msg("添加成功",{time:1000},function(){
			 				 window.location.reload();
			 			 });
			 		 }else{
			 			 layer.msg("添加失败");
			 		 }
			 	 }
			});
		}
	});
}

function addProject(obj,id){
	var html = '<div class="c-add-container">'+
					'<form action="" id="add_project">'+
						'<input type="hidden" name="cvId" value="'+id+'">'+
						'<dl>'+
						'<dd><span>开始时间：</span><input class="c-input-width01 Wdate" type="text" name="startTime" onclick="WdatePicker({dateFmt:\'yyyy.MM\',maxDate:\'%y-%M\',onpicked:function(){end_time.click();}})" id="start_time" readonly="readonly"><span>结束时间：</span><input class="c-input-width01 Wdate" type="text" name="endTime" id="end_time"  onclick="WdatePicker({dateFmt:\'yyyy.MM\',maxDate:\'%y-%M-%d\',minDate:\'#F{$dp.$D(\\\'start_time\\\')}\'})" readonly="readonly"></dd>'+
						'<dd><span>项目名称：</span><input class="c-input-width02" type="text" name="name" value="" id="pro_name"></dd>'+
							'<dd><span>软件环境：</span><input class="c-input-width02" type="text" name="software" value="" id="soft_ware"></dd>'+
							'<dd><span>硬件环境：</span><input class="c-input-width02" type="text" name="hardware" value="" id="hard_ware"></dd>'+
							'<dd><span>开发工具：</span><input class="c-input-width02" type="text" name="developmentTool" value="" id="user_tool"></dd>'+
						'<dd><span>项目简介：</span><textarea class="c-input-width02" rows="5" cols="30" name="describe" id="pro_desc"></textarea></dd>'+
						'<dd><span>项目职责：</span><textarea class="c-input-width02" rows="5" cols="30" name="duties" id="pro_duties"></textarea></dd>'+
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
		area: ['620px', '590px'],
		btn: ['确定', '取消'],
		yes: function(){
			var startTime = $("#start_time").val();
			 if(typeof(startTime) == "undefined" || startTime == "" ){
				layer.msg("请选择开始时间");
			  	return
			 }
			 var endTime = $("#end_time").val();
			 if(typeof(endTime) == "undefined" || endTime == "" ){
				  layer.msg("请选择结束时间");
				  return
			 }
			 var pro = $("#pro_name").val();
			 if(typeof(pro) == "undefined" || pro == "" ){
				  layer.msg("请输入项目名称");
				  return
			 }
			 var prodesc = $("#pro_desc").val();
			 if(typeof(prodesc) == "undefined" || prodesc == "" ){
				  layer.msg("请输入项目简介");
				  return
			 }
			 var produt = $("#pro_duties").val();
			 if(typeof(produt) == "undefined" || produt == "" ){
				  layer.msg("请输入职责描述");
				  return
			 }
			 
			$.ajax({
			 	 url:'add_project.do',
			 	 type:'post',
			 	 dataType:'json',
			 	 data:$("#add_project").serialize(),
			 	 success:function(data){
					 layer.closeAll();
			 		 if(data.code == 1){
			 			 layer.msg("添加成功",{time:1000},function(){
			 				 window.location.reload();
			 			 });
			 		 }else{
			 			 layer.msg("添加失败");
			 		 }
			 	 }
			});
		}
	});
}

function addTrain(obj,id){
	var html = '<div class="c-add-container">'+
					'<form action="" id="add_train">'+
						'<input type="hidden" name="cvId" value="'+id+'">'+
						'<dl>'+
							'<dd><span>培训机构：</span><input class="c-input-width02" type="text" name="name" value="" id="t_name"></dd>'+
							'<dd><span>培训地点：</span><input class="c-input-width02" type="text" name="place" value="" id="t_place"></dd>'+
							'<dd><span>所获证书：</span><input class="c-input-width02" type="text" name="certificate" value="" id="t_certiricate"></dd>'+
							'<dd><span>培训描述：</span><textarea class="c-input-width02" rows="5" cols="20" name="describe" id="t_desc"></textarea></dd>'+
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
		area: ['600px', '380px'],
		btn: ['确定', '取消'],
		yes: function(){
			var name = $("#t_name").val();
			if(typeof(name) == "undefined" || name ==""){
				layer.msg("请输入机构名称");
				return
			}
			var place = $("#t_place").val();
			if(typeof(place) == "undefined" || place ==""){
				layer.msg("请输入培训地点");
				return
			}
			var cert = $("#t_certiricate").val();
			if(typeof(cert) == "undefined" || cert ==""){
				layer.msg("请输入获得证书");
				return
			}
			var desc = $("#t_desc").val();
			if(typeof(desc) == "undefined" || desc == ""){
				layer.msg("请输入培训描述");
				return
			}
			$.ajax({
			 	 url:'add_train.do',
			 	 type:'post',
			 	 dataType:'json',
			 	 data:$("#add_train").serialize(),
			 	 success:function(data){
					 layer.closeAll();
			 		 if(data.code == 1){
			 			 layer.msg("添加成功",{time:1000},function(){
			 				 window.location.reload();
			 			 });
			 		 }else{
			 			 layer.msg("添加失败");
			 		 }
			 	 }
			});
		}
	});
}

function addLanguage(obj,id){
	var html = '<div class="c-add-container c-two-middle">'+
					'<form action="" id="add_language">'+
						'<input type="hidden" name="cvId" value="'+id+'">'+
						'<dl>'+
							'<dd><span>语种：</span><input class="c-input-width02" type="text" name="language" value="" id="lan_name"></dd>'+
							'<dd><span>熟练程度：</span><input class="c-input-width02" type="text" name="rwAbility" value="" id="lan_rwa"></dd>'+
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
			var name = $("#lan_name").val();
			if(typeof(name) == "undefined" || name == ""){
				layer.msg("请输入语言名称");
				return
			}
			var rwa = $("#lan_rwa").val();
			if(typeof(rwa) == "undefined" || rwa == ""){
				layer.msg("请输入熟练程度");
				return
			}
			$.ajax({
			 	 url:'add_language.do',
			 	 type:'post',
			 	 dataType:'json',
			 	 data:$("#add_language").serialize(),
			 	 success:function(data){
					 layer.closeAll();
			 		 if(data.code == 1){
			 			 layer.msg("添加成功",{time:1000},function(){
			 				 window.location.reload();
			 			 });
			 		 }else{
			 			 layer.msg("添加失败");
			 		 }
			 	 }
			});
		}
	});
}
function addSkill(obj,id){
	var html = '<div class="c-add-container c-two-middle">'+
					'<form action="" id="add_skill">'+
						'<input type="hidden" name="cvId" value="'+id+'">'+
						'<dl>'+
						'<dd><span>技能名称：</span><input class="c-input-width02" type="text" name="name" value="" id="sk_name"></dd>'+
						'<dd><span>熟练程度：</span><input class="c-input-width02" type="text" name="degree" value="" id="sk_deg"></dd>'+
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
			var name = $("#sk_name").val();
			if(typeof(name) == "undefined" || name == ""){
				layer.msg("请输入技能名称");
				return
			}
			var deg = $("#sk_deg").val();
			if(typeof(name) == "undefined" || name == ""){
				layer.msg("请输入熟练程度");
				return
			}
			$.ajax({
				 url:'add_skill.do',
				 type:'post',
				 dataType:'json',
				 data:$("#add_skill").serialize(),
				 success:function(data){
				 layer.closeAll();
					 if(data.code == 1){
						 layer.msg("添加成功",{time:1000},function(){
							 window.location.reload();
						 });
					 }else{
						 layer.msg("添加失败");
					 }
				 }
			});
		}
	});
}

function upEdu(obj,cvId,type){
	var len = $(obj).parents("div.sort_s").siblings().length;
	var index = $(obj).parents("div.sort_s").index();
    if(index > 0){
         var _obj = $(obj).parents("div.sort_s");
         var nowObj = _obj.clone(true);
         _obj.prev().before(nowObj);
         _obj.remove();
    } 
    loadSort(len+1,cvId,type);
}

function downEdu(obj,cvId,type){
	var index = $(obj).parents("div.sort_s").index();
    var len = $(obj).parents("div.sort_s").siblings().length;
    if(index < len){
         var _obj = $(obj).parents("div.sort_s");
         var nowObj = _obj.clone(true);
         _obj.next().after(nowObj);
         _obj.remove();
    }
    loadSort(len+1,cvId,type);
}

function loadSort(len,cvId,type){
	if(type == 1){
	  	$("#edu_sort").children("div.sort_s").each(function(i,o){
			var obj = $(o).find("dd.edu_s");
			$(obj).find("a.up_move").remove();
			$(obj).find("a.down_move").remove();
			if(i == 0){
				$(obj).append('<a class="i-icons i-movedown down_move" href="javascript:void(0)" onclick="downEdu(this,'+cvId+',1);">下移</a>');
			}else if(len-1 == i){
				$(obj).append('<a class="i-icons i-moveup up_move" href="javascript:void(0)" onclick="upEdu(this,'+cvId+',1);">上移</a>');
			}else if(i > 0 && len-1 > i){
				$(obj).append('<a class="i-icons i-moveup up_move" href="javascript:void(0)" onclick="upEdu(this,'+cvId+',1);">上移</a><a class="i-icons i-movedown down_move" href="javascript:void(0)" onclick="downEdu(this,'+cvId+',1);">下移</a>');
			}
		});
	  	eduSort(cvId);
	}else if(type == 2){
		$("#jobexp_sort").children("div.sort_s").each(function(i,o){
			var obj = $(o).find("dt");
			$(obj).find("a.up_move").remove();
			$(obj).find("a.down_move").remove();
			if(i == 0){
				$(obj).append('<a class="i-icons i-movedown down_move" href="javascript:void(0)" onclick="downEdu(this,'+cvId+',2);">下移</a>');
			}else if(len-1 == i){
				$(obj).append('<a class="i-icons i-moveup up_move" href="javascript:void(0)" onclick="upEdu(this,'+cvId+',2);">上移</a>');
			}else if(i > 0 && len-1 > i){
				$(obj).append('<a class="i-icons i-movedown up_move" href="javascript:void(0)" onclick="upEdu(this,'+cvId+',2);">上移</a><a class="i-icons i-movedown down_move" href="javascript:void(0)" onclick="downEdu(this,'+cvId+',2);">下移</a>');
			}
		});
		jobExpSort(cvId);
	}else if(type == 3){
		$("#sort_pro").children("div.sort_s").each(function(i,o){
			var obj = $(o).find("dt");
			$(obj).find("a.up_move").remove();
			$(obj).find("a.down_move").remove();
			if(i == 0){
				$(obj).append('<a class="i-icons i-movedown down_move" href="javascript:void(0)" onclick="downEdu(this,'+cvId+',3);">下移</a>');
			}else if(len-1 == i){
				$(obj).append('<a class="i-icons i-moveup up_move" href="javascript:void(0)" onclick="upEdu(this,'+cvId+',3);">上移</a>');
			}else if(i > 0 && len-1 > i){
				$(obj).append('<a class="i-icons i-moveup up_move" href="javascript:void(0)" onclick="upEdu(this,'+cvId+',3);">上移</a><a class="i-icons i-movedown down_move" href="javascript:void(0)" onclick="downEdu(this,'+cvId+',3);">下移</a>');
			}
		});
		proSort(cvId);
	}
}

function eduSort(cvId){
	var arr = [];
	$("#edu_sort").children("div.sort_s").each(function(i,o){
		var json = {};
		var id = $(o).attr("val_id");
		json.cvId = cvId;
		json.id = id;
		json.index = i;
		arr[i] = json;
	});
	if(arr.length > 0){
		$.ajax({
			url:'edu_save_sort.do',
			type:'post',
			dataType:'json',
			data:{'datas':JSON.stringify(arr)},
			success:function(data){
				if(data.code == 1){
					layer.msg("排序成功",{time:1000},function(){
						window.location.reload();
					});
				}else{
					layer.msg("排序失败");
				}
			}
		});
	}else{
		layer.msg("无排序数据");
	}
}

function jobExpSort(cvId){
	var arr = [];
	$("#jobexp_sort").children("div.sort_s").each(function(i,o){
		var json = {};
		var id = $(o).attr("val_id");
		json.cvId = cvId;
		json.id = id;
		json.index = i;
		arr[i] = json;
	});
	if(arr.length > 0){
		$.ajax({
			url:'jobexp_save_sort.do',
			type:'post',
			dataType:'json',
			data:{'datas':JSON.stringify(arr)},
			success:function(data){
				if(data.code == 1){
					layer.msg("排序成功",{time:1000},function(){
						window.location.reload();
					});
				}else{
					layer.msg("排序失败");
				}
			}
		});
	}else{
		layer.msg("无排序数据");
	}
}

function proSort(cvId){
	var arr = [];
	$("#sort_pro").children("div.sort_s").each(function(i,o){
		var json = {};
		var id = $(o).attr("val_id");
		json.cvId = cvId;
		json.id = id;
		json.index = i;
		arr[i] = json;
	});
	if(arr.length > 0){
		$.ajax({
			url:'pro_save_sort.do',
			type:'post',
			dataType:'json',
			data:{'datas':JSON.stringify(arr)},
			success:function(data){
				if(data.code == 1){
					layer.msg("排序成功",{time:1000},function(){
						window.location.reload();
					});
				}else{
					layer.msg("排序失败");
				}
			}
		});
	}else{
		layer.msg("无排序数据");
	}
}
function sureFun(obj){
	var str = new Array();
	var ids = new Array();
	var cus = $("#customer_id").find("option:selected").attr("number_val");
	$("#post_ul").find("input[checked='checked']").each(function(i){
		var txt = $(this).next().text();
		var id = $(this).val();
		ids.push(id);
		str.push(txt);
		if(i == 0){
			var val = $(this).attr("number_val");
			var cvId = $("#cv_id").val();
			var recommend = cus+""+val+""+cvId;
			$("#recommend_id").val(recommend);
		}
	});
	$("#post_job").val(str.join(","));
	$("#post_ids").val(ids.join(","));
	$("#post_div").hide();
}
function closeFun(obj){
	$("#post_div").hide();
}
function postFun(obj,cvId){
	var val = $(obj).find("option:selected").attr("number_val");
	var recommend = cus+""+val+""+cvId;
	$("#recommend_id").val(recommend);
}

function saveRecommend(){
	var ids = $("#post_ids").val();
	if(ids == ""){
		layer.msg("请选择推荐岗位");
		return
	}
	$("#recommend_form").submit();
}

function changeCusFun(obj){
	var cusId = $(obj).val();
	$("#post_ul").show();
	$("#post_ids").val("");
	$("#post_job").val("");
	var len = $("#post_ul li").length;
	$("#post_ul").empty();
	$.ajax({
		url:'/post/find_post.do',
		type:'post',
		dataType:'json',
		data:{'cusId':cusId},
		success:function(data){
			var objs = data.datas;
			var html = "";
			if(objs.length > 0){
				$.each(objs,function(i){
					var id = objs[i].pid;
					var name = objs[i].name;
					var num = objs[i].numbers;
					html+='<li><input type="checkbox" value="'+id+'" number_val="'+num+'"><label class="i-icons i-checkbox" >'+name+'</label></li>';
				});
				if(html.length > 0){
					$("#post_ul").append(html);
					$("#post_ul .i-checkbox").unbind("click");
					$("#post_ul .i-checkbox").on("click",function(){
						if($(this).hasClass("checked")){
							$(this).removeClass("checked").prev().removeAttr("checked");
						}else{
							var len = $("#post_ul").find("input[checked='checked']").length;
							if(len < 5){
								$(this).addClass("checked").prev().attr("checked","checked");
								var val = $(this).text()
							}else{
								layer.msg("最多可选择5个岗位");
							}
						}
					});
				}else{
					$("#post_div").hide();
				}
			}else{
				layer.msg("请先完成岗位设置，再进行数据编辑",{time:1000},function(){
					$("#post_div").hide();
				});
			}
		}
	});
}
</script>