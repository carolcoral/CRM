package com.hjcrm.system.service;

import com.hjcrm.system.entity.Action;


/**
 * 系统流程动作接口
 * @author likang
 * @date 2016-12-21 上午11:36:12
 */
public interface IActionService {
	
	/**
	 * 保存动作流程
	 * @param action
	 * @author likang 
	 * @date 2016-12-21 上午11:39:14
	 */
	public void save(Action action);

}
