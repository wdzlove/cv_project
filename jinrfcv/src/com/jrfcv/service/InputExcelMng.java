package com.jrfcv.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.jrfcv.bean.CV;
import com.jrfcv.bean.User;

/**
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *解析excel业务接口
 * <p>
 *
 * 区分　责任人　日期　　　　说明<br/>
 * 创建 wdz　2018年2月7日　<br/>
 * <p>
 *
 * @author Administrator
 *
 * @version 1.0, 2018年2月7日
 *
 */
public interface InputExcelMng {

	/**
	* @Title: readExcel
	* @Description:读取excel
	* @author wdz
	* @date 2018年2月7日 下午6:21:30
	* @param f
	 * @param user 
	* @return
	 */
	public String readExcel(File f, User user);

	/**
	* @Title: SaveFileFromInputStream
	* @Description: 将附件临时存储
	* @author wdz
	* @date 2018年2月7日 下午6:31:07
	* @param in
	* @param uploadPath
	* @param filename
	* @return
	* @throws IOException
	 */
	public String SaveFileFromInputStream(InputStream in, String uploadPath, String filename) throws IOException;

}
