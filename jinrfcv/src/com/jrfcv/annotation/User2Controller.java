package com.jrfcv.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//类注解
@Controller
@RequestMapping(value="/user2")
public class User2Controller {

	//方法注解 value是请求的路径 method就请求的方式
		@RequestMapping(value="/addUser")
		public ModelAndView addUser(HttpServletRequest request,HttpServletResponse response){
			
			String result="注解的用户添加方法----优化版";
			
			return new ModelAndView("/annotation","result",result);
		}
		
		@RequestMapping(value="/deleteUser")
		public ModelAndView deleteUser(HttpServletRequest request,HttpServletResponse response){
			
			String result="注解的用户删除方法----优化版";
			
			return new ModelAndView("/annotation","result",result);
		}
		
		//可以把value省略,返回String类型 request返回值
		@RequestMapping("/toUser")
		public String toUser(HttpServletRequest request,HttpServletResponse response){
			
			String result="注解的用户TO方法----优化版";
			request.setAttribute("result", result);
			return "/annotation";
		}
}
