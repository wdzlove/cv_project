package com.jrfcv.dao;

import java.util.List;

import com.jrfcv.bean.User;
/**
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *用户数据层接口
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
public interface UserDao {

	/**
	* @Title: checkUser
	* @Description: 校验用户
	* @author wdz
	* @date 2018年3月9日 上午9:46:41
	* @param userName
	* @param passWord
	* @return
	 */
	public User checkUser(String userName, String passWord);

	/**
	* @Title: findList
	* @Description: 获取全部
	* @author wdz
	* @date 2018年3月9日 上午10:34:16
	* @return
	 */
	public List<User> findList();
	/**
	* @Title: getUser
	* @Description: T获取用户
	* @author wdz
	* @date 2018年3月16日 上午10:19:59
	* @param toUserId
	* @return
	 */
	public User getUser(Integer toUserId);

}
