package com.jrfcv.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.jrfcv.bean.User;
import com.jrfcv.dao.UserDao;
/**
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *用户数据层实现类
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
@Repository
public class UserDaoImpl implements UserDao {
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public User checkUser(String userName, String passWord) {
		final User user = new User();
		StringBuilder sql = new StringBuilder();
		sql.append("select user_id,user_name,real_name,pass_word,is_admin from user ")
			.append(" where user_name=? and pass_word=?");
		this.jdbcTemplate.query(sql.toString(), new Object[]{userName,passWord},new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					user.setIsAdmin(r.getInt("is_admin"));
					user.setPassWord(r.getString("pass_word"));
					user.setRealName(r.getString("real_name"));
					user.setUserId(r.getInt("user_id"));
					user.setUserName(r.getString("user_name"));
				}
			}});
		if(user.getUserId() != null && user.getUserId() > 0){
			return user;
		}
		return null;
	}

	@Override
	public List<User> findList() {
		final List<User> users = new ArrayList<User>();
		StringBuilder sql = new StringBuilder();
		sql.append("select user_id,user_name,real_name,pass_word,is_admin from user ");
		this.jdbcTemplate.query(sql.toString(),new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					User user = new User();
					user.setIsAdmin(r.getInt("is_admin"));
					user.setPassWord(r.getString("pass_word"));
					user.setRealName(r.getString("real_name"));
					user.setUserId(r.getInt("user_id"));
					user.setUserName(r.getString("user_name"));
					users.add(user);
				}
			}});
		if(users != null && users.size() > 0){
			return users;
		}
		return null;
	}

	@Override
	public User getUser(Integer toUserId) {
		final User user = new User();
		StringBuilder sql = new StringBuilder();
		sql.append("select user_id,user_name,real_name,pass_word,is_admin from user ")
			.append(" where user_id=?");
		this.jdbcTemplate.query(sql.toString(), new Object[]{toUserId}, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					user.setIsAdmin(r.getInt("is_admin"));
					user.setPassWord(r.getString("pass_word"));
					user.setRealName(r.getString("real_name"));
					user.setUserId(r.getInt("user_id"));
					user.setUserName(r.getString("user_name"));
				}
			}
		});
		if(user.getUserId() != null){
			return user;
		}
		return null;
	}

}
