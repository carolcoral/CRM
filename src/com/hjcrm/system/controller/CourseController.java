package com.hjcrm.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjcrm.patter.entity.Pattertype;
import com.hjcrm.publics.constants.JumpViewConstants;
import com.hjcrm.publics.constants.ReturnConstants;
import com.hjcrm.publics.util.BaseController;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.Course;
import com.hjcrm.system.entity.Subject;
import com.hjcrm.system.service.ICourseService;

/**
 * 课程管理
 * @author likang
 * @date 2016-10-27 下午6:24:06
 */
@Controller
public class CourseController extends BaseController{
	
	@Autowired
	private ICourseService courseService;

	
	/**
	 * 跳转课程
	 * @param model
	 * @return
	 * @author likang 
	 * @date 2016-10-27 下午6:27:08
	 */
	@RequestMapping(value = "/system/courseMang.do",method = RequestMethod.GET)
	public String courseMang(Model model){
		if ((UserContext.getLoginUser() != null)) {
			return JumpViewConstants.SYSTEM_COURSE;
		}
		return ReturnConstants.USER_NO_LOGIN;
	}
	
	/**
	 * 跳转科目
	 * @param model
	 * @return
	 * @author likang 
	 * @date 2016-10-27 下午6:27:08
	 */
	@RequestMapping(value = "/system/subjectMang.do",method = RequestMethod.GET)
	public String subjectMang(Model model){
		if ((UserContext.getLoginUser() != null)) {
			return JumpViewConstants.SYSTEM_SUBJECT;
		}
		return ReturnConstants.USER_NO_LOGIN;
	}
	

	/**
	 * 跳转话术场景
	 * @param model
	 * @return
	 * @author likang 
	 * @date 2016-10-27 下午6:27:08
	 */
	@RequestMapping(value = "/system/patterType.do",method = RequestMethod.GET)
	public String patterType(Model model){
		if ((UserContext.getLoginUser() != null)) {
			return JumpViewConstants.SYSTEM_PATTERTYPE;
		}
		return ReturnConstants.USER_NO_LOGIN;
	}
	
	/**
	 * 查询所有课程
	 * @param request
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-10-27 下午6:29:03
	 */
	@RequestMapping(value = "/course/queryCourse.do",method = RequestMethod.GET)
	public @ResponseBody String queryCourse(HttpServletRequest request,Integer pageSize, Integer currentPage){
		List<Course> list = courseService.queryCourse(processPageBean(pageSize, currentPage));
		return jsonToPage(list);
	}
	
	
	/**
	 * 查询课程的场景
	 * @param request
	 * @param pageSize
	 * @param currentPage
	 * @param courseid 课程ID
	 * @return
	 * @author likang 
	 * @date 2016-10-27 下午6:29:03
	 */
	@RequestMapping(value = "/course/queryPattertype.do",method = RequestMethod.GET)
	public @ResponseBody String queryPattertype(HttpServletRequest request,Integer pageSize, Integer currentPage,String courseid){
		List<Pattertype> list = courseService.queryPattertype(processPageBean(pageSize, currentPage),courseid);
		return jsonToPage(list);
		
	}
	
	/**
	 * 增加或者修改课程
	 * @param request
	 * @param course
	 * @return
	 * @author likang 
	 * @date 2016-10-28 上午8:49:06
	 */
	@RequestMapping(value = "/course/addCourse.do",method = RequestMethod.POST)
	public @ResponseBody String addCourse(HttpServletRequest request,Course course){
		if (course != null) {
			courseService.saveOrUpdate(course);
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 增加或者修改课程场景
	 * @param request
	 * @param pattertype
	 * @return
	 * @author likang 
	 * @date 2016-10-28 上午8:49:06
	 */
	@RequestMapping(value = "/course/addPattertype.do",method = RequestMethod.POST)
	public @ResponseBody String addPattertype(HttpServletRequest request,Pattertype pattertype){
		if (pattertype != null) {
			courseService.saveOrUpdate(pattertype);
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 删除课程
	 * @param request
	 * @param courseid
	 * @return
	 * @author likang 
	 * @date 2016-10-28 上午8:52:34
	 */
	@RequestMapping(value = "/course/deleteCourse.do",method = RequestMethod.POST)
	public @ResponseBody String deleteCourse(HttpServletRequest request,String courseids){
		if (courseids != null && !"".equals(courseids)) {
			courseService.deletePattertype(courseids, null);//先删除课程的场景
			courseService.deleteCourse(courseids);//再删除课程
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	/**
	 * 删除课程场景
	 * @param request
	 * @param patterTypeIds
	 * @return
	 * @author likang 
	 * @date 2016-10-28 上午8:52:34
	 */
	@RequestMapping(value = "/course/deletepatterType.do",method = RequestMethod.POST)
	public @ResponseBody String deletepatterType(HttpServletRequest request,String patterTypeIds){
		if (patterTypeIds != null && !"".equals(patterTypeIds)) {
			courseService.deletePattertype(null, patterTypeIds);//先删除课程的场景
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 增加/修改科目
	 * @param request
	 * @param subject
	 * @return
	 * @author likang 
	 * @date 2016-10-31 下午6:28:51
	 */
	@RequestMapping(value = "/subject/addOrUpdateSubject.do",method = RequestMethod.POST)
	public @ResponseBody String addOrUpdateSubject(HttpServletRequest request,Subject subject){
		if (subject != null && UserContext.getLoginUser() != null) {
			courseService.saveOrUpdate(subject);
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 删除科目，支持批量删除，用逗号隔开
	 * @param request
	 * @param subjectids
	 * @return
	 * @author likang 
	 * @date 2016-10-31 下午6:31:00
	 */
	@RequestMapping(value = "/subject/deleteSubject.do",method = RequestMethod.POST)
	public @ResponseBody String deleteSubject(HttpServletRequest request,String subjectids){
		if (subjectids != null && !"".equals(subjectids) && UserContext.getLoginUser() != null) {
			courseService.deleteSubject(subjectids);
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 查询科目
	 * @param request
	 * @param courseid 课程ID
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-10-31 下午6:34:36
	 */
	@RequestMapping(value = "/subject/querySubject.do",method = RequestMethod.GET)
	public @ResponseBody String querySubject(HttpServletRequest request,Long courseid,Integer pageSize, Integer currentPage){
		List<Subject> list = courseService.querySubject(courseid, processPageBean(pageSize, currentPage));
		return jsonToPage(list);
	}
	
	
}
