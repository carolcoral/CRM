package com.hjcrm.system.entity;

import java.io.Serializable;

import com.hjcrm.publics.util.BaseEntity;

/**
 * 角色-菜单-对照
 * 
 * @author likang
 * @date 2016-10-13 下午4:47:28
 */
public class Role_menu extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;


	private Long id;// 主键
	private Long roleid;// 角色id
	private Long menuid;// 菜单id
	private Integer dr;// 删除标志 0未删除 1已删除

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public Long getMenuid() {
		return menuid;
	}

	public void setMenuid(Long menuid) {
		this.menuid = menuid;
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
