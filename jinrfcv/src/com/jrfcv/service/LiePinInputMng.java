package com.jrfcv.service;

import java.io.File;

import com.jrfcv.bean.User;

/**
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *猎聘简历读取业务接口
 * <p>
 *
 * 区分　责任人　日期　　　　说明<br/>
 * 创建 wdz　2018年3月7日　<br/>
 * <p>
 *
 * @author Administrator
 *
 * @version 1.0, 2018年3月7日
 *
 */
public interface LiePinInputMng {

	/**
	* @Title: readExcel
	* @Description: 读取简历
	* @author wdz
	* @date 2018年3月7日 下午1:22:05
	* @param f
	 * @param user 
	* @return
	 */
	public String readExcel(File f, User user);

}
