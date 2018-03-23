package com.jrfcv.bean;

/***
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *在校实践
 * <p>
 *
 * 区分　责任人　日期　　　　说明<br/>
 * 创建 wdz　2018年1月31日　<br/>
 * <p>
 *
 * @author Administrator
 *
 * @version 1.0, 2018年1月31日
 *
 */
public class CVSchoolPractice {

	/**编号*/
	private Integer id;
	/**名称*/
	private String name;
	/**开始时间*/
	private String startTime;
	/**结束时间*/
	private String endTime;
	/**描述*/
	private String describe;
	/**基础信息表主键*/
	private Integer cvId;
	/**用户编号*/
	private Integer userId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public void setCvId(Integer cvId) {
		this.cvId = cvId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCvId() {
		return cvId;
	}
	public Integer getUserId() {
		return userId;
	}
	
	
}
