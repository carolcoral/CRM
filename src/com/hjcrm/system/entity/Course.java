package com.hjcrm.system.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.hjcrm.patter.entity.Pattertype;
import com.hjcrm.publics.annotation.Transient;
import com.hjcrm.publics.util.BaseEntity;

/**
 * 课程
 * 
 * @author likang
 * @date 2016-10-27 下午3:38:47
 */
public class Course extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long courseid;// 课程ID主键
	private String courseName;// 课程名称
	private String coursecode;//课程编码
	private String courseMoney;// 课程价格
	private String courseDescribe;// 课程描述
	private Long courseTeacherid;// 授课老师ID
	private String courseTeacherName;// 授课老师姓名
	private Long create_id;// 创建人
	private Timestamp create_time;// 创建时间
	private Timestamp update_time;// 修改时间
	private Long update_id;// 修改人
	private String note;// 备注
	private Integer dr;// 删除标志 0未删除 1已删除
	
	@Transient
	private List<Pattertype> children;//子场景类别菜单
	
	

	public String getCoursecode() {
		return coursecode;
	}

	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

	public List<Pattertype> getChildren() {
		return children;
	}

	public void setChildren(List<Pattertype> children) {
		this.children = children;
	}

	public Long getCourseid() {
		return courseid;
	}

	public void setCourseid(Long courseid) {
		this.courseid = courseid;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseMoney() {
		return courseMoney;
	}

	public void setCourseMoney(String courseMoney) {
		this.courseMoney = courseMoney;
	}

	public String getCourseDescribe() {
		return courseDescribe;
	}

	public void setCourseDescribe(String courseDescribe) {
		this.courseDescribe = courseDescribe;
	}

	public Long getCourseTeacherid() {
		return courseTeacherid;
	}

	public void setCourseTeacherid(Long courseTeacherid) {
		this.courseTeacherid = courseTeacherid;
	}

	public String getCourseTeacherName() {
		return courseTeacherName;
	}

	public void setCourseTeacherName(String courseTeacherName) {
		this.courseTeacherName = courseTeacherName;
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
		return "courseid";
	}

}
