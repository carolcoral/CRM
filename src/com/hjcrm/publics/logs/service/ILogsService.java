package com.hjcrm.publics.logs.service;

import java.util.List;

import com.hjcrm.publics.logs.entity.Userlogs;
import com.hjcrm.publics.util.PageBean;


/**
 * 操作日志接口
 * @author likang
 * @date 2016-10-12 上午11:10:18
 */
public interface ILogsService {

	/**
	 * 保存用户操作日志信息
	 * @param userLogs
	 * @author likang 
	 * @date 2016-10-12 上午8:44:46
	 */
	public void saveUserLogsinfo(Userlogs userLogs);
	
	/**
	 * 查询用户操作日志
	 * @return
	 * @author likang 
	 * @date 2016-10-12 下午1:43:54
	 */
	public List<Userlogs> queryUserLogs(Long userid,PageBean pageBean);
	
}
