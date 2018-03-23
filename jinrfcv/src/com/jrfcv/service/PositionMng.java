package com.jrfcv.service;

import java.util.List;

import com.jrfcv.bean.Position;
import com.jrfcv.util.Pagination;

/**
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *岗位管理业务层接口
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
public interface PositionMng {

	/**
	* @Title: findList
	* @Description:获取岗位列表
	* @author wdz
	* @date 2018年3月14日 下午2:17:16
	* @return
	 */
	public List<Position> findList();

	/**
	* @Title: countName
	* @Description:根据名称统计
	* @author wdz
	* @date 2018年3月14日 下午2:17:36
	* @param id
	* @param name
	 * @param customerId 
	* @return
	 */
	public Integer countName(Integer id, String name, Integer customerId);

	/**
	* @Title: countNumber
	* @Description: 根据编号统计
	* @author wdz
	* @date 2018年3月14日 下午2:18:15
	* @param id
	* @param number
	* @return
	 */
	public Integer countNumber(Integer id, String number);

	/**
	* @Title: save
	* @Description: 保存
	* @author wdz
	* @date 2018年3月14日 下午2:18:40
	* @param p
	 */
	public void save(Position p);

	/**
	* @Title: update
	* @Description: 修改
	* @author wdz
	* @date 2018年3月14日 下午2:18:56
	* @param p
	 */
	public void update(Position p);

	/**
	* @Title: countUse
	* @Description: 统计使用
	* @author wdz
	* @date 2018年3月14日 下午2:19:05
	* @param id
	* @return
	 */
	public Integer countUse(Integer id);

	/**
	* @Title: delete
	* @Description: 删除
	* @author wdz
	* @date 2018年3月14日 下午2:19:18
	* @param id
	 */
	public void delete(Integer id);

	/**
	* @Title: findPage
	* @Description:分页数据
	* @author wdz
	* @date 2018年3月14日 下午4:15:14
	* @param pageNo
	* @param pageSize
	* @param name
	* @return
	 */
	public Pagination<Position> findPage(Integer pageNo, Integer pageSize, String name);

	/**
	* @Title: findListByCusId
	* @Description: 根据客户编号查询相对应的岗位
	* @author wdz
	* @date 2018年3月16日 下午1:28:41
	* @param cusId
	* @return
	 */
	public List<Position> findListByCusId(Integer cusId);

	/**
	* @Title: getPosition
	* @Description:获取岗位
	* @author wdz
	* @date 2018年3月19日 上午11:35:07
	* @param id
	* @return
	 */
	public Position getPosition(Integer id);
}
