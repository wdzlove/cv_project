package com.jrfcv.bean;
/***
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *专业技能
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
public class CVSkill {

	/**编号*/
	private Integer id;
	/**技能类别*/
	private String type;
	/**技能名称*/
	private String name;
	/**已使用时间*/
	private String useTime;
	/**熟练程度*/
	private String degree;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
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
	
	
}
