package com.jrfcv.bean;
/**
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *求职意向
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
public class CVJobIntention {

	/**编号*/
	private Integer id;
	/**工作性质*/
	private String jobNature;
	/**期望工作地点*/
	private String hopeWorkPlace;
	/**期望从事职业*/
	private String hopeJob;
	/**期望行业*/
	private String hopeIndustry;
	/**期望薪资（税前）*/
	private String hopeSalary;
	/**工作状态*/
	private String jobStatus;
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
	public String getJobNature() {
		return jobNature;
	}
	public void setJobNature(String jobNature) {
		this.jobNature = jobNature;
	}
	public String getHopeWorkPlace() {
		return hopeWorkPlace;
	}
	public void setHopeWorkPlace(String hopeWorkPlace) {
		this.hopeWorkPlace = hopeWorkPlace;
	}
	public String getHopeJob() {
		return hopeJob;
	}
	public void setHopeJob(String hopeJob) {
		this.hopeJob = hopeJob;
	}
	public String getHopeIndustry() {
		return hopeIndustry;
	}
	public void setHopeIndustry(String hopeIndustry) {
		this.hopeIndustry = hopeIndustry;
	}
	public String getHopeSalary() {
		return hopeSalary;
	}
	public void setHopeSalary(String hopeSalary) {
		this.hopeSalary = hopeSalary;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
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
	@Override
	public String toString() {
		return "CVJobIntention [id=" + id + ", jobNature=" + jobNature + ", hopeWorkPlace=" + hopeWorkPlace
				+ ", hopeJob=" + hopeJob + ", hopeIndustry=" + hopeIndustry + ", hopeSalary=" + hopeSalary
				+ ", jobStatus=" + jobStatus + ", cvId=" + cvId + ", userId=" + userId + "]";
	}

	
	
}
