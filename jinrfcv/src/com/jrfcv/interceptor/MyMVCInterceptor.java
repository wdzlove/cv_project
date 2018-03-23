package com.jrfcv.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyMVCInterceptor implements HandlerInterceptor {
	
	 private final static Logger logger = LoggerFactory.getLogger(MyMVCInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		logger.info("logback 登录检测");
		HttpSession session = request.getSession(true);
		  // 从session 里面获取用户名的信息  
		  Object obj = session.getAttribute("user");  
		  // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆  
		  if (obj == null || "".equals(obj.toString())) {  
			  response.sendRedirect("login.do");  
		  }  
		  return true;  
	}

}
