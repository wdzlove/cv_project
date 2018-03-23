package com.jrfcv.core;

import java.util.List;
import java.util.Map;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

/**
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 * 继承JdbcTemplate,重写或者添加自己的查询方法
 * <p>
 * 
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　pj　2015-9-17　<br/>
 * <p>
 * 
 * @author pj
 * 
 * @version 1.0, 2015-9-17
 * 
 */
public class JdbcTemplateDao extends JdbcTemplate {

	/**
	 * @Title: queryForEntity
	 * @Description: 查询对象
	 * @author pj
	 * @date 2015-9-17 上午10:06:26
	 * @param <T>
	 * @param sql
	 * @param rowMapper
	 * @param args
	 * @return
	 */
	public <T> T queryForEntity(String sql, RowMapper<T> rowMapper,Object... args) {
		List<T> results = query(sql, args, new RowMapperResultSetExtractor<T>(rowMapper, 1));
		if (results.isEmpty()) {
			return null;
		} else {
			return results.get(0);
		}
	}
	
	/**
	 * @Title: queryForEntity
	 * @Description: 查询对象
	 * @author: pj
	 * @date 2017年5月5日 上午10:08:28
	 * @param sql
	 * @param args
	 * @param rowMapper
	 * @return
	 * @return: T
	 */
	public <T> T queryForEntity(String sql,Object[] args, RowMapper<T> rowMapper) {
		List<T> results = query(sql, args, new RowMapperResultSetExtractor<T>(rowMapper, 1));
		if (results.isEmpty()) {
			return null;
		} else {
			return results.get(0);
		}
	}

	public Map<String, Object> queryForMapObject(String sql, Object... args) {
		List<Map<String, Object>> results = query(sql,args,new RowMapperResultSetExtractor<Map<String, Object>>(new ColumnMapRowMapper(), 1));
		if(results.isEmpty()){
			return null;
		}
		return DataAccessUtils.uniqueResult(results);
	}

}

