package com.jrfcv.util;

import java.util.List;

/**
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *	分页对象
 * <p>
 *
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　pj　2016-1-18　<br/>
 * <p>
 *
 * @author pj
 *
 * @version 1.0, 2016-1-18
 *
 * @param <T>
 */
@SuppressWarnings("serial")
public class Pagination<T> extends SimplePage implements java.io.Serializable,
		Paginable {

	public Pagination() {
	}

	/**
	 * 构造器
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页几条数据
	 * @param totalCount
	 *            总共几条数据
	 */
	public Pagination(int pageNo, int pageSize, int totalCount) {
		super(pageNo, pageSize, totalCount);
	}

	/**
	 * 构造器
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页几条数据
	 * @param totalCount
	 *            总共几条数据
	 * @param list
	 *            分页内容
	 */
	public Pagination(Integer pageNo, Integer pageSize, Integer totalCount, List<T> list) {
		super(pageNo, pageSize, totalCount);
		this.list = list;
	}

	/**
	 * 第一条数据位置
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return (pageNo - 1) * pageSize;
	}
	/**
	 * 当前页的数据
	 */
	private List<T> list;

	/**
	 * 获得分页内容
	 * 
	 * @return
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * 设置分页内容
	 * 
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	public void setList(List list) {
		this.list = list;
	}
	
	/**
	* @Title: isInvalid 
	* @Description: 判断分页参数是否无效，如果返回true(表示分页参数无效)则不分页，查询全部的记录
	* @author pj
	* @date 2015-7-30
	* @return boolean
	*/
	public boolean isInvalid() {
		return  pageNo< 0 || pageSize <= 0;
	}
	
	
	public String getPageHtml()
	{
		StringBuffer PageHtml = new StringBuffer();
		PageHtml.append("<script language=\"JavaScript\"> \n");
		PageHtml.append("function subPageForward(pageNo) { \n");
		PageHtml.append("document.getElementById('pageNo').value=pageNo; \n");
		PageHtml.append("document.forms[0].submit(); \n");
		PageHtml.append("} \n");
		PageHtml.append("function subPageSizeForward(pageSize) { \n");
		PageHtml.append("document.getElementById('pageSize').value=pageSize; \n");
		PageHtml.append("document.getElementById('pageNo').value=1; \n");
		PageHtml.append("document.forms[0].submit(); \n");
		PageHtml.append("} \n");
		PageHtml.append("</script> \n");
		PageHtml.append("<input type=\"hidden\" name=\"pageNo\" id=\"pageNo\" value=\"" + this.getPageNo() + "\" /> \n");
		PageHtml.append("<input type=\"hidden\" name=\"pageSize\" id=\"pageSize\" value=\"" + this.getPageSize() + "\" /> \n");
		PageHtml.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"mt5\">");
		PageHtml.append(" <tr><td>");
		if (this.getPageNo() <= 1) {
			PageHtml.append("<font>首页</font>&nbsp;&nbsp;&nbsp;</td>");
			PageHtml.append(" <td> <font>上页</font>&nbsp;&nbsp;</td>");
		} else {
			PageHtml.append("  <a  href=\"#\" onclick=\"subPageForward(1);");
			PageHtml.append("return false;\"><font>首页</font></a>&nbsp;&nbsp;</td>");
			PageHtml.append(" <td>");
			PageHtml.append(" <a  href=\"#\" onclick=\"subPageForward("+this.getPrePage()+");return false;\"><font>上页</font></a>&nbsp;&nbsp;</td>");
		}
		if (this.getPageNo() >= this.getTotalPage()) {
			PageHtml.append(" <td>");
			PageHtml.append(" <font>下页 </font>&nbsp;&nbsp;&nbsp;</td>");
			PageHtml.append("<td><font>尾页</font>&nbsp;&nbsp;</td>");
		} else {
			PageHtml.append(" <td>");
			PageHtml.append(" <a  href=\"#\" onclick=\"subPageForward("+this.getNextPage()+");return false;\"><font>下页</font></a>&nbsp;&nbsp;</td>");
			PageHtml.append("<td><a  href=\"#\" onclick=\"subPageForward("+this.getTotalPage()+");return false;\"><font>尾页</font></a>&nbsp;&nbsp;</td>");
		}
		PageHtml.append("<td>"+this.getPageNo()+"/"+this.getTotalPage()+"&nbsp;&nbsp;&nbsp;</td>");
		PageHtml.append("<td>记录总数："+this.getTotalCount()+"&nbsp;&nbsp;&nbsp;</td>");
		PageHtml.append("<td>第<select class='Select' onchange='subPageForward(this.value);'>");
		for(int i=1;i<this.getTotalPage()+1;i++) {
			if (i == this.getPageNo()) {
				PageHtml.append("<option value="+i+" selected>"+i+"</option>");
			} else {
				PageHtml.append("<option value="+i+">"+i+"</option>");
			}
		}
		PageHtml.append("</select>页</td></tr></table>");
		
		return PageHtml.toString();
		
	}
	
	
	public String getAjaxPageHtml()
	{
		StringBuffer PageHtml = new StringBuffer();
		PageHtml.append("<script language=\"JavaScript\"> \n");
		PageHtml.append("function subPageForward(pageNo) { \n");
		PageHtml.append("document.getElementById('pageNo').value=pageNo; \n");
		PageHtml.append(" ajaxFormSubmit() ;\n");
		PageHtml.append("} \n");
		PageHtml.append("function subPageSizeForward(pageSize) { \n");
		PageHtml.append("document.getElementById('pageSize').value=pageSize; \n");
		PageHtml.append("document.getElementById('pageNo').value=1; \n");
		PageHtml.append(" ajaxFormSubmit(); \n");
		PageHtml.append("} \n");
		PageHtml.append("</script> \n");
		PageHtml.append("<input type=\"hidden\" name=\"pageNo\" id=\"pageNo\" value=\"" + this.getPageNo() + "\" /> \n");
		PageHtml.append("<input type=\"hidden\" name=\"pageSize\" id=\"pageSize\" value=\"" + this.getPageSize() + "\" /> \n");
		PageHtml.append("<input type=\"hidden\" name=\"totalCount\" id=\"totalCount\" value=\"" + this.getTotalCount() + "\" /> \n");
		PageHtml.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
		PageHtml.append(" <tr><td>");
		if (this.getPageNo() <= 1) {
			PageHtml.append("<span>首页</span>&nbsp;&nbsp;</td>");
			PageHtml.append(" <td> <span>上页</span>&nbsp;&nbsp;</td>");
		} else {
			PageHtml.append("  <a  style='color:blue;' href=\"javascript:void(0)\" onclick=\"subPageForward(1,event);");
			PageHtml.append("return false;\"><span>首页</span></a>&nbsp;&nbsp;</td>");
			PageHtml.append(" <td>");
			PageHtml.append(" <a  href=\"javascript:void(0)\" onclick=\"subPageForward("+this.getPrePage()+",event);return false;\" style='color:blue;'><span>上页</span></a>&nbsp;&nbsp;</td>");
		}
		if (this.getPageNo() >= this.getTotalPage()) {
			PageHtml.append(" <td>");
			PageHtml.append(" <span >下页 </span>&nbsp;&nbsp;</td>");
			PageHtml.append("<td><span>尾页</span>&nbsp;&nbsp;</td>");
		} else {
			PageHtml.append(" <td>");
			PageHtml.append(" <a  href=\"javascript:void(0)\" onclick=\"subPageForward("+this.getNextPage()+",event);return false;\" style='color:blue;'><span>下页</span></a>&nbsp;&nbsp;</td>");
			PageHtml.append("<td><a  href=\"javascript:void(0)\" onclick=\"subPageForward("+this.getTotalPage()+",event);return false;\" style='color:blue;'><span>尾页</span></a>&nbsp;&nbsp;</td>");
		}
		PageHtml.append("<td>"+this.getPageNo()+"/"+this.getTotalPage()+"&nbsp;&nbsp;</td>");
		PageHtml.append("<td id='countNum'>记录总数："+this.getTotalCount()+"&nbsp;&nbsp;</td>");
		PageHtml.append("<td>第<select class='Select' onchange='subPageForward(this.value,event);'>");
		for(int i=1;i<this.getTotalPage()+1;i++) {
			if (i == this.getPageNo()) {
				PageHtml.append("<option value="+i+" selected>"+i+"</option>");
			} else {
				PageHtml.append("<option value="+i+">"+i+"</option>");
			}
		}
		PageHtml.append("</select>页</td></tr></table>");
		
		return PageHtml.toString();
		
	}
	
	public String getNumPageHtml(){
		StringBuffer PageHtml = new StringBuffer();
		PageHtml.append("<script language=\"JavaScript\"> \n");
		PageHtml.append("function subPageForward(pageNo) { \n");
		PageHtml.append("document.getElementById('pageNo').value=pageNo; \n");
		PageHtml.append("document.forms[0].submit(); \n");
		PageHtml.append("} \n");
		PageHtml.append("</script> \n");
		PageHtml.append("<input type=\"hidden\" name=\"pageNo\" id=\"pageNo\" value=\"" + this.getPageNo() + "\" /> \n");
		PageHtml.append("<input type=\"hidden\" name=\"pageSize\" id=\"pageSize\" value=\"" + this.getPageSize() + "\" /> \n");
		PageHtml.append("<input type=\"hidden\" name=\"totalCount\" id=\"totalCount\" value=\"" + this.getTotalCount() + "\" /> \n");
		PageHtml.append("<table width=\"99%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"scrollpage\">");
		PageHtml.append("<tr>");
		PageHtml.append("<td align=\"left\" width=\"30%\"> 	       共 "+this.getTotalCount()+" 条 ");
		PageHtml.append("每页<input type=\"text\" value=\""+this.getPageSize()+"\" onfocus=\"this.select();\"  class=\"focus_input\"  onkeypress=\"if(event.keyCode==13){$(this).blur();return false;}\" size=\"1\"/> 条");
		PageHtml.append("</td>");
		PageHtml.append("<td align=\"right\">");
		PageHtml.append("<div class=\"pagecorl\">当前 "+this.getPageNo()+"/"+this.getTotalPage()+" 页   转到第 <input type=\"text\" id=\"_goPs\" class=\"input_text08\" onfocus=\"this.select();\" onkeypress=\"if(event.keyCode==13){$('#_goPage').click();return false;}\"/> 页");
		PageHtml.append(" <input class=\"sc_button\" onmouseover=\"this.className='sc_button_m'\" onmouseout=\"this.className='sc_button'\" id=\"_goPage\" type=\"button\" value=\"GO\" onclick=\"_gotoPage($('#_goPs').val());\"/></div>");
		if(this.getTotalPage()>1){
			PageHtml.append("<div class=\"pagelist\">");
			if(this.isFirstPage()){
				PageHtml.append("<span><</span>");
				PageHtml.append("<em>1</em>");
			}else{
				PageHtml.append("<span><a href=\"javascript:void(0);\" onclick=\"subPageForward('"+this.getPrePage()+"');\"><</a></span>");
				PageHtml.append("<span><a href=\"javascript:void(0);\" onclick=\"subPageForward('1');\">1</a></span>");
			}
			if(this.isBeforEllipsis()){
				PageHtml.append("<i>...</i>");
			}
			int[] prePageArr =  this.getPrePageArr();
			for (int i : prePageArr) {
				if(i>0){
					if(i==this.getPageNo()){
						PageHtml.append("<em>"+i+"</em>");
					}else{
						PageHtml.append("<span><a href=\"javascript:void(0);\" onclick=\"subPageForward('"+i+"')\">"+i+"</a></span>");
					}
				}
			}
			int[] nextPageArr = this.getNextPageArr();
			for (int i : nextPageArr) {
				if(i==this.getPageNo()){
					PageHtml.append("<em>"+i+"</em>");
				}else{
					PageHtml.append("<span><a href=\"javascript:void(0);\" onclick=\"subPageForward('"+i+"')\">${i}</a></span>");
					
				}
			}
			if(this.isAfterEllipsis()){
				PageHtml.append("<i>...</i>");
			}
			if(this.isLastPage()){
				PageHtml.append("<em>"+this.getTotalPage()+"</em>");
				PageHtml.append("<span>></span>");
			}else{
				PageHtml.append("<span><a href=\"javascript:void(0);\" onclick=\"subPageForward('"+this.getTotalPage()+"');\"/>"+this.getTotalPage()+"</a></span>");
				PageHtml.append("<span><a href=\"javascript:void(0);\" onclick=\"subPageForward('"+this.getNextPage()+"');\">></a></span>");
			}
			PageHtml.append("</div>");
		}
		PageHtml.append("</td>");
		PageHtml.append("</tr>");
		PageHtml.append("</table>");
		return PageHtml.toString();
	}
	
	public String getStyleFirstPageHtml(){
		StringBuffer PageHtml = new StringBuffer();
		PageHtml.append("<div class=\"pages fl\">");
		PageHtml.append("	<div class=\"z-jl fl\" id=\"pageflId\"><span class=\"fl\">共"+this.getTotalCount()+"条记录</span></div>");
		if(this.getTotalPage()>1){
			PageHtml.append("	<div class=\"pages_text fr\" id=\"pagefrId\">");
			PageHtml.append("		<span class=\"fl\">当前"+this.getPageNo()+"/"+this.getTotalPage()+"页</span>");
			PageHtml.append("		<span class=\"fl\">转到第</span>");
			PageHtml.append("		<input type=\"text\" class=\"pages_num\" id=\"goPageId\" onkeyup=\"value=value.replace(/[^\\d]/g,'')\">");
			PageHtml.append("		<span class=\"fl\">页</span>");
			PageHtml.append("		<a href=\"#\" class=\"fl btn_go\" onclick=\"goPageFun("+this.getTotalPage()+")\">Go</a>");
			PageHtml.append("	</div>");
			PageHtml.append("	<div class=\"fr\">");
			PageHtml.append("		<ul class=\"pagination fr\" id=\"pagination\">");
				if(!this.isFirstPage()){
					PageHtml.append("	<li><a href=\"javascript:void(0);\" onclick=\"myPageFun("+this.getPrePage()+");\">上一页</a></li>");
				}else{
					PageHtml.append("	<li class=\"active\"><a href=\"javascript:void(0);\">1</a></li>");
				}
				int[] prePageArr =  this.getPrePageArr();
				if(prePageArr!=null){
					for (int i : prePageArr) {
						if(i>0){
							if(i==this.getPageNo()){
								PageHtml.append("	<li class=\"active\"><a href=\"javascript:void(0);\">"+i+"</a></li>");
							}else{
								PageHtml.append("	<li ><a href=\"javascript:void(0);\" onclick=\"myPageFun("+i+");\">"+i+"</a></li>");
							}
						}
					}
				}
				int[] nextPageArr = this.getNextPageArr();
				if(nextPageArr!=null){
					for (int i : nextPageArr) {
						if(i>0){
							if(i==this.getPageNo()){
								PageHtml.append("	<li class=\"active\"><a href=\"javascript:void(0);\">"+i+"</a></li>");
							}else{
								PageHtml.append("	<li ><a href=\"javascript:void(0);\" onclick=\"myPageFun("+i+");\">"+i+"</a></li>");
							}
						}
					}
				}
				if(!this.isLastPage()){
					PageHtml.append("	<li><a href=\"javascript:void(0);\" onclick=\"myPageFun("+this.getNextPage()+");\">下一页</a></li>");
				}else{
					PageHtml.append("	<li class=\"active\"><a href=\"javascript:void(0);\">"+this.getTotalPage()+"</a></li>");
				}
			PageHtml.append("		</ul>");
			PageHtml.append("	</div>");
		}
		PageHtml.append("</div>");
		
		return PageHtml.toString();
	}
}
