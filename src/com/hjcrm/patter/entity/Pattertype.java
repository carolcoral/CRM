package com.hjcrm.patter.entity;

import java.sql.Timestamp;

import com.hjcrm.publics.annotation.Transient;
import com.hjcrm.publics.util.BaseEntity;

/**
 * 话术场景类别
 * 
 * @author likang
 * @date 2016-10-27 下午3:18:01
 */
public class Pattertype extends BaseEntity {

	private Long patterTypeId;// 话术场景类别主键
	private Long courseid;// 所属课程ID
	private String patterTypeName;// 话术场景类别名称
	private Long create_id;// 创建人
	private Timestamp create_time;// 创建时间
	private Timestamp update_time;// 修改时间
	private Long update_id;// 修改人
	private Integer dr;// 删除标志 0未删除 1已删除
	
//	0 AFP
//	1 CFP
//	2 基金从业
//	3 银行从业
//	4 中级经济师
//	5 会计从业
//	6 初级会计
//	7 期货从业
//	8 证券从业

	
	@Transient
	private String courseName;//课程名称
	 
	
	

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Long getCourseid() {
		return courseid;
	}

	public void setCourseid(Long courseid) {
		this.courseid = courseid;
	}


	public String getPatterTypeName() {
		return patterTypeName;
	}

	public void setPatterTypeName(String patterTypeName) {
		this.patterTypeName = patterTypeName;
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
		return "patterTypeId";
	}

}
