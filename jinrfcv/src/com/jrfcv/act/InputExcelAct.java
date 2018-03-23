package com.jrfcv.act;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrfcv.bean.User;

/**
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *简历上传跳转
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
@Controller
public class InputExcelAct {

	/**
	* @Title: uploadPage
	* @Description:猎聘上传页面跳转
	* @author wdz
	* @date 2018年3月7日 上午10:45:50
	* @return
	 */
	@RequestMapping(value="upload_lp.do")
	public String liePin(){
		return "/liepin/upload";
	}
	
	@RequestMapping(value="upload_zl.do")
	public String zhiLian(){
		return "/zhilian/upload";
	}
	
	@RequestMapping(value="choose_upload.do")
	public String uploadPage(HttpServletRequest request,ModelMap model){
		User user = (User) request.getSession().getAttribute("user");
		if(user != null){
			model.put("user", user);
		}
		return "index";
	}
	
	@RequestMapping(value="exit.do")
	public String exit(HttpServletRequest request){
		request.getSession().removeAttribute("user");
		return "login";
	}
}
