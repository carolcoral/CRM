package com.hjcrm.system.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.hjcrm.publics.util.BaseEntity;

/**
 * 系统消息
 * 
 * @author likang
 * @date 2016-12-1 下午4:47:26
 */
public class Systemmessage extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;


	private Long systemmessageId;// 系统消息主键
	private String messagetitle;// 消息标题
	private String messageContent;// 消息内容
	private Integer issend;// 是否发布 0未发布 1已发布
	private Timestamp send_time;// 消息发布时间
	private Timestamp create_time;// 创建时间
	private Long create_id;// 创建人

	public Long getSystemmessageId() {
		return systemmessageId;
	}

	public void setSystemmessageId(Long systemmessageId) {
		this.systemmessageId = systemmessageId;
	}

	public String getMessagetitle() {
		return messagetitle;
	}

	public void setMessagetitle(String messagetitle) {
		this.messagetitle = messagetitle;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Integer getIssend() {
		return issend;
	}

	public void setIssend(Integer issend) {
		this.issend = issend;
	}

	public Timestamp getSend_time() {
		return send_time;
	}

	public void setSend_time(Timestamp send_time) {
		this.send_time = send_time;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Long getCreate_id() {
		return create_id;
	}

	public void setCreate_id(Long create_id) {
		this.create_id = create_id;
	}

	@Override
	public String getIdColumnName() {
		// TODO Auto-generated method stub
		return "systemmessageId";
	}

}
