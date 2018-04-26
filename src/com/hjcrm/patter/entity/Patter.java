package com.hjcrm.patter.entity;

import java.sql.Timestamp;

import com.hjcrm.publics.annotation.Transient;
import com.hjcrm.publics.util.BaseEntity;

/**
 * 话术
 * 
 * @author likang
 * @date 2016-10-27 下午3:03:59
 */
public class Patter extends BaseEntity {

	private Long patterid;// 话术主键
	private Long userid;// 用户id,话术创建人
	private Long roleid;// 用户角色ID
	private Integer courseid;// 课程id
	private Long patterTypeId;// 话术场景类别
	private Long contentTypeId;// 话术内容类别
	private String shortTitle;// 话术内容短标题
	private String content;// 话术内容
	private Timestamp create_time;// 创建时间
	private Timestamp update_time;// 修改时间
	private Long update_id;// 修改人
	private Integer isshare;// 是否共享 0不共享 1共享
	private String note;// 备注
	private Integer dr;// 删除标志 0未删除 1已删除
	
	@Transient
	private String username;//话术创建人
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getPatterid() {
		return patterid;
	}

	public void setPatterid(Long patterid) {
		this.patterid = patterid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public Integer getCourseid() {
		return courseid;
	}

	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}

	public Long getPatterTypeId() {
		return patterTypeId;
	}

	public void setPatterTypeId(Long patterTypeId) {
		this.patterTypeId = patterTypeId;
	}

	public Long getContentTypeId() {
		return contentTypeId;
	}

	public void setContentTypeId(Long contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Integer getIsshare() {
		return isshare;
	}

	public void setIsshare(Integer isshare) {
		this.isshare = isshare;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
		return "patterid";
	}

}
