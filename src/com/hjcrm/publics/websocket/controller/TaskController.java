package com.hjcrm.publics.websocket.controller;

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
import com.hjcrm.publics.util.BaseController;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.resource.entity.Resource;
import com.hjcrm.resource.service.ITaskService;
import com.hjcrm.system.entity.Menu;
import com.hjcrm.system.entity.User;
import com.hjcrm.system.service.IMenuService;

@Controller
public class TaskController extends BaseController{
	
	
	
	
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IMenuService menuService;
	
	
	/**
	 * 跳转资源管理(销售部)
	 * @param model
	 * @return
	 * @author likang 
	 * @date 2016-12-26 上午11:47:15
	 */
	@RequestMapping(value = "/task/resources.do",method = RequestMethod.GET)
	public String salesIndex(Model model){
		User user = UserContext.getLoginUser();
		if (user != null) {
			Long roleid = user.getRoleid();
			boolean isopen = isSholdOpenMenu(roleid,"/sales/resourcesMang.do");
			if (isopen) {
				List<Menu> menus = menuService.queryMenuList(UserContext.getLoginUser().getRoleid());
				model.addAttribute("menus", menus);
				return JumpViewConstants.RESOURCE_SALES;
			}else{
				return ReturnConstants.USER_NO_OPEN;//用户无权限操作并打开，如果需要此权限，请联系运营部
			}
		}
		return ReturnConstants.USER_NO_LOGIN;//用户未登录，请刷新
	}
	

	/**
	 * 根据条件进行查询资源--筛选导出
	 * @param request
	 * @param resource
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-11-4 下午5:30:46
	 */
	@RequestMapping(value = "/task/queryTaskResources.do",method = RequestMethod.GET)
	public @ResponseBody String queryTaskResources(HttpServletRequest request,String resourceIds,String userid,String roleid,String deptid,Integer pageSize,Integer currentPage){
		if (resourceIds != null && !"".equals(resourceIds.trim())) {
			List<Resource> list = taskService.queryTaskResourceList(deptid,userid, roleid,resourceIds, processPageBean(pageSize, currentPage));
			return jsonToPage(list);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
}
