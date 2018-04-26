package com.hjcrm.system.service;

import java.util.List;

import com.hjcrm.system.entity.Todaynote;


/**
 * 今日目标接口
 * @author likang
 * @date 2016-10-25 下午4:20:38
 */
public interface ITodayNoteService {

	/**
	 * 增加/修改今日目标
	 * @param todaynote
	 * @author likang 
	 * @date 2016-10-25 下午4:22:48
	 */
	public void saveOrUpdate(Todaynote todaynote);
	
	/**
	 * 查询
	 * @param userid
	 * @return
	 * @author likang 
	 * @date 2016-10-25 下午4:22:56
	 */
	public List<Todaynote> queryTodaynote(Long userid);
	
}
