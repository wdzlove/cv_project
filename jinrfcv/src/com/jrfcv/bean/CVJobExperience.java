package com.jrfcv.bean;
/**
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *工作经历
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
public class CVJobExperience {

	/**编号*/
	private Integer id;
	/**企业名称*/
	private String entName;
	/**行业类别*/
	private String industryType;
	/**职位名称*/
	private String jobName;
	/**职位类别*/
	private String jobType;
	/**工作开始*/
	private String starTime;
	/**工作结束*/
	private String endTime;
	/**工作时长*/
	private String inJobTime;
	/**职位月薪*/
	private String jobSalary;
	/**工作描述*/
	private String jobDescribe;
	/**企业性质*/
	private String entNature;
	/**企业规模*/
	private String entScale;
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
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}
	public String getIndustryType() {
		return industryType;
	}
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getStarTime() {
		return starTime;
	}
	public void setStarTime(String starTime) {
		this.starTime = starTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getJobSalary() {
		return jobSalary;
	}
	public void setJobSalary(String jobSalary) {
		this.jobSalary = jobSalary;
	}
	public String getJobDescribe() {
		return jobDescribe;
	}
	public void setJobDescribe(String jobDescribe) {
		this.jobDescribe = jobDescribe;
	}
	public String getEntNature() {
		return entNature;
	}
	public void setEntNature(String entNature) {
		this.entNature = entNature;
	}
	public String getEntScale() {
		return entScale;
	}
	public void setEntScale(String entScale) {
		this.entScale = entScale;
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
	public String getInJobTime() {
		return inJobTime;
	}
	public void setInJobTime(String inJobTime) {
		this.inJobTime = inJobTime;
	}
	@Override
	public String toString() {
		return "CVJobExperience [id=" + id + ", entName=" + entName + ", industryType=" + industryType + ", jobName="
				+ jobName + ", jobType=" + jobType + ", starTime=" + starTime + ", endTime=" + endTime + ", inJobTime="
				+ inJobTime + ", jobSalary=" + jobSalary + ", jobDescribe=" + jobDescribe + ", entNature=" + entNature
				+ ", entScale=" + entScale + ", cvId=" + cvId + ", userId=" + userId + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	

}
