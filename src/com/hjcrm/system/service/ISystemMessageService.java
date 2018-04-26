package com.hjcrm.system.service;

import java.sql.Timestamp;
import java.util.List;

import com.hjcrm.publics.util.PageBean;
import com.hjcrm.system.entity.Systemmessage;

/**
 * 系统消息接口
 * @author likang
 * @date 2016-12-1 下午4:46:37
 */
public interface ISystemMessageService {

	/**
	 * 增加或者修改消息
	 * @param message
	 * @author likang 
	 * @date 2016-12-1 下午4:50:21
	 */
	public void saveOrUpdate(Systemmessage message);
	
	/**
	 * 查询最近一条发布信息
	 * @return
	 * @author likang 
	 * @date 2016-12-1 下午4:57:59
	 */
	public List<Systemmessage> querySystemmessageSend();
	
	/**
	 * 查询所有系统消息
	 * @return
	 * @author likang 
	 * @date 2016-12-1 下午5:11:26
	 */
	public List<Systemmessage> querySystemmessages(PageBean pageBean);
	
	/**
	 * 发布或者撤回消息
	 * @param systemmessageId
	 * @param issend
	 * @author likang 
	 * @date 2016-12-1 下午4:58:56
	 */
	public void sendMessage(String systemmessageId,String issend,Timestamp send_time);
	
}
