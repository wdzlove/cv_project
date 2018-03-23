package com.jrfcv.bean;
/**
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *语言能力
 * 区分　责任人　日期　　　　说明<br/>
 * 创建 wdz　2018年1月31日　<br/>
 * <p>
 *
 * @author Administrator
 *
 * @version 1.0, 2018年1月31日
 *
 */
public class CVLanguage {

	/**编号*/
	private Integer id;
	/**语种*/
	private String language;
	/**读写能力*/
	private String rwAbility;
	/**听说能力*/
	private String hearAbility;
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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getRwAbility() {
		return rwAbility;
	}
	public void setRwAbility(String rwAbility) {
		this.rwAbility = rwAbility;
	}
	public String getHearAbility() {
		return hearAbility;
	}
	public void setHearAbility(String hearAbility) {
		this.hearAbility = hearAbility;
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
