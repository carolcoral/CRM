package com.hjcrm.system.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.hjcrm.publics.annotation.Transient;
import com.hjcrm.publics.util.BaseEntity;

/**
 * 科目信息
 * 
 * @author likang
 * @date 2016-10-31 下午4:59:09
 */
public class Subject extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;


	private Long subjectid;// 科目主键ID
	private Long courseid;// 所属课程ID
	private String subjectname;// 科目名称
	private String subjectcode;// 科目编码
	private Long create_id;// 创建人
	private Timestamp create_time;// 创建时间
	private Long update_id;// 修改人
	private Timestamp update_time;// 修改时间
	private String note;// 备注
	private Integer dr;// 删除标志 0未删除 1已删除
	
	@Transient
	private String coursename;//课程名称
	
	

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public Long getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(Long subjectid) {
		this.subjectid = subjectid;
	}

	public Long getCourseid() {
		return courseid;
	}

	public void setCourseid(Long courseid) {
		this.courseid = courseid;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getSubjectcode() {
		return subjectcode;
	}

	public void setSubjectcode(String subjectcode) {
		this.subjectcode = subjectcode;
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

	public Long getUpdate_id() {
		return update_id;
	}

	public void setUpdate_id(Long update_id) {
		this.update_id = update_id;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
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
		return "subjectid";
	}

}
