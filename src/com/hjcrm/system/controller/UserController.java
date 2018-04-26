package com.hjcrm.system.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjcrm.publics.constants.JumpViewConstants;
import com.hjcrm.publics.constants.ReturnConstants;
import com.hjcrm.publics.logs.controller.AfterMethodLog;
import com.hjcrm.publics.logs.controller.BeforeMethodLog;
import com.hjcrm.publics.util.BaseController;
import com.hjcrm.publics.util.MD5Tools;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.Menu;
import com.hjcrm.system.entity.User;
import com.hjcrm.system.service.IMenuService;
import com.hjcrm.system.service.IUserLoginService;
import com.hjcrm.system.service.IUserService;

/**
 * 用户管理控制类
 * @author likang
 * @date 2016-10-17 上午11:15:30
 */
@Controller
public class UserController extends BaseController {
	
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserLoginService userLoginService;
	@Autowired
	private IMenuService menuService;
	
	/**
	 * 跳转用户管理界面
	 * @param model
	 * @return
	 * @author likang 
	 * @date 2016-10-18 上午11:26:37
	 */
	@RequestMapping(value = "/system/userMang.do",method = RequestMethod.GET)
	public String index(Model model){
		if (UserContext.getLoginUser() != null) {
//			List<Menu> menus = menuService.queryMenuByUserid(UserContext.getLoginUser().getUserid());
//			model.addAttribute("menus", menus);
			return JumpViewConstants.SYSTEM_USER_MANAGE;
		}
		return ReturnConstants.USER_NO_LOGIN;
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param userid 用户id
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @return
	 * @author likang 
	 * @date 2016-10-18 上午10:05:31
	 */
	@RequestMapping(value = "/system/editPassword.do",method = RequestMethod.POST)
	public @ResponseBody String editPassword(HttpServletRequest request,Long userid,String oldPassword,String newPassword){
		if (userid != null) {
			if(!StringUtils.hasText(oldPassword) || !StringUtils.hasText(newPassword)){
				//提交参数不合法
				return ReturnConstants.PARAM_NULL;
			}else{
				try{
					User user = userService.queryByIdentity(userid);
					if(user != null){
						if(!MD5Tools.encode(oldPassword).equals(user.getPassword())){
							//密码不匹配
							return ReturnConstants.OLD_PASSWORD_NOT_SAME;
						}else{
							user.setPassword(newPassword);
							user.setIschange(1);
							userService.saveOrUpdate(user);
							//修改成功
							return  ReturnConstants.SUCCESS;
						}
					}else{
						//用户不存在
						return ReturnConstants.USER_NOT_EXIST;
					}
				}catch(Exception e){
					e.printStackTrace();
					//修改失败
					return ReturnConstants.ERROR;
				}
			}
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	/**
	 * 获取用户列表/根据用户手机号码，邮箱，查询符合条件的用户
	 * @param user
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-10-18 上午10:22:43
	 */
	@RequestMapping(value = "/system/userlist.do", method = RequestMethod.GET)
	public @ResponseBody String userList(User user,Integer pageSize, Integer currentPage){
		if (UserContext.getLoginUser() != null) {
			List<User> queryUserList = userService.queryUserList(user, processPageBean(pageSize, currentPage));
			return jsonToPage(queryUserList) ;
		}
		return ReturnConstants.USER_NO_LOGIN;
	}
	
	/**
	 * 添加/修改用户
	 * @param user
	 * @param request
	 * @return
	 * @author likang 
	 * @date 2016-10-18 上午11:55:16
	 */
	@RequestMapping(value = "/system/saveOrUpdate.do", method = RequestMethod.POST)
	public @ResponseBody String saveOrUpdate(String username,User user,HttpServletRequest request){
		if(user != null && user.getEmail() != null){
			if (user.getUserid() != null) {//修改
				userService.saveOrUpdate(user);
				return ReturnConstants.SUCCESS;
			}else{
				boolean isExist = userService.authPhoneOrEmailIsExist(user.getPhone(),user.getEmail());
				if(isExist){
					return ReturnConstants.USER_YES_EXIST;//用户已经存在
				}else{
					try{
						userService.saveOrUpdate(user);
						return ReturnConstants.SUCCESS;
					}catch(Exception e){
						e.printStackTrace();
						return e.getMessage();
					}
				}
			}
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 删除用户
	 * @param ids (可批量删除，用,隔开)
	 * @return
	 * @author likang 
	 * @date 2016-10-18 下午1:51:13
	 */
	@RequestMapping(value = "/system/deleteUser.do", method = RequestMethod.POST)
	public @ResponseBody String deleteUser(HttpServletRequest request,String ids) {
		if (ids != null) {
			try {
				userService.delete(ids);
				return ReturnConstants.SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	@RequestMapping(value = "/system/test.do", method = RequestMethod.GET)
	public @ResponseBody String test(HttpServletRequest request,String id){
		userService.queryusertestByid(id);
		return ReturnConstants.SUCCESS;
	}
	
	
	
	
	
}
