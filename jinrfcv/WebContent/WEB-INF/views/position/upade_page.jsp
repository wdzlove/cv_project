<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript" language="javascript" src="/js/wangEditor.js"></script>
<form action="" id="add_position">
	<input type="hidden" name="id" id="h_post_id" value="${p.id}">
	<dl>
		<dd><span>岗位编号：</span><input class="c-input-width02" type="text" name="number" value="${p.number}" id="p_number" maxLength="3" onkeyup="value=value.replace(/[^\d]/g,'') " ></dd>
		<dd>
			<span>关联客户：</span>
			<select name="customerId" class="c-input-width02">
				<c:forEach items="${findList}" var="list">
					<option value="${list.id}">${list.name}</option>
				</c:forEach>
			</select>
		</dd>
		<dd><span>岗位名称：</span><input class="c-input-width02" type="text" name="name" value="${p.name}" id="p_name"></dd>
		<dd><span>招聘人数：</span><input class="c-input-width02" type="text" name="recruitNum" value="${p.recruitNum}" id="recruit_num" maxLength="4" onkeyup="value=value.replace(/[^\d]/g,'') " ></dd>
		<dd>
			<span>岗位职责：</span>
			<input name="duty" type="hidden" value="" id="h_duty">
			<div class="c-input-width02 c-editer">
				<div id="p_duty">
					<p>${p.duty}</p>
				</div>
			</div>
		</dd>
	</dl>
</form>
<script>
$(function(){
	createEditor();
});
var editor;
function createEditor(){
   // 获取元素
    var div = document.getElementById('p_duty');
    // 生成编辑器
    editor = new wangEditor(div);
    editor.customConfig.menus = ['head', 'bold', 'italic','foreColor', 'backColor', 'list', 'justify', 'quote', 'undo', 'redo']
    editor.create();
}
</script>
