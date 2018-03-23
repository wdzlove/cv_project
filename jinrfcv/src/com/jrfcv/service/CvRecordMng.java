package com.jrfcv.service;

import com.jrfcv.bean.CvRecord;
import com.jrfcv.bean.User;
import com.jrfcv.util.Pagination;

/**
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *简历操作记录
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
public interface CvRecordMng {

	public Pagination<CvRecord> findPage(Integer pageNo,Integer pageSize,Integer cvId);

	
	public void saveRecord(Integer userId, Integer id, String record);
	
}
