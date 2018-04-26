package com.hjcrm.system.controller;

import java.sql.Timestamp;
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
import com.hjcrm.system.entity.Systemmessage;
import com.hjcrm.system.service.ISystemMessageService;

/**
 * 系统消息
 * @author likang
 * @date 2016-12-1 下午4:46:13
 */
@Controller
public class SystemMessageController extends BaseController{
	
	@Autowired
	private ISystemMessageService messageService;
	
	
	
	
	/**
	 * 跳转消息管理界面
	 * @param model
	 * @return
	 * @author likang 
	 * @date 2016-10-18 上午11:26:37
	 */
	@RequestMapping(value = "/system/systemMessage.do",method = RequestMethod.GET)
	public String index(Model model){
		if (UserContext.getLoginUser() != null) {
			return JumpViewConstants.SYSTEM_MESSAGE;
		}
		return ReturnConstants.USER_NO_LOGIN;
	}
	
	
	/**
	 * 增加或者修改消息
	 * @param request
	 * @param message
	 * @return
	 * @author likang 
	 * @date 2016-12-1 下午5:15:16
	 */
	@RequestMapping(value = "/system/saveOrUpdateMessage.do",method = RequestMethod.POST)
	public @ResponseBody String saveOrUpdateMessage(HttpServletRequest request,Systemmessage message){
		if (message != null) {
			messageService.saveOrUpdate(message);
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	/**
	 * 查询最近一条发布信息
	 * @param request
	 * @return
	 * @author likang 
	 * @date 2016-12-1 下午5:16:38
	 */
	@RequestMapping(value = "/system/querySystemmessageSend.do",method = RequestMethod.GET)
	public @ResponseBody String querySystemmessageSend(HttpServletRequest request){
		List<Systemmessage> list = messageService.querySystemmessageSend();
		if (list != null && list.size() > 0) {
			return jsonToPage(list.get(0));
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	/**
	 * 查询所有系统消息
	 * @return
	 * @author likang 
	 * @date 2016-12-1 下午5:11:26
	 */
	@RequestMapping(value = "/system/querySystemmessages.do",method = RequestMethod.GET)
	public @ResponseBody String querySystemmessages(HttpServletRequest request,Integer pageSize, Integer currentPage){
		List<Systemmessage> list = messageService.querySystemmessages(processPageBean(pageSize, currentPage));
		return jsonToPage(list);
	}
	
	/**
	 * 发布或者撤回消息
	 * @param systemmessageId
	 * @param issend
	 * @author likang 
	 * @date 2016-12-1 下午4:58:56
	 */
	@RequestMapping(value = "/system/sendMessage.do",method = RequestMethod.GET)
	public @ResponseBody String sendMessage(HttpServletRequest request,String systemmessageId,String issend){
		if (systemmessageId != null && !"".equals(systemmessageId) && issend != null && !"".equals(issend.trim())) {
			 messageService.sendMessage(systemmessageId,issend,new Timestamp(System.currentTimeMillis()));
			 return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	
	
	
	
	
	
	

}
