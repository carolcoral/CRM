package com.hjcrm.system.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.hjcrm.publics.annotation.Transient;
import com.hjcrm.publics.util.BaseEntity;

/**
 * 菜单
 * 
 * @author likang
 * @date 2016-10-13 下午4:41:47
 */

public class Menu extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;


	private Long menuid;// 菜单ID
	private Long menuparaid;// 上级菜单ID
	private String menuname;// 菜单名称
	private String menucode;// 菜单编码
	private String menuurl;// 菜单URL
	private Integer menuno;// 菜单序号
	private String menuimgurl;// 菜单图片地址
	private Integer menutype;//菜单类型
	private Integer dr;// 删除标志 0未删除 1已删除
	private Long create_id;//创建人ID
	private Long update_id;//更新人ID
	private Timestamp create_time;//创建时间
	private Timestamp update_time;//更新时间
	
	
	@Transient
	private List<Menu> children;//子菜单
	@Transient
	private String menuParaname;//上级菜单名称
	@Transient
	private boolean selected;//是否选中(权限分配)
	
	
	
 
 
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getMenuParaname() {
		return menuParaname;
	}

	public void setMenuParaname(String menuParaname) {
		this.menuParaname = menuParaname;
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

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public Integer getMenutype() {
		return menutype;
	}

	public void setMenutype(Integer menutype) {
		this.menutype = menutype;
	}

	public Integer getMenuno() {
		return menuno;
	}

	public void setMenuno(Integer menuno) {
		this.menuno = menuno;
	}

	public Long getMenuparaid() {
		return menuparaid;
	}

	public void setMenuparaid(Long menuparaid) {
		this.menuparaid = menuparaid;
	}

	public Long getMenuid() {
		return menuid;
	}

	public void setMenuid(Long menuid) {
		this.menuid = menuid;
	}

	public String getMenuimgurl() {
		return menuimgurl;
	}

	public void setMenuimgurl(String menuimgurl) {
		this.menuimgurl = menuimgurl;
	}

	public String getMenuurl() {
		return menuurl;
	}

	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getMenucode() {
		return menucode;
	}

	public void setMenucode(String menucode) {
		this.menucode = menucode;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	@Override
	public String getIdColumnName() {
		return "menuid";
	}

}
