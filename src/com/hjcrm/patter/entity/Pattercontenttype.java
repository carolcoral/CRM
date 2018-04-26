package com.hjcrm.patter.entity;

import java.sql.Timestamp;

import com.hjcrm.publics.util.BaseEntity;

/**
 * 话术内容类别
 * 
 * @author likang
 * @date 2016-10-27 下午3:18:32
 */
public class Pattercontenttype extends BaseEntity {

	private Long contentTypeId;// 话术内容类别主键ID
	private String contentTypeName;// 话术内容类别名称
	private Long patterTypeId;// 所属场景ID
	private Long create_id;// 创建人
	private Timestamp create_time;// 创建时间
	private Timestamp update_time;// 修改时间
	private Long update_id;// 修改人
	private Integer dr;// 删除标志 0未删除 1已删除

	public Long getContentTypeId() {
		return contentTypeId;
	}

	public void setContentTypeId(Long contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	public String getContentTypeName() {
		return contentTypeName;
	}

	public void setContentTypeName(String contentTypeName) {
		this.contentTypeName = contentTypeName;
	}

	public Long getPatterTypeId() {
		return patterTypeId;
	}

	public void setPatterTypeId(Long patterTypeId) {
		this.patterTypeId = patterTypeId;
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

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}

	public Long getUpdate_id() {
		return update_id;
	}

	public void setUpdate_id(Long update_id) {
		this.update_id = update_id;
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
		return "contentTypeId";
	}

}
