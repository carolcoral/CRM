package com.hjcrm.system.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hjcrm.publics.constants.JumpViewConstants;
import com.hjcrm.publics.constants.ReturnConstants;
import com.hjcrm.publics.util.BaseController;
import com.hjcrm.publics.util.ContextUtil;
import com.hjcrm.publics.util.MD5Tools;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.Menu;
import com.hjcrm.system.entity.User;
import com.hjcrm.system.service.IMenuService;
import com.hjcrm.system.service.IUserLoginService;


/**
 * CRM系统用户登录控制类 
 * @author likang
 * @date 2016-10-13 上午10:56:31
 */
@Controller
public class UserLoginController extends BaseController {
	
	@Autowired
	private IUserLoginService userLoginService;
	@Autowired
	private IMenuService menuService;
	
	
	
	/**
	 * 网站前台登录跳转界面
	 * @author likang	
	 * @return
	 * 2016年10月13日11:50:46
	 */
	@RequestMapping(value="/login.do",method = RequestMethod.GET)
	public String login() {
		if (UserContext.getLoginUser() != null) {
			return "redirect:/main.do";
		}
		return JumpViewConstants.SYSTEM_LOGIN;
	}
	
	public static final String COOKIE_KEY = "auth_key";//登录时cookie的key 
	public static final String COOKIE_KEY_SEPARATE = "_#_";//登录时的cookie的分隔符
	
	
	/**
	 * 用户登录(暂时只支持邮箱登陆)
	 * @param email 邮箱
	 * @param phone 手机号码
	 * @param password 密码
	 * @param model
	 * @param request
	 * @param sign 0   1  
	 * @return
	 * @author likang 
	 * @throws UnsupportedEncodingException 
	 * @date 2016-10-13 上午11:15:59
	 */
	@RequestMapping(value="/login.do",method = RequestMethod.POST)
	public String login(String sign,String email,String phone,String password,Model model,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		if (email != null && !"".equals(email) && sign != null && !"".equals(sign.trim())) {
			if ("0".equals(sign.trim())) {
				email = email + ContextUtil.getInitConfig("email_suffix");//获取邮箱后缀
			}else if("1".equals(sign.trim())){
				email = email + ContextUtil.getInitConfig("email_miduo_suffix");//获取邮箱后缀
			}
			User user = userLoginService.authLoginNameIsExist(email, null);
			if (user == null) {
				model.addAttribute("msg", ReturnConstants.USER_NOT_EXIST);// 用户不存在或者用户名错误
				return JumpViewConstants.SYSTEM_LOGIN;
			}
			Long userid = user.getUserid();
			Integer ischange = user.getIschange();
			boolean isMatch = userLoginService.isPasswordMatch(userid, password);
			if (!isMatch) {
				model.addAttribute("msg", ReturnConstants.PASSWORD_ERROR);// 密码错误
				return JumpViewConstants.SYSTEM_LOGIN;
			}
			UserContext.setLoginUser(user);
			
			//设置Cookies
			Cookie cookie = new Cookie(COOKIE_KEY,URLEncoder.encode(email, "UTF-8")+COOKIE_KEY_SEPARATE+MD5Tools.encode(password));
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			response.addCookie(cookie);
			
			request.getSession(true).setAttribute("ischange",ischange == null ?0:ischange);
			request.getSession(true).setAttribute("loginName",UserContext.getLoginUser().getUsername());
			return "redirect:/main.do";// 登陆成功
		}
		return ReturnConstants.PARAM_NULL;

	}
	
	
	/**
	 * 后台主页
	 * @author likang	
	 * @param model
	 * @return
	 * 2016-10-13 上午11:15:59
	 */
	@RequestMapping(value="/main.do",method = RequestMethod.GET)
	public String main(Model model) {
		if (UserContext.getLoginUser() != null) {
			List<Menu> menus = menuService.queryMenuList(UserContext.getLoginUser().getRoleid());
			model.addAttribute("menus", menus);
			return JumpViewConstants.SYSTEM_INDEX;
		}
		return JumpViewConstants.SYSTEM_LOGIN;
	}
	
	/**
	 * 后台登录跳出
	 * @return
	 * @author likang 
	 * @date 2016-10-14 上午8:15:16
	 */
	@RequestMapping(value="/logout.do",method = RequestMethod.GET)
	public String layout(HttpServletRequest request,HttpServletResponse response) {
		UserContext.clearLoginUser();
		Cookie cookie = new Cookie(COOKIE_KEY,null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		Cookie cookieJSESSIONID = new Cookie("JSESSIONID",null);
		String contextPath = request.getContextPath();
		cookieJSESSIONID.setMaxAge(0);
		cookieJSESSIONID.setPath(contextPath+"/");
		response.addCookie(cookieJSESSIONID);
		request.getSession(true).removeAttribute("loginName");
		return "redirect:/main.do";
	}

}
