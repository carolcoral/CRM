package com.hjcrm.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjcrm.publics.constants.JumpViewConstants;
import com.hjcrm.publics.constants.ReturnConstants;
import com.hjcrm.publics.exception.BusinessException;
import com.hjcrm.publics.logs.controller.AfterMethodLog;
import com.hjcrm.publics.util.BaseController;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.Menu;
import com.hjcrm.system.service.IMenuService;

/**
 * 菜单功能控制类
 * @author likang
 * @date 2016-10-17 上午8:57:47
 */
@Controller
public class MenuController extends BaseController {

	@Autowired
	private IMenuService menuService;
	
	
	
	/**
	 * 跳转菜单管理界面
	 * @param model
	 * @return
	 * @author likang 
	 * @date 2016-10-18 上午11:26:37
	 */
	@RequestMapping(value = "/system/menuMang.do",method = RequestMethod.GET)
	public String index(Model model){
		if (UserContext.getLoginUser() != null) {
//			List<Menu> menus = menuService.queryMenuByUserid(UserContext.getLoginUser().getUserid());
//			model.addAttribute("menus", menus);
			return JumpViewConstants.SYSTEM_MENU_MANAGE;
		}
		return ReturnConstants.USER_NO_LOGIN;
	}
	
	/**
	 * 新增保存/修改菜单
	 * @param request
	 * @param menu
	 * @return
	 * @author likang 
	 * @date 2016-10-17 上午9:14:51
	 */
	@RequestMapping(value = "/menu/addOrUpdateMenu.do",method = RequestMethod.POST)
	public @ResponseBody String addOrUpdateMenu(HttpServletRequest request,Menu menu){
		if (menu != null) {
			menuService.saveOrUpdate(menu);
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 删除菜单
	 * @author likang	
	 * @param ids
	 * @return
	 * 2016-10-17 上午9:14:51
	 */
	@RequestMapping(value = "/menu/delete.do", method = RequestMethod.POST)
	public @ResponseBody String delete(String ids) {
		try {
			StringBuffer error1 = new StringBuffer("");
			StringBuffer error2 = new StringBuffer("");
			boolean hasChildError = false;
			boolean hasMenuError = false;
			for (String id : ids.split(",")) {
				if (menuService.hasChild(Long.valueOf(id))) {
					if (!hasChildError)
						hasChildError = true;
					if (error1.length() == 0)
						error1.append("菜单[");
					error1.append(menuService.queryById(Long.valueOf(id)).getMenuname()).append(",");
					continue;
				}
				if (menuService.hasLinked4Role(Long.valueOf(id))) {
					if (!hasMenuError)
						hasMenuError = true;
					if (error2.length() == 0)
						error2.append("菜单[");
					error2.append(menuService.queryById(Long.valueOf(id)).getMenuname()).append(",");
					continue;
				}
				menuService.delete(id);
			}
			if (hasChildError)
				error1.deleteCharAt(error1.length() - 1).append("]存在下级菜单");
			if (hasMenuError)
				error2.deleteCharAt(error2.length() - 1).append("]存在角色关联");
			
			String msg = (hasChildError ? error1.toString() : "")+ (hasMenuError ? (hasChildError ? "，<br/>" : "")+ error2.toString() : "") + "，不能删除";
			if (StringUtils.hasText(msg) && (hasChildError || hasMenuError)){
				return msg;
			}else{
				return ReturnConstants.SUCCESS; 
			}
		} catch (BusinessException e) {
			return e.getMessage();
		}
	}
	
	/**
	 * 根据用户ID，查询用户权限的菜单
	 * @param request
	 * @param userid
	 * @return
	 * @author likang 
	 * @date 2016-10-17 上午9:20:18
	 */
	@RequestMapping(value = "/menu/queryMenuByUserid.do", method = RequestMethod.GET)
	public @ResponseBody String queryMenuByUserid(HttpServletRequest request, Long userid){
		if (userid != null) {
			List<Menu> list = menuService.queryMenuByUserid(userid);
			return json(list);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 查询加载所有菜单列表
	 * @param request
	 * @return
	 * @author likang 
	 * @date 2016-10-20 上午9:40:23
	 */
	@RequestMapping(value = "/menu/queryAllMenu.do", method = RequestMethod.GET)
	public @ResponseBody String queryAllMenu(HttpServletRequest request){
		List<Menu> list = menuService.queryAllMenu();
		return json(list);
	}
	
	
}
