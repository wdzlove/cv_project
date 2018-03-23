<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑</title>
<link rel="stylesheet" type="text/css" href="/css/layui.css">
<link rel="stylesheet" type="text/css" href="/css/info.css">
<script type="text/javascript" language="javascript" src="/js/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" language="javascript" src="/js/jquery/jquery.form.js"></script>
<script type="text/javascript" language="javascript" src="/js/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript" language="javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" language="javascript" src="https://cdn.bootcss.com/cropper/4.0.0-beta/cropper.js"></script>
</head>
<body>
<div class="g-wrap">
	<div class="c-title-box">
		<div class="c-main-title">编辑简历</div>
	</div>
	<div class="c-content-box c-edit">
		<div class="c-ovh">
			<div class="c-right-con c-fr">
				<input type="hidden" id="cv_id" value="${entity.id}">
				<%-- <div class="c-head-pic ">
					<c:if test="${!empty  userPhoto}">
						<img id="head_img" src="${userPhoto}" alt=""/>
					</c:if>
					<c:if test="${empty  userPhoto}">
						<img id="head_img" src="/img/grils.jpg" alt=""/>
					</c:if>
				</div> --%>
				<div class="c-head-pic head_input">
					<form action="" id="head_form">
					      <div class="upload_input" >
					      	  <img src="${userPhoto}">
						      <input name="file" type="file"  accept="image/png, image/jpeg, image/jpg" onchange="imgChange(this);" title="点击更换/上传图片(建议上传280px*280px)"/>
					      </div>
					      <input name="cvId" type="hidden" value="${entity.id}"/>
					</form>
				 </div>
				<div  id="base_div">
					<dl>
						<dt>基本信息   <a class="c-btn c-btn-xs" href="javascript:void(0);" onclick="editBase(this)">编辑</a></dt>
						<dd><span>姓名：</span>${entity.name}</dd>
						<dd><span>性别：</span>${entity.sex}</dd>
						<dd><span>年龄：</span>${entity.age}</dd>
						<dd><span>婚姻：</span>${entity.marriage}	</dd>
						<dd><span>工作年限：</span>${entity.joinWorkTime}</dd>
						<dd><span>现居地址：</span>${entity.nowLiveCity}</dd>
					</dl>
					<dl>
						<dt>联系方式</dt>
						<dd><span>联系电话：</span>${entity.mobile}</dd>
						<dd><span>电子邮箱：</span>${entity.email}</dd>
						<dd><span>家庭地址：</span>${entity.address}</dd>
					</dl>
				</div>
				<div style="display: none;" id="base_edit">
					<form id="base_form" method="post">
						<dl>
							<dt>基本信息 </dt>
							<dd><span>姓名：</span><input name="name" value="${entity.name}"></dd>
							<dd><span>性别：</span><input name="sex" value="${entity.sex}"></dd>
							<dd><span>年龄：</span><input name="age" value="${entity.age}"></dd>
							<dd><span>婚姻：</span><input name="marriage" value="${entity.marriage}">	</dd>
							<dd><span>工作年限：</span><input name="joinWorkTime" value="${entity.joinWorkTime}"></dd>
							<dd><span>现居地址：</span><input  name="nowLiveCity" value="${entity.nowLiveCity}"></dd>
						</dl>
						<dl>
							<dt>联系方式</dt>
							<dd><span>联系电话：</span><input name="mobile" value="${entity.mobile}"></dd>
							<dd><span>电子邮箱：</span><input name="email" value="${entity.email}"></dd>
							<dd><span>家庭地址：</span><input name="address" value="${entity.address}"></dd>
						</dl>
							<input type="hidden" name="id" value="${entity.id}">
							<div class="c-btn-box">
							<a href="javascript:void(0)" onclick="updateBase()" class="c-btn">保存</a>
							<a class=" c-btn c-cancel" href="javascript:void(0);" onclick="closeBase(this)">取消</a>
							</div>
						</form>
				</div>
			</div>
			<form action="/save_recommend.do" method="post" id="recommend_form">
				<input type="hidden" name="cvId" value="${entity.id}">
				<input type="hidden" name="id" value="${recom.id}">
				
				<div class="c-left-con">
					<dl class="c-info-title">
						<dd><span>ID： </span><input type="text" name="recommendId" id="recommend_id" value="${recom.recommendId}" readonly="readonly"></dd>
						<dd><span>推荐企业：</span><%-- <input type="text" name="entName" value="${recom.entName}"> --%>
							<div class="layui-input-inline">
								<select lay-verify="required" lay-search="" name="customerId" id="customer_id" onchange="changeCusFun(this);">
									<c:forEach items="${customer}" var="c">
										<option value="${c.id}" number_val="${c.number}" <c:if test="${c.id == recom.customerId}">selected="selected"</c:if>>${c.name}</option>
									</c:forEach>
								</select>
							</div>
						</dd>
						<dd><span>推荐岗位：</span>
							<div class="layui-input-inline">
								<input type="text" name="job" value="${recom.job}" onclick="showFun(this);" id="post_job" readonly="readonly">
								<input type="hidden" name="postIds" value="${recom.jobIds}" id="post_ids" >
								<div style="display:none" id="post_div"  class="c-ul-outer">
								<ul id="post_ul" >
									<c:forEach items="${post}" var="p">
										<li><input type="checkbox" value="${p.id}" number_val="${p.number}"><label class="i-icons i-checkbox" >${p.name}</label></li>
									</c:forEach>
										<%--<li class="c-btnbox">--%>
											<%--<a href="javascript:void(0);" onclick="sureFun(this);" class="c-btn">确定</a>--%>
											<%--<a href="javascript:void(0);" onclick="sureFun(this);" class="c-btn c-cancel">关闭</a>--%>
										<%--</li>--%>
								</ul>
								<div class="c-btnbox">
									<a href="javascript:void(0);" onclick="sureFun(this);" class="c-btn">确定</a>
									<a href="javascript:void(0);" onclick="closeFun(this);" class="c-btn c-cancel">关闭</a>
								</div>
								</div>
							</div>
						</dd>
					</dl>
					<div class="c-title">候选人评估：</div>
					<ul class="c-list">
						<li>
							<span><i class="c-dot">·</i>基本情况：</span>
							<textarea rows="" cols="" name="baseInfo">${recom.baseInfo}</textarea>
						</li>
						<li>
							<span><i class="c-dot">·</i>离职原因：</span>
							<textarea rows="" cols="" name="leaveReason">${recom.leaveReason}</textarea>
						</li>
						<li>
							<span><i class="c-dot">·</i>薪酬情况：</span>
							<input type="text" name="salary" value="${recom.salary}">
						</li>
						<li>
							<span><i class="c-dot">·</i>期望岗位：</span>
							<input type="text" name="hopeJob" value="${recom.hopeJob}">
						</li>
						<li>
							<span><i class="c-dot">·</i>推荐理由：</span>
							<textarea rows="" cols="" name="reason" >${recom.reason}</textarea>
						</li>
						<li>
							<span><i class="c-dot">·</i>面试时间：</span>
							<input type="text" name="faceTime" value="${recom.faceTime}">
						</li>
						<li>
							<span><i class="c-dot">·</i>入职时间：</span>
							<input type="text" name="inJobTime"  value="${recom.inJobTime}">
						</li>
					</ul>
					
						<div class="c-btn-box">
							<a href="javascript:void(0);" onclick="saveRecommend();" class="c-btn c-btn-large">保存</a>
						</div>
				</div>
				
			</form>
		</div>
		<div class="c-info-box">
			<div class="c-title">
				<div class="c-iconbox"><a class="i-icons i-add" href="javascript:void(0)" onclick="addEducation(this,${entity.id});">添加</a></div>
			教育经历：
			</div>
			<div id="edu_sort">
				<c:forEach items="${entity.cves}" var="edu" varStatus="edu_i">
					<div val_id="${edu.id}" class="sort_s">
						<dl>
							<dd class="c-nopadd edu_s">
								<div class="c-iconbox">
								<a class="i-icons i-edit" href="javascript:void(0)" onclick="editEducation(this);">编辑</a>
								<a class="i-icons i-delete" href="javascript:void(0)" onclick="delEdu(this,${edu.id},${entity.id});">删除</a>
								<c:if test="${edu_i.index > 0}">
									<a class="i-icons i-moveup up_move" href="javascript:void(0)" onclick="upEdu(this,${entity.id},1);">上移</a>
								</c:if>
								<c:if test="${edu_i.index < fn:length(entity.cves)-1}">
									<a class="i-icons i-movedown down_move" href="javascript:void(0)" onclick="downEdu(this,${entity.id},1);">下移</a>
								</c:if>
								</div>
								${edu.startTime} - ${edu.endTime} ${edu.school} ${edu.majorName} ${edu.education}
							</dd>
						</dl>
						<div class="c-edit-container">
							<form action="" id="edu_form_${edu.id}">
								<input type="hidden" name="id" value="${edu.id}">
								<input type="hidden" name="cvId" value="${entity.id}">
								<dl>
								<dd><span>开始时间：</span><input class="c-input-width01 Wdate" type="text" name="startTime" value="${edu.startTime}" onclick="WdatePicker({dateFmt:'yyyy.MM',maxDate:'%y-%M',onpicked:function(){e_end_time.click();}})" id="e_start_time" readonly="readonly"><span>结束时间：</span><input class="c-input-width01 Wdate" type="text" name="endTime" value="${edu.endTime}"  id="e_end_time"  onclick="WdatePicker({dateFmt:'yyyy.MM',maxDate:'%y-%M',minDate:'#F{$dp.$D(\'e_start_time\')}'})" readonly="readonly"></dd>
								<dd><span>学校名称：</span><input class="c-input-width02" type="text" name="school" value="${edu.school}"></dd>
								<dd><span>专业名称：</span><input class="c-input-width02" type="text" name="majorName" value="${edu.majorName}"></dd>
								<dd><span>学历：</span><input class="c-input-width02" type="text" name="education" value="${edu.education}"></dd>
								</dl>
								<div class="c-btn-box">
									<a class="c-btn" href="javascript:void(0);" onclick="updateEducation(${edu.id})">保存</a>
									<a class="c-btn c-cancel" href="javascript:void(0)" onclick="closeEdit(this)">取消</a>
								</div>
							</form>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="c-info-box">
			<div class="c-title">
				<div class="c-iconbox"><a class="i-icons i-add" href="javascript:void(0)" onclick="addJobExp(this,${entity.id});">添加</a></div>
			工作经历：
			</div>
			<div id="jobexp_sort">
				<c:forEach items="${entity.cvjs}" var="cvjs" varStatus="job_i">
					<div val_id="${cvjs.id}" class="sort_s">
						<dl>
							<dt>
							 	<div class="c-iconbox">
							 	<a class="i-icons i-edit" href="javascript:void(0)" onclick="editJob(this);">编辑</a>
								<a class="i-icons i-delete" href="javascript:void(0)" onclick="delJobExp(this,${cvjs.id},${entity.id});">删除</a>
								<c:if test="${job_i.index > 0}">
									<a class="i-icons i-moveup up_move" href="javascript:void(0)" onclick="upEdu(this,${entity.id},2);">上移</a>
								</c:if>
								<c:if test="${job_i.index < fn:length(entity.cvjs)-1}">
									<a class="i-icons i-movedown down_move" href="javascript:void(0)" onclick="downEdu(this,${entity.id},2);">下移</a>
								</c:if>
								</div>
								${cvjs.starTime} - ${cvjs.endTime}  ${cvjs.entName} 
							</dt>
							<c:if test="${!empty cvjs.jobName}">
								<dd><span>职位名称：</span>${cvjs.jobName}</dd>
							</c:if>
							<c:if test="${!empty cvjs.jobSalary}">
								<dd><span>职位薪资：</span>${cvjs.jobSalary}</dd>
							</c:if>
							<c:if test="${!empty cvjs.industryType}">
								<dd><span>公司行业：</span>${cvjs.industryType}</dd>
							</c:if>
							<c:if test="${!empty cvjs.entNature}">
								<dd><span>公司性质：</span>${cvjs.entNature}</dd>
							</c:if>
							<c:if test="${!empty cvjs.jobDescribe}">
								<dd><span>职责描述：</span>
									<ul>
										<li><p>${cvjs.jobDescribe}</p></li>
									</ul>
								</dd>
							</c:if>
						</dl>
						<div class="c-edit-container">
							<form action="" id="job_form_${cvjs.id}">
								<input type="hidden" name="id" value="${cvjs.id}">
								<input type="hidden" name="cvId" value="${entity.id}">
								<dl>
									<dd>
									<span>开始时间：</span><input class="c-input-width01  Wdate" type="text" name="starTime" value="${cvjs.starTime}" onclick="WdatePicker({dateFmt:'yyyy.MM',maxDate:'%y-%M',onpicked:function(){j_end_time.click();}})" id="j_start_time" readonly="readonly"><span>结束时间：</span><input class="c-input-width01 Wdate" type="text" name="endTime" value="${edu.endTime}"  id="j_end_time"  onclick="WdatePicker({dateFmt:'yyyy.MM',maxDate:'%y-%M',minDate:'#F{$dp.$D(\'j_start_time\')}'})" readonly="readonly">
									</dd>
									<dd><span>公司名称：</span><input class="c-input-width02" type="text" name="entName" value="${cvjs.entName}"></dd>
									<dd><span>职位名称：</span><input class="c-input-width02" type="text" name="jobName" value="${cvjs.jobName}"></dd>
									<dd><span>职位薪资：</span><input class="c-input-width02" type="text" name="jobSalary" value="${cvjs.jobSalary}"></dd>
									<dd><span>公司行业：</span><input class="c-input-width02" type="text" name="industryType" value="${cvjs.industryType}"></dd>
									<dd><span>公司性质：</span><input class="c-input-width02" type="text" name="entNature" value="${cvjs.entNature}"></dd>
									<dd><span>职责描述：</span><textarea class="c-input-width02" rows="5" cols="30" name="jobDescribe" id="work_desc">${cvjs.jobDescribe}</textarea></dd>
								</dl>
								<div class="c-btn-box">
									<a class="c-btn" href="javascript:void(0);" onclick="updateJob(${cvjs.id})">保存</a>
									<a class="c-btn c-cancel" href="javascript:void(0)" onclick="closeEdit(this)">取消</a>
								</div>
							</form>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="c-info-box">
			<div class="c-title">
				<div class="c-iconbox"><a class="i-icons i-add" href="javascript:void(0)" onclick="addProject(this,${entity.id});">添加</a></div>
			项目业绩：
			</div>
			<div id="sort_pro">
				<c:forEach items="${entity.cvps}" var="cvp" varStatus="pro_i">
					<div val_id="${cvp.id}" class="sort_s">
						<dl>
							<dt> 
								<div class="c-iconbox">
								<a class="i-icons i-edit" href="javascript:void(0)" onclick="editPro(this);">编辑</a>
								<a class="i-icons i-delete" href="javascript:void(0)" onclick="delPro(this,${cvp.id},${entity.id});">删除</a>
								<c:if test="${pro_i.index > 0}">
									<a class="i-icons i-moveup up_move" href="javascript:void(0)" onclick="upEdu(this,${entity.id},3);">上移</a>
								</c:if>
								<c:if test="${pro_i.index != fn:length(entity.cvps)-1}">
									<a class="i-icons i-movedown down_move" href="javascript:void(0)" onclick="downEdu(this,${entity.id},3);">下移</a>
								</c:if>
								</div>
								${cvp.startTime} - ${cvp.endTime} ${cvp.name} 
							</dt>
							<c:if test="${!empty cvp.software}">
								<dd><span>软件环境：</span>${cvp.software}</dd>
							</c:if>
							<c:if test="${!empty cvp.hardware}">
								<dd><span>硬件环境：</span>${cvp.hardware}</dd>
							</c:if>
							<c:if test="${!empty cvp.developmentTool}">
								<dd><span>开发工具：</span>${cvp.developmentTool}</dd>
							</c:if>
							<c:if test="${!empty cvp.describe}">
								<dd><span>项目简介：</span>
								<ul>
										<li><p>${cvp.describe}</p></li>
									</ul>
								</dd>
							</c:if>
							<c:if test="${!empty cvp.duties}">
								<dd><span>项目职责：</span>
									<ul>
										<li><p>${cvp.duties}</p></li>
									</ul>
								</dd>
							</c:if>
						</dl>
						<div class="c-edit-container">
							<form action="" id="pro_form_${cvp.id}">
								<input type="hidden" name="id" value="${cvp.id}">
								<input type="hidden" name="cvId" value="${entity.id}">
								<dl>
								<dd><span>开始时间：</span><input class="c-input-width01 Wdate" type="text" name="startTime" value="${cvp.startTime}" onclick="WdatePicker({dateFmt:'yyyy.MM',maxDate:'%y-%M',onpicked:function(){p_end_time.click();}})" id="p_start_time" readonly="readonly"><span>结束时间：</span><input class="c-input-width01 Wdate" type="text" name="endTime" value="${cvp.endTime}" id="p_end_time"  onclick="WdatePicker({dateFmt:'yyyy.MM',maxDate:'%y-%M',minDate:'#F{$dp.$D(\'p_start_time\')}'})" readonly="readonly"><br>
								<dd><span>项目名称：</span><input class="c-input-width02" type="text" name="name" value="${cvp.name}"></dd>
								<c:if test="${!empty cvp.software  || !empty cvp.hardware || !empty cvp.developmentTool}">
									<dd><span>软件环境：</span><input class="c-input-width02" type="text" name="software" value="${cvp.software}"></dd>
									<dd><span>硬件环境：</span><input class="c-input-width02" type="text" name="hardware" value="${cvp.hardware}"></dd>
									<dd><span>开发工具：</span><input class="c-input-width02" type="text" name="developmentTool" value="${cvp.developmentTool}"></dd>
								</c:if>
								<dd><span>项目简介：</span><textarea class="c-input-width02" rows="5" cols="30" name="describe">${cvp.describe}</textarea></dd>
								<dd><span>项目职责：</span><textarea class="c-input-width02" rows="5" cols="30" name="duties" >${cvp.duties}</textarea></dd>
								</dl>
								<div class="c-btn-box">
									<a class="c-btn" href="javascript:void(0);" onclick="updatePro(${cvp.id})">保存</a>
									<a class="c-btn c-cancel" href="javascript:void(0)" onclick="closeEdit(this)">取消</a>
								</div>
							</form>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="c-info-box">
			<div class="c-title">
				<div class="c-iconbox"><a class="i-icons i-edit" href="javascript:void(0);" onclick="editHope(this);">编辑</a>
				</div>
				求职意向：
			</div>
			<dl>
				<dd><span>期望工作性质：</span>${intention.jobNature}</dd>
				<dd><span>期望从事职业：</span>${intention.hopeJob}</dd>
				<dd><span>期望从事行业：</span>${intention.hopeIndustry}</dd>
				<dd><span>期望工作地区：</span>${intention.hopeWorkPlace}</dd>
				<dd><span>期望月薪：	</span>${intention.hopeSalary}</dd>
			</dl>
			<div id="hope_job_dl" class="c-edit-container">
				<form action="" id="hope_form">
					<input type="hidden" name="id" value="${intention.id}">
					<input type="hidden" name="cvId" value="${entity.id}">
					<dl>
					<dd><span>期望工作性质：</span><input class="c-input-width02" type="text" name="jobNature" value="${intention.jobNature}"></dd>
					<dd><span>期望从事职业：</span><input class="c-input-width02" type="text" name="hopeJob" value="${intention.hopeJob}"></dd>
					<dd><span>期望从事行业：</span><input class="c-input-width02" type="text" name="hopeIndustry" value="${intention.hopeIndustry}"></dd>
					<dd><span>期望工作地区：</span><input class="c-input-width02" type="text" name="hopeWorkPlace" value="${intention.hopeWorkPlace}"></dd>
					<dd><span>期望月薪：</span><input class="c-input-width02" type="text" name="hopeSalary" value="${intention.hopeSalary}"></dd>
					</dl>
					<div class="c-btn-box">
						<a class="c-btn" href="javascript:void(0);" onclick="updateHope()">保存</a>
						<a class="c-btn c-cancel" href="javascript:void(0)" onclick="closeEdit(this)">取消</a>
					</div>
				</form>
			</div>
		</div>
		<div class="c-info-box">
			<div class="c-title">
				<div class="c-iconbox"><a class="i-icons i-add" href="javascript:void(0)" onclick="addTrain(this,${entity.id});">添加</a></div>
				培训经历：
			</div>
			<c:forEach items="${entity.cvts}" var="cvt">
				<dl>
					<c:if test="${!empty cvt.name}">
						<dd>  
							<div class="c-iconbox">
							<a class="i-icons i-edit" href="javascript:void(0);" onclick="editTrain(this);">编辑</a>
							<a class="i-icons i-delete" href="javascript:void(0);" onclick="delTrain(this,${cvt.id},${entity.id});">编辑</a>
							</div>
							<span>培训机构：</span>${cvt.name} 
						</dd>
					</c:if>
					<c:if test="${!empty cvt.place}">
						<dd><span>培训地点：</span>${cvt.place}</dd>
					</c:if>
					<c:if test="${!empty cvt.certificate}">
						<dd><span>所获证书：</span>${cvt.certificate}</dd>
					</c:if>
					<c:if test="${!empty cvt.describe}">
						<dd><span>培训描述：</span>${cvt.describe}</dd>
					</c:if>
				</dl>
				<div class="c-edit-container">
					<form action="" id="train_form_${cvt.id}">
						<input type="hidden" name="id" value="${cvt.id}">
						<input type="hidden" name="cvId" value="${entity.id}">
						<dl>
							<dd><span>培训机构：</span><input class="c-input-width02" type="text" name="name" value="${cvt.name}"></dd>
							<dd><span>培训地点：</span><input class="c-input-width02" type="text" name="place" value="${cvt.place}"></dd>
							<dd><span>所获证书：</span><input class="c-input-width02" type="text" name="certificate" value="${cvt.certificate}"></dd>
							<dd><span>培训描述：</span><textarea class="c-input-width02" rows="5" cols="20" name="describe" >${cvt.describe}</textarea></dd>
						</dl>
						<div class="c-btn-box">
						<a class="c-btn" href="javascript:void(0)" onclick="updateTrain(${cvt.id});">保存</a>
						<a class="c-btn c-cancel" href="javascript:void(0)" onclick="closeEdit(this)">取消</a>
						</div>
					</form>
				</div>
			</c:forEach>
		</div>
		<div class="c-info-box">
			<div class="c-title">
				<div class="c-iconbox"><a class="i-icons i-add" href="javascript:void(0)" onclick="addLanguage(this,${entity.id});">添加</a></div>
			语言能力：
			</div>
			<c:forEach items="${entity.cvls}" var="cvl">
				<dl>
					<dd class="c-nopadd"> 
						<div class="c-iconbox">
						<a class="i-icons i-edit" href="javascript:void(0);" onclick="editLanguage(this);">编辑</a>
						<a class="i-icons i-delete" href="javascript:void(0);" onclick="delLanguage(this,${cvl.id},${entity.id});">删除</a>
						</div>
						${cvl.language}：${cvl.rwAbility} 
					</dd>
				</dl>
				<div class="c-edit-container">
					<form action="" id="language_form_${cvl.id}">
						<input type="hidden" name="id" value="${cvl.id}">
						<input type="hidden" name="cvId" value="${entity.id}">
						<dl>
							<dd><span>语种：</span><input class="c-input-width02" type="text" name="language" value="${cvl.language}"></dd>
							<dd><span>熟练程度：</span><input class="c-input-width02" type="text" name="rwAbility" value="${cvl.rwAbility}"></dd>
						</dl>
						<div class="c-btn-box">
						<a class="c-btn" href="javascript:void(0)" onclick="updateLanguage(${cvl.id});">保存</a>
						<a class="c-btn c-cancel" href="javascript:void(0)" onclick="closeEdit(this)">取消</a>
						</div>
					</form>
				</div>
			</c:forEach>
		</div>
		<div class="c-info-box">
			<div class="c-title">
			<div class="c-iconbox"><a class="i-icons i-add" href="javascript:void(0)" onclick="addSkill(this,${entity.id});">添加</a></div>
			专业技能：</div>
			<c:forEach items="${entity.cvks}" var="cvk">
				<dl>
					<dd class="c-nopadd">  
						<div class="c-iconbox">
						<a class="i-icons i-edit" href="javascript:void(0);" onclick="editSkill(this);">编辑</a>
						<a class="i-icons i-delete" href="javascript:void(0);" onclick="delSkill(this,${cvk.id},${entity.id});">删除</a>
						</div>
						${cvk.name}：${cvk.degree} 
					</dd>
				</dl>
				<div class="c-edit-container">
					<form action="" id="skill_form_${cvk.id}">
						<input type="hidden" name="id" value="${cvk.id}">
						<input type="hidden" name="cvId" value="${entity.id}">
						<dl>
						<dd><span>技能名称：</span><textarea class="c-input-width02" rows="5" cols="60"  name="name">${cvk.name}</textarea></dd>
						<dd><span>熟练程度：</span><input class="c-input-width02" type="text" name="degree" value="${cvk.degree}"></dd>
						</dl>
						<div class="c-btn-box">
						<a class="c-btn" href="javascript:void(0)" onclick="updateSkill(${cvk.id});">保存</a>
						<a class="c-btn c-cancel" href="javascript:void(0)" onclick="closeEdit(this)">取消</a>
						</div>
					</form>
				</div>
			</c:forEach>
		</div>
		<c:if test="${!empty entity.hobby}">
			<div class="c-info-box">
				<div class="c-title">
					<div class="c-iconbox"><a class="i-icons i-edit" href="javascript:void(0);" onclick="editHobby(this);">编辑</a></div>
				兴趣爱好：
				</div>
				<dl>
					<dd class="c-nopadd">${entity.hobby}</dd>
				</dl>
				<div id="hobby_dl" class="c-edit-container">
					<form action="" id="hobby_form">
						<input name="id" type="hidden"  value="${entity.id}">
						<dl>
							<dd><textarea rows="5" cols="30" name="hobby" >${entity.hobby}</textarea></dd>
						</dl>
						<div class="c-btn-box">
						<a class="c-btn" href="javascript:void(0)" onclick="updateHobby();">保存</a>
						<a class="c-btn c-cancel" href="javascript:void(0)" onclick="closeEdit(this,'train_form')">取消</a>
						</div>
					</form>
				</div>
			</div>
		</c:if>
		<c:if test="${!empty intention.jobStatus}">
			<div class="c-info-box">
			<div class="c-title">
				<div class="c-iconbox"><a class="i-icons i-edit" href="javascript:void(0);" onclick="editStatus(this)">编辑</a></div>
			目前状态：
			</div>
			<dl>
				<dd class="c-nopadd">${intention.jobStatus}</dd>
			</dl>
			<div id="status_dl" class="c-edit-container">
				<form action="" id="status_form">
					<input name="id" type="hidden"  value="${intention.id}">
					<input name="cvId" type="hidden"  value="${entity.id}">
					<dl>
						<dd><textarea rows="5" cols="30" name="jobStatus" >${intention.jobStatus}</textarea></dd>
					</dl>
					<div class="c-btn-box">
						<a class="c-btn" href="javascript:void(0)" onclick="updateStatus();">保存</a>
						<a class="c-btn c-cancel" href="javascript:void(0)" onclick="closeEdit(this,'train_form')">取消</a>
					</div>
				</form>
			</div>
		</div>
		</c:if>
	</div>
</div>
<jsp:include page="edit_js.jsp"></jsp:include>
<script type="text/javascript">
function showFun(obj){
	$("#post_div").show();
}
</script>
</body>
</html>