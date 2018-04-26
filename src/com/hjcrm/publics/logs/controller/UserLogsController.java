package com.hjcrm.publics.logs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjcrm.publics.logs.entity.Userlogs;
import com.hjcrm.publics.logs.service.ILogsService;
import com.hjcrm.publics.util.BaseController;


/**
 * 用户操作监控控制类
 * @author likang
 * @date 2016-10-24 上午10:16:30
 */
@Controller
public class UserLogsController extends BaseController{
	
	@Autowired
	private ILogsService userLogsService;

	/**
	 * 查询用户擦操作
	 * @param userid 用户id
	 * @param request
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-10-24 上午10:18:08
	 */
	@RequestMapping(value = "/logs/queryUserLogs.do",method = RequestMethod.GET)
	public @ResponseBody String queryUserLogs(Long userid,HttpServletRequest request,Integer pageSize, Integer currentPage){
		List<Userlogs> list = userLogsService.queryUserLogs(userid,processPageBean(pageSize, currentPage));
		return jsonToPage(list);
	}
	
	
}
