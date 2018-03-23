package com.jrfcv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrfcv.bean.User;
import com.jrfcv.dao.UserDao;
import com.jrfcv.service.UserMng;
/**
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *用户业务层实现类
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
@Service
public class UserMngImpl implements UserMng {

	@Autowired
	private UserDao userDao;

	@Override
	public User checkUser(String userName, String passWord) {
		return userDao.checkUser(userName,passWord);
	}

	@Override
	public List<User> findList() {
		return userDao.findList();
	}
}
