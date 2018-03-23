package com.jrfcv.bean;
/**
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *教育经历
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
public class CVEducation {

	/**编号*/
	private Integer id;
	/**学习时间*/
	private String startTime;
	/**学习结束时间*/
	private String endTime;
	/**学校名称*/
	private String school;
	/**是否统招*/
	private String isUnified;
	/**专业类别*/
	private String majorType;
	/**专业名称*/
	private String majorName;
	/**学历/学位*/
	private String education;
	/**基础信息表主键*/
	private Integer cvId;
	/**用户编号*/
	private Integer userId;
	/**排序位置*/
	private Integer postion;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getIsUnified() {
		return isUnified;
	}
	public void setIsUnified(String isUnified) {
		this.isUnified = isUnified;
	}
	public String getMajorType() {
		return majorType;
	}
	public void setMajorType(String majorType) {
		this.majorType = majorType;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public Integer getCvId() {
		return cvId;
	}
	public void setCvId(Integer cvId) {
		this.cvId = cvId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getPostion() {
		return postion;
	}
	public void setPostion(Integer postion) {
		this.postion = postion;
	}
	@Override
	public String toString() {
		return "CVEducation [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", school=" + school
				+ ", isUnified=" + isUnified + ", majorType=" + majorType + ", majorName=" + majorName + ", education="
				+ education + ", cvId=" + cvId + ", userId=" + userId + "]";
	}
	
}
