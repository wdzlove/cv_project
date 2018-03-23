package com.jrfcv.bean;

public class User {

	/**编号*/
	private Integer userId;
	/**用户名*/
	private String userName;
	/**真实姓名*/
	private String realName;
	/**密码*/
	private String passWord;
	/**0:正常 1：管理员*/
	private Integer isAdmin;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", realName=" + realName + ", passWord=" + passWord
				+ ", isAdmin=" + isAdmin + "]";
	}
	
	
	
}
