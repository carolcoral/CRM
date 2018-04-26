package com.hjcrm.system.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcrm.patter.entity.Pattertype;
import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.Course;
import com.hjcrm.system.entity.Subject;
import com.hjcrm.system.entity.User;
import com.hjcrm.system.service.ICourseService;

@Service
@Transactional(rollbackFor = Exception.class)
public class CourseServiceImpl implements ICourseService {
	
	@Autowired
	private IDataAccess<Course> courseDao;
	@Autowired
	private IDataAccess<Pattertype> pattertypeDao;
	@Autowired
	private IDataAccess<Subject> subjectDao;
	
	/**
	 * 查询所有课程
	 */
	
	public List<Course> queryCourse(PageBean pageBean) {
		List<Course> list = courseDao.queryByStatment("queryCourse", null, pageBean);
		return list;
	}

	/**
	 * 查询课程的场景
	 */
	
	public List<Pattertype> queryPattertype(PageBean pageBean, String courseid) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("courseid", courseid);
		List<Pattertype> list = pattertypeDao.queryByStatment("queryPattertype", param, pageBean);
		return list;
	}

	/**
	 * 增加或者修改课程
	 */
	
	public void saveOrUpdate(Course course) {
		if (course != null && course.getCourseid() != null) {//修改
			course.setUpdate_time(new Timestamp(System.currentTimeMillis()));
			course.setUpdate_id(UserContext.getLoginUser().getUserid());
			courseDao.update(course);
		}else if(course != null && course.getCourseid() == null){//增加
			course.setCreate_time(new Timestamp(System.currentTimeMillis()));
			course.setCreate_id(UserContext.getLoginUser().getUserid());
			courseDao.insert(course);
		}
	}

	/**
	 * 增加或者修改课程场景
	 */
	
	public void saveOrUpdate(Pattertype pattertype) {
		if (pattertype != null && pattertype.getPatterTypeId() != null) {//修改
			pattertype.setUpdate_time(new Timestamp(System.currentTimeMillis()));
			pattertype.setUpdate_id(UserContext.getLoginUser().getUserid());
			pattertypeDao.update(pattertype);
		}else if(pattertype != null && pattertype.getPatterTypeId() == null){//增加
			pattertype.setCreate_time(new Timestamp(System.currentTimeMillis()));
			pattertype.setCreate_id(UserContext.getLoginUser().getUserid());
			pattertypeDao.insert(pattertype);
		}
	}

	/**
	 * 删除课程
	 * 课程ID用逗号隔开，支持批量
	 */
	
	public void deleteCourse(String courseids) {
		if(courseids != null && !"".equals(courseids)){
			courseDao.deleteByIds(Course.class, courseids);
		}
	}

	/**
	 * 根据课程ID或者场景ID，删除课程场景
	 */
	
	public void deletePattertype(String courseids, String patterTypeIds) {
		 if (courseids != null && !"".equals(courseids)) {
			 Map<String, Object> param = new HashMap<String, Object>();
			 for (String id : courseids.split(",")) {
				 param.put("courseid", id);
				 pattertypeDao.deleteByStatment("deletePattertype", param);
				 param.clear();
			}
		}else if(patterTypeIds != null && !"".equals(patterTypeIds)){
			pattertypeDao.deleteByIds(Pattertype.class, patterTypeIds);
		}
	}

	/**
	 * 保存/修改科目
	 */
	
	public void saveOrUpdate(Subject subject) {
		if (subject != null && subject.getSubjectid() != null) {
			subject.setUpdate_id(UserContext.getLoginUser().getUserid());
			subject.setUpdate_time(new Timestamp(System.currentTimeMillis()));
			subjectDao.update(subject);
		}else if(subject != null && subject.getSubjectid() == null) {
			subject.setCreate_id(UserContext.getLoginUser().getUserid());
			subject.setCreate_time(new Timestamp(System.currentTimeMillis()));
			subjectDao.insert(subject);
		}
	}

	/**
	 * 删除科目
	 */
	
	public void deleteSubject(String subjectids) {
		if (subjectids != null && !"".equals(subjectids)) {
			subjectDao.deleteByIds(Subject.class, subjectids);
		}
	}

	/**
	 * 查询科目
	 */
	
	public List<Subject> querySubject(Long courseid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("courseid", courseid);
		List<Subject> list = subjectDao.queryByStatment("querySubject", param, pageBean);
		return list;
	}

	/**
	 * 根据课程ID，查询课程
	 */
	@Cacheable(value = "baseCache",key = "#courseid+'queryCourseById'")
	
	public Course queryCourseById(Integer courseid) {
		List<Course> courses = courseDao.queryByStatment("queryCourseById", courseid,null);
		if (courses != null && courses.size() > 0) {
			return courses.get(0);
		}
		return null;
	}

	/**
	 * 根据课程ID，查询科目，用逗号隔开
	 */
//	@Cacheable(value = "baseCache",key = "#courseid+'querySubjectByCourseId'")
	public Subject querySubjectByCourseId(Integer courseid) {
		List<Subject> subject = subjectDao.queryByStatment("querySubjectByCourseId", courseid,null);
		if (subject != null && subject.size() > 0) {
			return subject.get(0);
		}
		return null;
	}
 
}
