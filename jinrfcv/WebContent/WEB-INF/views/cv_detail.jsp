<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>详细</title>
<link rel="stylesheet" type="text/css" href="/css/layui.css">
<link rel="stylesheet" type="text/css" href="/css/info.css">
<script type="text/javascript" language="javascript" src="/js/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" language="javascript" src="/js/jquery/jquery.form.js"></script>
<script type="text/javascript" language="javascript" src="/js/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript" language="javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div class="g-wrap">
	<div class="c-title-box">
		<div class="c-fr c-edit-btns">
			<a href="/cv_edit.do?id=${entity.id}">编辑</a>
		</div>
		<div class="c-main-title">简历详情</div>
	</div>	
	<div class="c-content-box">
		
		<div class="c-ovh">
			<div class="c-right-con c-fr">
				<div class="c-head-pic">
					<img src="${userPhoto}" alt=""/>
				</div>
				<dl>
					<dt>基本信息</dt>
					<dd><span>姓名：</span>${entity.name}</dd>
					<dd><span>性别：</span>${entity.sex}</dd>
					<dd><span>年龄：</span>${entity.age}</dd>
					<dd><span>学历：</span>${entity.education}</dd>
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
			<div class="c-left-con">
				<dl class="c-info-title">
					<dd><span>ID：</span> ${recom.recommendId}</dd>
					<dd><span>推荐企业：</span>${recom.entName}</dd>
					<dd><span>推荐岗位：</span>
						<c:forEach items="${recom.posts}" var="post">
							<a href="javascript:void(0);" onclick="dutyFun('${post.postName}',${post.posId})" title="点击查看职责">
							<input type="hidden" value="${post.duty}" id="duty_${post.posId}">
							${post.postName}</a>
						</c:forEach>
					</dd>
				</dl>
				<div class="c-title">候选人评估：</div>
				<ul class="c-list">
					<li>
						<span><i class="c-dot">·</i>基本情况：</span>${recom.baseInfo}
					</li>
					<li>
						<span><i class="c-dot">·</i>离职原因：</span>${recom.leaveReason}
					</li>
					<li><span><i class="c-dot">·</i>薪酬情况：</span>${recom.salary}</li>
					<li><span><i class="c-dot">·</i>期望岗位：</span>${recom.hopeJob}</li>
					<li><span><i class="c-dot">·</i>推荐理由：</span>${recom.reason}</li>
					<li><span><i class="c-dot">·</i>面试时间：</span>${recom.faceTime}</li>
					<li><span><i class="c-dot">·</i>入职时间：</span>${recom.inJobTime}</li>
				</ul>
			</div>
		</div>
		<c:if test="${!empty entity.cves && fn:length(entity.cves) > 0 }">
			<div class="c-info-box">
				<div class="c-title">教育经历：</div>
				<c:forEach items="${entity.cves}" var="edu">
					<dl>
						<dd class="c-nopadd">${edu.startTime} - ${edu.endTime} ${edu.school} ${edu.majorName} ${edu.education}</dd>
					</dl>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${!empty entity.cvjs && fn:length(entity.cvjs) > 0 }">
			<div class="c-info-box">
				<div class="c-title">工作经历：</div>
					<c:forEach items="${entity.cvjs}" var="cvjs">
					<dl>
						<dt>${cvjs.starTime} - ${cvjs.endTime}  ${cvjs.entName}</dt>
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
							<dd><span>职责业绩：</span>
								<ul>
									<li><p>${cvjs.jobDescribe}</p></li>
								</ul>
							</dd>
						</c:if>
					</dl>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${!empty entity.cvps && fn:length(entity.cvps) > 0 }">
			<div class="c-info-box">
				<div class="c-title">项目业绩：</div>
				<c:forEach items="${entity.cvps}" var="cvp">
					<dl>
						<dt>${cvp.startTime} - ${cvp.endTime} ${cvp.name}</dt>
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
				</c:forEach>
			</div>
		</c:if>
		<div class="c-info-box">
			<div class="c-title">求职意向：</div>
			<dl>
				<c:if test="${!empty intention.jobNature}">
					<dd><span>期望工作性质：</span>${intention.jobNature}</dd>
				</c:if>
				<c:if test="${!empty intention.hopeJob}">
					<dd><span>期望从事职业：</span>${intention.hopeJob}</dd>
				</c:if>
				<c:if test="${!empty intention.hopeIndustry}">
					<dd><span>期望从事行业：</span>${intention.hopeIndustry}</dd>
				</c:if>
				<c:if test="${!empty intention.hopeWorkPlace}">
					<dd><span>期望工作地区：</span>${intention.hopeWorkPlace}</dd>
				</c:if>
				<c:if test="${!empty intention.hopeSalary}">
					<dd><span>期望月薪：	</span>${intention.hopeSalary}</dd>
				</c:if>
			</dl>
		</div>
		<c:if test="${!empty entity.cvts && fn:length(entity.cvts) > 0 }">
			<div class="c-info-box">
				<div class="c-title">培训经历：</div>
				<c:forEach items="${entity.cvts}" var="cvt">
					<dl>
						<c:if test="${!empty cvt.name}">
							<dd><span>培训机构：</span>${cvt.name}</dd>
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
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${!empty entity.cvls && fn:length(entity.cvls) > 0 }">
			<div class="c-info-box">
				<div class="c-title">语言能力：</div>
				<c:forEach items="${entity.cvls}" var="cvl">
					<dl>
						<dd class="c-nopadd">${cvl.language}：${cvl.rwAbility}</dd>
					</dl>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${!empty entity.cvks && fn:length(entity.cvks) > 0 }">
			<div class="c-info-box">
				<div class="c-title">专业技能：</div>
				<c:forEach items="${entity.cvks}" var="cvk">
					<dl>
						<dd class="c-nopadd">${cvk.name}：${cvk.degree}</dd>
					</dl>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${!empty entity.hobby}">
			<div class="c-info-box">
				<div class="c-title">兴趣爱好：</div>
				<dl>
					<dd class="c-nopadd">${entity.hobby}</dd>
				</dl>
			</div>
		</c:if>
		<c:if test="${!empty intention.jobStatus}">
			<div class="c-info-box">
				<div class="c-title">目前状态：</div>
				<dl>
					<dd class="c-nopadd">${intention.jobStatus}</dd>
				</dl>
			</div>
		</c:if>
	</div>
</div>
<script type="text/javascript">
	function dutyFun(name,id){
		var duty = $("#duty_"+id).val();
		layer.open({
			  title: name+'岗位职责'
			  ,skin: 'demo-class'
			  ,area:["400px","200px"]
			  ,content: duty
			});
	}
</script>
</body>
</html>