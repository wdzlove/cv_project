package com.jrfcv.act;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jrfcv.bean.User;
import com.jrfcv.service.UserMng;
import com.jrfcv.util.StringUtils;

import net.sf.json.JSONObject;


/**
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *登录控制器
 * <p>
 *
 * 区分　责任人　日期　　　　说明<br/>
 * 创建 wdz　2018年3月8日　<br/>
 * <p>
 *
 * @author Administrator
 *
 * @version 1.0, 2018年3月8日
 *
 */
@Controller
public class LoginAct {

	@Autowired
	private UserMng userMng;
	
	@RequestMapping(value="login.do",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="login.do",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String chooseUpload(HttpServletRequest request, String userName,String passWord){
		JSONObject obj = new JSONObject();
		String code = "0";
		String message = "";
		try {
			if(!StringUtils.isBlank(userName) && !StringUtils.isBlank(passWord)){
				User user = userMng.checkUser(userName,passWord);
				if(user != null){
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					code = "1";
					message = "登录成功";
				}else{
					code = "2";
					message = "用户名/密码不正确";
				}
			}else{
				code = "3";
				message = "用户名/密码不正确";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "登录失败";
		}
		obj.put("code", code);
		obj.put("message", message);
		return obj.toString();
	}
}
