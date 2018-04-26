package com.hjcrm.resource.entity;

import java.sql.Timestamp;

import com.hjcrm.publics.annotation.Transient;
import com.hjcrm.publics.util.BaseEntity;

/**
 * 回访记录
 * 
 * @author likang
 * @date 2016-10-31 下午4:44:19
 */
public class Visitrecord extends BaseEntity {

	private Long visitRecordid;// 回访表主键ID
	private Long userid;// 销售或者客服id
	private Long resourceId;// 资源id
	private Long studentId;// 学员ID
	private String visitRecord;// 回访记录
	private Timestamp create_time;// 创建时间
	private Timestamp update_time;// 修改时间
	private Long update_id;// 修改人id
	private Integer dr;// 删除标志 0未删除 1已删除
	private Long courseid;//课程ID
	private Long subjectid;//科目ID
	
	@Transient
	private String visitCount;//回访次数
	@Transient
	private Timestamp firstVisitTime;// 首次回访时间
	@Transient
	private Timestamp nearVisitTime;// 下次回访时间
	
	
	
	
	

	public String getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(String visitCount) {
		this.visitCount = visitCount;
	}

	public Timestamp getFirstVisitTime() {
		return firstVisitTime;
	}

	public void setFirstVisitTime(Timestamp firstVisitTime) {
		this.firstVisitTime = firstVisitTime;
	}

	 

	public Timestamp getNearVisitTime() {
		return nearVisitTime;
	}

	public void setNearVisitTime(Timestamp nearVisitTime) {
		this.nearVisitTime = nearVisitTime;
	}

	public Long getCourseid() {
		return courseid;
	}

	public void setCourseid(Long courseid) {
		this.courseid = courseid;
	}

	public Long getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(Long subjectid) {
		this.subjectid = subjectid;
	}

	public Long getVisitRecordid() {
		return visitRecordid;
	}

	public void setVisitRecordid(Long visitRecordid) {
		this.visitRecordid = visitRecordid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
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

	public String getVisitRecord() {
		return visitRecord;
	}

	public void setVisitRecord(String visitRecord) {
		this.visitRecord = visitRecord;
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
		return "visitRecordid";
	}

}
