package com.hjcrm.publics.websocket.entity;

import java.sql.Timestamp;
import java.util.List;


/**
 * WebSocket 需要的信息
 * @author likang
 * @date 2016-10-17 上午11:08:01
 */
public class WebSocketNeedBean {
	/**
	 * 对象类型
	 */
	public static final Integer OBJ_TYPE_LIVE = 1;
	
	public static final Integer OBJ_TYPE_TASK = 2;
	
	/**
	 * 对象用户Id
	 */
	private String objId;
	
	private String deptid;//
	
	private Integer objType;
	
	/**
	 * 推送数据Id
	 */
	private String dataId;
	
	/**
	 * 同一个socket链接，不同数据类型
	 */
	private String dataType;
	/**
	 * 头像
	 */
	private String photo;
	/**
	 * 时间
	 */
	private String date;
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 展示时间
	 */
	private String showTimeStr;
	
	private String nickName;
	
	private List<Object> childList;
	
	private String userName;
	
	private Long parentId;//父ID
	
	
	
	
		
 

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public Integer getObjType() {
		return objType;
	}

	public void setObjType(Integer objType) {
		this.objType = objType;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	 

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	 
	public String getShowTimeStr() {
		return showTimeStr;
	}

	public void setShowTimeStr(String showTimeStr) {
		this.showTimeStr = showTimeStr;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<Object> getChildList() {
		return childList;
	}

	public void setChildList(List<Object> childList) {
		this.childList = childList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
