package com.hjcrm.resource.entity;

import java.sql.Timestamp;

import com.hjcrm.publics.annotation.Transient;
import com.hjcrm.publics.util.BaseEntity;

/**
 * 转移记录
 * 
 * @author likang
 * @date 2016-12-21 下午1:44:44
 */
public class Transferrecord extends BaseEntity {

	private Long transferrecordId;//转移记录ID
	private Long resourceId;//资源ID
	private String phone;//手机号
	private String tel;//座机
	private Long sourceId;//数据来源人ID
	private String sourceName;//数据来源人
	private Long belongId;//归属者ID
	private String belongName;//归属者
	private Timestamp create_time;//转移时间
	private Integer state;//资源状态  0未分配 1已分配(未处理) 2已处理 
	private Long deptid;//部门ID  
	private Integer source;//渠道: 0自建  1课程注册  2在线注册  3app下载注册   4电话咨询   5金考网注册用户   6线上渠道  7在线咨询  8大库资源 9 在线购买  
	private Long operationId;//操作人ID
	private String operationName;//操作人姓名
	private String resourceLevelBefore;//转移前资源等级     A B C D
	
	@Transient
	private String resourceLevelAfter;//转移后资源等级     A B C D
	@Transient
	private String createStarttime;//转移开始时间
	@Transient
	private String createEndtime;//转移结束时间
	
	
	
	
	
	
	public String getCreateStarttime() {
		return createStarttime;
	}

	public void setCreateStarttime(String createStarttime) {
		this.createStarttime = createStarttime;
	}

	public String getCreateEndtime() {
		return createEndtime;
	}

	public void setCreateEndtime(String createEndtime) {
		this.createEndtime = createEndtime;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}

	public String getResourceLevelBefore() {
		return resourceLevelBefore;
	}

	public void setResourceLevelBefore(String resourceLevelBefore) {
		this.resourceLevelBefore = resourceLevelBefore;
	}

	public String getResourceLevelAfter() {
		return resourceLevelAfter;
	}

	public void setResourceLevelAfter(String resourceLevelAfter) {
		this.resourceLevelAfter = resourceLevelAfter;
	}

	public Long getDeptid() {
		return deptid;
	}

	public void setDeptid(Long deptid) {
		this.deptid = deptid;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Long getTransferrecordId() {
		return transferrecordId;
	}

	public void setTransferrecordId(Long transferrecordId) {
		this.transferrecordId = transferrecordId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public Long getBelongId() {
		return belongId;
	}

	public void setBelongId(Long belongId) {
		this.belongId = belongId;
	}

	public String getBelongName() {
		return belongName;
	}

	public void setBelongName(String belongName) {
		this.belongName = belongName;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String getIdColumnName() {
		// TODO Auto-generated method stub
		return "transferrecordId";
	}

}
