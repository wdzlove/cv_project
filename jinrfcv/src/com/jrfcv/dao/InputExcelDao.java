package com.jrfcv.dao;

import java.util.List;

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

public interface InputExcelDao {

	public String test();

	public void saveIntention(CVJobIntention intention, Integer cvId);

	public void saveCVJobExperience(List<CVJobExperience> cvjs, Integer cvId);

	public void saveCVEducation(List<CVEducation> cves, Integer cvId);

	public void saveCVLanguage(List<CVLanguage> cvls, Integer cvId);

	public void saveCVProject(List<CVProject> cvps, Integer cvId);

	public void saveCVSkill(List<CVSkill> cvks, Integer cvId);

	public void saveCVInSchool(List<CVInSchool> cvss, Integer cvId);

	public void saveCVSchoolPractice(List<CVSchoolPractice> cvsps, Integer cvId);

	public void saveCVTrain(List<CVTrain> cvts, Integer cvId);

	public void saveCVCertificate(List<CVCertificate> cvcs, Integer cvId);

	public Integer saveCV(CV c);

	public void updateEduPosition(Integer cvId, Integer id, Integer index);

	public void jobExpSaveSort(Integer cvId, Integer id, Integer index);

	public void proSaveSort(Integer cvId, Integer id, Integer index);

}
