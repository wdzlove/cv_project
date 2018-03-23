package com.jrfcv.bean;
/**
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *项目经验
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
public class CVProject {
	
	/**编号*/
	private Integer id;
	/**项目名称*/
	private String name;
	/**开始时间*/
	private String startTime;
	/**结束时间*/
	private String endTime;
	/**是否是IT项目 0：不是 1：是*/
	private Integer isIt;
	/**软件环境*/
	private String software;
	/**硬件环境*/
	private String hardware;
	/**开发工具*/
	private String developmentTool;
	/**职责*/
	private String duties;
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
	public Integer getIsIt() {
		return isIt;
	}
	public void setIsIt(Integer isIt) {
		this.isIt = isIt;
	}
	public String getSoftware() {
		return software;
	}
	public void setSoftware(String software) {
		this.software = software;
	}
	public String getHardware() {
		return hardware;
	}
	public void setHardware(String hardware) {
		this.hardware = hardware;
	}
	public String getDevelopmentTool() {
		return developmentTool;
	}
	public void setDevelopmentTool(String developmentTool) {
		this.developmentTool = developmentTool;
	}
	public String getDuties() {
		return duties;
	}
	public void setDuties(String duties) {
		this.duties = duties;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
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
		return "CVProject [id=" + id + ", name=" + name + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", isIt=" + isIt + ", software=" + software + ", hardware=" + hardware + ", developmentTool="
				+ developmentTool + ", duties=" + duties + ", describe=" + describe + ", cvId=" + cvId + ", userId="
				+ userId + "]";
	}

}
