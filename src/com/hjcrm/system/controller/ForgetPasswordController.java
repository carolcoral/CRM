package com.hjcrm.system.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjcrm.publics.constants.JumpViewConstants;
import com.hjcrm.publics.constants.ReturnConstants;
import com.hjcrm.publics.email.EmailSendTool;
import com.hjcrm.publics.logs.controller.AfterMethodLog;
import com.hjcrm.publics.util.BaseController;
import com.hjcrm.publics.util.DateUtil;
import com.hjcrm.publics.util.EmailConfig;
import com.hjcrm.publics.util.MD5Tools;
import com.hjcrm.system.entity.Forget;
import com.hjcrm.system.entity.User;
import com.hjcrm.system.service.IForgetService;
import com.hjcrm.system.service.IUserService;

/**
 * 忘记密码
 * @author likang
 * @date 2016-10-19 上午10:37:13
 */
@Controller
public class ForgetPasswordController extends BaseController{

	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IForgetService forgetService;
	
	/**
	 * 跳转忘记密码收入邮箱页面
	 * @param model
	 * @return
	 * @author likang 
	 * @date 2016-10-26 上午9:56:29
	 */
	@RequestMapping(value = "/forget/indexEmail.do",method = RequestMethod.GET)
	public String indexEmail(Model model){
		return JumpViewConstants.SYSTEM_RESET_POSSWORD_INDEX;
	}
	
	
	/**
	 * 忘记密码--发送邮件链接
	 * @param request
	 * @param email
	 * @return
	 * @author likang 
	 * @date 2016-10-19 上午10:38:51
	 */
	@RequestMapping(value = "/forget/sendEmail.do",method = RequestMethod.POST)
	public @ResponseBody String sendEmail(HttpServletRequest request,String email){
		
		if (email != null && !"".equals(email)) {
			User user = userService.queryUserByPhoneOrEmail(null, email);
			if (user == null) {
				return ReturnConstants.USER_NOT_EXIST;//用户不存在
			}
			//保存创建邮箱链接和链接信息表
			Forget forget = new Forget();
			forget.setUserid(user.getUserid());//用户id
			forget.setEmail(user.getEmail());//邮箱
			forget.setCreate_time(new Timestamp(System.currentTimeMillis()));//创建时间
			forget.setExpiration_time(Timestamp.valueOf(DateUtil.format(DateUtil.getDayAfter(1))));//过期时间
			forget.setRandomcode(MD5Tools.encode(email + System.currentTimeMillis()));//随机MD5值
			forget.setLinknumber(0);//链接点击次数   最大值10次  
			forgetService.saveForget(forget);
			
			//发送邮箱链接邮件
			StringBuffer url = request.getRequestURL();
			String path = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();  
			String conurl = path +  "/forget/resetLink.do?sid=" + forget.getRandomcode() + "&email=" + email;//链接地址
//			String emailContent = "请勿回复本邮件.点击下面的链接,重设密码<br/><a href="+conurl +" target='_BLANK'>点击我重新设置密码</a>" +
//	                    "<br/>tips:本邮件超过24小时,链接将会失效，需要重新申请'找回密码'";//邮件正文
			String emailContent = "<html>"
					+"<head>"
					+"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"
					+"</head>"
					+"<body>"
					+" 请勿回复本邮件.点击下面的链接,重设密码 "
					+"<br/>"
					+"    <a href= '"+conurl+"' target='_BLANK'>点击我重新设置密码</a> "
					+"<br/>"
					+"  本邮件超过24小时,链接将会失效，需要重新申请 "
					+"</body>"
					+"</html>";//邮件正文
			
			String subject = EmailConfig.getValue("subject");//主题
			String port =  EmailConfig.getValue("port");//发送方服务器
			String fromemail =  EmailConfig.getValue("fromemail");//发送方
			String frompassword =  EmailConfig.getValue("frompassword");//发送方邮箱密码
			String fromemailname =  EmailConfig.getValue("fromemailname");//接收方显示的发送人名称
			
			EmailSendTool sendEmail = new EmailSendTool(port,fromemail, frompassword, email,subject, emailContent, fromemailname, "", "");
			try {
				sendEmail.send();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;//请输入邮箱用户
	}
	
	/**
	 * 验证点击的邮件链接是否正确，并跳转重置密码界面
	 * @param request
	 * @param email
	 * @param sid
	 * @return
	 * @author likang 
	 * @date 2016-10-19 下午2:23:10
	 */
	@RequestMapping(value = "/forget/resetLink.do",method = RequestMethod.GET)
	public String resetLink(Model model,HttpServletRequest request,String email,String sid){
		if (sid != null && !"".equals(sid) && email != null && !"".equals(email)) {
			
			User user = userService.queryUserByPhoneOrEmail(null, email);
			if (user == null) {
				return ReturnConstants.USER_NOT_EXIST;//用户不存在
			}
			Forget forget = forgetService.queryForget(user.getUserid(), email);
			if (forget != null) {
				Timestamp outDate = forget.getExpiration_time();//过期时间
				if(outDate.getTime() <= System.currentTimeMillis()){
					return ReturnConstants.FAILED;	//表示已经过期
				}					
				if (forget.getLinknumber() >= 10) {
					return ReturnConstants.FAILED;	//表示链接地址已经失效
				}
				String key = forget.getRandomcode();
				if (!key.equals(sid)) {
					return ReturnConstants.FAILED;	//表示链接地址已经失效
				}
				forget.setLinknumber(forget.getLinknumber() + 1);
				forgetService.updateForget(forget);
				//跳转重置密码界面
				return JumpViewConstants.SYSTEM_RESET_POSSWORD;
			}
		}
		return ReturnConstants.PARAM_NULL;//链接地址不正确
	}
	
	/**
	 * 重置密码
	 * @param request
	 * @param email
	 * @param newPassword
	 * @return
	 * @author likang 
	 * @date 2016-10-19 下午2:26:27
	 */
	@RequestMapping(value = "/forget/resetPassword.do",method = RequestMethod.POST)
	public @ResponseBody String resetPassword(HttpServletRequest request,String email,String newPassword){
		if (email != null && !"".equals(email) && newPassword != null && !"".equals(newPassword)) {
			User user = userService.queryUserByPhoneOrEmail(null, email);
			if (user != null) {
				user.setPassword(newPassword);
				userService.saveOrUpdate(user);
				return ReturnConstants.SUCCESS;
			}
			return ReturnConstants.USER_NOT_EXIST;//用户不存在 
		}
		return ReturnConstants.PARAM_NULL;//输入错误
	}
	
	
	
	
}
