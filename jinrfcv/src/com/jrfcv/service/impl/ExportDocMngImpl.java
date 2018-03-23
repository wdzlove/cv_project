package com.jrfcv.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrfcv.bean.CV;
import com.jrfcv.bean.CVCertificate;
import com.jrfcv.bean.CVEducation;
import com.jrfcv.bean.CVInSchool;
import com.jrfcv.bean.CVJobExperience;
import com.jrfcv.bean.CVJobIntention;
import com.jrfcv.bean.CVLanguage;
import com.jrfcv.bean.CVProject;
import com.jrfcv.bean.CVRecommend;
import com.jrfcv.bean.CVSchoolPractice;
import com.jrfcv.bean.CVSkill;
import com.jrfcv.bean.CVTrain;
import com.jrfcv.bean.Customer;
import com.jrfcv.bean.CvRecord;
import com.jrfcv.bean.RecommendPosition;
import com.jrfcv.bean.User;
import com.jrfcv.dao.CustomerDao;
import com.jrfcv.dao.CvRecordDao;
import com.jrfcv.dao.ExportDocDao;
import com.jrfcv.dao.InputExcelDao;
import com.jrfcv.dao.UserDao;
import com.jrfcv.service.ExportDocMng;
import com.jrfcv.util.Pagination;
import com.jrfcv.util.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ExportDocMngImpl implements ExportDocMng {

	@Autowired
	private ExportDocDao exportDocDao;
	@Autowired
	private InputExcelDao inputExcelDao;
	@Autowired
	private CvRecordDao cvRecordDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public List<CV> findList() {
		return exportDocDao.findList();
	}
	@Override
	public CV getDetail(Integer id)  {
		CV cv = exportDocDao.getDetail(id);
		CVJobIntention intention = exportDocDao.getIntention(id);
		List<CVCertificate> cvcs = exportDocDao.getCvcs(id);
		List<CVEducation> cves = exportDocDao.getCves(id);
		if(cves != null && cves.size() > 0){
			for (CVEducation cvEducation : cves) {
				String startTime = cvEducation.getStartTime();
				if(!StringUtils.isBlank(startTime)){
					if(startTime.indexOf("/") != -1){
						String replace = startTime.replace("/", ".");
						cvEducation.setStartTime(replace);
					}
				}
				String endTime = cvEducation.getEndTime();
				if(!StringUtils.isBlank(endTime)){
					if(endTime.indexOf("/") != -1){
						String replace = endTime.replace("/", ".");
						cvEducation.setEndTime(replace);
					}
				}
			}
			String education = cves.get(0).getEducation();
			cv.setEducation(education);
		}
		List<CVJobExperience> cvjs = exportDocDao.getCvjs(id);
		if(cvjs != null && cvjs.size() > 0){
			for (CVJobExperience cvJobExperience : cvjs) {
				String startTime = cvJobExperience.getStarTime();
				String endTime = cvJobExperience.getEndTime();
				if(!StringUtils.isBlank(startTime)){
					if(startTime.indexOf("/") != -1){
						String replace = startTime.replace("/", ".");
						cvJobExperience.setStarTime(replace);
					}
				}
				if(!StringUtils.isBlank(endTime)){
					if(endTime.indexOf("/") != -1){
						String replace = endTime.replace("/", ".");
						cvJobExperience.setEndTime(replace);
					}
				}
			}
		}
		List<CVSkill> cvks = exportDocDao.getCvks(id);
		List<CVLanguage> cvls = exportDocDao.getCvls(id);
		List<CVProject> cvps = exportDocDao.getCvps(id);
		if(cvps != null && cvps.size() > 0){
			for (CVProject cvProject : cvps) {
				String startTime = cvProject.getStartTime();
				String endTime = cvProject.getEndTime();
				if(!StringUtils.isBlank(startTime)){
					if(startTime.indexOf("/") != -1){
						String replace = startTime.replace("/", ".");
						cvProject.setStartTime(replace);
					}
				}
				if(!StringUtils.isBlank(endTime)){
					if(endTime.indexOf("/") != -1){
						String replace = endTime.replace("/", ".");
						cvProject.setEndTime(replace);
					}
				}
			}
		}
		List<CVSchoolPractice> cvsps = exportDocDao.getCvsps(id);
		List<CVInSchool> cvss = exportDocDao.getCvss(id);
		List<CVTrain> cvts = exportDocDao.getCvts(id);
		CVRecommend recommend = exportDocDao.getRecommend(id);
		if(recommend != null){
			Integer rid = recommend.getId();
			List<RecommendPosition> lists = exportDocDao.getRecomPost(rid);
			if(lists != null && lists.size() > 0){
				String ids = null;
				for (RecommendPosition recommendPosition : lists) {
					if(ids == null){
						ids = recommendPosition.getPosId().toString();
					}else{
						ids = ids+","+recommendPosition.getPosId();
					}
				}
				recommend.setJobIds(ids);
			}
			recommend.setPosts(lists);
			cv.setRecommend(recommend);
		}
		if(intention != null){
			cv.setIntention(intention);
		}
		cv.setCvcs(cvcs);
		cv.setCves(cves);
		cv.setCvjs(cvjs);
		cv.setCvks(cvks);
		cv.setCvls(cvls);
		cv.setCvps(cvps);
		cv.setCvsps(cvsps);
		cv.setCvss(cvss);
		cv.setCvts(cvts);
		if(StringUtils.isBlank(cv.getAge())){
			String birthday = cv.getBirthday();
			boolean checkDate = checkDate(birthday);
			if(checkDate){
				SimpleDateFormat ym = new SimpleDateFormat("yyyy年MM月");
				SimpleDateFormat y = new SimpleDateFormat("yyyy");
				try {
					Date parse = ym.parse(birthday);
					String format = y.format(parse);
					String format2 = y.format(new Date());
					Integer age = Integer.valueOf(format2)-Integer.valueOf(format);
					cv.setAge(age.toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		
		return cv;
	}
	public boolean checkDate(String date) {
		DateFormat df = new SimpleDateFormat("yyyy年MM月");
		Date d = null;
		try {
			d = df.parse(date);
		} catch (Exception e) {
			// 如果不能转换,肯定是错误格式
			return false;
		}
		//String s1 = df.format(d);
		// 转换后的日期再转换回String,如果不等,逻辑错误.如format为"yyyy-MM-dd",date为
		// "2006-02-31",转换为日期后再转换回字符串为"2006-03-03",说明格式虽然对,但日期
		// 逻辑上不对.
		return true;
	}
	@Override
	public Integer saveRecommend(CVRecommend cvr, String postIds) {
		Integer saveRecommend = exportDocDao.saveRecommend(cvr);
		String record = "保存了推荐信息";
		saveRecord(cvr.getUserId(), cvr.getCvId(),record);
		if(!StringUtils.isBlank(postIds)){
			String[] split = postIds.split(",");
			for (int i = 0; i < split.length; i++) {
				exportDocDao.saveRecomPost(split[i],saveRecommend);
			}
		}
		exportDocDao.updateCVTime(cvr.getCvId(), new Date());
		return saveRecommend;
	}
	@Override
	public CVJobExperience getJobExp(Integer id, Integer cvId) {
		return exportDocDao.getJobExp(id,cvId);
	}
	@Override
	public CVProject getProjectExp(Integer id, Integer cvId) {
		return exportDocDao.getProjectExp(id,cvId);
	}
	@Override
	public CVJobIntention getHopeJob(Integer id, Integer cvId) {
		return exportDocDao.getIntention(cvId);
	}
	@Override
	public void updateEducation(CVEducation edu) {
		exportDocDao.updateCVTime(edu.getCvId(),new Date());
		exportDocDao.updateEducation(edu);
		String record = "修改了教育经历";
		saveRecord(edu.getUserId(), edu.getCvId(), record);
	}
	@Override
	public void updateJob(CVJobExperience job) {
		exportDocDao.updateCVTime(job.getCvId(),new Date());
		exportDocDao.updateJob(job);	
		String record = "修改了工作经历";
		saveRecord(job.getUserId(), job.getCvId(), record);
	}
	@Override
	public void updatePro(CVProject pro) {
		exportDocDao.updateCVTime(pro.getCvId(),new Date());
		exportDocDao.updatePro(pro);
		String record = "修改了项目经历";
		saveRecord(pro.getUserId(), pro.getCvId(), record);
	}
	@Override
	public void updateHope(CVJobIntention hope) {
		exportDocDao.updateCVTime(hope.getCvId(),new Date());
		exportDocDao.updateHope(hope);
	}
	@Override
	public CVTrain findTrain(Integer id, Integer cvId) {
		return exportDocDao.findTrain(id,cvId);
	}
	@Override
	public void updateTrain(CVTrain train) {
		exportDocDao.updateCVTime(train.getCvId(),new Date());
		exportDocDao.updateTrain(train);
		String record = "修改了培训经历";
		saveRecord(train.getUserId(), train.getCvId(), record);
	}
	@Override
	public void updateLanguage(CVLanguage lan) {
		exportDocDao.updateCVTime(lan.getCvId(),new Date());
		exportDocDao.updateLanguage(lan);	
		String record = "修改了语言";
		saveRecord(lan.getUserId(), lan.getCvId(), record);
	}
	@Override
	public void updateSkill(CVSkill sk) {
		exportDocDao.updateCVTime(sk.getCvId(),new Date());
		exportDocDao.updateSkill(sk);
		String record = "修改了技能";
		saveRecord(sk.getUserId(), sk.getCvId(), record);
	}
	@Override
	public void updateCV(CV detail) {
		exportDocDao.updateCV(detail);		
	}
	@Override
	public void delCv(Integer id) {
		exportDocDao.delCv(id);		
		exportDocDao.delCerti(id);
		exportDocDao.delEdu(id,null);
		exportDocDao.delInSchool(id);
		exportDocDao.delJobInter(id);
		exportDocDao.delLanguage(id,null);
		exportDocDao.delOther(id);
		exportDocDao.delProject(id,null);
		exportDocDao.delSchool(id);
		exportDocDao.delSkill(id,null);
		exportDocDao.delTrain(id,null);
		exportDocDao.delJobExper(id,null);
		cvRecordDao.delRecord(id);
		CVRecommend recommend = exportDocDao.getRecommend(id);
		if(recommend != null){
			exportDocDao.delRecomPost(recommend.getId());
		}
		exportDocDao.delRecomment(id);
	}
	@Override
	public Pagination<CV> findPage(Integer pageNo, Integer pageSize, Integer state, Integer source, Integer userId, Integer inputUser, String value) {
		Pagination<CV> findPage = exportDocDao.findPage(pageNo,pageSize,state,source,userId,inputUser,value);
		if(findPage != null){
			List<CV> list = findPage.getList();
			if(list != null && list.size() > 0){
				for (int i = 0; i < list.size(); i++) {
					CV cv = list.get(i);
					CVRecommend recommend = exportDocDao.getRecommend(cv.getId());
					if(recommend != null){
						String entName = recommend.getEntName();
						cv.setCustomer(entName);
					}
					if(StringUtils.isBlank(cv.getAge())){
						String birthday = cv.getBirthday();
						boolean checkDate = checkDate(birthday);
						if(checkDate){
							SimpleDateFormat ym = new SimpleDateFormat("yyyy年MM月");
							SimpleDateFormat y = new SimpleDateFormat("yyyy");
							try {
								Date parse = ym.parse(birthday);
								String format = y.format(parse);
								String format2 = y.format(new Date());
								Integer age = Integer.valueOf(format2)-Integer.valueOf(format);
								cv.setAge(age.toString());
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		return findPage;
	}
	
	@Override
	public void delEdu(Integer id, Integer cvId,Integer userId) {
		exportDocDao.delEdu(cvId,id);
		String record = "删除了教育经历";
		saveRecord(userId, cvId, record);
		exportDocDao.updateCVTime(cvId, new Date());
	}
	@Override
	public void delJobExp(Integer id, Integer cvId,Integer userId) {
		exportDocDao.delJobExper(cvId,id);
		String record = "删除了工作经历";
		saveRecord(userId, cvId, record);
		exportDocDao.updateCVTime(cvId, new Date());
	}
	@Override
	public void delLanguage(Integer cvId, Integer id,Integer userId) {
		exportDocDao.delLanguage(cvId, id);	
		String record = "删除了语言能力";
		saveRecord(userId, cvId, record);
		exportDocDao.updateCVTime(cvId, new Date());
	}
	@Override
	public void delProject(Integer cvId, Integer id,Integer userId) {
		exportDocDao.delProject(cvId, id);
		String record = "删除了项目经历";
		saveRecord(userId, cvId, record);
		exportDocDao.updateCVTime(cvId, new Date());
	}
	@Override
	public void delSkill(Integer cvId, Integer id,Integer userId) {
		exportDocDao.delSkill(cvId, id);
		String record = "删除了技能";
		saveRecord(userId, cvId, record);
		exportDocDao.updateCVTime(cvId, new Date());
	}
	@Override
	public void delTrain(Integer cvId, Integer id,Integer userId) {
		exportDocDao.delTrain(cvId, id);
		String record = "删除了培训经历";
		saveRecord(userId, cvId, record);
		exportDocDao.updateCVTime(cvId, new Date());
	}
	@Override
	public void updateState(Integer id, Integer state, Integer userId,Integer toUserId) {
		exportDocDao.updateState(id,state,toUserId);
		if(state != null){
			String record = "将简历状态修改为：";
			if(state == 1){
				record+="已通知";
			}else if(state == 2){
				record+="已面试";
			}else if(state == 3){
				record+="已淘汰";
			}else if(state == 4){
				record+="已入职";
			}else if(state == 5){
				record+="已转正";
			}else if(state == 6){
				record+="已离职";
			}else if(state == 7){
				record+="已储备";
			}
			saveRecord(userId, id, record);
		}else if(toUserId != null && toUserId > 0){
			User user = userDao.getUser(toUserId);
			String record = "将简历指派给："+user.getRealName();
			saveRecord(userId, id, record);
		}
		exportDocDao.updateCVTime(id, new Date());
	}
	@Override
	public void addEducation(CVEducation edu) {
		List<CVEducation> cves = new ArrayList<CVEducation>();
		cves.add(edu);
		inputExcelDao.saveCVEducation(cves, edu.getCvId());
		String record = "添加了教育经历";
		saveRecord(edu.getUserId(), edu.getCvId(), record);
		exportDocDao.updateCVTime(edu.getCvId(), new Date());
	}
	@Override
	public void addJobExp(CVJobExperience jobexp) {
		List<CVJobExperience> cvjs = new ArrayList<CVJobExperience>();
		cvjs.add(jobexp);
		inputExcelDao.saveCVJobExperience(cvjs, jobexp.getCvId());
		String record = "添加了工作经历";
		saveRecord(jobexp.getUserId(), jobexp.getCvId(), record);
		exportDocDao.updateCVTime(jobexp.getCvId(), new Date());
	}
	@Override
	public void addProject(CVProject pro) {
		List<CVProject> cvps = new ArrayList<CVProject>();
		cvps.add(pro);
		inputExcelDao.saveCVProject(cvps, pro.getCvId());
		String record = "添加了项目经历";
		saveRecord(pro.getUserId(), pro.getCvId(), record);
		exportDocDao.updateCVTime(pro.getCvId(), new Date());
	}
	@Override
	public void addSkill(CVSkill skill) {
		List<CVSkill> cvks = new ArrayList<CVSkill>();
		cvks.add(skill);
		inputExcelDao.saveCVSkill(cvks, skill.getCvId());
		String record = "添加了技能";
		saveRecord(skill.getUserId(), skill.getCvId(), record);
		exportDocDao.updateCVTime(skill.getCvId(), new Date());
	}
	@Override
	public void addLanguage(CVLanguage language) {
		List<CVLanguage> cvls = new ArrayList<CVLanguage>();
		cvls.add(language);
		inputExcelDao.saveCVLanguage(cvls, language.getCvId());
		String record = "添加了语言";
		saveRecord(language.getUserId(), language.getCvId(), record);
		exportDocDao.updateCVTime(language.getCvId(), new Date());
	}
	@Override
	public void addTrain(CVTrain train) {
		List<CVTrain> cvts = new ArrayList<CVTrain>();
		cvts.add(train);
		inputExcelDao.saveCVTrain(cvts, train.getCvId());
		String record = "添加了培训经历";
		saveRecord(train.getUserId(), train.getCvId(), record);
		exportDocDao.updateCVTime(train.getCvId(), new Date());
	}
	@Override
	public void eduSaveSort(String datas,Integer userId) {
		Integer cvId = 0;
		JSONArray fromObject = JSONArray.fromObject(datas);
		for (Object object : fromObject) {
			JSONObject obj = JSONObject.fromObject(object);
			if(!StringUtils.isBlank(obj)){
				cvId = obj.getInt("cvId");
				Integer id = obj.getInt("id");
				Integer index = obj.getInt("index");
				inputExcelDao.updateEduPosition(cvId,id,index);
			}
		}
		String record = "给教育经历排序";
		saveRecord(userId, cvId, record);
		exportDocDao.updateCVTime(cvId, new Date());
	}
	@Override
	public void jobExpSaveSort(String datas, Integer userId) {
		Integer cvId = 0;
		JSONArray fromObject = JSONArray.fromObject(datas);
		for (Object object : fromObject) {
			JSONObject obj = JSONObject.fromObject(object);
			if(!StringUtils.isBlank(obj)){
				cvId = obj.getInt("cvId");
				Integer id = obj.getInt("id");
				Integer index = obj.getInt("index");
				inputExcelDao.jobExpSaveSort(cvId,id,index);
			}
		}
		String record = "给工作经历排序";
		saveRecord(userId, cvId, record);
		exportDocDao.updateCVTime(cvId, new Date());
	}
	@Override
	public void proSaveSort(String datas, Integer userId) {
		Integer cvId = 0;
		JSONArray fromObject = JSONArray.fromObject(datas);
		for (Object object : fromObject) {
			JSONObject obj = JSONObject.fromObject(object);
			if(!StringUtils.isBlank(obj)){
				cvId = obj.getInt("cvId");
				Integer id = obj.getInt("id");
				Integer index = obj.getInt("index");
				inputExcelDao.proSaveSort(cvId,id,index);
			}
		}
		String record = "给项目经历排序";
		saveRecord(userId, cvId, record);
		exportDocDao.updateCVTime(cvId, new Date());
	}
	@Override
	public void upRecommend(CVRecommend cvr, String postIds) {
		exportDocDao.upRecommend(cvr);	
		exportDocDao.delRecomPost(cvr.getId());
		if(!StringUtils.isBlank(postIds)){
			String[] split = postIds.split(",");
			for (int i = 0; i < split.length; i++) {
				exportDocDao.saveRecomPost(split[i],cvr.getId());
			}
		}
		String record = "修改了推荐信息";
		saveRecord(cvr.getUserId(), cvr.getCvId(), record);
		exportDocDao.updateCVTime(cvr.getCvId(), new Date());
	}
	@Override
	public CVRecommend getRecommend(Integer id) {
		CVRecommend recommend = exportDocDao.getRecommend(id);
		if(recommend != null){
			Integer rid = recommend.getId();
			List<RecommendPosition> lists = exportDocDao.getRecomPost(rid);
			if(lists != null && lists.size() > 0){
				String ids = null;
				for (RecommendPosition recommendPosition : lists) {
					if(ids == null){
						ids = recommendPosition.getPosId().toString();
					}else{
						ids = ids+","+recommendPosition.getPosId();
					}
				}
				recommend.setJobIds(ids);
			}
			recommend.setPosts(lists);
		}
		return recommend;
	}
	
	private void saveRecord(Integer userId,Integer cvId,String record){
		CvRecord c = new CvRecord();
		c.setCvId(cvId);
		c.setUserId(userId);
		c.setRecord(record);
		c.setUpdateTime(new Date());
		String customerName = getCustomerName(cvId);
		c.setCustomer(customerName);
		cvRecordDao.saveRecord(c);
	}
	
	private String getCustomerName(Integer cvId){
		CVRecommend recommend = exportDocDao.getRecommend(cvId);
		if(recommend != null){
			Customer cust =  customerDao.getCustomer(recommend.getCustomerId());
			if(cust != null){
				return cust.getName();
			}
		}
		return null;
	}
	
}
