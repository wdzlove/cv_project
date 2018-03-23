package com.jrfcv.web.util;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
@Component
public class ServletContextRealPathResolver implements RealPathResolver,ServletContextAware {

	private final Logger logger = LoggerFactory.getLogger(ServletContextRealPathResolver.class);
	
	public String getPath(String path) {
		String realpath = context.getRealPath(path);
		// tomcat8.0获取不到真实路径，通过/获取路径
		if (realpath == null) {
			realpath = context.getRealPath("/") + path;
		}
		return realpath;
	}

	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}

	private ServletContext context;

}
