package com.jrfcv.act;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrfcv.bean.CvRecord;
import com.jrfcv.service.CvRecordMng;
import com.jrfcv.util.Pagination;

/**
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *
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
@Controller
public class CvRecordAct {

	@Autowired
	private CvRecordMng cvRecordMng;
	
	
	/**
	* @Title: findPage
	* @Description:操作记录列表
	* @author wdz
	* @date 2018年3月15日 下午2:01:09
	* @param model
	* @param pageNo
	* @param pageSize
	* @param cvId
	* @return
	 */
	@RequestMapping(value="find_log.do")
	public String findPage(ModelMap model,Integer pageNo,Integer pageSize,Integer id){
		if(pageNo == null){
			pageNo = 1;
		}
		if(pageSize == null){
			pageSize = 10;
		}
		Pagination<CvRecord> page = cvRecordMng.findPage(pageNo, pageSize, id);
		model.put("page", page);
		model.put("cvId", id);
		return "log/list";
	}
}
