package com.jrfcv.service;

import java.util.List;

import com.jrfcv.bean.User;
/**
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *用户业务层接口
 * <p>
 *
 * 区分　责任人　日期　　　　说明<br/>
 * 创建 wdz　2018年3月9日　<br/>
 * <p>
 *
 * @author Administrator
 *
 * @version 1.0, 2018年3月9日
 *
 */
public interface UserMng {

	/**
	* @Title: checkUser
	* @Description: 验证用户
	* @author wdz
	* @date 2018年3月9日 上午9:32:13
	* @param userName
	* @param passWord
	* @return
	 */
	public User checkUser(String userName, String passWord);

	/***
	* @Title: findList
	* @Description: 获取列表
	* @author wdz
	* @date 2018年3月9日 上午10:33:35
	* @return
	 */
	public List<User> findList();

}
