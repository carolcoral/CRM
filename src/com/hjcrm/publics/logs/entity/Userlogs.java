package com.hjcrm.publics.logs.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.hjcrm.publics.util.BaseEntity;

/**
 * 用户操作日志实体
 * @author likang
 * @date 2016-10-11 下午6:31:35
 */
public class Userlogs extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private Long userLogsId;//主键
	private Long userid;//用户id
	private String username;//用户名称
	private String logsinter;//用户操作日志接口地址
	private String logsparam;//操作参数
	private Timestamp logstime;//操作时间
	private String logsuserip;//操作IP
	private String ipcountry;//操作IP国家
	private String iparea;//IP地区
	private String ipregion;//IP省份
	private String ipcity;//IP城市
	private String ipcounty;//IP区/县
	private String ipisp;//互联网服务提供商 
	private String logsnote;//备注
	private Integer dr;//删除标志  0 未删除 1 已删除  
	
	
	
	
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	 
	 
	public Long getUserLogsId() {
		return userLogsId;
	}
	public void setUserLogsId(Long userLogsId) {
		this.userLogsId = userLogsId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLogsinter() {
		return logsinter;
	}
	public void setLogsinter(String logsinter) {
		this.logsinter = logsinter;
	}
	public String getLogsparam() {
		return logsparam;
	}
	public void setLogsparam(String logsparam) {
		this.logsparam = logsparam;
	}
	public Timestamp getLogstime() {
		return logstime;
	}
	public void setLogstime(Timestamp logstime) {
		this.logstime = logstime;
	}
	public String getLogsuserip() {
		return logsuserip;
	}
	public void setLogsuserip(String logsuserip) {
		this.logsuserip = logsuserip;
	}
	public String getIpcountry() {
		return ipcountry;
	}
	public void setIpcountry(String ipcountry) {
		this.ipcountry = ipcountry;
	}
	public String getIparea() {
		return iparea;
	}
	public void setIparea(String iparea) {
		this.iparea = iparea;
	}
	public String getIpregion() {
		return ipregion;
	}
	public void setIpregion(String ipregion) {
		this.ipregion = ipregion;
	}
	public String getIpcity() {
		return ipcity;
	}
	public void setIpcity(String ipcity) {
		this.ipcity = ipcity;
	}
	public String getIpcounty() {
		return ipcounty;
	}
	public void setIpcounty(String ipcounty) {
		this.ipcounty = ipcounty;
	}
	public String getIpisp() {
		return ipisp;
	}
	public void setIpisp(String ipisp) {
		this.ipisp = ipisp;
	}
	public String getLogsnote() {
		return logsnote;
	}
	public void setLogsnote(String logsnote) {
		this.logsnote = logsnote;
	}
	public Integer getDr() {
		return dr;
	}
	public void setDr(Integer dr) {
		this.dr = dr;
	}
	@Override
	public String getIdColumnName() {
		// TODO Auto-generated method stub
		return "userLogsId";
	}

	
	
	
}
