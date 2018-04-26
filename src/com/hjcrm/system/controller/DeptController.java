package com.hjcrm.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjcrm.publics.constants.JumpViewConstants;
import com.hjcrm.publics.constants.ReturnConstants;
import com.hjcrm.publics.logs.controller.AfterMethodLog;
import com.hjcrm.publics.util.BaseController;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.Dept;
import com.hjcrm.system.service.IDeptService;


/**
 * 部门管理控制类
 * @author likang
 * @date 2016-10-18 下午4:06:53
 */
@Controller
public class DeptController extends BaseController{

	
	@Autowired
	private IDeptService deptService;
	
	/**
	 * 跳转部门管理界面
	 * @param model
	 * @return
	 * @author likang 
	 * @date 2016-10-18 上午11:26:37
	 */
	@RequestMapping(value = "/system/deptMang.do",method = RequestMethod.GET)
	public String index(Model model){
		if (UserContext.getLoginUser() != null) {
			return JumpViewConstants.SYSTEM_DEPT_MANAGE;
		}
		return ReturnConstants.USER_NO_LOGIN;
	}
	
	
	/**
	 * 查询所有部门
	 * @param request
	 * @param pageSize 页面数量
	 * @param currentPage 当前页数
	 * @return
	 * @author likang 
	 * @date 2016-10-18 下午4:56:23
	 */
	@RequestMapping(value = "/dept/queryDept.do",method = RequestMethod.GET)
	public @ResponseBody String queryDept(HttpServletRequest request,Integer pageSize, Integer currentPage){
		List<Dept> list = deptService.queryDept(processPageBean(pageSize, currentPage));
		return jsonToPage(list);
	}
	
	
	/**
	 * 保存或者修改部门
	 * @param dept
	 * @param request
	 * @return
	 * @author likang 
	 * @date 2016-10-18 下午4:59:19
	 */
	@RequestMapping(value = "/dept/saveOrUpdate.do",method = RequestMethod.POST)
	public @ResponseBody String saveOrUpdate(Dept dept,HttpServletRequest request){
		if (dept != null) {
			deptService.saveOrUpdate(dept);
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 删除部门
	 * @param ids
	 * @param request
	 * @return
	 * @author likang 
	 * @date 2016-10-18 下午5:02:31
	 */
	@RequestMapping(value = "/dept/delete.do",method = RequestMethod.POST)
	public @ResponseBody String delete(String ids,HttpServletRequest request){
		if (ids != null) {
			deptService.delete(ids);
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	
}
