package com.hjcrm.resource.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.hjcrm.publics.annotation.Transient;
import com.hjcrm.publics.util.BaseEntity;

/**
 * 到账信息上传
 * 
 * @author likang
 * @date 2016-11-10 上午10:44:48
 */
public class Matchinfo extends BaseEntity {
	
	public static final Integer IS_MATCH_NO = 0;//未匹配
	public static final Integer IS_MATCH_YES = 1;//已匹配

	private Long matchInfoId;// 到账信息主键ID
	private String matchname;// 姓名
	private String payMoney;// 收款金额
	private Date receiveTime;// 收款日期
	private String payType;// 汇款方式
	private String matchnote;// 备注
	private Integer ismatch;// 是否已匹配 0未匹配 1已匹配
	private Integer dr;// 删除标志 0未删除 1已删除
	private String studentName;//确认到账对应的学员姓名
	
	@Transient
	private String receiveStartTime;//收款开始日期
	@Transient
	private String receiveEndTime;//收款结束日期
	@Transient
	private String receive_time;
	
	
	
	
	
	

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getReceive_time() {
		if (receiveTime != null) {
			return new SimpleDateFormat("yyyy-MM-dd").format(receiveTime);
		}
		return receive_time;
	}

	public void setReceive_time(String receive_time) {
		this.receive_time = receive_time;
	}

	public String getReceiveStartTime() {
		return receiveStartTime;
	}

	public void setReceiveStartTime(String receiveStartTime) {
		this.receiveStartTime = receiveStartTime;
	}

	public String getReceiveEndTime() {
		return receiveEndTime;
	}

	public void setReceiveEndTime(String receiveEndTime) {
		this.receiveEndTime = receiveEndTime;
	}

	public Long getMatchInfoId() {
		return matchInfoId;
	}

	public void setMatchInfoId(Long matchInfoId) {
		this.matchInfoId = matchInfoId;
	}

	public String getMatchname() {
		return matchname;
	}

	public void setMatchname(String matchname) {
		this.matchname = matchname;
	}

	public String getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getMatchnote() {
		return matchnote;
	}

	public void setMatchnote(String matchnote) {
		this.matchnote = matchnote;
	}

	public Integer getIsmatch() {
		return ismatch;
	}

	public void setIsmatch(Integer ismatch) {
		this.ismatch = ismatch;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	@Override
	public String getIdColumnName() {
		return "matchInfoId";
	}

}
