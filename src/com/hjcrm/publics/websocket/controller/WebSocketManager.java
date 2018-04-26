package com.hjcrm.publics.websocket.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

import com.hjcrm.publics.util.JackSonUtils;
import com.hjcrm.publics.websocket.entity.WebSocketNeedBean;
import com.hjcrm.system.entity.User;


/**
 * 
 * @author likang
 * @date 2016-10-17 上午11:10:15
 */
@ServerEndpoint(value = "/websocket")
public class WebSocketManager {
	
	private static Logger logger = Logger.getLogger(WebSocketManager.class);
	private static ExecutorService pushPool = Executors.newFixedThreadPool(10);
	
	/**
	 * 保存客户端的Websocket连接信息
	 *  key:键 形如 2_1:前面的 2表示对象类型 _为分隔符 1:表示对象的Id
	 *  value:websocket的Session
	 */
	private static Map<String, CopyOnWriteArraySet<Session>> socketMap = new HashMap<String, CopyOnWriteArraySet<Session>>();
	private static Object object = new Object();
	
	 
	 
	@OnMessage
	public void onMessage(String key, Session session) throws IOException,InterruptedException {
		if(key != null){
			CopyOnWriteArraySet<Session> sessionSet = socketMap.get(key);
			if(sessionSet == null || sessionSet.size() == 0){
				sessionSet = new CopyOnWriteArraySet<Session>();
			}
			sessionSet.add(session);
			socketMap.put(key, sessionSet);
		}
		
	}

	/**
	 * 使用线程池推送消息到客户端
	 * @param needBean 消息实体
	 * @author likang 
	 * @date 2016-10-17 上午11:11:03
	 */
	public static void pushInfoToClient(final WebSocketNeedBean needBean) {
		Integer objType = needBean.getObjType();
		final String objId = needBean.getObjId();
		final String deptid = needBean.getDeptid();
		if (objId == null) {
			final String key = objType+"_"+deptid;
			try {
				CopyOnWriteArraySet<Session> sendSessionSet = socketMap.get(key);
				if(sendSessionSet != null){
					for(final Session session : sendSessionSet){
						
						pushPool.execute(new Runnable(){
							public void run() {
								if(session != null){
									//判断客户端是否没有连接。
									if(!session.isOpen()){
										//从map中移除。
										socketMap.get(key).remove(session);
									}else{
										synchronized (object) {
											try {
												session.getBasicRemote().sendText(JackSonUtils.writeObj2Str(needBean));
											} catch (IOException e) {
												e.printStackTrace();
											}
										}
									}
								}
							}
						});
					}
				}
			} catch (Exception e) {
				logger.debug("WebSocket往客户端发送信息失败!",e);
			}
		}else{
			final String key = objType+"_"+objId;
			try {
				CopyOnWriteArraySet<Session> sendSessionSet = socketMap.get(key);
				if(sendSessionSet != null){
					for(final Session session : sendSessionSet){
						
						pushPool.execute(new Runnable(){
							public void run() {
								if(session != null){
									//判断客户端是否没有连接。
									if(!session.isOpen()){
										//从map中移除。
										socketMap.get(key).remove(session);
									}else{
										synchronized (object) {
											try {
												session.getBasicRemote().sendText(JackSonUtils.writeObj2Str(needBean));
											} catch (IOException e) {
												e.printStackTrace();
											}
										}
									}
								}
							}
						});
					}
				}
			} catch (Exception e) {
				logger.debug("WebSocket往客户端发送信息失败!",e);
			}
		}
		
	}
	
	@OnClose
	public void onClose(Session session){
		Set<Entry<String, CopyOnWriteArraySet<Session>>> entrySet = socketMap.entrySet();
		Iterator<Entry<String, CopyOnWriteArraySet<Session>>> iterator = entrySet.iterator();
		while(iterator.hasNext()){
			Entry<String, CopyOnWriteArraySet<Session>> entry = iterator.next();
			if(entry.getValue().contains(session)){
				entry.getValue().remove(session);
			}
		}
	}
}
