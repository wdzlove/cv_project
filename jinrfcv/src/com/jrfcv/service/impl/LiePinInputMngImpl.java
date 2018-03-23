package com.jrfcv.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrfcv.bean.CV;
import com.jrfcv.bean.CVEducation;
import com.jrfcv.bean.CVJobExperience;
import com.jrfcv.bean.CVJobIntention;
import com.jrfcv.bean.CVLanguage;
import com.jrfcv.bean.CVProject;
import com.jrfcv.bean.CvRecord;
import com.jrfcv.bean.User;
import com.jrfcv.dao.CvRecordDao;
import com.jrfcv.dao.ExportDocDao;
import com.jrfcv.dao.InputExcelDao;
import com.jrfcv.service.LiePinInputMng;
import com.jrfcv.util.StringUtils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
/**
 * 
 * 开发公司：北京金瑞帆科技有限公司<br/>
 * 版权：北京金瑞帆科技有限公司<br/>
 * <p>
 *猎聘简历读取业务层实现类
 * <p>
 *
 * 区分　责任人　日期　　　　说明<br/>
 * 创建 wdz　2018年3月7日　<br/>
 * <p>
 *
 * @author Administrator
 *
 * @version 1.0, 2018年3月7日
 *
 */
@Service
public class LiePinInputMngImpl implements LiePinInputMng {

	@Autowired
	private InputExcelDao inputExcelDao;
	@Autowired
	private ExportDocDao exportDocDao;
	@Autowired
	private CvRecordDao  cvRecordDao; 
	
	
	
	@Override
	public String readExcel(File f,User user) {
		String message = "";
		String name = f.getName();
		int lastIndexOf = name.lastIndexOf(".");
		String ext = name.substring(lastIndexOf + 1);
		if (ext.equals("xls")) {
			message = jxlReadXLS(f,message,user);
		} else{
			message = "文件格式有误";
		}
		return message;
	}

	private String jxlReadXLS(File f,String message,User user) {
		try {
			InputStream str = new FileInputStream(f);
			// 构造Workbook（工作薄）对象
			Workbook rwb = Workbook.getWorkbook(str);
			Sheet rs = rwb.getSheet(0);// 获取第一张工作表
			message = saveExcel(rs,message,user);
			rwb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			message = "路径找不到指定文件";
		} catch (BiffException e) {
			e.printStackTrace();
			message = "找不到输入文件。";
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			message = "读取数据越界";
		} catch (IOException e) {
			e.printStackTrace();
			message = "读写异常";
		}
		return message;
	}

	private String saveExcel(Sheet rs,String message,User user) {
		// 简历基础信息
		CV c = new CV();
		c.setCreateTime(new Date());
		c.setUpdateTime(new Date());
		c.setSource(2);
		c.setState(0);
		c.setUserId(user.getUserId());
		c.setInputUser(user.getUserId());
		// 求职意向
		CVJobIntention ci = new CVJobIntention();
		//ci.setJobStatus(c.getIntention().getJobStatus());
		// 工作经历
		List<CVJobExperience> cvjs = new ArrayList<CVJobExperience>();
		// 教育经历
		List<CVEducation> cves = new ArrayList<CVEducation>();
		// 语言
		List<CVLanguage> cvls = new ArrayList<CVLanguage>();
		// 项目经验
		List<CVProject> cvps = new ArrayList<CVProject>();
		int rsRows = rs.getRows();// 获取Sheet表中所包含的总行数
		int rsCols = 4;// 获取Sheet表中所包含的总列数
		for (int i = 0; i < rsRows; i++) {
			for (int j = 0; j < rsCols; j++) {
				Cell cell = rs.getCell(j,i);
				String txt = cell.getContents();
				message = i+"行"+j+"列";
				if(txt.trim().equals("个人信息")){
					c = readBaseInfo(rs,i,rsRows,c);
				}else if(txt.indexOf("职业发展意向") != -1){
					ci = readIntention(rs,i,rsRows,ci);
				}else if(txt.trim().equals("工作经历")){
					cvjs = readJobExp(rs,i,rsRows,cvjs);
				}else if(txt.trim().equals("项目经历")){
					cvps = readProject(rs,i,rsRows,cvps);
				}else if(txt.trim().equals("教育经历")){
					cves = readEducation(rs,i,rsRows,cves);
				}else if(txt.trim().equals("语言能力")){
					Cell langu = rs.getCell(0,i+2);
					String lt = langu.getContents();
					String[] split = lt.split("、");
					for (int k = 0; k < split.length; k++) {
						CVLanguage l = new CVLanguage();
						l.setLanguage(split[k]);
						cvls.add(l);
					}
				}else if(txt.trim().equals("自我评价")){
					Cell langu = rs.getCell(0,i+2);
					String lt = langu.getContents();
					c.setEvaluate(lt);
				}
			}
		}
		String jobStatus = c.getIntention().getJobStatus();
		if(jobStatus != null){
			ci.setJobStatus(jobStatus);
		}
		CV findCV = exportDocDao.findCV(c.getMobile(),c.getName());
		if(findCV == null){
			if(!StringUtils.isBlank(c.getMobile()) || !StringUtils.isBlank(c.getEmail())){
				Integer cvId = inputExcelDao.saveCV(c);
				inputExcelDao.saveIntention(ci, cvId);
				inputExcelDao.saveCVEducation(cves, cvId);
				inputExcelDao.saveCVJobExperience(cvjs, cvId);
				inputExcelDao.saveCVLanguage(cvls, cvId);
				inputExcelDao.saveCVProject(cvps, cvId);
				saveRecord(user,cvId);
				message = "上传成功";
			}else{
				message = "电话和email没有数据";
			}
		}else{
			message = "文件重复";
		}
		return message;
	}

	/**
	* @Title: saveRecord
	* @Description: 保存记录
	* @author wdz
	* @date 2018年3月15日 下午2:03:53
	* @param user
	* @param cvId
	 */
	private void saveRecord(User user, Integer cvId) {
		CvRecord c = new CvRecord();
		c.setCvId(cvId);
		c.setUserId(user.getUserId());
		c.setRecord("上传猎聘简历");
		c.setUpdateTime(new Date());
		cvRecordDao.saveRecord(c);
	}

	/**
	* @Title: readEducation
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author wdz
	* @date 2018年3月7日 下午5:45:52
	* @param rs
	* @param i
	* @param rsRows
	* @param cves
	* @return
	 */
	private List<CVEducation> readEducation(Sheet rs, int i, int rsRows, List<CVEducation> cves) {
		CVEducation e = new CVEducation();
		for (int j = i+1; j < rsRows; j++) {
			Cell cell = rs.getCell(0, j);
			String contents = cell.getContents();
			if(!contents.trim().equals("语言能力")){
				if(!contents.trim().equals("")){
					String[] split = contents.split(" ");
					String school = split[0];
					e.setSchool(school);
					if(split[2].indexOf("–") != -1){
						String[] split2 = split[2].split("–");
						e.setStartTime(split2[0]);	
						e.setEndTime(split2[1]);
					}
					Cell majorName = rs.getCell(1, j);
					String mn = majorName.getContents();
					e.setMajorName(mn);
					Cell edu = rs.getCell(2, j);
					String education = edu.getContents();
					e.setEducation(education);
					cves.add(e);
					e = new CVEducation();
					continue;
				}
			}else{
				return cves;
			}
		}
		return null;
	}

	/**
	* @Title: readProject
	* @Description:读取项目经历
	* @author wdz
	* @date 2018年3月7日 下午4:55:33
	* @param rs
	* @param i
	* @param rsRows
	* @param cvps
	* @return
	 */
	private List<CVProject> readProject(Sheet rs, int i, int rsRows, List<CVProject> cvps) {
		CVProject p = new CVProject();
		for (int j = i+2; j < rsRows; j++) {
			Cell cell = rs.getCell(0, j);
			String contents = cell.getContents();
			if(contents.indexOf("教育经历") == -1){
				if(contents.trim().equals("")){
					continue;
				}
				String[] split = contents.split("–");
				p.setStartTime(split[0]);
				p.setEndTime(split[1]);
				Cell pname = rs.getCell(1, j);
				String pn = pname.getContents();
				p.setName(pn);
				for (int k = j+1; k < rsRows; k++) {
					Cell keys = rs.getCell(1, k);
					String keyt = keys.getContents();
					if(keyt.indexOf("项目简介") != -1){
						Cell vals = rs.getCell(2, k);
						p.setDescribe(vals.getContents());
					}else if(keyt.indexOf("项目职责") != -1){
						Cell vals = rs.getCell(2, k);
						p.setDuties(vals.getContents());
						cvps.add(p);
						p = new CVProject();
						break;
					}
				}
			}else{
				return cvps;
			}
		}
		return null;
	}

	/**
	* @Title: readJobExp
	* @Description: 读取工作经历
	* @author wdz
	* @date 2018年3月7日 下午3:06:48
	* @param rs
	* @param i
	* @param rsRows
	* @param cvjs
	* @return
	 */
	private List<CVJobExperience> readJobExp(Sheet rs, int i, int rsRows, List<CVJobExperience> cvjs) {
		CVJobExperience c = new CVJobExperience();
		for (int j = i+2; j < rsRows; j++) {
			Cell cell = rs.getCell(0,j);
			String txt = cell.getContents();
			if(!txt.equals("项目经历") && !txt.equals("")){
				Cell p1 = rs.getCell(0,j);//时间
				String time = p1.getContents();
				if(time.indexOf("-") != -1){
					String[] split = time.split("-");
					c.setStarTime(split[0]);
					c.setEndTime(split[1]);
				}else if(time.indexOf("，") != -1){
					String[] split = time.split("，");
					c.setStarTime(split[0]);
					c.setEndTime(split[1]);
				}
				
				Cell c1 = rs.getCell(1,j);//项目名称
				String pname = c1.getContents();
				c.setEntName(pname);
				
				Cell c2 = rs.getCell(1,j+1);//公司性质： 国有企业 | 公司规模： 1-49人 | 公司行业： 银行
				String nature = c2.getContents();
				String[] split2 = nature.split("[|]");
				for (int l = 0; l < split2.length; l++) {
					String[] split3 = split2[l].split("：");
					if(split3[0].trim().equals("公司性质")){
						c.setEntNature(split3[1]);
					}else if(split3[0].trim().equals("公司规模")){
						c.setEntScale(split3[1]);
					}else if(split3[0].trim().equals("公司行业")){
						c.setIndustryType(split3[1]);
					}
				}
				for (int k = j+2; k < rsRows; k++) {
					Cell c3 = rs.getCell(1,k);
					String place = c3.getContents();
					if(place.indexOf("下属人数") != -1){
						Cell jobName = rs.getCell(1,k-2);
						String jn = jobName.getContents();
						c.setJobName(jn);
						Cell c4 = rs.getCell(3,k);
						String salary = c4.getContents();
						if(salary.indexOf("薪酬情况") != -1){
							String[] split3 = salary.split("：");
							c.setJobSalary(split3[1]);
						}
						Cell c41 = rs.getCell(0,k+1);
						String txts = c41.getContents();
						String strs = "";
						if(txts.equals("")){
							for (int l = 1; l < rsRows; l++) {
								if( k+l < rsRows){
									Cell c5 = rs.getCell(0,k+l);
									if(c5.getContents().equals("")){
										Cell c6 = rs.getCell(2,k+l);
										String duts = c6.getContents();
										strs+=duts;
									}
								}
							}
							c.setJobDescribe(strs);
							cvjs.add(c);
							c = new CVJobExperience();
							break;
						}
					}
				}
			}else if(txt.equals("项目经历")){
				return cvjs;
			}
		}
		return null;
	}

	/**
	* @Title: readIntention
	* @Description:求职意向
	* @author wdz
	* @date 2018年3月7日 下午2:49:08
	* @param rs
	* @param i
	* @param rsRows
	* @param ci
	* @return
	 */
	private CVJobIntention readIntention(Sheet rs, int i, int rsRows, CVJobIntention ci) {
		for (int j = i+1; j < rsRows; j++) {
			for (int k = 0; k < 2; k++) {
				Cell cell = rs.getCell(k,j);
				String txt = cell.getContents();
				if(txt.indexOf("期望行业") != -1){
					Cell industry = rs.getCell(k+1,j);
					ci.setHopeIndustry(industry.getContents());
				}else if(txt.indexOf("期望职位") != -1){
					Cell hopeJob = rs.getCell(k+1,j);
					ci.setHopeJob(hopeJob.getContents());
				}else if(txt.indexOf("期望地点") != -1){
					Cell place = rs.getCell(k+1,j);
					ci.setHopeWorkPlace(place.getContents());
				}else if(txt.indexOf("期望年薪") != -1){
					Cell salary = rs.getCell(k+1,j);
					ci.setHopeSalary(salary.getContents());
				}else if(txt.equals("工作经历")){
					return ci;
				}
			}
		}
		return null;
	}

	/**
	* @Title: readBaseInfo
	* @Description: 读取个人信息
	* @author wdz
	* @date 2018年3月7日 下午2:07:41
	* @param i
	* @param j
	* @param rsRows
	* @param c
	* @return
	 */
	private CV readBaseInfo(Sheet rs,int i, int rsRows, CV c) {
		for (int j = i; j < rsRows; j++) {
			for (int x = 0; x < 4; x++) {
				Cell cell = rs.getCell(x,j);
				String txt = cell.getContents();
				if(txt.indexOf("姓名") != -1){
					Cell name = rs.getCell(x+1,j);
					c.setName(name.getContents().trim());
				}else if(txt.indexOf("性别") != -1){
					Cell sex = rs.getCell(x+1,j);
					c.setSex(sex.getContents().trim());
				}else if(txt.indexOf("手机号码") != -1){
					Cell mobile = rs.getCell(x+1,j);
					c.setMobile(mobile.getContents().trim());
				}else if(txt.indexOf("年龄") != -1){
					Cell ages = rs.getCell(x+1,j);
					c.setAge(ages.getContents());
				}else if(txt.indexOf("电子邮件") != -1){
					Cell email = rs.getCell(x+1,j);
					c.setEmail(email.getContents().trim());
				}else if(txt.indexOf("教育程度") != -1){
					Cell edu = rs.getCell(x+1,j);
					c.setEducation(edu.getContents().trim());
				}else if(txt.indexOf("工作年限") != -1){
					Cell workTime = rs.getCell(x+1,j);
					c.setJoinWorkTime(workTime.getContents().trim());
				}else if(txt.indexOf("婚姻状况") != -1){
					Cell marriage = rs.getCell(x+1,j);
					c.setMarriage(marriage.getContents().trim());
				}else if(txt.indexOf("职业状态") != -1){
					CVJobIntention ci = new CVJobIntention();
					Cell jobStatus = rs.getCell(x+1,j);
					ci.setJobStatus(jobStatus.getContents().trim());
					c.setIntention(ci);
				}else if(txt.indexOf("所在地") != -1){
					Cell place = rs.getCell(x+1,j);
					c.setNowLiveCity(place.getContents().trim());
				}else if(txt.indexOf("国籍") != -1){
					Cell nation = rs.getCell(x+1,j);
					c.setNationality(nation.getContents().trim());
				}else if(txt.indexOf("户籍") != -1){
					Cell place = rs.getCell(x+1,j);
					c.setAddress(place.getContents().trim());
				}else if(txt.indexOf("目前职业概况") != -1){
					return c;
				}
			}
		}
		return null;
	}

}
