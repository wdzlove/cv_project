package com.jrfcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.jrfcv.bean.CvRecord;
import com.jrfcv.dao.CvRecordDao;
import com.jrfcv.util.Pagination;

/**
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *简历记录数层实现类
 * <p>
 *
 * 区分　责任人　日期　　　　说明<br/>
 * 创建 wdz　2018年3月15日　<br/>
 * <p>
 *
 * @author Administrator
 *
 * @version 1.0, 2018年3月15日
 *
 */
@Repository
public class CvRecordDaoImpl implements CvRecordDao {

	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate; 
	@Override
	public Pagination<CvRecord> findPage(Integer pageNo, Integer pageSize,Integer cvId) {
		List<CvRecord> list = findList(pageNo,pageSize,cvId);
		Integer totalCount = getCount(cvId);
		Pagination<CvRecord> page = new Pagination<CvRecord>(pageNo, pageSize, totalCount, list);
		return page;
	}

	private Integer getCount(Integer cvId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(id) from cv_record where cv_id=? ");
		Integer count = 0;
		try {
			count = this.jdbcTemplate.queryForObject(sql.toString(), Integer.class, cvId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return count;
	}

	private List<CvRecord> findList(Integer pageNo, Integer pageSize, Integer cvId) {
		Integer row = 0;
		if(pageNo > 1){
			row = (pageNo - 1)*pageSize;
		}
		final List<CvRecord> list = new ArrayList<CvRecord>();
		List<Object> args = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select c.id,c.user_id,c.cv_id,c.update_time,c.record,c.customer,u.real_name")
			.append(" from cv_record c ")
			.append(" left join user u on u.user_id = c.user_id ")
			.append(" where c.cv_id=? order by c.update_time desc limit ?,? ");
		args.add(cvId);
		args.add(row);
		args.add(pageSize);
		this.jdbcTemplate.query(sql.toString(), args.toArray(), new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					CvRecord c = new CvRecord();
					c.setId(r.getInt("id"));
					c.setCvId(r.getInt("cv_id"));
					c.setRecord(r.getString("record"));
					c.setUserId(r.getInt("user_id"));
					c.setUpdateTime(r.getTimestamp("update_time"));
					c.setUserName(r.getString("real_name"));
					c.setCustomer(r.getString("customer"));
					list.add(c);
				}
			}});
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public void saveRecord(final CvRecord cd) {
		final StringBuilder sql = new StringBuilder();
		sql.append("insert into cv_record(user_id,cv_id,update_time,record,customer) values(?,?,?,?,?)");
		try {
			KeyHolder holder = new GeneratedKeyHolder();
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement p = con.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
					p.setInt(1, cd.getUserId());
					p.setInt(2, cd.getCvId());
					p.setTimestamp(3, new java.sql.Timestamp(cd.getUpdateTime().getTime()));
					p.setString(4, cd.getRecord());
					p.setString(5, cd.getCustomer());
					return p;
				}
			}, holder);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delRecord(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from  cv_record where cv_id = ?");
		try {
			this.jdbcTemplate.update(sql.toString(), id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

}
