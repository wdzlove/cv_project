package com.jrfcv.bean;

import java.util.List;

/**
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *客户实体
 * <p>
 *
 * 区分　责任人　日期　　　　说明<br/>
 * 创建 wdz　2018年3月14日　<br/>
 * <p>
 *
 * @author Administrator
 *
 * @version 1.0, 2018年3月14日
 *
 */
public class Customer {

	/**编号*/
	private Integer id;
	/**姓名*/
	private String name;
	/**编号标识*/
	private String number;

	private List<Position> posts;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public List<Position> getPosts() {
		return posts;
	}

	public void setPosts(List<Position> posts) {
		this.posts = posts;
	}

}
