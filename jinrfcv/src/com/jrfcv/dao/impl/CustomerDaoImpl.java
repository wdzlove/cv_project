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

import com.jrfcv.bean.Customer;
import com.jrfcv.dao.CustomerDao;
import com.jrfcv.util.Pagination;
import com.jrfcv.util.StringUtils;
/**
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *客户管理数据层实现类
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
public class CustomerDaoImpl implements CustomerDao {

	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<Customer> findList() {
		final List<Customer> list = new ArrayList<Customer>();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,name,`number` from customer ");
		sql.append(" order by name desc");
		try {
			this.jdbcTemplate.query(sql.toString(), new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet r) throws SQLException {
					if(r != null){
						Customer c = new Customer();
						c.setId(r.getInt("id"));
						c.setName(r.getString("name"));
						c.setNumber(r.getString("number"));
						list.add(c);
					}				
				}
			});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public Pagination<Customer> findPage(Integer pageNo, Integer pageSize, String name) {
		List<Customer> list = findCustomers(pageNo,pageSize,name);
		Integer totalCount = getCustomerCount(name);
		Pagination<Customer> page = new Pagination<Customer>(pageNo, pageSize, totalCount, list);
		return page;
	}
	private Integer getCustomerCount(String name) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(id) from customer ")
			.append(" where 1=1 ");
		if(!StringUtils.isBlank(name)){
			sql.append(" and name like '%"+name+"%'");
		}
		Integer queryForObject = 0;
		try {
			queryForObject = this.jdbcTemplate.queryForObject(sql.toString(), Integer.class);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return queryForObject;
	}


	private List<Customer> findCustomers(Integer pageNo, Integer pageSize, String name) {
		final List<Customer> list = new ArrayList<Customer>();
		Integer row = 0;
		if(pageNo > 1){
			row = (pageNo-1)*pageSize;
		}
		StringBuilder sql = new StringBuilder();
		sql.append("select id,name,`number` from customer ");
		if(!StringUtils.isBlank(name)){
			sql.append(" where name like '%"+name+"%'");
		}
		sql.append(" order by name desc limit "+row+","+pageSize);
		try {
			this.jdbcTemplate.query(sql.toString(), new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet r) throws SQLException {
					if(r != null){
						Customer c = new Customer();
						c.setId(r.getInt("id"));
						c.setName(r.getString("name"));
						c.setNumber(r.getString("number"));
						list.add(c);
					}				
				}
			});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(list.size() > 0){
			return list;
		}
		return null;
	}



	@Override
	public void save(final Customer cus) {
		final StringBuilder sql = new StringBuilder();
		sql.append("insert into customer(name,`number`) values(?,?)");
		try {
			KeyHolder holder = new GeneratedKeyHolder();
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection c) throws SQLException {
					PreparedStatement p = c.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
					p.setString(1, cus.getName());
					p.setString(2, cus.getNumber());
					return p;
				}
			}, holder);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer getCount(Integer id, String name) {
		List<Object> args = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(id) from customer where 1=1 ");
		if(!StringUtils.isBlank(name)){
			sql.append(" and name=? ");
			args.add(name);
		}
		if(id != null && id > 0){
			sql.append(" and id <> ? ");
			args.add(id);
		}
		Integer queryForObject = 0;
		try {
			queryForObject = this.jdbcTemplate.queryForObject(sql.toString(),args.toArray(),Integer.class);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return queryForObject;
	}


	@Override
	public void update(Customer cus) {
		StringBuilder sql = new StringBuilder();
		sql.append("update customer set name=?,`number`=? where id=? ");
		try {
			this.jdbcTemplate.update(sql.toString(), cus.getName(),cus.getNumber(),cus.getId());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}


	@Override
	public Integer countUse(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(id) from cv_recommend where customer_id=?");
		Integer queryForObject = 0;
		try {
			queryForObject = this.jdbcTemplate.queryForObject(sql.toString(), Integer.class, id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return queryForObject;
	}


	@Override
	public Integer countNumber(String number,Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(id) from customer where `number`=? ");
		if(id != null){
			sql.append(" and id <>"+id);
		}
		Integer queryForObject = 0;
		try {
			queryForObject = this.jdbcTemplate.queryForObject(sql.toString(), Integer.class, number);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return queryForObject;
	}


	@Override
	public void delete(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from customer where id=? ");
		try {
			this.jdbcTemplate.update(sql.toString(), id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Customer getCustomer(Integer customerId) {
		final Customer c = new Customer();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,name,`number` from customer where id=? ");
		sql.append(" order by name desc");
		try {
			this.jdbcTemplate.query(sql.toString(),new Object[]{customerId}, new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet r) throws SQLException {
					if(r != null){
						c.setId(r.getInt("id"));
						c.setName(r.getString("name"));
						c.setNumber(r.getString("number"));
					}				
				}
			});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(c.getId() != null && c.getId() > 0){
			return c;
		}
		return null;
	}

}
