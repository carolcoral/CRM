package com.hjcrm.resource.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.hjcrm.publics.annotation.Transient;
import com.hjcrm.publics.util.BaseEntity;

/**
 * 成交记录表
 * 
 * @author likang
 * @date 2016-10-31 下午4:48:25
 */
public class Dealrecord extends BaseEntity {

	private Long dealrecordId;// 成交记录主键ID
	private Long resourceId;// 资源ID
	private Long studentId;// 学员ID
	private Long courseid;// 课程ID
	private Long subjectid;// 科目ID
	private Long userid;// 创建人
	private Timestamp create_time;// 创建日期
	private Long update_id;// 修改人
	private Timestamp update_time;// 修改日期
	private String note;// 备注
	private Integer dr;// 删除标志 0未删除 1已删除
	private Timestamp scoretime;// 考试时间
	private String score;// 考试成绩
	private Integer ispass;// 是否通过 0未通过 1通过 2缺考
	

	@Transient
	private String courseName;// 课程名称
	@Transient
	private String subjectname;// 科目名称
	
	
	
	
	
	

	public Integer getIspass() {
		return ispass;
	}

	public void setIspass(Integer ispass) {
		this.ispass = ispass;
	}

	public Long getDealrecordId() {
		return dealrecordId;
	}

	public void setDealrecordId(Long dealrecordId) {
		this.dealrecordId = dealrecordId;
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

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
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

	public Timestamp getScoretime() {
		if (scoretime != null) {
			return Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(scoretime));
		}
		return scoretime;
	}

	public void setScoretime(Timestamp scoretime) {
		if (scoretime != null) {
			this.scoretime = Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(scoretime));
		}
		this.scoretime = scoretime;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	@Override
	public String getIdColumnName() {
		// TODO Auto-generated method stub
		return "dealrecordId";
	}

}
