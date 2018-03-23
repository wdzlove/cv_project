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

import com.jrfcv.bean.Position;
import com.jrfcv.dao.PositionDao;
import com.jrfcv.util.Pagination;
import com.jrfcv.util.StringUtils;
/**
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *岗位管理数据层实现类
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
@Repository
public class PositionDaoImpl implements PositionDao {

	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Position> findList() {
		final List<Position> list = new ArrayList<Position>();
		StringBuilder sql = new StringBuilder();
		sql.append("select p.id,name,p.`number`,p.duty,p.recruit_num,p.customer_id  ")
			.append(",c.name as customer ")
			.append(" from `position` p ")
			.append(" left join customer c on c.id = p.customer_id ");
		try {
			this.jdbcTemplate.query(sql.toString(),new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet r) throws SQLException {
					if(r != null){
						Position p = new Position();
						p.setDuty(r.getString("duty"));
						p.setId(r.getInt("id"));
						p.setName(r.getString("name"));
						p.setNumber(r.getString("number"));
						p.setRecruitNum(r.getInt("recruit_num"));
						p.setCustomerId(r.getInt("customer_id"));
						p.setCustomer(r.getString("customer"));
						list.add(p);
					}
				}});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public Integer countName(Integer id, String name,Integer customerId) {
		List<Object> args = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(id) from `position` where customer_id=? and name=?");
		args.add(customerId);
		args.add(name);
		if(id != null && id > 0){
			sql.append(" and id <> ?");
			args.add(id);
		}
		Integer queryForObject = 0;
		try {
			queryForObject = this.jdbcTemplate.queryForObject(sql.toString(), args.toArray(), Integer.class);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return queryForObject;
	}

	@Override
	public Integer countNumber(Integer id, String number) {
		List<Object> args = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(id) from `position` where number=?");
		args.add(number);
		if(id != null && id > 0){
			sql.append(" and id <> ?");
			args.add(id);
		}
		Integer queryForObject = 0;
		try {
			queryForObject = this.jdbcTemplate.queryForObject(sql.toString(), args.toArray(), Integer.class);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return queryForObject;
	}

	@Override
	public void save(final Position pos) {
		final StringBuilder sql = new StringBuilder();
		sql.append("insert into `position`(`name`,`number`,duty,recruit_num,customer_id) values(?,?,?,?,?)");
		try {
			KeyHolder holder = new GeneratedKeyHolder();
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement p = con.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
					p.setString(1,pos.getName());
					p.setString(2,pos.getNumber());
					p.setString(3,pos.getDuty());
					p.setInt(4,pos.getRecruitNum());
					p.setInt(5, pos.getCustomerId());
					return p;
				}
			}, holder);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Position p) {
		StringBuilder sql = new StringBuilder();
		sql.append("update  `position` set name=?,`number`=?,duty=?,recruit_num=? where id=?");
		try {
			this.jdbcTemplate.update(sql.toString(), p.getName(),p.getNumber(),p.getDuty(),p.getRecruitNum(),p.getId());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer countUse(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(rec_id) from recommend_position where post_id=?");
		Integer count = 0;
		try {
			count = this.jdbcTemplate.queryForObject(sql.toString(), Integer.class, id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public void delete(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from `position` where id=?");
		try {
			this.jdbcTemplate.update(sql.toString(), id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Pagination<Position> findPage(Integer pageNo, Integer pageSize, String name) {
		List<Position> list = findLists(pageNo,pageSize,name);
		Integer totalCount = getTotalCount(name);
		Pagination<Position> page = new Pagination<Position>(pageNo, pageSize, totalCount, list);
		return page;
	}

	private Integer getTotalCount(String name) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(id) from `position` ");
		if(!StringUtils.isBlank(name)){
			sql.append(" where name like '%"+name+"%'");
		}
		Integer count = 0;
		try {
			count = this.jdbcTemplate.queryForObject(sql.toString(), Integer.class);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return count;
	}

	private List<Position> findLists(Integer pageNo, Integer pageSize, String name) {
		Integer row = 0;
		if(pageNo > 1){
			row = (pageNo -1)*pageSize;
		}
		final List<Position> list = new ArrayList<Position>();
		StringBuilder sql = new StringBuilder();
		sql.append("select p.id,p.name,p.`number`,p.duty,p.recruit_num,p.customer_id  ")
			.append(",c.name as customer ")
			.append(" from `position` p ")
			.append(" left join customer c on c.id = p.customer_id ");
		if(!StringUtils.isBlank(name)){
			sql.append(" where name like '%"+name+"%'");
		}
		sql.append(" order by name desc limit "+row+","+pageSize);
		try {
			this.jdbcTemplate.query(sql.toString(),new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet r) throws SQLException {
					if(r != null){
						Position p = new Position();
						p.setDuty(r.getString("duty"));
						p.setId(r.getInt("id"));
						p.setName(r.getString("name"));
						p.setNumber(r.getString("number"));
						p.setRecruitNum(r.getInt("recruit_num"));
						p.setCustomer(r.getString("customer"));
						list.add(p);
					}
				}});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public List<Position> findListByCusId(Integer cusId) {
		final List<Position> list = new ArrayList<Position>();
		StringBuilder sql = new StringBuilder();
		sql.append("select p.id,name,p.`number`,p.duty,p.recruit_num,p.customer_id  ")
			.append(" from `position` p  where p.customer_id=?");
		try {
			this.jdbcTemplate.query(sql.toString(),new Object[]{cusId},new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet r) throws SQLException {
					if(r != null){
						Position p = new Position();
						p.setDuty(r.getString("duty"));
						p.setId(r.getInt("id"));
						p.setName(r.getString("name"));
						p.setNumber(r.getString("number"));
						p.setRecruitNum(r.getInt("recruit_num"));
						p.setCustomerId(r.getInt("customer_id"));
						list.add(p);
					}
				}});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public void delByCusId(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from `position` where customer_id=?");
		try {
			this.jdbcTemplate.update(sql.toString(), id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Position getPosition(Integer id) {
		final Position p = new Position();
		StringBuilder sql = new StringBuilder();
		sql.append("select p.id,name,p.`number`,p.duty,p.recruit_num,p.customer_id  ")
			.append(" from `position` p  where p.id=?");
		try {
			this.jdbcTemplate.query(sql.toString(),new Object[]{id},new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet r) throws SQLException {
					if(r != null){
						p.setDuty(r.getString("duty"));
						p.setId(r.getInt("id"));
						p.setName(r.getString("name"));
						p.setNumber(r.getString("number"));
						p.setRecruitNum(r.getInt("recruit_num"));
						p.setCustomerId(r.getInt("customer_id"));
					}
				}});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(p.getId() != null && p.getId() > 0){
			return p;
		}
		return null;
	}

}
