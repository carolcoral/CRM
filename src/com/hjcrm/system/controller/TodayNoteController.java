package com.hjcrm.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjcrm.publics.constants.ReturnConstants;
import com.hjcrm.publics.util.BaseController;
import com.hjcrm.system.entity.Todaynote;
import com.hjcrm.system.service.ITodayNoteService;

/**
 * 今日目标
 * @author likang
 * @date 2016-10-25 下午4:16:43
 */
@Controller
public class TodayNoteController extends BaseController{
	
	
	@Autowired
	private ITodayNoteService todaynoteService;
	
	/**
	 * 增加/修改今日目标
	 * @param request
	 * @param note
	 * @author likang 
	 * @date 2016-10-25 下午4:18:01
	 */
	@RequestMapping(value = "/system/addOrUpdateTodayNote.do",method = RequestMethod.POST)
	public @ResponseBody String addOrUpdateTodayNote(HttpServletRequest request,Todaynote todaynote){
		if (todaynote != null) {
			todaynoteService.saveOrUpdate(todaynote);
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	/**
	 * 查询今日目标
	 * @param request
	 * @param userid
	 * @return
	 * @author likang 
	 * @date 2016-10-25 下午4:31:12
	 */
	@RequestMapping(value = "/system/queryTodaynote.do",method = RequestMethod.GET)
	public @ResponseBody String queryTodaynote(HttpServletRequest request,Long userid){
		List<Todaynote> list = 	todaynoteService.queryTodaynote(userid);
		return json(list);
	}
	
	
	
}
