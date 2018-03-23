package com.jrfcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

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
import com.jrfcv.dao.InputExcelDao;

/**
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *持久城
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
@Repository
public class InputExcelDaoImpl implements InputExcelDao {
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public String test() {
		
		return null;
	}

	@Override
	public void saveIntention(final CVJobIntention intention, final Integer cvId) {
		final StringBuilder sql = new StringBuilder();
		sql.append("insert into cv_job_intention(job_nature,hope_work_place,hope_job,hope_industry,")
			.append("hope_salary,job_status,cv_id,user_id)")
			.append(" values(?,?,?,?,?  ,?,?,?)");
		try{
			KeyHolder holder = new GeneratedKeyHolder();
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement p = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
					p.setString(1, intention.getJobNature());
					p.setString(2, intention.getHopeWorkPlace());
					p.setString(3, intention.getHopeJob());
					p.setString(4, intention.getHopeIndustry());
					p.setString(5, intention.getHopeSalary());
					p.setString(6, intention.getJobStatus());
					p.setInt(7, cvId);
					p.setInt(8, intention.getUserId() == null?0:intention.getUserId());
					return p;
				}
			}, holder);
		} catch (DataAccessException e) {
			System.out.println("保存培训信息失败----saveIntention");
			e.printStackTrace();
		}
		
	}

	@Override
	public void saveCVJobExperience(final List<CVJobExperience> cvjs, final Integer cvId) {
		final StringBuilder sql = new StringBuilder();
		sql.append("insert into cv_job_experience(ent_name,industry_type,job_name,job_type,star_time")
			.append(",end_time,job_salary,job_describe,ent_nature,ent_scale,cv_id,user_id,position)")
			.append(" values(?,?,?,?,?  ,?,?,?,?,?  ,?,?,?)");
		try{
			this.jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement p, int i) throws SQLException {
					CVJobExperience c = cvjs.get(i);
					p.setString(1, c.getEntName());
					p.setString(2, c.getIndustryType());
					p.setString(3, c.getJobName());
					p.setString(4, c.getJobType());
					p.setString(5, c.getStarTime());
					p.setString(6, c.getEndTime());
					p.setString(7, c.getJobSalary());
					p.setString(8, c.getJobDescribe());
					p.setString(9, c.getEntNature());
					p.setString(10, c.getEntScale());
					p.setInt(11, cvId);
					p.setInt(12, c.getUserId() == null?0:c.getUserId());
					p.setInt(13, 0);
				}
				@Override
				public int getBatchSize() {
					return cvjs.size();
				}
			});
		} catch (DataAccessException e) {
			System.out.println("保存工作经历信息失败----"+130);
			e.printStackTrace();
		}
		
	}

	@Override
	public void saveCVEducation(final List<CVEducation> cves, final Integer cvId) {
		final StringBuilder sql = new StringBuilder();
		sql.append("insert into cv_education(start_time,end_time,school,is_unified,major_type,")
			.append("major_name,education,cv_id,user_id,position)")
			.append(" values(?,?,?,?,?  ,?,?,?,?,?)");
		try{
			this.jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement p, int i) throws SQLException {
					CVEducation c = cves.get(i);
					p.setString(1, c.getStartTime());
					p.setString(2, c.getEndTime());
					p.setString(3, c.getSchool());
					p.setString(4, c.getIsUnified());
					p.setString(5, c.getMajorType());
					p.setString(6, c.getMajorName());
					p.setString(7, c.getEducation());
					p.setInt(8, cvId);
					p.setInt(9, c.getUserId()==null?0:c.getUserId());
					p.setInt(10, 0);
				}
				@Override
				public int getBatchSize() {
					return cves.size();
				}
			});
		} catch (DataAccessException e) {
			System.out.println("保存教育息失败----"+130);
			e.printStackTrace();
		}
	}

	@Override
	public void saveCVLanguage(final List<CVLanguage> cvls, final Integer cvId) {
		final StringBuilder sql = new StringBuilder();
		sql.append("insert into cv_language(language,rw_ability,hear_ability,cv_id,user_id)")
			.append(" values(?,?,?,?,?)");
		try{
			this.jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement p, int i) throws SQLException {
					CVLanguage c = cvls.get(i);
					p.setString(1, c.getLanguage());
					p.setString(2, c.getRwAbility());
					p.setString(3, c.getHearAbility());
					p.setInt(4, cvId);
					p.setInt(5, c.getUserId()==null?0:c.getUserId());
				}
				@Override
				public int getBatchSize() {
					return cvls.size();
				}
			});
		} catch (DataAccessException e) {
			System.out.println("保存语言能力息失败----"+130);
			e.printStackTrace();
		}
	}

	@Override
	public void saveCVProject(final  List<CVProject> cvps,final Integer cvId) {
		final StringBuilder sql = new StringBuilder();
		sql.append("insert into cv_project(name,start_time,end_time,software,hardware,")
			.append("development_tool,duties,`describe`,is_it,cv_id,user_id,position)")
			.append(" values(?,?,?,?,?  ,?,?,?,?,?  ,?,?)");
		try{
			this.jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement p, int i) throws SQLException {
					CVProject c = cvps.get(i);
					p.setString(1, c.getName());
					p.setString(2, c.getStartTime());
					p.setString(3, c.getEndTime());
					p.setString(4, c.getSoftware());
					p.setString(5, c.getHardware());
					p.setString(6, c.getDevelopmentTool());
					p.setString(7, c.getDuties());
					p.setString(8, c.getDescribe());
					p.setInt(9, c.getIsIt()== null?0:c.getIsIt());
					p.setInt(10, cvId);
					p.setInt(11, c.getUserId()==null?0:c.getUserId());
					p.setInt(12, 0);
				}
				@Override
				public int getBatchSize() {
					return cvps.size();
				}
			});
		} catch (DataAccessException e) {
			System.out.println("保存项目经验息失败----"+130);
			e.printStackTrace();
		}
	}

	@Override
	public void saveCVSkill(final List<CVSkill> cvks,final  Integer cvId) {
		final StringBuilder sql = new StringBuilder();
		sql.append("insert into cv_skill(type,name,use_time,degree,cv_id,user_id)")
			.append(" values(?,?,?,?,?  ,?)");
		try{
			this.jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement p, int i) throws SQLException {
					CVSkill c = cvks.get(i);
					System.out.println(c.getName()+"--------------------");
					p.setString(1, c.getType());
					p.setString(2, c.getName());
					p.setString(3, c.getUseTime());
					p.setString(4, c.getDegree());
					p.setInt(5, cvId);
					p.setInt(6, c.getUserId()==null?0:c.getUserId());
				}
				@Override
				public int getBatchSize() {
					return cvks.size();
				}
			});
		} catch (DataAccessException e) {
			System.out.println("保存技能息失败----"+130);
			e.printStackTrace();
		}
	}

	@Override
	public void saveCVInSchool(final List<CVInSchool> cvss,final  Integer cvId) {
		final StringBuilder sql = new StringBuilder();
		sql.append("insert into cv_in_school(bonus_grade_a,grade_a,bonus_grade_b,grade_b,bonus_grade_c,")
			.append("grade_c,`describe`,item_a,item_grade_a,item_time_a,describe_a,item_b,item_grade_b,")
			.append("item_time_b,describe_b,item_c,item_grade_c,item_time_c,describe_c,cv_id,user_id)")
			.append(" values(?,?,?,?,?  ,?,?,?,?,?  ,?,?,?,?,?  ,?,?,?,?,?  ,?)");
		try{
			this.jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement p, int i) throws SQLException {
					CVInSchool c = cvss.get(i);
					p.setString(1, c.getBonusGrade_a());
					p.setString(2, c.getGrade_a());
					p.setString(3, c.getBonusGrade_b());
					p.setString(4, c.getGrade_b());
					p.setString(5, c.getBonusGrade_c());
					p.setString(6, c.getGrade_c());
					p.setString(7, c.getDescribe());
					p.setString(8, c.getItem_a());
					p.setString(9, c.getItemGrade_a());
					p.setString(10, c.getItemTime_a());
					p.setString(11, c.getDescribe_a());
					p.setString(12, c.getItem_b());
					p.setString(13, c.getItemGrade_b());
					p.setString(14, c.getItemTime_b());
					p.setString(15, c.getDescribe_b());
					p.setString(16, c.getItem_c());
					p.setString(17, c.getItemGrade_c());
					p.setString(18, c.getItemTime_c());
					p.setString(19, c.getDescribe_c());
					p.setInt(20, cvId);
					p.setInt(21, c.getUseId()==null?0:c.getUseId());
				}
				@Override
				public int getBatchSize() {
					return cvss.size();
				}
			});
		} catch (DataAccessException e) {
			System.out.println("保存在校息失败----"+130);
			e.printStackTrace();
		}
	}

	@Override
	public void saveCVSchoolPractice(final List<CVSchoolPractice> cvsps,final Integer cvId) {
		final StringBuilder sql = new StringBuilder();
		sql.append("insert into cv_school_practice(name,start_time,end_time,`describe`,cv_id,user_id)")
			.append(" values(?,?,?,?,?  ,?)");
		try{
			this.jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement p, int i) throws SQLException {
					CVSchoolPractice c = cvsps.get(i);
					p.setString(1, c.getName());
					p.setString(2, c.getStartTime());
					p.setString(3, c.getEndTime());
					p.setString(4, c.getDescribe());
					p.setInt(5, cvId);
					p.setInt(6, c.getUserId() == null?0:c.getUserId());
				}
				@Override
				public int getBatchSize() {
					return cvsps.size();
				}
			});
		} catch (DataAccessException e) {
			System.out.println("保存在校实践息失败----"+130);
			e.printStackTrace();
		}
	}

	@Override
	public void saveCVTrain(final List<CVTrain> cvts, final Integer cvId) {
		final StringBuilder sql = new StringBuilder();
		sql.append("insert into cv_train(start_time,end_time,`name`,course,place,certificate")
			.append(",`describe`,cv_id,user_id)")
			.append(" values(?,?,?,?,?  ,?,?,?,?)");
		try{
			this.jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement p, int i) throws SQLException {
					CVTrain c = cvts.get(i);
					p.setString(1, c.getStartTime());
					p.setString(2, c.getEndTime());
					p.setString(3, c.getName());
					p.setString(4, c.getCourse());
					p.setString(5, c.getPlace());
					p.setString(6, c.getCertificate());
					p.setString(7, c.getDescribe());
					p.setInt(8, cvId);
					p.setInt(9, c.getUserId()==null?0:c.getUserId());
				}
				@Override
				public int getBatchSize() {
					return cvts.size();
				}
			});
		} catch (DataAccessException e) {
			System.out.println("保存培训信息失败----"+130);
			e.printStackTrace();
		}
	}

	@Override
	public void saveCVCertificate(final List<CVCertificate> cvcs, final Integer cvId) {
		final StringBuilder sql = new StringBuilder();
		sql.append("insert into cv_certificate(certificate,get_time,cv_id,user_id)")
			.append(" values(?,?,?,?)");
		try{
			this.jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement p, int i) throws SQLException {
					CVCertificate c = cvcs.get(i);
					p.setString(1, c.getCertificate());
					p.setString(2, c.getGetTime());
					p.setInt(3, cvId);
					p.setInt(4, c.getUserId()==null?0:c.getUserId());
				}
				@Override
				public int getBatchSize() {
					return cvcs.size();
				}
			});
		} catch (DataAccessException e) {
			System.out.println("保存证书信息失败----"+154);
			e.printStackTrace();
		}
	}

	@Override
	public Integer saveCV(final CV c) {
		Integer id = 0;
		final StringBuilder sql = new StringBuilder();
		sql.append("insert into cv_base(`name`,sex,birthday,join_work_time,address,post_code,")
			.append("id_card,domicile_place,now_live_city,mobile,e_mail,marriage,nationality,")
			.append("is_overseas,overseas_time,hobby,politics_face,evaluate,user_id,create_time")
			.append(",source,input_user,update_time,state)")
			.append(" values(?,?,?,?,?   ,?,?,?,?,?   ,?,?,?,?,?  ,?,?,?,?,? ,?,?,?,?)");
		KeyHolder holder = new GeneratedKeyHolder();
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement p = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
					p.setString(1, c.getName());
					p.setString(2, c.getSex());
					p.setString(3, c.getBirthday());
					p.setString(4, c.getJoinWorkTime());
					p.setString(5, c.getAddress());
					p.setString(6, c.getPostCode());
					p.setString(7, c.getIdCard());
					p.setString(8, c.getDomicilePlace());
					p.setString(9, c.getNowLiveCity());
					p.setString(10, c.getMobile());
					p.setString(11, c.getEmail());
					p.setString(12, c.getMarriage());
					p.setString(13, c.getNationality());
					p.setString(14, c.getIsOverseas());
					p.setString(15, c.getOverseasTime());
					p.setString(16, c.getHobby());
					p.setString(17, c.getPoliticsFace());
					p.setString(18, c.getEvaluate());
					p.setInt(19, c.getUserId()==null?0:c.getUserId());
					p.setTimestamp(20, new java.sql.Timestamp(c.getCreateTime().getTime()));
					p.setInt(21, c.getSource());
					p.setInt(22, c.getInputUser()==null?0:c.getInputUser());
					p.setTimestamp(23, new java.sql.Timestamp(c.getUpdateTime().getTime()));
					p.setInt(24, c.getState());
					return p;
				}
			}, holder);
			id = holder.getKey().intValue();
		} catch (DataAccessException e) {
			System.out.println("保存简历信息失败----"+154);
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public void updateEduPosition(Integer cvId, Integer id, Integer index) {
		StringBuilder sql = new StringBuilder();
		sql.append("update cv_education set position=? where id=? and cv_id=? ");
		try {
			this.jdbcTemplate.update(sql.toString(),index,id,cvId);
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("保存教育排序失败");
		}
	}

	@Override
	public void jobExpSaveSort(Integer cvId, Integer id, Integer index) {
		StringBuilder sql = new StringBuilder();
		sql.append("update cv_job_experience set position=? where id=? and cv_id=? ");
		try {
			this.jdbcTemplate.update(sql.toString(),index,id,cvId);
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("保存工作经历排序失败");
		}
	}

	@Override
	public void proSaveSort(Integer cvId, Integer id, Integer index) {
		StringBuilder sql = new StringBuilder();
		sql.append("update cv_project set position=? where id=? and cv_id=? ");
		try {
			this.jdbcTemplate.update(sql.toString(),index,id,cvId);
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("保存项目经历排序失败");
		}
	}

	
	
}
