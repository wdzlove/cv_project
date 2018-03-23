package com.jrfcv.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class WordUtils {
	// 配置信息,代码本身写的还是很可读的,就不过多注解了
	private static Configuration configuration = null;
	// 这里注意的是利用WordUtils的类加载器动态获得模板文件的位置
	 private static final String templateFolder =
	 WordUtils.class.getClassLoader().getResource("../../").getPath() +"WEB-INF/templetes/";
	//private static final String templateFolder = "/templet/";

	static {
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
		try {
			configuration.setDirectoryForTemplateLoading(new File(templateFolder));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private WordUtils() {
		throw new AssertionError();
	}

	public static void exportMillCertificateWord(HttpServletRequest request, HttpServletResponse response, Map map,
			String title, String ftlFile) throws IOException {
		Template freemarkerTemplate = configuration.getTemplate(ftlFile);
		File file = null;
		InputStream fin = null;
		ServletOutputStream out = null;
		try {
			// 生成Word文档
			file = createDoc(map, freemarkerTemplate);
			fin = new FileInputStream(file);
			//设置编码及相应类型
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/msword");
			// 告诉浏览器下载的方式处理该文件名，防止乱码
			String fileName = title;
			response.setHeader("Content-Disposition","attachment;filename=".concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
			out = response.getOutputStream();
			// 缓冲区
			byte[] buffer = new byte[512];
			int bytesToRead = -1;
			// 将读入的Word文件的内容写入到浏览器
			while ((bytesToRead = fin.read(buffer)) != -1) {
				out.write(buffer, 0, bytesToRead);
			}
		} finally {
			if (fin != null)
				fin.close();
			if (out != null)
				out.close();
			if (file != null)
				file.delete(); // 删除临时文件
		}
	}

	
	private static File createDoc(Map<?, ?> dataMap, Template template) {
		String name = "model.doc";
		File f = new File(name);
		Template t = template;
		try {
			//指定编码：如果不指定模板，文件将打不开
			Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
			t.process(dataMap, w);
			w.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return f;
	}
}
