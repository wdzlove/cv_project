package com.jrfcv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrfcv.bean.Position;
import com.jrfcv.dao.PositionDao;
import com.jrfcv.service.PositionMng;
import com.jrfcv.util.Pagination;
/**
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *岗位管理业务层实现类
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
public class PositionMngImpl implements PositionMng {

	@Autowired
	private PositionDao positionDao;
	@Override
	public List<Position> findList() {
		return positionDao.findList();
	}

	@Override
	public Integer countName(Integer id, String name,Integer customerId) {
		return positionDao.countName(id,name,customerId);
	}

	@Override
	public Integer countNumber(Integer id, String number) {
		return positionDao.countNumber(id,number);
	}

	@Override
	public void save(Position p) {
		positionDao.save(p);
	}

	@Override
	public void update(Position p) {
		positionDao.update(p);
	}

	@Override
	public Integer countUse(Integer id) {
		return positionDao.countUse(id);
	}

	@Override
	public void delete(Integer id) {
		positionDao.delete(id);
	}

	@Override
	public Pagination<Position> findPage(Integer pageNo, Integer pageSize, String name) {
		return positionDao.findPage(pageNo,pageSize,name);
	}

	@Override
	public List<Position> findListByCusId(Integer cusId) {
		return positionDao.findListByCusId(cusId);
	}

	@Override
	public Position getPosition(Integer id) {
		return positionDao.getPosition(id);
	}

}
