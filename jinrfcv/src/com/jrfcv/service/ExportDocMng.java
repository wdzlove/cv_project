package com.jrfcv.service;

import java.util.List;

import com.jrfcv.bean.CV;
import com.jrfcv.bean.CVEducation;
import com.jrfcv.bean.CVJobExperience;
import com.jrfcv.bean.CVJobIntention;
import com.jrfcv.bean.CVLanguage;
import com.jrfcv.bean.CVProject;
import com.jrfcv.bean.CVRecommend;
import com.jrfcv.bean.CVSkill;
import com.jrfcv.bean.CVTrain;
import com.jrfcv.util.Pagination;

/**
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *导出业务接口
 * <p>
 *
 * 区分　责任人　日期　　　　说明<br/>
 * 创建 wdz　2018年2月8日　<br/>
 * <p>
 *
 * @author Administrator
 *
 * @version 1.0, 2018年2月8日
 *
 */
public interface ExportDocMng {

	/**
	* @Title: findList
	* @Description:查询简历列表
	* @author wdz
	* @date 2018年2月8日 下午4:10:40
	* @return
	 */
	public List<CV> findList();

	/**
	* @Title: getDetail
	* @Description:获取简历详细
	* @author wdz
	* @date 2018年2月8日 下午4:40:29
	* @param id
	* @return
	 */
	public CV getDetail(Integer id);

	/***
	 * 
	* @Title: saveRecommend
	* @Description: 保存推荐信息
	* @author wdz
	* @date 2018年2月9日 上午10:10:32
	* @param cvr
	 * @param cvId 
	 * @param postIds 
	* @return
	 */
	public Integer saveRecommend(CVRecommend cvr, String postIds);

	/**
	* @Title: getJobExp
	* @Description: 获取工作经历
	* @author wdz
	* @date 2018年2月11日 下午5:44:10
	* @param id
	* @param cvId
	* @return
	 */
	public CVJobExperience getJobExp(Integer id, Integer cvId);
	/***
	* @Title: getProjectExp
	* @Description:获取项目经验
	* @author wdz
	* @date 2018年2月11日 下午6:08:29
	* @param id
	* @param cvId
	* @return
	 */
	public CVProject getProjectExp(Integer id, Integer cvId);

	/**
	* @Title: getHopeJob
	* @Description: 获取求职意向
	* @author wdz
	* @date 2018年2月11日 下午6:32:16
	* @param id
	* @param cvId
	* @return
	 */
	public CVJobIntention getHopeJob(Integer id, Integer cvId);

	/***
	* @Title: updateEducation
	* @Description: 修改教育经历
	* @author wdz
	* @date 2018年2月11日 下午6:42:32
	* @param edu
	 */
	public void updateEducation(CVEducation edu);

	/**
	 * 
	* @Title: updateJob
	* @Description: 修改工作经历
	* @author wdz
	* @date 2018年2月12日 上午9:16:03
	* @param job
	 */
	public void updateJob(CVJobExperience job);

	/**
	* @Title: updatePro
	* @Description:修改项目经验
	* @author wdz
	* @date 2018年2月12日 上午9:32:01
	* @param pro
	 */
	public void updatePro(CVProject pro);

	/**
	* @Title: updateHope
	* @Description:修改求职意向
	* @author wdz
	* @date 2018年2月12日 上午9:32:16
	* @param hope
	 */
	public void updateHope(CVJobIntention hope);

	/***
	* @Title: findTrain
	* @Description: 获取培训经历
	* @author wdz
	* @date 2018年2月12日 上午10:48:29
	* @param id
	* @param cvId
	* @return
	 */
	public CVTrain findTrain(Integer id, Integer cvId);

	/***
	* @Title: updateTrain
	* @Description: 修改培训经历
	* @author wdz
	* @date 2018年2月12日 上午10:53:07
	* @param train
	 */
	public void updateTrain(CVTrain train);

	/**
	* @Title: updateLanguage
	* @Description: 修改语言
	* @author wdz
	* @date 2018年2月12日 上午11:36:56
	* @param lan
	 */
	public void updateLanguage(CVLanguage lan);

	/**
	* @Title: updateSkill
	* @Description: 修改技能
	* @author wdz
	* @date 2018年2月12日 下午1:10:17
	* @param sk
	 */
	public void updateSkill(CVSkill sk);

	/**
	* @Title: updateCV
	* @Description: 修改简历
	* @author wdz
	* @date 2018年2月12日 下午1:43:58
	* @param detail
	 */
	public void updateCV(CV detail);

	/**
	* @Title: delCv
	* @Description: 删除简历
	* @author wdz
	* @date 2018年3月5日 上午9:49:16
	* @param id
	 */
	public void delCv(Integer id);

	/**
	* @Title: findPage
	* @Description: 分页
	* @author wdz
	 * @param pageSize 
	 * @param pageNo 
	 * @param value 
	 * @param inputUser 
	 * @param userId 
	 * @param source 
	 * @param state 
	* @date 2018年3月5日 上午11:29:03
	* @return
	 */
	public Pagination<CV> findPage(Integer pageNo, Integer pageSize, Integer state, Integer source, Integer userId, Integer inputUser, String value);

	/**
	* @Title: delEdu
	* @Description: 删除教育
	* @author wdz
	* @date 2018年3月7日 上午9:49:34
	* @param id
	* @param cvId
	 * @param userId 
	 */
	public void delEdu(Integer id, Integer cvId, Integer userId);

	/**
	* @Title: delJobExp
	* @Description:删除工作经历
	* @author wdz
	* @date 2018年3月7日 上午9:58:36
	* @param id
	* @param cvId
	 * @param userId 
	 */
	public void delJobExp(Integer id, Integer cvId, Integer userId);
	
	public void delLanguage(Integer cvId,Integer id,Integer userId);
	
	public void delProject(Integer cvId,Integer id, Integer userId);
	
	public void delSkill(Integer cvId,Integer id,Integer userId);
	
	public void delTrain(Integer cvId,Integer id, Integer userId);

	public void updateState(Integer id, Integer state, Integer userId, Integer toUserId);

	public void addEducation(CVEducation edu);

	public void addJobExp(CVJobExperience jobexp);

	public void addProject(CVProject pro);

	public void addSkill(CVSkill skill);

	public void addLanguage(CVLanguage language);

	public void addTrain(CVTrain train);

	public void eduSaveSort(String datas, Integer userId);

	public void jobExpSaveSort(String datas, Integer userId);

	public void proSaveSort(String datas, Integer userId);

	/**
	* @Title: upRecommend
	* @Description: 修改推荐
	* @author wdz
	* @date 2018年3月14日 下午5:54:27
	* @param cvr
	 * @param cvId 
	 * @param postIds 
	 */
	public void upRecommend(CVRecommend cvr, String postIds);

	public CVRecommend getRecommend(Integer id);

}
