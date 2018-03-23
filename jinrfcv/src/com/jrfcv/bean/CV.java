package com.jrfcv.bean;

import java.util.Date;
import java.util.List;

public class CV {

	/**编号*/
	private Integer id;
	/**姓名*/
	private String name;
	/**性别*/
	private String sex;
	/**生日*/
	private String birthday;
	/**年龄*/
	private String age;
	/**参加工作日期*/
	private String joinWorkTime;
	/**户口所在地*/
	private String domicilePlace;
	/**身份证*/
	private String idCard;
	/**学历*/
	private String education;
	/**现居城市*/
	private String nowLiveCity;
	/**联系方式*/
	private String mobile;
	/**电子邮箱*/
	private String email;
	/**邮编*/
	private String postCode;
	/**婚姻状况*/
	private String marriage;
	/**家庭地址*/
	private String address;
	/**国籍*/
	private String nationality;
	/**海外工作/学习经历*/
	private String isOverseas;
	/**海外工作/学习时长*/
	private String overseasTime;
	/**政治面貌*/
	private String politicsFace;
	/**自我评价/职业/规划*/
	private String evaluate;
	/**责任人*/
	private Integer userId;
	private String userReal;
	/**兴趣爱好*/
	private String hobby;
	/**创建时间*/
	private Date createTime;
	/**来源 1：智联2：猎聘*/
	private Integer source;
	/**录入人*/
	private Integer inputUser;
	private String  inputReal;
	/**0:新简历 1：已通知2：已面试3：已淘汰4：已入职5：已转正6：已离职7：已储备*/
	private Integer state;
	private Date updateTime;
	private String workPaper;
	private String customer;
	/***关联数据*/
	/**求职意向*/
	private CVJobIntention intention;
	/**工作经历*/
	private List<CVJobExperience> cvjs;
	/**教育经历*/
	private List<CVEducation> cves;
	/**语言能力*/
	private List<CVLanguage> cvls;
	/**项目经验*/
	private List<CVProject> cvps;
	/**技能*/
	private List<CVSkill> cvks;
	/**在校学习情况*/
	private List<CVInSchool> cvss;
	private List<CVSchoolPractice> cvsps;
	private List<CVTrain> cvts;
	private List<CVCertificate> cvcs;
	
	private CVRecommend recommend;
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
	
	public CVRecommend getRecommend() {
		return recommend;
	}
	public void setRecommend(CVRecommend recommend) {
		this.recommend = recommend;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getJoinWorkTime() {
		return joinWorkTime;
	}
	public void setJoinWorkTime(String joinWorkTime) {
		this.joinWorkTime = joinWorkTime;
	}
	public String getDomicilePlace() {
		return domicilePlace;
	}
	public void setDomicilePlace(String domicilePlace) {
		this.domicilePlace = domicilePlace;
	}
	public String getNowLiveCity() {
		return nowLiveCity;
	}
	public void setNowLiveCity(String nowLiveCity) {
		this.nowLiveCity = nowLiveCity;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getIsOverseas() {
		return isOverseas;
	}
	public void setIsOverseas(String isOverseas) {
		this.isOverseas = isOverseas;
	}
	public String getOverseasTime() {
		return overseasTime;
	}
	public void setOverseasTime(String overseasTime) {
		this.overseasTime = overseasTime;
	}
	public String getPoliticsFace() {
		return politicsFace;
	}
	public void setPoliticsFace(String politicsFace) {
		this.politicsFace = politicsFace;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	public CVJobIntention getIntention() {
		return intention;
	}
	public void setIntention(CVJobIntention intention) {
		this.intention = intention;
	}
	
	public List<CVJobExperience> getCvjs() {
		return cvjs;
	}
	public void setCvjs(List<CVJobExperience> cvjs) {
		this.cvjs = cvjs;
	}
	public List<CVEducation> getCves() {
		return cves;
	}
	public void setCves(List<CVEducation> cves) {
		this.cves = cves;
	}
	public List<CVLanguage> getCvls() {
		return cvls;
	}
	public void setCvls(List<CVLanguage> cvls) {
		this.cvls = cvls;
	}
	public List<CVProject> getCvps() {
		return cvps;
	}
	public void setCvps(List<CVProject> cvps) {
		this.cvps = cvps;
	}
	public List<CVSkill> getCvks() {
		return cvks;
	}
	public void setCvks(List<CVSkill> cvks) {
		this.cvks = cvks;
	}
	
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getWorkPaper() {
		return workPaper;
	}
	public void setWorkPaper(String workPaper) {
		this.workPaper = workPaper;
	}
	
	public List<CVInSchool> getCvss() {
		return cvss;
	}
	public void setCvss(List<CVInSchool> cvss) {
		this.cvss = cvss;
	}
	public List<CVSchoolPractice> getCvsps() {
		return cvsps;
	}
	public void setCvsps(List<CVSchoolPractice> cvsps) {
		this.cvsps = cvsps;
	}
	public List<CVTrain> getCvts() {
		return cvts;
	}
	public void setCvts(List<CVTrain> cvts) {
		this.cvts = cvts;
	}
	public List<CVCertificate> getCvcs() {
		return cvcs;
	}
	public void setCvcs(List<CVCertificate> cvcs) {
		this.cvcs = cvcs;
	}
	
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Integer getInputUser() {
		return inputUser;
	}
	public void setInputUser(Integer inputUser) {
		this.inputUser = inputUser;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getUserReal() {
		return userReal;
	}
	public void setUserReal(String userReal) {
		this.userReal = userReal;
	}
	public String getInputReal() {
		return inputReal;
	}
	public void setInputReal(String inputReal) {
		this.inputReal = inputReal;
	}
	
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "CV [id=" + id + ", name=" + name + ", sex=" + sex + ", birthday=" + birthday + ", joinWorkTime="
				+ joinWorkTime + ", domicilePlace=" + domicilePlace + ", idCard=" + idCard + ", nowLiveCity="
				+ nowLiveCity + ", mobile=" + mobile + ", email=" + email + ", postCode=" + postCode + ", marriage="
				+ marriage + ", address=" + address + ", nationality=" + nationality + ", isOverseas=" + isOverseas
				+ ", overseasTime=" + overseasTime + ", politicsFace=" + politicsFace + ", evaluate=" + evaluate
				+ ", userId=" + userId + ", hobby=" + hobby + ", workPaper=" + workPaper + ", intention=" + intention
				+ ", cvjs=" + cvjs + ", cves=" + cves + ", cvls=" + cvls + ", cvps=" + cvps + ", cvks=" + cvks + "]";
	}
	
	
}
