package com.jrfcv.dao;

import java.util.List;

import com.jrfcv.bean.Customer;
import com.jrfcv.util.Pagination;

/**
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *客户管理数据层接口
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
public interface CustomerDao {

	/***
	* @Title: findList
	* @Description: 全部数据
	* @author wdz
	* @date 2018年3月14日 上午10:32:13
	* @param pageNo
	* @param pageSize
	* @param name
	* @return
	 */
	public List<Customer> findList();

	/**
	* @Title: save
	* @Description: 保存
	* @author wdz
	* @date 2018年3月14日 上午10:45:45
	* @param cus
	 */
	public void save(Customer cus);

	/**
	* @Title: getCount
	* @Description:统计
	* @author wdz
	* @date 2018年3月14日 上午10:53:25
	* @param id
	* @param name
	* @return
	 */
	public Integer getCount(Integer id, String name);

	/**
	* @Title: update
	* @Description: 修改
	* @author wdz
	* @date 2018年3月14日 上午10:56:05
	* @param cus
	 */
	public void update(Customer cus);

	/**
	* @Title: countUse
	* @Description: 统计使用数量
	* @author wdz
	* @date 2018年3月14日 上午11:03:11
	* @param id
	* @return
	 */
	public Integer countUse(Integer id);

	/**
	* @Title: countNumber
	* @Description:统计编号
	* @author wdz
	* @date 2018年3月14日 下午1:20:11
	* @param number
	 * @param id 
	* @return
	 */
	public Integer countNumber(String number, Integer id);

	/**
	* @Title: delete
	* @Description: 删除
	* @author wdz
	* @date 2018年3月14日 下午1:20:54
	* @param id
	 */
	public void delete(Integer id);

	/**
	* @Title: findPage
	* @Description: 列表
	* @author wdz
	* @date 2018年3月14日 下午2:49:49
	* @param pageNo
	* @param pageSize
	* @param name
	* @return
	 */
	public Pagination<Customer> findPage(Integer pageNo, Integer pageSize, String name);

	/**
	* @Title: getCustomer
	* @Description: 获取对象
	* @author wdz
	* @date 2018年3月16日 上午11:07:21
	* @param customerId
	* @return
	 */
	public Customer getCustomer(Integer customerId);

}
