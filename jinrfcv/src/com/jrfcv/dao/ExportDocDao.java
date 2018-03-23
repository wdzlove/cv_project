package com.jrfcv.dao;

import java.util.Date;
import java.util.List;

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
import com.jrfcv.bean.RecommendPosition;
import com.jrfcv.util.Pagination;

/**
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *持久层接口
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
public interface ExportDocDao {

	/**
	* @Title: findList
	* @Description: 简历列表
	* @author wdz
	* @date 2018年2月8日 下午4:13:53
	* @return
	 */
	public List<CV> findList();

	public CV getDetail(Integer id);

	public CVJobIntention getIntention(Integer id);

	public List<CVCertificate> getCvcs(Integer id);

	public List<CVEducation> getCves(Integer id);

	public List<CVJobExperience> getCvjs(Integer id);

	public List<CVSkill> getCvks(Integer id);

	public List<CVLanguage> getCvls(Integer id);

	public List<CVProject> getCvps(Integer id);

	public List<CVSchoolPractice> getCvsps(Integer id);

	public List<CVInSchool> getCvss(Integer id);

	public List<CVTrain> getCvts(Integer id);
	
	public CVRecommend getRecommend(Integer cvId);

	public Integer saveRecommend(CVRecommend cvr);

	public CVJobExperience getJobExp(Integer id, Integer cvId);

	public CVProject getProjectExp(Integer id, Integer cvId);

	public void updateEducation(CVEducation edu);

	public void updateJob(CVJobExperience job);

	public void updatePro(CVProject pro);

	public void updateHope(CVJobIntention hope);

	public CVTrain findTrain(Integer id, Integer cvId);

	public void updateTrain(CVTrain train);

	public void updateLanguage(CVLanguage lan);

	public void updateSkill(CVSkill sk);

	public void updateCV(CV detail);

	public void delCv(Integer id);

	public void delProject(Integer cvId,Integer id);

	public void delCerti(Integer id);

	public void delInSchool(Integer id);

	public void delEdu(Integer cvId, Integer id);

	public void delJobInter(Integer id);

	public void delLanguage(Integer cvId,Integer id);

	public void delOther(Integer id);

	public void delRecomment(Integer id);

	public void delSchool(Integer id);

	public void delSkill(Integer cvId,Integer id);

	public void delTrain(Integer cvId,Integer id);

	public void delJobExper(Integer cvId, Integer id);
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
	* @date 2018年3月5日 上午11:30:18
	* @return
	 */
	public Pagination<CV> findPage(Integer pageNo, Integer pageSize, Integer state, Integer source, Integer userId, Integer inputUser, String value);

	/**
	* @Title: findCV
	* @Description: 获取简历
	* @author wdz
	* @date 2018年3月5日 下午7:19:09
	* @param mobile
	* @param name
	* @return
	 */
	public com.jrfcv.bean.CV findCV(String mobile, String name);

	public void updateState(Integer id, Integer state, Integer userId);

	public void updateCVTime(Integer cvId, Date date);

	public void upRecommend(CVRecommend cvr);

	public void saveRecomPost(String string, Integer cvId);

	public void delRecomPost(Integer cvId);

	public List<RecommendPosition> getRecomPost(Integer rid);



}
