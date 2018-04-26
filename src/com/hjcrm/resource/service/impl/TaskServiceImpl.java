package com.hjcrm.resource.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.resource.entity.Resource;
import com.hjcrm.resource.service.ITaskService;

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskServiceImpl implements ITaskService{
	
	@Autowired
	private IDataAccess<Resource> resourceDao;

	/**
	 * 查询符合条件的数据
	 */
	
	public List<Resource> queryTaskResources(String nowtime,String nexttime) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("nowtime", nowtime);
		param.put("nexttime", nexttime);
		List<Resource> list = resourceDao.queryByStatment("queryTaskResources", param, null);
		return list;
		
	}
	
	

	/**
	 * 消息提醒定时任务查询数据接口
	 */
	
	public List<Resource> queryTaskResourceList(String deptid, String userid,String roleid, String resourceIds, PageBean processPageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("resourceIds", resourceIds);
		param.put("deptid", deptid);
		param.put("userid", userid);
		param.put("roleid", roleid);
		List<Resource> list  = resourceDao.queryByStatment("queryTaskResourceList", param, processPageBean);
		return list;
	}


}
