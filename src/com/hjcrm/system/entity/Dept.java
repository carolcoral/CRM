package com.hjcrm.system.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.hjcrm.publics.annotation.Transient;
import com.hjcrm.publics.util.BaseEntity;

/**
 * 部门
 * 
 * @author likang
 * @date 2016-10-13 下午4:46:59
 */
public class Dept extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;


	private Long deptid;// 部门ID
	private Long deptparaid;// 上级部门ID
	private String deptname;// 部门名称
	private String deptcode;// 部门编码
	private Integer dr;// 删除标志 0未删除 1已删除
	private Long create_id;//创建人ID
	private Long update_id;//更新人ID
	private Timestamp create_time;//创建时间
	private Timestamp update_time;//更新时间
	
	@Transient
	private String deptParaname;//上级部门名称
	
	
	
	
	

	public String getDeptParaname() {
		return deptParaname;
	}

	public void setDeptParaname(String deptParaname) {
		this.deptParaname = deptParaname;
	}

	public Long getCreate_id() {
		return create_id;
	}

	public void setCreate_id(Long create_id) {
		this.create_id = create_id;
	}

	public Long getUpdate_id() {
		return update_id;
	}

	public void setUpdate_id(Long update_id) {
		this.update_id = update_id;
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

	public Long getDeptid() {
		return deptid;
	}

	public void setDeptid(Long deptid) {
		this.deptid = deptid;
	}

	public Long getDeptparaid() {
		return deptparaid;
	}

	public void setDeptparaid(Long deptparaid) {
		this.deptparaid = deptparaid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	@Override
	public String getIdColumnName() {
		return "deptid";
	}

}
