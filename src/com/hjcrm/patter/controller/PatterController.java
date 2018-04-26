package com.hjcrm.patter.controller;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjcrm.patter.entity.Patter;
import com.hjcrm.patter.entity.Pattertype;
import com.hjcrm.patter.service.IPatterService;
import com.hjcrm.publics.constants.JumpViewConstants;
import com.hjcrm.publics.constants.ReturnConstants;
import com.hjcrm.publics.util.BaseController;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.Course;

/**
 * 话术
 * @author likang
 * @date 2016-10-27 下午3:04:39
 */
@Controller
public class PatterController extends BaseController{
	
	
	@Autowired
	private IPatterService patterService;
	
	/**
	 * 跳转
	 * @param model
	 * @return
	 * @author likang 
	 * @date 2016-10-27 下午3:06:37
	 */
	@RequestMapping(value = "/patter/myPatter.do",method = RequestMethod.GET)
	public String index(Model model){
		return JumpViewConstants.SYSTEM_PATTER;
	}
	
	/**
	 * 查询课程和课程下的场景
	 * @param request
	 * @param userid
	 * @return
	 * @author likang 
	 * @date 2016-10-27 下午4:50:28
	 */
	@RequestMapping(value = "/patter/queryPattertype.do",method = RequestMethod.GET)
	public @ResponseBody String queryPattertype(HttpServletRequest request,Long userid,String courseid,Integer pageSize, Integer currentPage){
		List<Pattertype> list = patterService.queryPattertype(null, null, null, courseid, null);
		return jsonToPage(list);
	}
	
	/**
	 * 查询话术内容
	 * @param request
	 * @param userid 当前用户id
	 * @param patterTypeId 场景ID
	 * @param courseid 课程ID
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-10-27 下午4:55:22
	 */
	@RequestMapping(value = "/patter/queryPatter.do",method = RequestMethod.GET)
	public @ResponseBody String queryPatter(HttpServletRequest request,Long userid,Long patterTypeId,Long courseid,Integer pageSize, Integer currentPage){
		if (patterTypeId != null && userid != null) {
			List<Patter> list = patterService.queryPatter(null, userid, patterTypeId, courseid, processPageBean(pageSize, currentPage));
			return jsonToPage(list);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 增加或者修改话术内容
	 * @param request
	 * @param patter
	 * @return
	 * @author likang 
	 * @date 2016-10-27 下午4:59:25
	 */
	@RequestMapping(value = "/patter/saveOrUpdatePatter.do",method = RequestMethod.POST) 
	public @ResponseBody String saveOrUpdatePatter(HttpServletRequest request,Patter patter){
		if (patter != null && patter.getUserid() != null) {
			patterService.saveOrUpdate(patter);
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 删除话术内容
	 * @param request
	 * @param patterids
	 * @param userid 话术创建人ID
	 * @return
	 * @author likang 
	 * @date 2016-10-27 下午5:03:50
	 */
	@RequestMapping(value = "/patter/deletePatter.do",method = RequestMethod.POST)
	public @ResponseBody String deletePatter(HttpServletRequest request,String patterids,Long userid){
		if (UserContext.getLoginUser() != null) {
			Long loginuserid = UserContext.getLoginUser().getUserid();
			if (loginuserid.longValue() != (userid == null?-1:userid.longValue())) {
				return ReturnConstants.lOGINUSER_NO_CREATERUSER;//当前登录用户不是数据创建人
			}
			if (patterids != null && !"".equals(patterids) && userid != null) {
				patterService.deletePatter(patterids);
				return ReturnConstants.SUCCESS;
			}
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 共享话术
	 * @param request
	 * @param isshare 0 不共享  1共享
	 * @param patterid 话术ID
	 * @return
	 * @author likang 
	 * @date 2016-10-28 下午2:32:24
	 */
	@RequestMapping(value = "/patter/updatePatterIsShare.do",method = RequestMethod.POST)
	public @ResponseBody String updatePatterIsShare(HttpServletRequest request,Integer isshare,Long patterid){
		if (patterid != null) {
			patterService.updatePatterIsShare(patterid, 1);
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	
	
	
	
}
