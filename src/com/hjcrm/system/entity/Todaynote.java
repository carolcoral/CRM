package com.hjcrm.system.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.hjcrm.publics.util.BaseEntity;
import com.hjcrm.publics.util.ZgUtil;

/**
 * 今日目标
 * 
 * @author likang
 * @date 2016-10-25 下午4:10:47
 */
public class Todaynote extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;


	private Long id;//主键
	private Long userid;//用户ID
	private String note;//今日目标
	private Timestamp create_time;//创建时间
	private Timestamp update_time;//修改时间
	private Integer dr;// 删除标志 0未删除 1已删除

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getNote() {
		if(note!=null){
			String filterContent = ZgUtil.filterHtml(note).trim();
				return filterContent;
		}
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
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
		return "id";
	}

}
