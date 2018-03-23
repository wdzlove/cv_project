package com.jrfcv.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jrfcv.vo.UserVo;

@Controller
@RequestMapping(value="/data")
public class DataController {

	//方法注解 value是请求的路径 method就请求的方式
			@RequestMapping("/addUser")
			public String addUser(String username,String age,HttpServletRequest request,HttpServletResponse response){
				System.out.println(username);
				System.out.println(age);
				request.setAttribute("username", username);
				request.setAttribute("age", age);
				return "/userManage";
			}
			
			//uservo传递参数
			@RequestMapping("/addUserVo")
			public String addUserVo(UserVo uvo,HttpServletRequest request,HttpServletResponse response){
				
				request.setAttribute("username", uvo.getUsername());
				request.setAttribute("age", uvo.getAge());
				return "/userManage";
			}
			@RequestMapping("/toUser")
			public String toUser(HttpServletRequest request,HttpServletResponse response){
				
				return "/addUser";
			}
}
