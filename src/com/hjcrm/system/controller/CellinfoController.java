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
import com.hjcrm.system.entity.Cellinfo;
import com.hjcrm.system.service.ICellinfoService;

/**
 * 
 * @author likang
 * @date 2016-12-2 上午10:27:19
 */
@Controller
public class CellinfoController extends BaseController{
	
	@Autowired
	private ICellinfoService cellinfoService;
	
	
	/**
	 * 保存/修改列名
	 * @param request
	 * @param userid
	 * @param cellmenu
	 * @param cellnames
	 * @return
	 * @author likang 
	 * @date 2016-12-2 上午10:30:03
	 */
	@RequestMapping(value = "/cellinfo/saveOrUpdateCellinfo.do",method = RequestMethod.POST)
	public @ResponseBody String saveOrUpdateCellinfo(HttpServletRequest request,Long userid,String cellmenu,String cellnamesjson,String cellcodesjson){
		if (userid != null && cellmenu != null && !"".equals(cellmenu.trim()) && cellnamesjson != null && !"".equals(cellnamesjson.trim())) {
			String[] cellnames = cellnamesjson.split(",");
			cellinfoService.deleteCellinfo(userid, cellmenu);
			for (int i = 0; i < cellnames.length; i++) {
				String[] cellcodes = cellnames[i].split("\\|"); 
				Cellinfo cellinfo = new Cellinfo();
				cellinfo.setCellno(i);
				cellinfo.setCellname(cellcodes[1]);
				cellinfo.setCelluserid(userid);
				cellinfo.setCellmenu(cellmenu);
				cellinfo.setCellcode(cellcodes[0]);
				cellinfoService.saveOrUpdate(cellinfo);
			}
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	
	/**
	 * 查询用户的菜单列名
	 * @param request
	 * @param userid
	 * @param cellmenu
	 * @return
	 * @author likang 
	 * @date 2016-12-2 上午11:05:48
	 */
	@RequestMapping(value = "/cellinfo/queryCellinfo.do",method = RequestMethod.GET)
	public @ResponseBody String queryCellinfo(HttpServletRequest request,Long userid,String cellmenu){
		if (userid != null && cellmenu != null && !"".equals(cellmenu.trim())) {
			List<Cellinfo> list = cellinfoService.queryCellinfo(userid, cellmenu);
			return jsonToPage(list);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
