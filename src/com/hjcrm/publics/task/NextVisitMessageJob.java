package com.hjcrm.publics.task;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hjcrm.publics.jpush.pool.TaskPushInfoThreadPool;
import com.hjcrm.publics.websocket.entity.WebSocketNeedBean;
import com.hjcrm.resource.entity.Resource;
import com.hjcrm.resource.service.ITaskService;



/**
 * 根据下次回访时间，确定提醒，提高资源利用率
 * @author likang
 * @date 2016-12-26 上午8:49:42
 */
public class NextVisitMessageJob {
	

	/**
	 * @author likang 
	 * @throws ParseException 
	 * @date 2016-12-23 下午5:30:18
	 */
	public void nextVisitMessage() throws ParseException{
		 System.out.println("1222");
	}
	
	/**
	 * 推送消息
	 * @param objType
	 * @param objId
	 * @param dataIds
	 * @param dataType
	 * @param content
	 * @author likang 
	 * @date 2016-12-1 上午10:39:47
	 */
	public void sendmessage(Integer objType,String objId,String deptid,String dataIds,String dataType,String content) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("objType", objType);// 对象类型
		paramMap.put("objId", objId);//对象用户ID
		paramMap.put("deptid", deptid);//用户部门ID
		paramMap.put("dataId", dataIds);//数据ID
		paramMap.put("dataType", dataType);//数据类型
		paramMap.put("content",content);//提示内容
		TaskPushInfoThreadPool.executePushInfoTask(paramMap);
	}
}
