package com.jrfcv.act;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrfcv.bean.CV;
import com.jrfcv.bean.CVEducation;
import com.jrfcv.bean.CVJobExperience;
import com.jrfcv.bean.CVJobIntention;
import com.jrfcv.bean.CVLanguage;
import com.jrfcv.bean.CVProject;
import com.jrfcv.bean.CVRecommend;
import com.jrfcv.bean.CVSkill;
import com.jrfcv.bean.CVTrain;
import com.jrfcv.bean.User;
import com.jrfcv.service.CvRecordMng;
import com.jrfcv.service.ExportDocMng;
import com.jrfcv.util.StringUtils;
import com.jrfcv.util.WordUtils;
import com.jrfcv.web.util.RealPathResolver;

import sun.misc.BASE64Encoder;

/***
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *导出控制器
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
@Controller
@RequestMapping(produces = "text/json;charset=UTF-8")
public class ExportExcelAct {

	@Autowired
	private ExportDocMng exportDocMng;
	@Autowired 
	RealPathResolver realPath;
	@Autowired
	private CvRecordMng cvRecordMng;
	
	
	@RequestMapping(value="export_doc.do")
	public void exportSellPlan(HttpServletRequest request, HttpServletResponse response,ModelMap model,Integer id) throws FileNotFoundException {
		User user = (User) request.getSession().getAttribute("user");
		if(user != null){
			CV cv = exportDocMng.getDetail(id);
			CVJobIntention intention = cv.getIntention();
			// 获得数据
			Map<String, Object> map = new HashMap<String, Object>();
			CVRecommend recommend = cv.getRecommend();
			if(recommend != null){
				map.put("recommendId", recommend.getRecommendId() == null?" ":recommend.getRecommendId());
				map.put("entName",recommend.getEntName() == null?" ":recommend.getEntName());
				map.put("job",recommend.getJob() == null?" ":recommend.getJob());
				map.put("baseInfo", recommend.getBaseInfo() == null?" ":recommend.getBaseInfo());
				map.put("leaveReason",recommend.getLeaveReason() == null?" ":recommend.getLeaveReason());
				map.put("reason", recommend.getReason() == null?" ":recommend.getReason());
				map.put("certificates",recommend.getCertificates() == null?" ":recommend.getCertificates());
				map.put("honors", recommend.getHonors() == null?" ":recommend.getHonors());
				map.put("faceTime",recommend.getFaceTime() == null?" ":recommend.getFaceTime());
				map.put("injobtime", recommend.getInJobTime() == null?" ":recommend.getInJobTime());
				map.put("salary",recommend.getSalary()==null?" ":recommend.getSalary());
				map.put("recommendJob",recommend.getHopeJob()==null?" ":recommend.getHopeJob());
			}
			map.put("username", cv.getName());
			map.put("logo", getImageStr(realPath.getPath("/img/logo.jpg")));
			map.put("sex", cv.getSex());
			String userPhoto = getUserPhoto(cv.getId());
			if(userPhoto == null){
				if(!StringUtils.isBlank(cv.getSex())){
					String path = "";
					if(cv.getSex().equals("男")){
						path = realPath.getPath("/img/boys.jpg");
					}else if(cv.getSex().equals("女")){
						path = realPath.getPath("/img/grils.jpg");
					}
					userPhoto = getImageBase(path);
				}
			}else{
				userPhoto = getImageBase(userPhoto);
			}
			map.put("userPhoto",userPhoto);
			map.put("marriage", cv.getMarriage()==null?"":cv.getMarriage());
			map.put("age", cv.getAge()==null?"":cv.getAge());
			map.put("workTime", cv.getJoinWorkTime()==null?" ":cv.getJoinWorkTime());
			map.put("email", cv.getEmail()==null?" ":cv.getEmail());
			map.put("newLife", cv.getNowLiveCity()==null?" ":cv.getNowLiveCity());
			map.put("detailAddress", cv.getAddress()==null?"":cv.getAddress());
			map.put("education", cv.getEducation()==null?"":cv.getEducation());
			map.put("mobile", cv.getMobile()==null?"":cv.getMobile());
			if(intention != null){
				//求职意向
				map.put("hopeWorkPlace", intention.getHopeWorkPlace()==null?"":intention.getHopeWorkPlace());
				map.put("hopeJob", intention.getHopeJob()==null?"":intention.getHopeJob());
				map.put("jobNature", intention.getJobNature()==null?"":intention.getJobNature());
				map.put("hopeIndustry", intention.getHopeIndustry()==null?"":intention.getHopeIndustry());
				map.put("hopeSalary", intention.getHopeSalary()==null?"":intention.getHopeSalary());
				map.put("newStatus", intention.getJobStatus()==null?"":intention.getJobStatus());
			}
			List<CVProject> cvps = cv.getCvps();
			map.put("projects",cvps);
			List<CVEducation> cves = cv.getCves();
			map.put("educations",cves);
			List<CVJobExperience> cvjes = cv.getCvjs();
			map.put("works", cvjes);
			List<CVTrain> cvTrain = cv.getCvts();
			map.put("trains", cvTrain);
			List<CVLanguage> languages = cv.getCvls();
			map.put("languages", languages);
			List<CVSkill> skills = cv.getCvks();
			map.put("skills", skills);
			map.put("hobby", cv.getHobby()==null?"":cv.getHobby());
			cvRecordMng.saveRecord(user.getUserId(), id, "导出了简历");
			try {
				WordUtils.exportMillCertificateWord(request, response, map, cv.getName()+".doc", "doc_model.ftl");//
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private String getUserPhoto(Integer cvId){
		File file = new File("/upload/"+"cv_"+cvId+".jpg");
		if(!file.exists()){
			String userPhoto = "/upload/"+"cv_"+cvId+".jpg";
			return realPath.getPath(userPhoto);
		}
		return null;
	}
	
	public byte[] getStream(String path) {
		try {
			File file = new File(path); // 将图片转换成file类型的文件
			if(!file.exists()){
				return null;
			}
			byte[] readFileToByteArray = FileUtils.readFileToByteArray(file);
			return readFileToByteArray;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getImageBase(String path) {
		byte[] data = getStream(path);
		if(data != null){
			BASE64Encoder encoder = new BASE64Encoder();
			String encode = encoder.encode(data);
			return encode;
		}
		return null;
	}
	
	/**
     * 图片转码
     * @return 返回图片base64字符串
     * @throws Exception
     */
    public String getImageStr(String imgFile){
        InputStream in = null;
        BASE64Encoder encoder = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
        } catch (FileNotFoundException e) {
            System.out.println("文件没找到！");
            e.printStackTrace();
        }
        try {
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
}
