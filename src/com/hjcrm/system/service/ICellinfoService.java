package com.hjcrm.system.service;

import java.util.List;

import com.hjcrm.system.entity.Cellinfo;

/**
 * 选择列接口
 * @author likang
 * @date 2016-12-2 上午10:16:17
 */
public interface ICellinfoService {
	
	/**
	 * 保存/修改列名
	 * @param cellinfo
	 * @author likang 
	 * @date 2016-12-2 上午10:18:13
	 */
	public void saveOrUpdate(Cellinfo cellinfo);
	
	/**
	 * 删除用户的菜单选择列
	 * @param userid
	 * @param cellmenu
	 * @author likang 
	 * @date 2016-12-2 上午10:25:53
	 */
	public void deleteCellinfo(Long userid,String cellmenu);
	
	/**
	 * 查询用户的菜单列名
	 * @param userid
	 * @param cellmenu
	 * @return
	 * @author likang 
	 * @date 2016-12-2 上午10:19:10
	 */
	public List<Cellinfo> queryCellinfo(Long userid,String cellmenu);

}
