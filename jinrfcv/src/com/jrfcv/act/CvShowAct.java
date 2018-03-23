package com.jrfcv.act;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jrfcv.bean.CV;
import com.jrfcv.bean.CVEducation;
import com.jrfcv.bean.CVJobExperience;
import com.jrfcv.bean.CVJobIntention;
import com.jrfcv.bean.CVLanguage;
import com.jrfcv.bean.CVProject;
import com.jrfcv.bean.CVRecommend;
import com.jrfcv.bean.CVSkill;
import com.jrfcv.bean.CVTrain;
import com.jrfcv.bean.Customer;
import com.jrfcv.bean.Position;
import com.jrfcv.bean.User;
import com.jrfcv.service.CustomerMng;
import com.jrfcv.service.CvRecordMng;
import com.jrfcv.service.ExportDocMng;
import com.jrfcv.service.PositionMng;
import com.jrfcv.service.UserMng;
import com.jrfcv.util.Pagination;
import com.jrfcv.util.StringUtils;
import com.jrfcv.web.util.RealPathResolver;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
public class CvShowAct {

	@Autowired
	private ExportDocMng exportDocMng;
	@Autowired
	private UserMng userMng;
	@Autowired
	private CustomerMng customerMng;
	@Autowired
	private PositionMng positionMng;
	@Autowired 
	RealPathResolver realPath;
	@Autowired
	private CvRecordMng cvRecordMng;
	
	/**
	* @Title: findList
	* @Description:查询简历列表
	* @author wdz
	* @date 2018年2月8日 下午4:29:14
	* @param model
	* @return
	 */
	@RequestMapping(value="/find_list.do")
	public String findList(HttpServletRequest request, ModelMap model,Integer pageNo,Integer pageSize,String states,String sources,String userId,String inputUser,String value){
		User user = (User) request.getSession().getAttribute("user");
		if(user != null){
			if(pageNo == null){
				pageNo = 1;
			}
			if(pageSize == null){
				pageSize = 10;
			}
			Integer state = null;
			if(!StringUtils.isBlank(states)){
				state = Integer.valueOf(states);
			}
			Integer source = null;
			if(!StringUtils.isBlank(sources)){
				source = Integer.valueOf(sources);
			}
			Integer uid = null;
			if(!StringUtils.isBlank(userId)){
				uid = Integer.valueOf(userId);
			}
			Integer inpId = null;
			if(!StringUtils.isBlank(inputUser)){
				inpId = Integer.valueOf(inputUser);
			}
			if(user.getIsAdmin() == 0){
				uid = user.getUserId();
			}
			Pagination<CV> page = exportDocMng.findPage(pageNo,pageSize,state,source,uid,inpId,value);
			model.put("page", page);
			model.put("states", state);
			model.put("sources", source);
			model.put("userId", uid);
			model.put("inputUser", inpId);
			model.put("value", value);
			model.put("isAdmin", user.getIsAdmin());
			List<User> users = userMng.findList();
			model.put("users", users);
		}else{
			return "login";
		}
		return "cv_list";
	}
	/**
	* @Title: getDetail
	* @Description: 获取详细
	* @author wdz
	* @date 2018年2月8日 下午4:32:16
	* @param id
	* @return
	 */
	@RequestMapping(value="get_detail.do")
	public String getDetail(Integer id,ModelMap model){
		CV cv = exportDocMng.getDetail(id);
		String userPhoto = getUserPhoto(id);
		if(userPhoto == null){
			String sex = cv.getSex();
			if(sex.equals("男")){
				model.put("userPhoto", "/img/boys.jpg");
			}else if(sex.equals("女")){
				model.put("userPhoto", "/img/grils.jpg");
			}
		}else{
			model.put("userPhoto", userPhoto);
		}
		CVJobIntention intention = cv.getIntention();
		CVRecommend recommend = cv.getRecommend();
		model.put("entity", cv);
		model.put("recom", recommend);
		model.put("intention", intention);
		return "cv_detail";
	}
	
	private String getUserPhoto(Integer cvId){
		File file = new File(realPath.getPath("/upload/"+"cv_"+cvId+".jpg"));
		if(file.exists()){
			String userPhoto = "/upload/"+"cv_"+cvId+".jpg";
			return userPhoto;
		}
		return null;
	}
	/**
	* @Title: editCV
	* @Description:
	* @author wdz
	* @date 2018年3月19日 上午9:44:16
	* @param id
	* @param model
	* @return
	 */
	@RequestMapping(value="cv_edit.do")
	public String editCV(Integer id,ModelMap model){
		CV cv = exportDocMng.getDetail(id);
		String userPhoto = getUserPhoto(id);
		if(userPhoto == null){
			String sex = cv.getSex();
			if(sex.equals("男")){
				model.put("userPhoto", "/img/boys.jpg");
			}else if(sex.equals("女")){
				model.put("userPhoto", "/img/grils.jpg");
			}
		}else{
			model.put("userPhoto", userPhoto);
		}
		CVJobIntention intention = cv.getIntention();
		model.put("entity", cv);
		model.put("intention", intention);
		CVRecommend recommend = cv.getRecommend();
		model.put("recom", recommend);
		List<Customer> customer = customerMng.findList();
		if(customer != null && customer.size() > 0){
			if(recommend != null){
				Integer customerId = recommend.getCustomerId();
				if(customerId != null){
					List<Position> post = positionMng.findListByCusId(customerId);
					model.put("post", post);
				}else{
					Customer customer2 = customer.get(0);
					List<Position> post = positionMng.findListByCusId(customer2.getId());
					model.put("post", post);
				}
			}else{
				Customer customer2 = customer.get(0);
				List<Position> post = positionMng.findListByCusId(customer2.getId());
				model.put("post", post);
			}
		}
		model.put("customer", customer);
		return "cv_edit";
	}
	
	/**
	* @Title: saveRecommend
	* @Description:保存/编辑推荐
	* @author wdz
	* @date 2018年3月19日 上午9:44:32
	* @param cvr
	* @param model
	* @param postIds
	* @return
	 */
	@RequestMapping(value="save_recommend.do")
	public String saveRecommend(CVRecommend cvr,ModelMap model,String postIds){
		CV cv = exportDocMng.getDetail(cvr.getCvId());
		cvr.setUserId(cv.getUserId());
		if(cvr.getId() == null){
			exportDocMng.saveRecommend(cvr,postIds);
		}else{
			exportDocMng.upRecommend(cvr,postIds);
		}
		cvr = exportDocMng.getRecommend(cv.getId());
		CVJobIntention intention = cv.getIntention();
		List<Customer> customer = customerMng.findList();
		if(customer != null && customer.size() > 0){
			if(cvr != null){
				Integer customerId = cvr.getCustomerId();
				if(customerId != null){
					List<Position> post = positionMng.findListByCusId(customerId);
					model.put("post", post);
				}else{
					Customer customer2 = customer.get(0);
					List<Position> post = positionMng.findListByCusId(customer2.getId());
					model.put("post", post);
				}
			}else{
				Customer customer2 = customer.get(0);
				List<Position> post = positionMng.findListByCusId(customer2.getId());
				model.put("post", post);
			}
		}
		String userPhoto = getUserPhoto(cv.getId());
		if(userPhoto == null){
			String sex = cv.getSex();
			if(sex.equals("男")){
				model.put("userPhoto", "/img/boys.jpg");
			}else if(sex.equals("女")){
				model.put("userPhoto", "/img/grils.jpg");
			}
		}else{
			model.put("userPhoto", userPhoto);
		}
		model.put("customer", customer);
		model.put("entity", cv);
		model.put("intention", intention);
		model.put("recom", cvr);
		return "cv_edit";
	}
	/**
	* @Title: getJobExp
	* @Description: 获取工作经历
	* @author wdz
	* @date 2018年2月11日 下午5:42:45
	* @param id
	* @param cvId
	* @return
	 */
	@RequestMapping(value="find_work_exp.do")
	@ResponseBody
	public String getJobExp(Integer id,Integer cvId){
		JSONObject obj = new JSONObject();
		CVJobExperience job =  exportDocMng.getJobExp(id,cvId);
		if(job != null){
			obj.put("startTime", job.getStarTime());
			obj.put("endTime", job.getEndTime());
			obj.put("entName", job.getEntName());
			obj.put("jobName", job.getJobName());
			obj.put("jobSalary", job.getJobSalary());
			obj.put("entNature", job.getEntNature());
			obj.put("desc", job.getJobDescribe());
			obj.put("entType", job.getIndustryType());
		}
		return obj.toString();
	}
	/***
	* @Title: getProjectExp
	* @Description: 获取项目经历
	* @author wdz
	* @date 2018年2月11日 下午6:07:20
	* @param id
	* @param cvId
	* @return
	 */
	@RequestMapping(value="find_project_exp.do")
	@ResponseBody
	public String getProjectExp(Integer id,Integer cvId){
		JSONObject obj = new JSONObject();
		CVProject job =  exportDocMng.getProjectExp(id,cvId);
		if(job != null){
			obj.put("startTime", job.getStartTime());
			obj.put("endTime", job.getEndTime());
			obj.put("name", job.getName());
			obj.put("software", job.getSoftware());
			obj.put("hardware", job.getHardware());
			obj.put("tool", job.getDevelopmentTool());
			obj.put("desc", job.getDescribe());
			obj.put("duties", job.getDuties());
		}
		return obj.toString();
	}
	
	/***
	* @Title: getHopeJob
	* @Description: 获取求职意向
	* @author wdz
	* @date 2018年2月11日 下午6:31:48
	* @param id
	* @param cvId
	* @return
	 */
	@RequestMapping(value="find_hope_job.do")
	@ResponseBody
	public String getHopeJob(Integer id,Integer cvId){
		JSONObject obj = new JSONObject();
		CVJobIntention job =  exportDocMng.getHopeJob(id,cvId);
		if(job != null){
			obj.put("nature", job.getJobNature());
			obj.put("jobName", job.getHopeJob());
			obj.put("jobType", job.getHopeIndustry());
			obj.put("jobPlace", job.getHopeWorkPlace());
			obj.put("salary", job.getHopeSalary());
		}
		return obj.toString();
	}
	/***
	* @Title: updateEducation
	* @Description: 修改教育经历
	* @author wdz
	* @date 2018年2月11日 下午6:42:13
	* @param edu
	* @return
	 */
	@RequestMapping(value="save_education.do")
	@ResponseBody
	public String updateEducation(HttpServletRequest request, CVEducation edu){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				edu.setUserId(user.getUserId());
				exportDocMng.updateEducation(edu);
				code = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/**
	* @Title: updateJob
	* @Description: 修改工作经历
	* @author wdz
	* @date 2018年2月12日 上午10:51:45
	* @param job
	* @return
	 */
	@RequestMapping(value="update_job.do")
	@ResponseBody
	public String updateJob(HttpServletRequest request,CVJobExperience job){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				job.setUserId(user.getUserId());
				exportDocMng.updateJob(job);
				code = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	
	/***
	* @Title: updatePro
	* @Description: 修改项目
	* @author wdz
	* @date 2018年2月12日 上午10:51:59
	* @param pro
	* @return
	 */
	@RequestMapping(value="update_project.do")
	@ResponseBody
	public String updatePro(HttpServletRequest request,CVProject pro){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				pro.setUserId(user.getUserId());
				exportDocMng.updatePro(pro);
				code = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/**
	* @Title: updateHope
	* @Description:修改求职意向
	* @author wdz
	* @date 2018年2月12日 上午10:52:20
	* @param hope
	* @return
	 */
	@RequestMapping(value="update_hope.do")
	@ResponseBody
	public String updateHope(HttpServletRequest request,CVJobIntention hope){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				CVJobIntention hopes = exportDocMng.getHopeJob(hope.getId(), hope.getCvId());
				hope.setJobStatus(hopes.getJobStatus());
				hope.setUserId(user.getUserId());
				exportDocMng.updateHope(hope);
				String record = "修改了求职意向";
				cvRecordMng.saveRecord(hope.getUserId(), hope.getCvId(), record);
				code = "1";
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/***
	* @Title: findTrain
	* @Description: 获取培训经历
	* @author wdz
	* @date 2018年2月12日 上午10:52:35
	* @param id
	* @param cvId
	* @return
	 */
	@RequestMapping(value="find_train.do")
	@ResponseBody
	public String findTrain(Integer id,Integer cvId){
		JSONObject obj = new JSONObject();
		CVTrain job =  exportDocMng.findTrain(id,cvId);
		if(job != null){
			obj.put("name", job.getName());
			obj.put("place", job.getPlace());
			obj.put("cert", job.getCertificate());
			obj.put("desc", job.getDescribe());
		}
		return obj.toString();
	}
	/***
	* @Title: updateTrain
	* @Description:修改培训经历
	* @author wdz
	* @date 2018年2月12日 上午10:52:50
	* @param train
	* @return
	 */
	@RequestMapping(value="update_train.do")
	@ResponseBody
	public String updateTrain(HttpServletRequest request,CVTrain train){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				train.setUserId(user.getUserId());
				exportDocMng.updateTrain(train);
				code = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	
	/***
	* @Title: updateLanguage
	* @Description: 修改语言
	* @author wdz
	* @date 2018年2月12日 上午11:36:07
	* @return
	 */
	@RequestMapping(value="update_language.do")
	@ResponseBody
	public String updateLanguage(HttpServletRequest request,CVLanguage lan){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				lan.setUserId(user.getUserId());
				exportDocMng.updateLanguage(lan);
				code = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/***
	* @Title: updateSkill
	* @Description: 修改技能
	* @author wdz
	* @date 2018年2月12日 下午1:09:51
	* @param sk
	* @return
	 */
	@RequestMapping(value="update_skill.do")
	@ResponseBody
	public String updateSkill(HttpServletRequest request,CVSkill sk){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				sk.setUserId(user.getUserId());
				exportDocMng.updateSkill(sk);
				code = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/***
	* @Title: updateHobby
	* @Description: 修改兴趣爱好
	* @author wdz
	* @date 2018年2月12日 下午1:41:58
	* @return
	 */
	@RequestMapping(value="update_hobby.do")
	@ResponseBody
	public String updateHobby(HttpServletRequest request,String hobby,Integer id){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				CV detail = exportDocMng.getDetail(id);
				detail.setHobby(hobby);
				exportDocMng.updateCV(detail);
				String record = "修改了兴趣爱好";
				cvRecordMng.saveRecord(user.getUserId(), id, record);
				code = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	
	/**
	* @Title: updateJobStatus
	* @Description:修改目前状态
	* @author wdz
	* @date 2018年2月12日 下午2:41:24
	* @param id
	* @param cvId
	* @param jobStatus
	* @return
	 */
	@RequestMapping(value="update_status.do")
	@ResponseBody
	public String updateJobStatus(HttpServletRequest request,Integer id,Integer cvId,String jobStatus){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				CVJobIntention hopeJob = exportDocMng.getHopeJob(id, cvId);
				hopeJob.setJobStatus(jobStatus);
				exportDocMng.updateHope(hopeJob);
				String record = "修改了目前状态";
				cvRecordMng.saveRecord(user.getUserId(), cvId, record);
				code = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/**
	* @Title: updateBase
	* @Description:保存基本信息
	* @author wdz
	* @date 2018年2月12日 下午2:41:08
	* @param cv
	* @return
	 */
	@RequestMapping(value="update_base.do")
	@ResponseBody
	public String updateBase(HttpServletRequest request,CV cv){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				CV detail = exportDocMng.getDetail(cv.getId());
				String hobby = detail.getHobby();
				cv.setHobby(hobby);
				exportDocMng.updateCV(cv);
				String record = "修改了基本信息";
				cvRecordMng.saveRecord(user.getUserId(), cv.getId(), record);
				code = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	
	/**
	* @Title: delCv
	* @Description:删除简历
	* @author wdz
	* @date 2018年3月5日 上午9:48:54
	* @param id
	* @return
	 */
	@RequestMapping(value="del_cv.do")
	@ResponseBody
	public String delCv(Integer id){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			exportDocMng.delCv(id);
			code = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	
	/**
	* @Title: delEdu
	* @Description: 删除教育经历
	* @author wdz
	* @date 2018年3月7日 上午9:48:52
	* @param id
	* @param cvId
	* @return
	 */
	@RequestMapping(value="cv_del_edu.do")
	@ResponseBody
	public String delEdu(HttpServletRequest request,Integer id,Integer cvId){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				exportDocMng.delEdu(id,cvId,user.getUserId());
				code = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/**
	* @Title: delJobExp
	* @Description: 删除工作经历
	* @author wdz
	* @date 2018年3月7日 上午9:58:16
	* @param id
	* @param cvId
	* @return
	 */
	@RequestMapping(value="cv_del_job_exp.do")
	@ResponseBody
	public String delJobExp(HttpServletRequest request,Integer id,Integer cvId){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				exportDocMng.delJobExp(id,cvId,user.getUserId());
				code = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/**
	* @Title: delProExp
	* @Description: 删除项目经验
	* @author wdz
	* @date 2018年3月7日 上午10:09:48
	* @param cvId
	* @param id
	* @return
	 */
	@RequestMapping(value="cv_del_pro_exp.do")
	@ResponseBody
	public String delProExp(HttpServletRequest request,Integer cvId,Integer id){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				exportDocMng.delProject(cvId, id,user.getUserId());
				code = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/**
	* @Title: delTrain
	* @Description: 删除培训经历
	* @author wdz
	* @date 2018年3月7日 上午10:14:03
	* @param cvId
	* @param id
	* @return
	 */
	@RequestMapping(value="cv_del_train_exp.do")
	@ResponseBody
	public  String delTrain(HttpServletRequest request,Integer cvId,Integer id){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				exportDocMng.delTrain(cvId, id,user.getUserId());
				code = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/**
	* @Title: delLanguage
	* @Description: 删除语言
	* @author wdz
	* @date 2018年3月7日 上午10:20:44
	* @param cvId
	* @param id
	* @return
	 */
	@RequestMapping(value="cv_del_language.do")
	@ResponseBody
	public String delLanguage(HttpServletRequest request,Integer cvId,Integer id){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				exportDocMng.delLanguage(cvId, id,user.getUserId());
				code = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/**
	* @Title: delSkill
	* @Description: 删除技能
	* @author wdz
	* @date 2018年3月7日 上午10:24:48
	* @param cvId
	* @param id
	* @return
	 */
	@RequestMapping(value="cv_del_skill.do")
	@ResponseBody
	public String delSkill(HttpServletRequest request,Integer cvId,Integer id){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				exportDocMng.delSkill(cvId, id,user.getUserId());
				code = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/**
	* @Title: updateState
	* @Description: 修改状态
	* @author wdz
	* @date 2018年3月9日 下午2:28:55
	* @param id
	* @param state
	* @return
	 */
	@RequestMapping(value="update_state_user.do")
	@ResponseBody
	public String updateState(HttpServletRequest request,Integer id,Integer state,Integer userId){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				exportDocMng.updateState(id,state,user.getUserId(),userId);
				code = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/**
	* @Title: findUsers
	* @Description: 获取用户数据
	* @author wdz
	* @date 2018年3月9日 下午2:58:41
	* @return
	 */
	@RequestMapping(value="find_users.do")
	@ResponseBody
	public String findUsers(HttpServletRequest request){
		JSONObject obj = new JSONObject();
		try {
			User own = (User) request.getSession().getAttribute("user");
			if(own != null){
				List<User> findList = userMng.findList();
				JSONArray array = new JSONArray();
				if(findList != null && findList.size() > 0){
					for (User user : findList) {
						if(!user.getUserId().equals(own.getUserId())){
							JSONObject data = new JSONObject();
							data.put("userId", user.getUserId());
							data.put("userName", user.getRealName());
							array.add(data);
						}
					}
				}
				obj.put("datas", array);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj.toString();
	}
	
	/**
	* @Title: addEducation
	* @Description: 添加教育经历
	* @author wdz
	* @date 2018年3月9日 下午5:38:16
	* @param request
	* @param edu
	* @return
	 */
	@RequestMapping(value="add_education.do")
	@ResponseBody
	public String addEducation(HttpServletRequest request,CVEducation edu){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				edu.setUserId(user.getUserId());
				exportDocMng.addEducation(edu);
				code = "1";
			}else{
				code = "2";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	
	/**
	* @Title: addJobExp
	* @Description: 添加工作经历
	* @author wdz
	* @date 2018年3月9日 下午5:38:50
	* @param jobexp
	* @return
	 */
	@RequestMapping(value="add_jobexp.do")
	@ResponseBody
	public String addJobExp(HttpServletRequest request,CVJobExperience jobexp){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				jobexp.setUserId(user.getUserId());
				exportDocMng.addJobExp(jobexp);
				code = "1";
			}else{
				code = "2";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	
	/**
	* @Title: addProject
	* @Description: 添加项目
	* @author wdz
	* @date 2018年3月9日 下午5:49:04
	* @param request
	* @param pro
	* @return
	 */
	@RequestMapping(value="add_project.do")
	@ResponseBody
	public String addProject(HttpServletRequest request,CVProject pro){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				pro.setUserId(user.getUserId());
				exportDocMng.addProject(pro);
				code = "1";
			}else{
				code = "2";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	
	/**
	* @Title: addTrain
	* @Description: 添加教育经历
	* @author wdz
	* @date 2018年3月9日 下午6:13:47
	* @param request
	* @param train
	* @return
	 */
	@RequestMapping(value="add_train.do")
	@ResponseBody
	public String addTrain(HttpServletRequest request,CVTrain train){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				train.setUserId(user.getUserId());
				exportDocMng.addTrain(train);
				code = "1";
			}else{
				code = "2";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/**
	* @Title: addLanguage
	* @Description:添加语言
	* @author wdz
	* @date 2018年3月9日 下午6:13:43
	* @param request
	* @param language
	* @return
	 */
	@RequestMapping(value="add_language.do")
	@ResponseBody
	public String addLanguage(HttpServletRequest request,CVLanguage language){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				language.setUserId(user.getUserId());
				exportDocMng.addLanguage(language);
				code = "1";
			}else{
				code = "2";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	
	/**
	* @Title: addSkill
	* @Description: 添加技能
	* @author wdz
	* @date 2018年3月9日 下午6:13:39
	* @param request
	* @param skill
	* @return
	 */
	@RequestMapping(value="add_skill.do")
	@ResponseBody
	public String addSkill(HttpServletRequest request,CVSkill skill){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				skill.setUserId(user.getUserId());
				exportDocMng.addSkill(skill);
				code = "1";
			}else{
				code = "2";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/**
	* @Title: eduSaveSort
	* @Description: 教育经历排序
	* @author wdz
	* @date 2018年3月12日 上午10:20:15
	* @param datas
	* @return
	 */
	@RequestMapping(value="edu_save_sort.do")
	@ResponseBody
	public String eduSaveSort(HttpServletRequest request,String datas,Integer cvId){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				if(!StringUtils.isBlank(datas)){
					exportDocMng.eduSaveSort(datas,user.getUserId());
					code = "1";
				}else{
					code = "2";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/**
	* @Title: jobExpSaveSort
	* @Description: 工作经历排序
	* @author wdz
	* @date 2018年3月12日 上午10:43:59
	* @param datas
	* @return
	 */
	@RequestMapping(value="jobexp_save_sort.do")
	@ResponseBody
	public String jobExpSaveSort(HttpServletRequest request,String datas){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				if(!StringUtils.isBlank(datas)){
					exportDocMng.jobExpSaveSort(datas,user.getUserId());
					code = "1";
				}else{
					code = "2";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/**
	* @Title: proSaveSort
	* @Description: 项目排序
	* @author wdz
	* @date 2018年3月12日 上午10:43:42
	* @param datas
	* @return
	 */
	@RequestMapping(value="pro_save_sort.do")
	@ResponseBody
	public String proSaveSort(HttpServletRequest request,String datas){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user != null){
				if(!StringUtils.isBlank(datas)){
					exportDocMng.proSaveSort(datas,user.getUserId());
					code = "1";
				}else{
					code = "2";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/**
	* @Title: checkRecommend
	* @Description:校验推荐信息
	* @author wdz
	* @date 2018年3月19日 上午9:45:49
	* @param cvId
	* @return
	 */
	@RequestMapping(value="check_recommend.do")
	@ResponseBody
	public String checkRecommend(Integer cvId){
		JSONObject obj = new JSONObject();
		String code = "0";
		try {
			if(cvId != null && cvId > 0){
				CVRecommend recommend = exportDocMng.getRecommend(cvId);
				if(recommend == null){
					code = "2";
				}else if(recommend.getCustomerId() != null && recommend.getCustomerId() > 0){
					code = "1";
				}
			}else{
				code = "3";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.put("code", code);
		return obj.toString();
	}
	/**
	* @Title: liePinExplain
	* @Description: 猎聘导入说明
	* @author wdz
	* @date 2018年3月19日 上午9:45:10
	* @return
	 */
	@RequestMapping("linepin_explain.do")
	public String liePinExplain(){
		return "liepin_explain";
	}
}
