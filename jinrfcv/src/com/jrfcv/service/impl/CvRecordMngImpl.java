package com.jrfcv.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrfcv.bean.CvRecord;
import com.jrfcv.dao.CvRecordDao;
import com.jrfcv.service.CvRecordMng;
import com.jrfcv.util.Pagination;
/**
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *简历记录业务层实现类
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
@Service
public class CvRecordMngImpl implements CvRecordMng {

	@Autowired
	private CvRecordDao cvRecordDao;
	@Override
	public Pagination<CvRecord> findPage(Integer pageNo, Integer pageSize,Integer cvId) {
		return cvRecordDao.findPage(pageNo,pageSize,cvId);
	}
	@Override
	public void saveRecord(Integer userId, Integer id, String record) {
		CvRecord c = new CvRecord();
		c.setCvId(id);
		c.setUserId(userId);
		c.setUpdateTime(new Date());
		c.setRecord(record);
		cvRecordDao.saveRecord(c);	
	}
}
