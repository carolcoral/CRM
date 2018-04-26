package com.hjcrm.system.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.system.entity.Systemmessage;
import com.hjcrm.system.service.ISystemMessageService;

/**
 * 
 * @author likang
 * @date 2016-12-1 下午4:57:13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SystemMessageServiceImpl implements ISystemMessageService{
	
	@Autowired
	private IDataAccess<Systemmessage> messageDao;

	/**
	 * 增加或者修改消息
	 */
	
	public void saveOrUpdate(Systemmessage message) {
		if (message != null) {
			if (message.getSystemmessageId() != null) {
				messageDao.update(message);
			}else{
				message.setCreate_time(new Timestamp(System.currentTimeMillis()));
				messageDao.insert(message);
			}
		}
		
	}

	/**
	 *  查询最近一条发布信息
	 */
	
	public List<Systemmessage> querySystemmessageSend() {
		List<Systemmessage> list = messageDao.queryByStatment("querySystemmessageSend", null, null);
		return list;
	}
	
	/**
	 *  查询最近一条发布信息
	 */
	
	public List<Systemmessage> querySystemmessages(PageBean pageBean) {
		List<Systemmessage> list = messageDao.queryByStatment("querySystemmessages", null, pageBean);
		return list;
	}

	/**
	 * 发布或者撤回消息
	 */
	
	public void sendMessage(String systemmessageId, String issend,Timestamp send_time) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("systemmessageId", systemmessageId);
		param.put("issend", issend);
		param.put("send_time", send_time);
		messageDao.updateByStatment("sendMessage", param);
		
	}

}
