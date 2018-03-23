package com.jrfcv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrfcv.bean.Customer;
import com.jrfcv.dao.CustomerDao;
import com.jrfcv.dao.PositionDao;
import com.jrfcv.service.CustomerMng;
import com.jrfcv.util.Pagination;
/***
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *
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
@Service
public class CustomerMngImpl implements CustomerMng {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private PositionDao positionDao;
	
	@Override
	public List<Customer> findList() {
		List<Customer> findList = customerDao.findList();
		return findList;
	}
	@Override
	public void save(Customer cus) {
		customerDao.save(cus);		
	}
	@Override
	public Integer getCount(Integer id, String name) {
		return customerDao.getCount(id,name);
	}
	@Override
	public void update(Customer cus) {
		customerDao.update(cus);		
	}
	@Override
	public Integer countUse(Integer id) {
		return customerDao.countUse(id);
	}
	@Override
	public void delete(Integer id) {
		customerDao.delete(id);
	}
	@Override
	public Integer countNumber(String number,Integer id) {
		return customerDao.countNumber(number,id);
	}
	@Override
	public Pagination<Customer> findPage(Integer pageNo, Integer pageSize, String name) {
		return customerDao.findPage(pageNo,pageSize,name);
	}

}
