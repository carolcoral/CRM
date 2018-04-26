package com.hjcrm.system.entity;

import java.sql.Timestamp;

import com.hjcrm.publics.util.BaseEntity;

/**
 * 资源动作
 * 
 * @author likang
 * @date 2016-10-31 下午3:47:48
 */
public class Action extends BaseEntity {

	private Long actionid;//动作流程主键
	private String billno;//单据号
	private String actioncode;//动作编码
	private Long resourceId;//资源主键
	private Long studentId;//学员主键
	private Integer billstate;//状态  0资源未处理 1资源已处理 2学员已成交  3学员已提交行政  4已付款  5行政已分配客服  6已转入回访表
	private Long fromuserid;//数据来源人ID 
	private Long touserid;//数据接收人ID 
	private Long create_id;//创建人
	private Timestamp create_time;//创建时间 
	private Integer dr;//删除标志 0未删除  1已删除
	
	
	

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public Long getActionid() {
		return actionid;
	}

	public void setActionid(Long actionid) {
		this.actionid = actionid;
	}

	public String getActioncode() {
		return actioncode;
	}

	public void setActioncode(String actioncode) {
		this.actioncode = actioncode;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Integer getBillstate() {
		return billstate;
	}

	public void setBillstate(Integer billstate) {
		this.billstate = billstate;
	}

	public Long getFromuserid() {
		return fromuserid;
	}

	public void setFromuserid(Long fromuserid) {
		this.fromuserid = fromuserid;
	}

	public Long getTouserid() {
		return touserid;
	}

	public void setTouserid(Long touserid) {
		this.touserid = touserid;
	}

	public Long getCreate_id() {
		return create_id;
	}

	public void setCreate_id(Long create_id) {
		this.create_id = create_id;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
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
		return "actionid";
	}

}
