package com.jrfcv.act;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jrfcv.bean.User;
import com.jrfcv.service.InputExcelMng;
import com.jrfcv.service.LiePinInputMng;
import com.jrfcv.web.util.RealPathResolver;

import net.sf.json.JSONObject;

/**
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *简历上传
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
public class UploadExcelCvAct {

	@Autowired
	private InputExcelMng inputExcelMng;
	@Autowired
	private LiePinInputMng liePinInputMng;
	@Autowired 
	RealPathResolver realPath;
		
	
	@RequestMapping(value="upload_file.do",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String uploadFile(HttpServletRequest request,HttpServletResponse response,Integer type){
		User user = (User) request.getSession().getAttribute("user");
		if(user == null){
			return "redirect:login.do";
		}
		JSONObject obj = new JSONObject();
		String code = "0";
		String message = "";
		//得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String uploadPath = realPath.getPath("/upload");
		//判断文件夹是否存在，不存在则创建文件夹
		File folder = new File(uploadPath);
		if(!folder.exists()){
			folder.mkdir();
		}
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multipartRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multipartRequest.getFile((String) iter.next());
				String fileName = file.getOriginalFilename();
				String path = SaveFileFromInputStream(file.getInputStream(), uploadPath,fileName);
				File f = new File(path);
				if(type == 1){
					message = inputExcelMng.readExcel(f,user);
				}else if(type == 2){
					message = liePinInputMng.readExcel(f,user);
				}
				if(message.equals("文件重复") || message.indexOf("没有数据") != -1){
					code = "2";
				}else{
					code = "1";
				}
				f.delete();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		obj.put("message", message);
		return obj.toString();
	}
	private String SaveFileFromInputStream(InputStream stream, String path, String fileName) throws Exception {
		//创建输出流
		FileOutputStream fs = new FileOutputStream(path + "/"+ fileName);
		File file = new File(path + "/"+ fileName);
		if(!file.exists()){
			file.createNewFile();
		}
		//创建缓冲区
		byte[] buffer = new byte[1024 * 1024];
		//判断输入流中的数据是否已经读完的标识
		int bytesum = 0;//记录已读取的数据
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, bytesum);//将读到的数据写入文件
			fs.flush();
		}
		fs.close();
		stream.close();
		return path + "\\" + fileName;
	}
	
	
	/**
	* @Title: uploadHead
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author wdz
	* @date 2018年3月19日 下午1:14:12
	* @param request
	* @param response
	* @param cvId
	 */
	@RequestMapping(value="save_head.do")
	@ResponseBody
	public String uploadHead(HttpServletRequest request,HttpServletResponse response,Integer cvId){
		JSONObject obj = new JSONObject();
		String code = "0";
		String uploadPath = realPath.getPath("/upload");
		//判断文件夹是否存在，不存在则创建文件夹
		File folder = new File(uploadPath);
		if(!folder.exists()){
			folder.mkdir();
		}
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multipartRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multipartRequest.getFile((String) iter.next());
				String fileName = file.getOriginalFilename();
				int lastIndexOf = fileName.lastIndexOf(".");
				fileName = fileName.substring(lastIndexOf);
				SaveFileFromInputStream(file.getInputStream(), uploadPath,"cv_"+cvId+".jpg");
				code = "1";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	
}
