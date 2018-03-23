package com.jrfcv.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.jrfcv.bean.CVSchoolPractice;
import com.jrfcv.bean.CVSkill;
import com.jrfcv.bean.CVTrain;
import com.jrfcv.bean.CvRecord;
import com.jrfcv.bean.User;
import com.jrfcv.dao.CvRecordDao;
import com.jrfcv.dao.ExportDocDao;
import com.jrfcv.dao.InputExcelDao;
import com.jrfcv.service.InputExcelMng;
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
 *解析数据业务层实现类
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
@Service
public class InputExcelMngImpl implements InputExcelMng{

	@Autowired
	private InputExcelDao inputExcelDao;
	@Autowired
	private ExportDocDao  exportDocDao;
	@Autowired
	private CvRecordDao  cvRecordDao; 
	

	
	@Override
	public String readExcel(File f,User user) {
		String message = "";
		String name = f.getName();
		int lastIndexOf = name.lastIndexOf(".");
		String ext = name.substring(lastIndexOf + 1);
		if (ext.equals("xls")) {
			message = jxlReadXLS(f,user);
		}else{
			message="文件格式有误";
		}
		return message;
	}

	private String jxlReadXLS(File f,User user) {
		String message = "";
		try {
			InputStream str = new FileInputStream(f);
			// 构造Workbook（工作薄）对象
			Workbook rwb = Workbook.getWorkbook(str);
			Sheet rs = rwb.getSheet(1);// 获取第一张工作表
			message = saveExcel(rs,user);
			rwb.close();
			f.delete();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			message=" 找不到文件";
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			message=" 数据越界";
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			message="导入异常";
		}
		return message;
	}

	private String saveExcel(Sheet rs,User user) {
		String message = "";
		// 简历基础信息
		CV c = new CV();
		c.setCreateTime(new Date());
		c.setUpdateTime(new Date());
		c.setSource(1);
		c.setState(0);
		c.setUserId(user.getUserId());
		c.setInputUser(user.getUserId());
		// 求职意向
		CVJobIntention ci = new CVJobIntention();
		// 工作经历
		List<CVJobExperience> cvjs = new ArrayList<CVJobExperience>();
		// 教育经历
		List<CVEducation> cves = new ArrayList<CVEducation>();
		// 在校
		List<CVInSchool> cvss = new ArrayList<CVInSchool>();
		// 语言
		List<CVLanguage> cvls = new ArrayList<CVLanguage>();
		// 项目经验
		List<CVProject> cvps = new ArrayList<CVProject>();
		// 在校实践
		List<CVSchoolPractice> cvsps = new ArrayList<CVSchoolPractice>();
		// 技能
		List<CVSkill> cvks = new ArrayList<CVSkill>();
		// 培训经历
		List<CVTrain> cvts = new ArrayList<CVTrain>();
		// 证书
		List<CVCertificate> cvcs = new ArrayList<CVCertificate>();
		int rsRows = rs.getRows();// 获取Sheet表中所包含的总行数
		int rsCols = 2;// 获取Sheet表中所包含的总列数
		for (int i = 1; i < rsRows; i++) {// 读取行
			for (int j = 0; j < rsCols; j++) {
				// 单元格定位列，再定位行
				Cell cell = rs.getCell(j, i); // 姓名
				String txt = cell.getContents();
				//message +="<p>"+i+"行"+j+"列";
				if (i < 21 && j > 0 && txt != null && !txt.equals("")) {
					switch (i) {
					case 1:// 姓名
						c.setName(txt.trim());
						break;
					case 2:// 性别
						c.setSex(txt.trim());
						break;
					case 3:// 婚姻状况
						c.setMarriage(txt.trim());
						break;
					case 4:// 出生年月
						c.setBirthday(txt.trim());
						break;
					case 5:// 户口
						c.setDomicilePlace(txt.trim());
						break;
					case 6:// 现居住地
						c.setNowLiveCity(txt.trim());
						break;
					case 7:// 工作经验
						c.setJoinWorkTime(txt.trim());
						break;
					case 8:// 政治面貌
						c.setPoliticsFace(txt.trim());
						break;
					case 9:// 联系电话
						c.setMobile(txt.trim());
						break;
					case 10:// E-mail
						c.setEmail(txt.trim());
						break;
					case 11:// 身份证
						c.setIdCard(txt.trim());
						break;
					case 12:// 地址
						c.setAddress(txt.trim());
						break;
					case 13:// 邮编
						c.setPostCode(txt.trim());
						break;
					case 15:// 期望工作性质
						ci.setJobNature(txt.trim());
						break;
					case 16:// 期望从事职业
						ci.setHopeJob(txt.trim());
						break;
					case 17:// 期望从事行业
						ci.setHopeIndustry(txt.trim());
						break;
					case 18:// 期望工作地区
						ci.setHopeWorkPlace(txt.trim());
						break;
					case 19:// 期望月薪
						ci.setHopeSalary(txt.trim());
						break;
					case 20:// 目前状况
						ci.setJobStatus(txt.trim());
						break;
					default:
						break;
					}
				} else if (i > 20 && txt != null && !txt.equals("")) {
					if (j == 0) {
						if (txt.equals("自我评价")) {
							Cell pc = rs.getCell(j, i + 1);
							String tx = pc.getContents();
							if (tx == null || tx.equals("")) {
								Cell pc1 = rs.getCell(1, i + 1);
								String tx1 = pc1.getContents();
								c.setEvaluate(tx1);
							}
						} else if (txt.equals("工作经历")) {
							Cell next = rs.getCell(0, i + 1);
							String nexttxt = next.getContents();
							if (!nexttxt.equals("项目经验")) {
								cvjs = setJobExperience(rs, i, cvjs, rsRows);
							} else {
								break;
							}
						}
						if (txt.equals("项目经验")) {
							Cell next = rs.getCell(0, i + 1);
							String nexttxt = next.getContents();
							if (!nexttxt.equals("教育经历")) {
								Cell next2 = rs.getCell(0, i + 2);
								String nexttxt2 = next2.getContents();
								cvps = setProject(rs, i, cvps, rsRows, nexttxt2);
							} else {
								break;
							}
						}
						if (txt.equals("教育经历")) {
							for (int k = 1; k < rsRows; k++) {
								Cell pc = rs.getCell(j, i + k);
								String tx = pc.getContents();
								if (tx == null || tx.equals("")) {
									CVEducation cve = new CVEducation();
									Cell edu = rs.getCell(1, i + k);
									String edutx = edu.getContents();
									if (edutx != null && !edutx.equals("")) {
										String[] split = edutx.split(" ");
										cve.setStartTime(split[0]);
										cve.setEndTime(split[2]);
										cve.setSchool(split[4]);
										cve.setMajorType(split[6]);
										cve.setEducation(split[8]);
									}
									cves.add(cve);
								} else {
									break;
								}
							}
						} else if (txt.equals("培训经历")) {
							Cell next = rs.getCell(0, i + 1);
							String nexttxt = next.getContents();
							if (!nexttxt.equals("证书")) {
								cvts = setTrain(rs, i, cvts, rsRows);
							} else {
								break;
							}
						} else if (txt.equals("证书")) {
							Cell next = rs.getCell(0, i + 1);
							String nexttxt = next.getContents();
							if (!nexttxt.equals("在校学习情况")) {
								cvcs = setCertificate(rs, i, cvcs, rsRows);
							} else {
								break;
							}
						} else if (txt.equals("在校学习情况")) {
							Cell next = rs.getCell(0, i + 1);
							String nexttxt = next.getContents();
							if (!nexttxt.equals("在校实践经验")) {
								cvss = setInSchool(rs, i, cvss, rsRows);
							} else {
								break;
							}
						} else if (txt.equals("在校实践经验")) {
							Cell next = rs.getCell(0, i + 1);
							String nexttxt = next.getContents();
							if (!nexttxt.equals("语言能力")) {
								cvsps = setSchoolPractice(rs, i, cvsps, rsRows);
							} else {
								break;
							}
						} else if (txt.equals("语言能力")) {
							Cell next = rs.getCell(0, i + 1);
							String nexttxt = next.getContents();
							if (nexttxt == null || nexttxt.equals("")) {
								cvls = setLanguage(rs, i, cvls, rsRows);
							} else {
								break;
							}
						} else if (txt.equals("专业技能")) {
							if (i + 1 < rsRows) {
								Cell next = rs.getCell(0, i + 1);
								String nexttxt = next.getContents();
								if (nexttxt == null || nexttxt.equals("")) {
									cvks = setSkill(rs, i, cvks, rsRows);
								} else {
									break;
								}
							} else {
								break;
							}
						} else if (txt.equals("兴趣爱好")) {
							String hobby = "";
							for (int k = 1; k < rsRows; k++) {
								if (k + i < rsRows) {
									Cell next = rs.getCell(0, i + k);
									String nexttxt = next.getContents();
									if ((nexttxt == null || nexttxt.equals(""))) {
										Cell tx = rs.getCell(1, i + k);
										String ntx = tx.getContents();
										if (hobby.equals("")) {
											hobby = ntx;
										} else {
											hobby = hobby + "、" + ntx;
										}
									} else {
										break;
									}
								} else {
									break;
								}
							}
							c.setHobby(hobby);
						} else if (txt.indexOf("著作") != -1 || txt.indexOf("论文") != -1) {
							String work = "";
							for (int k = 1; k < rsRows; k++) {
								if (i + k < rsRows) {
									Cell next = rs.getCell(0, i + k);
									String nexttxt = next.getContents();
									if ((nexttxt == null || nexttxt.equals("")) && k + i < rsRows) {
										Cell tx = rs.getCell(1, i + k);
										String ntx = tx.getContents();
										if (work.equals("")) {
											work = ntx;
										} else {
											work = work + "、" + ntx;
										}
									} else {
										break;
									}
								} else {
									break;
								}
							}
							c.setWorkPaper(work);
						}
					}
				}
			}
		}
		c.setIntention(ci);
		c.setCves(cves);
		c.setCvjs(cvjs);
		c.setCvks(cvks);
		c.setCvls(cvls);
		c.setCvps(cvps);
		c.setCvss(cvss);
		c.setCvsps(cvsps);
		c.setCvts(cvts);
		c.setCvcs(cvcs);
		String mobile = c.getMobile();
		String name = c.getName();
		CV cv = exportDocDao.findCV(mobile,name);
		if(cv != null){
			message = "文件重复";
		}else{
			if(!StringUtils.isBlank(c.getMobile()) || !StringUtils.isBlank(c.getEmail())){
				Integer cvId = saveCV(c);
				saveCVCertificate(cvcs,cvId);
				saveCVEducation(cves,cvId);
				saveCVInSchool(cvss,cvId);
				saveCVJobExperience(cvjs,cvId);
				saveCVLanguage(cvls,cvId);
				saveCVProject(cvps,cvId);
				saveCVSchoolPractice(cvsps,cvId);
				saveCVSkill(cvks,cvId);
				saveCVTrain(cvts,cvId);
				saveIntention(ci,cvId);
				saveRecord(user,cvId);
				message = "上传成功";
			}else{
				message = "电话和email没有数据";
			}
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
		c.setRecord("上传智联简历");
		c.setUpdateTime(new Date());
		cvRecordDao.saveRecord(c);
	}

	private List<CVSkill> setSkill(Sheet rs, int i, List<CVSkill> cvks, int rsRows) {
		for (int j = 1; j < rsRows; j++) {
			if (i + j < rsRows) {
				Cell cell = rs.getCell(0, i + j);
				String nexttxt = cell.getContents();
				if (nexttxt == null || nexttxt.equals("")) {
					Cell cl = rs.getCell(1, i + j);
					String nt = cl.getContents();
					if (nt != null && !nt.equals("")) {
						CVSkill cvk = new CVSkill();
						String[] split = nt.split("：");
						cvk.setName(split[0]);
						cvk.setDegree(split[1]);
						cvks.add(cvk);
					}
				} else {
					break;
				}
			} else {
				break;
			}
		}
		return cvks;
	}

	private List<CVLanguage> setLanguage(Sheet rs, int i, List<CVLanguage> cvls, int rsRows) {
		for (int j = 1; j < rsRows; j++) {
			if (i + j < rsRows) {
				Cell cell = rs.getCell(0, i + j);
				String nexttxt = cell.getContents();
				if (nexttxt == null || nexttxt.equals("") || i + j > rsRows) {
					Cell cl = rs.getCell(1, i + j);
					String nt = cl.getContents();
					if (nt != null && !nt.equals("")) {
						CVLanguage cvl = new CVLanguage();
						String[] str = nt.split("：");
						cvl.setLanguage(str[0]);
						cvl.setRwAbility(str[1]);
						cvls.add(cvl);
					}
				} else {
					break;
				}
			} else {
				break;
			}
		}
		return cvls;
	}

	private List<CVSchoolPractice> setSchoolPractice(Sheet rs, int i, List<CVSchoolPractice> cvsps, int rsRows) {
		for (int j = 1; j < rsRows; j++) {
			if (i + j < rsRows) {
				Cell cell = rs.getCell(0, i + j);
				String nexttxt = cell.getContents();
				if (nexttxt == null || nexttxt.equals("") || i + j > rsRows) {
					CVSchoolPractice cvsp = new CVSchoolPractice();
					Cell name = rs.getCell(1, i + j);
					String n = name.getContents();
					if (j % 2 == 0) {
						cvsp.setDescribe(n);
					} else if (j % 2 == 1) {
						String[] split = n.split(" ");
						cvsp.setStartTime(split[0]);
						cvsp.setEndTime(split[2]);
						cvsp.setName(split[4]);
						cvsps.add(cvsp);
					}
				} else {
					break;
				}
			} else {
				break;
			}
		}
		return cvsps;
	}

	private List<CVInSchool> setInSchool(Sheet rs, int i, List<CVInSchool> cvss, int rsRows) {
		for (int j = 1; j < rsRows; j++) {
			if (i + j < rsRows) {
				Cell cell = rs.getCell(0, i + j);
				String nexttxt = cell.getContents();
				if (!nexttxt.equals("在校实践经验") || i + j > rsRows) {
					Cell name = rs.getCell(1, i + j);
					String n = name.getContents();
					if (n != null && !n.equals("")) {
						CVInSchool cvs = new CVInSchool();
						cvs.setDescribe(n);
						cvss.add(cvs);
					}
				} else {
					break;
				}
			} else {
				break;
			}
		}
		return cvss;
	}

	private List<CVCertificate> setCertificate(Sheet rs, int i, List<CVCertificate> cvcs, int rsRows) {
		for (int j = 1; j < rsRows; j++) {
			if (i + j < rsRows) {
				Cell cell = rs.getCell(0, i + j);
				String nexttxt = cell.getContents();
				if (!nexttxt.equals("在校学习情况") || i + j > rsRows) {
					Cell name = rs.getCell(1, i + j);
					String n = name.getContents();
					if (n != null && !n.equals("")) {
						CVCertificate cvc = new CVCertificate();
						String[] split = n.split("  ");
						cvc.setGetTime(split[0]);
						cvc.setCertificate(split[1]);
						cvcs.add(cvc);
					}
				} else {
					break;
				}
			} else {
				break;
			}
		}
		return cvcs;
	}

	private List<CVTrain> setTrain(Sheet rs, int i, List<CVTrain> cvts, int rsRows) {
		CVTrain cvt = new CVTrain();
		for (int j = i + 1; j < rsRows; j++) {
			Cell cell = rs.getCell(0, j);
			String nexttxt = cell.getContents();
			if (nexttxt.equals("证书")) {
				break;
			}
			if (nexttxt.equals("培训机构：")) {
				Cell name = rs.getCell(1, j);
				String n = name.getContents();
				cvt.setName(n);
				continue;
			} else if (nexttxt.equals("培训地点：")) {
				Cell pname = rs.getCell(1, j);
				String place = pname.getContents();
				cvt.setPlace(place);
				continue;
			} else if (nexttxt.equals("所获证书：")) {
				Cell certificate = rs.getCell(1, j);
				String certi = certificate.getContents();
				cvt.setCertificate(certi);
				continue;
			} else if (nexttxt.equals("培训描述：")) {
				Cell describe = rs.getCell(1, j);
				String desc = describe.getContents();
				cvt.setDescribe(desc);
				cvts.add(cvt);
				cvt = new CVTrain();
				continue;
			}
		}
		return cvts;
	}

	private List<CVProject> setProject(Sheet rs, int i, List<CVProject> cvps, Integer rsRows, String nexttxt2) {
		CVProject cvp = new CVProject();
		for (int a = i + 1; a < rsRows; a++) {
			Cell nexttxtName = rs.getCell(0, a);
			String nexttxt = nexttxtName.getContents();
			if (!nexttxt.equals("教育经历")) {
				// 项目名称
				if (nexttxt.equals("项目名称：")) {
					Cell proNames = rs.getCell(1, a);
					String tx = proNames.getContents();
					if (tx != null && !tx.equals("")) {
						String[] split = tx.split("  ");
						String times = split[0];
						String[] split2 = times.split("-");
						cvp.setStartTime(split2[0]);
						cvp.setEndTime(split2[1]);
						String proName = split[1];
						cvp.setName(proName);
					}
					continue;
				}
				// 软件环境
				if (nexttxt.equals("软件环境：")) {
					Cell ruan = rs.getCell(1, a);
					String ruantxt = ruan.getContents();
					if (ruantxt != null && !ruantxt.equals("")) {
						cvp.setSoftware(ruantxt);
						cvp.setIsIt(1);
					}
					continue;
				}
				// 硬件环境
				if (nexttxt.equals("硬件环境：")) {
					Cell ying = rs.getCell(1, a);
					String yingtxt = ying.getContents();
					if (yingtxt != null && !yingtxt.equals("")) {
						cvp.setHardware(yingtxt);
					}
					continue;
				}
				// 开发工具
				if (nexttxt.equals("开发工具：")) {
					Cell tool = rs.getCell(1, a);
					String tooltxt = tool.getContents();
					if (tooltxt != null && !tooltxt.equals("")) {
						cvp.setDevelopmentTool(tooltxt);
					}
					continue;
				}
				// 责任描述
				if (nexttxt.equals("责任描述：")) {
					Cell desc = rs.getCell(1, a);
					String describe = desc.getContents();
					if (describe != null && !describe.equals("")) {
						cvp.setDuties(describe);
					}
					continue;
				}
				// 项目描述
				if (nexttxt.equals("项目描述：")) {
					Cell prodesc = rs.getCell(1, a);
					String prodescribe = prodesc.getContents();
					if (prodescribe != null && !prodescribe.equals("")) {
						cvp.setDescribe(prodescribe);
					}
					cvps.add(cvp);
					cvp = new CVProject();
					continue;
				}
			} else {
				break;
			}
		}
		return cvps;
	}

	private List<CVJobExperience> setJobExperience(Sheet rs, Integer i, List<CVJobExperience> cvjs, Integer rsRows) {
		CVJobExperience cvj = new CVJobExperience();
		Integer con = 1;
		for (int j = i+1; j < rsRows; j++) {
			if(j > i+1){
				con+=4;
			}
			if(i+con > rsRows){
				break;
			}
			Cell nexttxtName = rs.getCell(0, i+con);
			String nexttxt1 = nexttxtName.getContents();
			if(nexttxt1.equals("项目经验") && j+con < rsRows){
				break;
			}
			Cell job = rs.getCell(1, i+con);
			String jobtxt = job.getContents();
			if (jobtxt != null && !jobtxt.equals("")) {
				String[] split = jobtxt.split("  ");
				for (int k = 0; k < split.length; k++) {
					if (k == 0) {
						String time = split[k];
						if (time.indexOf(" - ") > -1) {
							String[] split2 = time.split(" - ");
							cvj.setStarTime(split2[0]);
							cvj.setEndTime(split2[1]);
						}
					} else if (k == 1) {
						String name = split[k];
						cvj.setEntName(name);
					} else if (k == 2) {
						String intime = split[k];
						cvj.setInJobTime(intime);
					}
				}
			}
			Cell job1 = rs.getCell(1, i + con+1 );
			String jobtxt1 = job1.getContents();
			if (jobtxt1.indexOf("|") > -1) {
				String[] split1 = jobtxt1.split("[|]");
				String jobName = split1[0];
				String jobSalary = split1[1];
				cvj.setJobName(jobName);
				cvj.setJobSalary(jobSalary);
			} else {
				cvj.setJobName(jobtxt1);
			}
			if (i + con + 2 > rsRows) {
				break;
			}
			Cell job2 = rs.getCell(1, i +con+2);
			String jobtxt2 = job2.getContents();
			if (jobtxt2.indexOf("|") > -1) {
				String[] split2s = jobtxt2.split("[|]");
				String hangye = split2s[0];
				String natives = split2s[1];
				cvj.setIndustryType(hangye);
				if(natives.indexOf("：") > -1){
					String[] split = natives.split("：");
					cvj.setEntNature(split[1]);
				}else{
					cvj.setEntNature(natives);
				}
			} else {
				cvj.setIndustryType(jobtxt2);
			}
			Cell job3 = rs.getCell(1, i + con + 3);
			String jobtxt3 = job3.getContents();
			if(i + con + 3 < rsRows){
				cvj.setJobDescribe(jobtxt3);
				cvjs.add(cvj);
				cvj = new CVJobExperience();
				continue;
			}
		}
		return cvjs;
	}

	@Override
	public String SaveFileFromInputStream(InputStream in, String uploadPath, String filename) throws IOException {
		File file = new File(uploadPath + "/" + filename);
		if (!file.exists()) {
			file.createNewFile();
		}
		// 创建输出流
		FileOutputStream fs = new FileOutputStream(file);
		// 创建缓冲区
		byte[] buffer = new byte[1024 * 1024];
		// 判断输入流中的数据是否已经读完的标识
		int bytesum = 0;// 记录已读取的数据
		int byteread = 0;
		while ((byteread = in.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, bytesum);// 将读到的数据写入文件
			fs.flush();
		}
		fs.close();
		in.close();
		return uploadPath + "\\" + filename;
	}
	private Integer saveCV(CV c) {
		if(c != null){
			return inputExcelDao.saveCV(c);
		}
		return 0;
	}
	public void saveIntention(CVJobIntention intention, Integer cvId){
		if(intention != null){
			inputExcelDao.saveIntention(intention,cvId);
		}
	}
	public void saveCVJobExperience(List<CVJobExperience> cvjs, Integer cvId){
		if(cvjs != null && cvjs.size() > 0 ){
			inputExcelDao.saveCVJobExperience(cvjs,cvId);
		}
	}
	public void saveCVEducation(List<CVEducation> cves, Integer cvId){
		if(cves != null && cves.size() > 0){
			inputExcelDao.saveCVEducation(cves,cvId);
		}
	}
	public void saveCVLanguage(List<CVLanguage> cvls, Integer cvId){
		if(cvls != null && cvls.size() > 0){
			inputExcelDao.saveCVLanguage(cvls,cvId);
		}
	}
	public void saveCVProject(List<CVProject> cvps, Integer cvId){
		if(cvps != null && cvps.size() > 0){
			inputExcelDao.saveCVProject(cvps,cvId);
		}
	}
	public void saveCVSkill(List<CVSkill> cvks, Integer cvId){
		if(cvks != null && cvks.size() > 0){
			inputExcelDao.saveCVSkill(cvks,cvId);
		}
	}
	public void saveCVInSchool(List<CVInSchool> cvss, Integer cvId){
		if(cvss != null && cvss.size() > 0){
			inputExcelDao.saveCVInSchool(cvss,cvId);
		}
	}
	public void saveCVSchoolPractice(List<CVSchoolPractice> cvsps, Integer cvId){
		if(cvsps != null && cvsps.size() > 0){
			inputExcelDao.saveCVSchoolPractice(cvsps,cvId);
		}
	}
	public void saveCVTrain(List<CVTrain> cvts, Integer cvId){
		if(cvts != null && cvts.size() > 0){
			inputExcelDao.saveCVTrain(cvts,cvId);
		}
	}
	public void saveCVCertificate(List<CVCertificate> cvcs, Integer cvId){
		if(cvcs != null && cvcs.size() > 0){
			inputExcelDao.saveCVCertificate(cvcs,cvId);
		}
	}

}
