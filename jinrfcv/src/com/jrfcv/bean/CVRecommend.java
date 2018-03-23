package com.jrfcv.bean;

import java.util.List;

public class CVRecommend {
	/**编号*/
	private Integer id;
	/**推荐编号*/
	private String recommendId;
	/**推荐企业*/
	private Integer customerId;
	private String entName;
	/**推荐岗位*/
	private String job;
	private String jobIds;
	/**基本情况*/
	private String baseInfo;
	/**离职原因*/
	private String leaveReason;
	/**薪酬情况*/
	private String salary;
	/**期望岗位 */
	private String hopeJob;
	/**推荐理由*/
	private String reason;
	/**获得证书*/
	private String certificates;
	/**获得荣誉*/
	private String honors;
	/**入职时间*/
	private String faceTime;
	/**面试时间*/
	private String inJobTime;
	/**基础信息表主键*/
	private Integer cvId;
	/**用户编号*/
	private Integer userId;
	
	
	/**临时数据**/
	private List<RecommendPosition> posts;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRecommendId() {
		return recommendId;
	}
	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getBaseInfo() {
		return baseInfo;
	}
	public void setBaseInfo(String baseInfo) {
		this.baseInfo = baseInfo;
	}
	public String getLeaveReason() {
		return leaveReason;
	}
	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getHopeJob() {
		return hopeJob;
	}
	public void setHopeJob(String hopeJob) {
		this.hopeJob = hopeJob;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getCertificates() {
		return certificates;
	}
	public void setCertificates(String certificates) {
		this.certificates = certificates;
	}
	public String getHonors() {
		return honors;
	}
	public void setHonors(String honors) {
		this.honors = honors;
	}
	public String getFaceTime() {
		return faceTime;
	}
	public void setFaceTime(String faceTime) {
		this.faceTime = faceTime;
	}
	public String getInJobTime() {
		return inJobTime;
	}
	public void setInJobTime(String inJobTime) {
		this.inJobTime = inJobTime;
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
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}
	
	public String getJobIds() {
		return jobIds;
	}
	public void setJobIds(String jobIds) {
		this.jobIds = jobIds;
	}
	
	public List<RecommendPosition> getPosts() {
		return posts;
	}
	public void setPosts(List<RecommendPosition> posts) {
		this.posts = posts;
	}
	@Override
	public String toString() {
		return "CVRecommend [id=" + id + ", recommendId=" + recommendId + ", customerId=" + customerId + ", job=" + job
				+ ", baseInfo=" + baseInfo + ", leaveReason=" + leaveReason + ", salary=" + salary + ", hopeJob="
				+ hopeJob + ", reason=" + reason + ", certificates=" + certificates + ", honors=" + honors
				+ ", faceTime=" + faceTime + ", inJobTime=" + inJobTime + ", cvId=" + cvId + ", userId=" + userId + "]";
	}

	
}
