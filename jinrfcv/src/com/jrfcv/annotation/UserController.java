package com.jrfcv.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//类注解
@Controller
public class UserController {

	//方法注解 value是请求的路径 method就请求的方式
	@RequestMapping(value="/user/addUser",method=RequestMethod.GET)
	public ModelAndView addUser(HttpServletRequest request,HttpServletResponse response){
		
		String result="注解的用户添加方法";
		
		return new ModelAndView("/annotation","result",result);
	}
	
	@RequestMapping(value="/user/deleteUser",method=RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request,HttpServletResponse response){
		
		String result="注解的用户删除方法";
		
		return new ModelAndView("/annotation","result",result);
	}
	
}
