package com.jrfcv.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
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
import com.jrfcv.bean.RecommendPosition;
import com.jrfcv.dao.ExportDocDao;
import com.jrfcv.util.Pagination;
/**
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *数据层实现类
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
@Repository
public  class ExportDocDaoImpl implements ExportDocDao {
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<CV> findList() {
		final List<CV> list = new ArrayList<CV>();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,name,now_live_city,sex,birthday,e_mail,join_work_time from cv_base ");
		this.jdbcTemplate.query(sql.toString(), new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					CV c = new CV();
					c.setName(r.getString("name"));
					c.setId(r.getInt("id"));
					c.setNowLiveCity(r.getString("now_live_city"));
					c.setSex(r.getString("sex"));
					c.setBirthday(r.getString("birthday"));
					c.setEmail(r.getString("e_mail"));
					c.setJoinWorkTime(r.getString("join_work_time"));
					list.add(c);
				}
			}});
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public CV getDetail(Integer id) {
		final CV c = new CV();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,`name`,sex,birthday,join_work_time,address,post_code,id_card,domicile_place,")
			.append("now_live_city,mobile,e_mail,marriage,nationality,is_overseas,overseas_time,hobby,")
			.append("politics_face,evaluate,user_id,age from cv_base where id=?");
		this.jdbcTemplate.query(sql.toString(),new Object[]{id}, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					c.setId(r.getInt("id"));
					c.setName(r.getString("name"));
					c.setSex(r.getString("sex"));
					c.setBirthday(r.getString("birthday"));
					c.setJoinWorkTime(r.getString("join_work_time"));
					c.setAddress(r.getString("address"));
					c.setPostCode(r.getString("post_code"));
					c.setIdCard(r.getString("id_card"));
					c.setDomicilePlace(r.getString("domicile_place"));
					c.setNowLiveCity(r.getString("now_live_city"));
					c.setMobile(r.getString("mobile"));
					c.setEmail(r.getString("e_mail"));
					c.setMarriage(r.getString("marriage"));
					c.setNationality(r.getString("nationality"));
					c.setIsOverseas(r.getString("is_overseas"));
					c.setOverseasTime(r.getString("overseas_time"));
					c.setHobby(r.getString("hobby"));
					c.setPoliticsFace(r.getString("politics_face"));
					c.setEvaluate(r.getString("evaluate"));
					c.setUserId(r.getInt("user_id"));
					c.setAge(r.getString("age"));
				}
			}});
		if(c.getId() != null && c.getId() > 0){
			return c;
		}
		return null;
	}

	@Override
	public CVJobIntention getIntention(Integer id) {
		final CVJobIntention c = new CVJobIntention();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,job_nature,hope_work_place,hope_job,hope_industry,hope_salary,")
			.append("job_status,cv_id,user_id  from cv_job_intention where cv_id=?");
		this.jdbcTemplate.query(sql.toString(),new Object[]{id}, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					c.setId(r.getInt("id"));
					c.setJobNature(r.getString("job_nature"));
					c.setHopeWorkPlace(r.getString("hope_work_place"));
					c.setHopeJob(r.getString("hope_job"));
					c.setHopeIndustry(r.getString("hope_industry"));
					c.setHopeSalary(r.getString("hope_salary"));
					c.setJobStatus(r.getString("job_status"));
					c.setCvId(r.getInt("cv_id"));
					c.setUserId(r.getInt("user_id"));
				}
			}});
		if(c.getId() != null && c.getId() > 0){
			return c;
		}
		return null;
	}

	@Override
	public List<CVCertificate> getCvcs(Integer id) {
		final List<CVCertificate> list = new ArrayList<CVCertificate>();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,certificate,get_time,cv_id,user_id from cv_certificate where cv_id=? ");
		this.jdbcTemplate.query(sql.toString(), new Object[]{id},new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					CVCertificate c = new CVCertificate();
					c.setId(r.getInt("id"));
					c.setCertificate(r.getString("certificate"));
					c.setGetTime(r.getString("get_time"));
					c.setCvId(r.getInt("cv_id"));
					c.setUserId(r.getInt("user_id"));
					list.add(c);
				}
			}});
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public List<CVEducation> getCves(Integer id) {
		final List<CVEducation> list = new ArrayList<CVEducation>();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,start_time,end_time,school,is_unified,major_type,")
			.append("major_name,education,cv_id,user_id from cv_education where cv_id=? ")
			.append(" order by position asc");
		this.jdbcTemplate.query(sql.toString(), new Object[]{id},new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					CVEducation c = new CVEducation();
					c.setId(r.getInt("id"));
					c.setStartTime(r.getString("start_time"));
					c.setEndTime(r.getString("end_time"));
					c.setSchool(r.getString("school"));
					c.setIsUnified(r.getString("is_unified"));
					c.setMajorType(r.getString("major_type"));
					c.setMajorName(r.getString("major_name"));
					c.setEducation(r.getString("education"));
					c.setCvId(r.getInt("cv_id"));
					c.setUserId(r.getInt("user_id"));
					list.add(c);
				}
			}});
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public List<CVJobExperience> getCvjs(Integer id) {
		final List<CVJobExperience> list = new ArrayList<CVJobExperience>();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,ent_name,industry_type,job_name,job_type,star_time,")
			.append("end_time,job_salary,job_describe,ent_nature,ent_scale,cv_id,")
			.append("user_id from cv_job_experience where cv_id=? order by position asc");
		this.jdbcTemplate.query(sql.toString(), new Object[]{id},new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					CVJobExperience c = new CVJobExperience();
					c.setId(r.getInt("id"));
					c.setEntName(r.getString("ent_name"));
					c.setIndustryType(r.getString("industry_type"));
					c.setJobName(r.getString("job_name"));
					c.setJobType(r.getString("job_type"));
					c.setStarTime(r.getString("star_time"));
					c.setEndTime(r.getString("end_time"));
					c.setJobDescribe(r.getString("job_describe"));
					c.setJobSalary(r.getString("job_salary"));
					c.setEntNature(r.getString("ent_nature"));
					c.setEntScale(r.getString("ent_scale"));
					c.setCvId(r.getInt("cv_id"));
					c.setUserId(r.getInt("user_id"));
					list.add(c);
				}
			}});
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public List<CVSkill> getCvks(Integer id) {
		final List<CVSkill> list = new ArrayList<CVSkill>();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,type,name,use_time,degree,cv_id,user_id from cv_skill where cv_id=? ");
		this.jdbcTemplate.query(sql.toString(), new Object[]{id},new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					CVSkill c = new CVSkill();
					c.setId(r.getInt("id"));
					c.setType(r.getString("type"));
					c.setName(r.getString("name"));
					c.setUseTime(r.getString("use_time"));
					c.setDegree(r.getString("degree"));
					c.setCvId(r.getInt("cv_id"));
					c.setUserId(r.getInt("user_id"));
					list.add(c);
				}
			}});
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public List<CVLanguage> getCvls(Integer id) {
		final List<CVLanguage> list = new ArrayList<CVLanguage>();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,language,rw_ability,hear_ability,cv_id,user_id from cv_language where cv_id=? ");
		this.jdbcTemplate.query(sql.toString(), new Object[]{id},new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					CVLanguage c = new CVLanguage();
					c.setId(r.getInt("id"));
					c.setLanguage(r.getString("language"));
					c.setRwAbility(r.getString("rw_ability"));
					c.setHearAbility(r.getString("hear_ability"));
					c.setCvId(r.getInt("cv_id"));
					c.setUserId(r.getInt("user_id"));
					list.add(c);
				}
			}});
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public List<CVProject> getCvps(Integer id) {
		final List<CVProject> list = new ArrayList<CVProject>();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,name,start_time,end_time,is_it,software,hardware,")
			.append("development_tool,duties,`describe`,cv_id,user_id from cv_project where cv_id=? ")
			.append(" order by position asc");
		this.jdbcTemplate.query(sql.toString(), new Object[]{id},new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					CVProject c = new CVProject();
					c.setId(r.getInt("id"));
					c.setName(r.getString("name"));
					c.setStartTime(r.getString("start_time"));
					c.setEndTime(r.getString("end_time"));
					c.setIsIt(r.getInt("is_it"));
					c.setSoftware(r.getString("software"));
					c.setHardware(r.getString("hardware"));
					c.setDevelopmentTool(r.getString("development_tool"));
					c.setDuties(r.getString("duties"));
					c.setDescribe(r.getString("describe"));
					c.setCvId(r.getInt("cv_id"));
					c.setUserId(r.getInt("user_id"));
					list.add(c);
				}
			}});
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public List<CVSchoolPractice> getCvsps(Integer id) {
		final List<CVSchoolPractice> list = new ArrayList<CVSchoolPractice>();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,name,start_time,end_time,`describe`,cv_id,user_id from cv_school_practice where cv_id=? ");
		this.jdbcTemplate.query(sql.toString(), new Object[]{id},new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					CVSchoolPractice c = new CVSchoolPractice();
					c.setId(r.getInt("id"));
					c.setName(r.getString("name"));
					c.setStartTime(r.getString("start_time"));
					c.setEndTime(r.getString("end_time"));
					c.setDescribe(r.getString("describe"));
					c.setCvId(r.getInt("cv_id"));
					c.setUserId(r.getInt("user_id"));
					list.add(c);
				}
			}});
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public List<CVInSchool> getCvss(Integer id) {
		final List<CVInSchool> list = new ArrayList<CVInSchool>();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,bonus_grade_a,grade_a,bonus_grade_b,grade_b,bonus_grade_c,grade_c,")
			.append("`describe`,item_a,item_grade_a,item_time_a,describe_a,item_b,item_grade_b,")
			.append("item_time_b,describe_b,item_c,item_grade_c,item_time_c,describe_c,cv_id,user_id ")
			.append(" from cv_in_school where cv_id=? ");
		this.jdbcTemplate.query(sql.toString(), new Object[]{id},new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					CVInSchool c = new CVInSchool();
					c.setId(r.getInt("id"));
					c.setBonusGrade_a(r.getString("bonus_grade_a"));
					c.setGrade_a(r.getString("grade_a"));
					c.setBonusGrade_b(r.getString("bonus_grade_b"));
					c.setGrade_b(r.getString("grade_b"));
					c.setBonusGrade_c(r.getString("bonus_grade_c"));
					c.setGrade_c(r.getString("grade_c"));
					c.setDescribe(r.getString("describe"));
					c.setItem_a(r.getString("item_a"));
					c.setItemGrade_a(r.getString("item_grade_a"));
					c.setItemTime_a(r.getString("item_time_a"));
					c.setDescribe_a(r.getString("describe_a"));
					c.setItem_b(r.getString("item_b"));
					c.setItemGrade_b(r.getString("item_grade_b"));
					c.setItemTime_b(r.getString("item_time_b"));
					c.setDescribe_b(r.getString("describe_b"));
					c.setItem_c(r.getString("item_c"));
					c.setItemGrade_c(r.getString("item_grade_c"));
					c.setItemTime_c(r.getString("item_time_c"));
					c.setDescribe_c(r.getString("describe_c"));
					c.setCvId(r.getInt("cv_id"));
					c.setUseId(r.getInt("user_id"));
					list.add(c);
				}
			}});
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public List<CVTrain> getCvts(Integer id) {
		final List<CVTrain> list = new ArrayList<CVTrain>();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,start_time,end_time,name,course,place,certificate,`describe`,cv_id,user_id ")
			.append(" from cv_train where cv_id=? ");
		this.jdbcTemplate.query(sql.toString(), new Object[]{id},new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					CVTrain c = new CVTrain();
					c.setId(r.getInt("id"));
					c.setStartTime(r.getString("start_time"));
					c.setEndTime(r.getString("end_time"));
					c.setName(r.getString("name"));
					c.setCourse(r.getString("course"));
					c.setPlace(r.getString("place"));
					c.setCertificate(r.getString("certificate"));
					c.setDescribe(r.getString("describe"));
					c.setCvId(r.getInt("cv_id"));
					c.setUserId(r.getInt("user_id"));
					list.add(c);
				}
			}});
		if(list.size() > 0){
			return list;
		}
		return null;
	}
	
	@Override
	public CVRecommend getRecommend(Integer cvId) {
		final CVRecommend c = new CVRecommend();
		StringBuilder sql = new StringBuilder();
		sql.append("select cv.id,cv.recommend_id,cv.customer_id,cv.job,cv.base_info,cv.leave_reason,cv.salary,cv.hope_job,")
			.append("cv.reason,cv.certificates,cv.honors,cv.face_time,cv.in_job_time,cv.cv_id,cv.user_id ")
			.append(",ct.name as ent_name ")
			.append(" from cv_recommend as cv ")
			.append(" left join customer ct on ct.id = cv.customer_id ")
			.append(" where cv_id=? ");
		this.jdbcTemplate.query(sql.toString(), new Object[]{cvId},new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					c.setId(r.getInt("id"));
					c.setRecommendId(r.getString("recommend_id"));
					c.setCustomerId(r.getInt("customer_id"));
					c.setEntName(r.getString("ent_name"));
					c.setJob(r.getString("job"));
					c.setBaseInfo(r.getString("base_info"));
					c.setLeaveReason(r.getString("leave_reason"));
					c.setSalary(r.getString("salary"));
					c.setHopeJob(r.getString("hope_job"));
					c.setReason(r.getString("reason"));
					c.setCertificates(r.getString("certificates"));
					c.setHonors(r.getString("honors"));
					c.setFaceTime(r.getString("face_time"));
					c.setInJobTime(r.getString("in_job_time"));
					c.setCvId(r.getInt("cv_id"));
					c.setUserId(r.getInt("user_id"));
				}
			}});
		if(c.getId() != null && c.getId() > 0){
			return c;
		}
		return null;
	}

	@Override
	public Integer saveRecommend(final CVRecommend cvr) {
		Integer id = 0;
		final StringBuilder sql = new StringBuilder();
		sql.append("insert into cv_recommend(recommend_id,customer_id,job,base_info,leave_reason,salary,hope_job,")
			.append("reason,face_time,in_job_time,cv_id,user_id)")
			.append(" values(?,?,?,?,?   ,?,?,?,?,?   ,?,?)");
		KeyHolder holder = new GeneratedKeyHolder();
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement p = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
					p.setString(1, cvr.getRecommendId());
					p.setInt(2, cvr.getCustomerId()==null?0:cvr.getCustomerId());
					p.setString(3, cvr.getJob());
					p.setString(4, cvr.getBaseInfo());
					p.setString(5, cvr.getLeaveReason());
					p.setString(6, cvr.getSalary());
					p.setString(7, cvr.getHopeJob());
					p.setString(8, cvr.getReason());
					p.setString(9, cvr.getFaceTime());
					p.setString(10, cvr.getInJobTime());
					p.setInt(11, cvr.getCvId());
					p.setInt(12, cvr.getUserId() == null ? 0:cvr.getUserId());
					return p;
				}
			}, holder);
			id = holder.getKey().intValue();
		} catch (InvalidDataAccessApiUsageException e) {
			e.printStackTrace();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public CVJobExperience getJobExp(Integer id, Integer cvId) {
		final CVJobExperience c = new CVJobExperience();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,ent_name,industry_type,job_name,job_type,star_time,")
			.append("end_time,job_salary,job_describe,ent_nature,ent_scale,cv_id,")
			.append("user_id from cv_job_experience where cv_id=? and id=?");
		this.jdbcTemplate.query(sql.toString(), new Object[]{cvId,id},new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					c.setId(r.getInt("id"));
					c.setEntName(r.getString("ent_name"));
					c.setIndustryType(r.getString("industry_type"));
					c.setJobName(r.getString("job_name"));
					c.setJobType(r.getString("job_type"));
					c.setStarTime(r.getString("star_time"));
					c.setEndTime(r.getString("end_time"));
					c.setJobDescribe(r.getString("job_describe"));
					c.setJobSalary(r.getString("job_salary"));
					c.setEntNature(r.getString("ent_nature"));
					c.setEntScale(r.getString("ent_scale"));
					c.setCvId(r.getInt("cv_id"));
					c.setUserId(r.getInt("user_id"));
				}
			}});
		if(c.getId() != null ){
			return c;
		}
		return null;
	}

	@Override
	public CVProject getProjectExp(Integer id, Integer cvId) {
		final CVProject c = new CVProject();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,name,start_time,end_time,is_it,software,hardware,")
			.append("development_tool,duties,`describe`,cv_id,user_id from cv_project where cv_id=? and id=? ");
		this.jdbcTemplate.query(sql.toString(), new Object[]{cvId,id},new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					c.setId(r.getInt("id"));
					c.setName(r.getString("name"));
					c.setStartTime(r.getString("start_time"));
					c.setEndTime(r.getString("end_time"));
					c.setIsIt(r.getInt("is_it"));
					c.setSoftware(r.getString("software"));
					c.setHardware(r.getString("hardware"));
					c.setDevelopmentTool(r.getString("development_tool"));
					c.setDuties(r.getString("duties"));
					c.setDescribe(r.getString("describe"));
					c.setCvId(r.getInt("cv_id"));
					c.setUserId(r.getInt("user_id"));
				}
			}});
		if(c.getId() != null){
			return c;
		}
		return null;
	}

	@Override
	public void updateEducation(CVEducation edu) {
		StringBuilder sql = new StringBuilder();
		sql.append("update cv_education set start_time=?,end_time=?,school=?,")
			.append("major_name=?,education=? where cv_id=? and id=? ");
		try {
			this.jdbcTemplate.update(sql.toString(), edu.getStartTime(),edu.getEndTime(),
													 edu.getSchool(),edu.getMajorName(),
													 edu.getEducation(),edu.getCvId(),
													 edu.getId());
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("修改教育经历失败");
		}
	}

	@Override
	public void updateJob(CVJobExperience job) {
		StringBuilder sql = new StringBuilder();
		sql.append("update cv_job_experience  set  ent_name=?,industry_type=?,job_name=?,star_time=?, ")
			.append(" end_time=?,job_salary=?,job_describe=?,ent_nature=? ")
			.append(" where  cv_id=? and id = ?");
		try {
			this.jdbcTemplate.update(sql.toString(), job.getEntName(),
													 job.getIndustryType(),
													 job.getJobName(),
													 job.getStarTime(),
												 	 job.getEndTime(),
												 	 job.getJobSalary(),
												 	 job.getJobDescribe(),
												 	 job.getEntNature(),
												 	 job.getCvId(),
												 	 job.getId());
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("修改工作经历失败");
		}
		
	}

	@Override
	public void updatePro(CVProject pro) {
		StringBuilder sql = new StringBuilder();
		sql.append("update cv_project set name=?,start_time=?,end_time=?,software=?,hardware=?,")
			.append("development_tool=?,duties=?,`describe`=?  where cv_id=? and id=? ");
		try {
			this.jdbcTemplate.update(sql.toString(), pro.getName(),
													 pro.getStartTime(),
													 pro.getEndTime(),
													 pro.getSoftware(),
													 pro.getHardware(),
													 pro.getDevelopmentTool(),
													 pro.getDuties(),
													 pro.getDescribe(),
													 pro.getCvId(),
													 pro.getId());
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("修改项目经验失败");
		}
	}

	@Override
	public void updateHope(CVJobIntention hope) {
		StringBuilder sql = new StringBuilder();
		sql.append("update  cv_job_intention  set  job_nature=?,hope_work_place=?,hope_job=?,")
			.append("hope_industry=?,hope_salary=?,job_status=?  where cv_id=? and id=? ");
		try {
			this.jdbcTemplate.update(sql.toString(),hope.getJobNature(),hope.getHopeWorkPlace(),
													hope.getHopeJob(),hope.getHopeIndustry(),
													hope.getHopeSalary(),hope.getJobStatus(),
													hope.getCvId(),hope.getId());
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("修改求职意向失败");
		}
		
	}

	@Override
	public CVTrain findTrain(Integer id, Integer cvId) {
		final CVTrain c = new CVTrain();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,start_time,end_time,name,course,place,certificate,`describe`,cv_id,user_id ")
			.append(" from cv_train where cv_id=? and id=? ");
		this.jdbcTemplate.query(sql.toString(), new Object[]{cvId,id},new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					c.setId(r.getInt("id"));
					c.setStartTime(r.getString("start_time"));
					c.setEndTime(r.getString("end_time"));
					c.setName(r.getString("name"));
					c.setCourse(r.getString("course"));
					c.setPlace(r.getString("place"));
					c.setCertificate(r.getString("certificate"));
					c.setDescribe(r.getString("describe"));
					c.setCvId(r.getInt("cv_id"));
					c.setUserId(r.getInt("user_id"));
				}
			}});
		if(c.getId() != null && c.getId() > 0){
			return c;
		}
		return null;
	}

	@Override
	public void updateTrain(CVTrain train) {
		StringBuilder sql = new StringBuilder();
		sql.append("update cv_train set name=?,place=?,certificate=?,`describe`=?  ")
			.append("  where cv_id=? and id=? ");
		try {
			this.jdbcTemplate.update(sql.toString(), train.getName(),
													 train.getPlace(),
													 train.getCertificate(),
													 train.getDescribe(),
													 train.getCvId(),
													 train.getId());
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("修改培训经历失败");
		}
	}

	@Override
	public void updateLanguage(CVLanguage lan) {
		StringBuilder sql = new StringBuilder();
		sql.append("update cv_language set language=?,rw_ability=?  where cv_id=? and id=?");
		try {
			this.jdbcTemplate.update(sql.toString(), lan.getLanguage(),lan.getRwAbility(),lan.getCvId(),lan.getId());
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("修改语言失败");
		}
	}

	@Override
	public void updateSkill(CVSkill sk) {
		StringBuilder sql = new StringBuilder();
		sql.append("update cv_skill set name=?,degree=? where cv_id=? and id=? ");
		try {
			this.jdbcTemplate.update(sql.toString(), sk.getName(),sk.getDegree(),sk.getCvId(),sk.getId());
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("修改技能失败");
		}
	}

	@Override
	public void updateCV(CV d) {
		StringBuilder sql = new StringBuilder();
		sql.append("update cv_base set `name`=?,sex=?,join_work_time=?,address=?,")
			.append("now_live_city=?,mobile=?,e_mail=?,marriage=?,hobby=? ")
			.append(",evaluate=?,update_time=?,age=?   where id=?");
		try {
			this.jdbcTemplate.update(sql.toString(), 
									d.getName(),
									d.getSex(),
									d.getJoinWorkTime(),
									d.getAddress(),
									d.getNowLiveCity(),
									d.getMobile(),
									d.getEmail(),
									d.getMarriage(),
									d.getHobby(),
									d.getEvaluate(),
									new Date(),
									d.getAge(),
									d.getId());
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("修改基本信息失败");
		}
	}

	@Override
	public void delCv(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from cv_base where id=? ");
		try {
			this.jdbcTemplate.update(sql.toString(), id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delProject(Integer cvId,Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from cv_project where cv_id=? ");
		if(id != null && id > 0){
			sql.append(" and id="+id);
		}
		try {
			this.jdbcTemplate.update(sql.toString(), cvId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delCerti(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from cv_certificate where cv_id=? ");
		try {
			this.jdbcTemplate.update(sql.toString(), id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delInSchool(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from cv_in_school where cv_id=? ");
		try {
			this.jdbcTemplate.update(sql.toString(), id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delEdu(Integer cvId,Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from cv_education where cv_id=? ");
		if(id != null && id > 0){
			sql.append(" and id="+id);
		}
		try {
			this.jdbcTemplate.update(sql.toString(), cvId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delJobInter(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from cv_job_intention where cv_id=? ");
		try {
			this.jdbcTemplate.update(sql.toString(), id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delLanguage(Integer cvId,Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from cv_language where cv_id=? ");
		if(id != null && id > 0){
			sql.append(" and id="+id);
		}
		try {
			this.jdbcTemplate.update(sql.toString(), cvId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delOther(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from cv_other where cv_id=? ");
		try {
			this.jdbcTemplate.update(sql.toString(), id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delRecomment(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from cv_recommend where cv_id=? ");
		try {
			this.jdbcTemplate.update(sql.toString(), id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delSchool(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from cv_school_practice where cv_id=? ");
		try {
			this.jdbcTemplate.update(sql.toString(), id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delSkill(Integer cvId,Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from cv_skill where cv_id=? ");
		if(id != null && id > 0){
			sql.append(" and id="+id);
		}
		try {
			this.jdbcTemplate.update(sql.toString(), cvId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delTrain(Integer cvId,Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from cv_train where cv_id=? ");
		if(id != null && id > 0){
			sql.append(" and id="+id);
		}
		try {
			this.jdbcTemplate.update(sql.toString(), cvId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delJobExper(Integer cvId,Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from cv_job_experience where cv_id=? ");
		if(id != null && id > 0){
			sql.append(" and id="+id);
		}
		try {
			this.jdbcTemplate.update(sql.toString(), cvId);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Pagination<CV> findPage(Integer pageNo,Integer pageSize, Integer state, Integer source, Integer userId, Integer inputUser, String value) {
		List<CV> list = findPageList(pageNo,pageSize,state,source,userId,inputUser,value);
		Integer count = findPageCount(state,source,userId,inputUser,value);
		Pagination<CV> page = new Pagination<CV>(pageNo, pageSize, count, list);
		return page;
	}

	private Integer findPageCount(Integer state, Integer source, Integer userId, Integer inputUser, String value) {
		List<Object> args = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(id)  from cv_base where 1=1 ");
		if(state != null){
			sql.append(" and state=? ");
			args.add(state);
		}
		if(source != null){
			sql.append(" and source=? ");
			args.add(source);
		}
		if(userId != null && userId > 0 ){
			sql.append(" and user_id=? ");
			args.add(userId);
		}
		if(inputUser != null && inputUser > 0){
			sql.append(" and input_user=? ");
			args.add(inputUser);
		}
		if(value != null){
			sql.append(" and (name like '%"+value+"%' or mobile like '%"+value+"%' or e_mail like '%"+value+"%' ) ");
		}
		Integer queryForObject = 0;
		try {
			queryForObject = this.jdbcTemplate.queryForObject(sql.toString(), args.toArray(),Integer.class);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return queryForObject;
	}

	private List<CV> findPageList(Integer pageNo, Integer pageSize, Integer state, Integer source, Integer userId, Integer inputUser, String value) {
		List<Object> args = new ArrayList<Object>();
		final List<CV> list = new ArrayList<CV>();
		Integer row = 0;
		if(pageNo > 1){
			row = (pageNo - 1)*pageSize;
		}
		StringBuilder sql = new StringBuilder();
		sql.append("select cb.id,cb.`name`,cb.sex,cb.address,cb.mobile,cb.now_live_city,cb.e_mail")
			.append(",cb.input_user,cb.create_time,cb.user_id,cb.update_time,cb.age,cb.birthday ")
			.append(",cb.source,cb.state,u.real_name as u_name,u1.real_name as u1_name ")
			.append(" from cv_base cb ")
			.append(" left join user u on u.user_id = cb.user_id ")
			.append(" left join user u1 on u1.user_id = cb.input_user ")
			.append(" left join cv_recommend cd on cd.cv_id = cb.id ")
			.append(" left join customer ct on ct.id = cd.customer_id ");
		sql.append(" where 1=1 ");
			if(state != null){
				sql.append(" and cb.state=? ");
				args.add(state);
			}
			if(source != null){
				sql.append(" and cb.source=? ");
				args.add(source);
			}
			if(userId != null && userId >0){
				sql.append(" and cb.user_id=? ");
				args.add(userId);
			}
			if(inputUser != null && inputUser > 0){
				sql.append(" and cb.input_user=? ");
				args.add(inputUser);
			}
			if(value != null){
				sql.append(" and (cb.name like '%"+value+"%' or cb.mobile like '%"+value+"%' or ct.name like '%"+value+"%' or cd.recommend_id like '%"+value+"%') ");
			}
			sql.append(" order by cb.update_time desc limit ?,?");
			args.add(row);
			args.add(pageSize);
		this.jdbcTemplate.query(sql.toString(), new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					CV c = new CV();
					c.setId(r.getInt("id"));
					c.setName(r.getString("name"));
					c.setSex(r.getString("sex"));
					c.setAddress(r.getString("address"));
					c.setMobile(r.getString("mobile"));
					c.setNowLiveCity(r.getString("now_live_city"));
					c.setEmail(r.getString("e_mail"));
					c.setInputUser(r.getInt("input_user"));
					c.setCreateTime(r.getTimestamp("create_time"));
					c.setUserId(r.getInt("user_id"));
					c.setUpdateTime(r.getTimestamp("update_time"));
					c.setSource(r.getInt("source"));
					c.setState(r.getInt("state"));
					c.setUserReal(r.getString("u_name"));
					c.setInputReal(r.getString("u1_name"));
					c.setAge(r.getString("age"));
					c.setBirthday(r.getString("birthday"));
					list.add(c);
				}
			}},args.toArray());
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public CV findCV(String mobile, String name) {
		final CV c = new CV();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,`name`,sex,birthday from cv_base where name=? and mobile=?");
		this.jdbcTemplate.query(sql.toString(),new Object[]{name,mobile}, new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet r) throws SQLException {
				if(r != null){
					c.setId(r.getInt("id"));
					c.setName(r.getString("name"));
					c.setSex(r.getString("sex"));
					c.setBirthday(r.getString("birthday"));
				}
			}});
		if(c.getId() != null && c.getId() > 0){
			return c;
		}
		return null;
	}

	@Override
	public void updateState(Integer id, Integer state, Integer userId) {
		List<Object> args = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("update cv_base set ");
		if(state != null){
			sql.append(" state=? ");
			args.add(state);
		}
		if(userId != null){
			sql.append(" user_id=? ");
			args.add(userId);
		}
		sql.append("   where id=? ");
		args.add(id);
		try {
			this.jdbcTemplate.update(sql.toString(),args.toArray());
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("修改状态失败");
		}
	}

	@Override
	public void updateCVTime(Integer cvId, Date date) {
		StringBuilder sql = new StringBuilder();
		sql.append("update cv_base set update_time=? where id=?");
		try {
			this.jdbcTemplate.update(sql.toString(), date,cvId);
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("更新时间失败");
		}
	}

	@Override
	public void upRecommend(CVRecommend cvr) {
		final StringBuilder sql = new StringBuilder();
		sql.append("update cv_recommend set recommend_id=?,customer_id=?,job=?,base_info=?")
			.append(",leave_reason=?,salary=?,hope_job=?,")
			.append("reason=?,face_time=?,in_job_time=? ")
			.append(" where id=? ");
		try {
			this.jdbcTemplate.update(sql.toString(), cvr.getRecommendId(),
													 cvr.getCustomerId(),
													 cvr.getJob(),
													 cvr.getBaseInfo(),
													 cvr.getLeaveReason(),
													 cvr.getSalary(),
													 cvr.getHopeJob(),
													 cvr.getReason(),
													 cvr.getFaceTime(),
													 cvr.getInJobTime(),
													 cvr.getId()
					);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void saveRecomPost(final String string, final Integer recId) {
		final StringBuilder sql = new StringBuilder();
		sql.append("insert into recommend_position(rec_id,post_id) values(?,?)");
		KeyHolder holder = new GeneratedKeyHolder();
		this.jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection c) throws SQLException {
				PreparedStatement p = c.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
				p.setInt(1,recId);
				p.setInt(2,Integer.valueOf(string));
				return p;
			}
		}, holder);
	}

	@Override
	public void delRecomPost(Integer id) {
		final StringBuilder sql = new StringBuilder();
		sql.append("delete from recommend_position where rec_id=?");
		try {
			this.jdbcTemplate.update(sql.toString(), id);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<RecommendPosition> getRecomPost(Integer rid) {
		final  List<RecommendPosition> list = new ArrayList<RecommendPosition>();
		StringBuilder sql = new StringBuilder();
		sql.append("select rp.rec_id,rp.post_id,p.name,p.duty ")
			.append(" from recommend_position rp ")
			.append(" left join position p on p.id = rp.post_id ")
			.append(" where rec_id=? ");
		try {
			this.jdbcTemplate.query(sql.toString(),new Object[]{rid},new RowCallbackHandler(){
				@Override
				public void processRow(ResultSet r) throws SQLException {
					if(r != null){
						RecommendPosition rp = new RecommendPosition();
						rp.setPosId(r.getInt("post_id"));
						rp.setRecId(r.getInt("rec_id"));
						rp.setPostName(r.getString("name"));
						rp.setDuty(r.getString("duty"));
						list.add(rp);
					}
				}});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	
	
}
