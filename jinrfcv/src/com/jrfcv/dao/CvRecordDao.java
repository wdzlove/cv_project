package com.jrfcv.dao;

import com.jrfcv.bean.CvRecord;
import com.jrfcv.util.Pagination;

/**
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *简历记录数据层接口
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
public interface CvRecordDao {

	/**
	* @Title: findPage
	* @Description:记录列表
	* @author wdz
	* @date 2018年3月15日 下午1:24:47
	* @param pageNo
	* @param pageSize
	 * @param cvId 
	* @return
	 */
	public Pagination<CvRecord> findPage(Integer pageNo, Integer pageSize, Integer cvId);

	/**
	* @Title: saveRecord
	* @Description: 保存
	* @author wdz
	* @date 2018年3月15日 下午2:22:19
	* @param cd
	 */
	public void saveRecord(CvRecord cd);


	/**
	* @Title: delRecord
	* @Description: 删除
	* @author wdz
	* @date 2018年3月15日 下午2:22:16
	* @param id
	 */
	public void delRecord(Integer id);
	
}
