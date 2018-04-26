package com.hjcrm.system.entity;

import java.io.Serializable;

import com.hjcrm.publics.util.BaseEntity;

/**
 * 选择列
 * @author likang
 * @date 2016-12-2 上午10:13:41
 */
public class Cellinfo extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;


	private Long cellinfoId;// 选择列ID
	private String cellname;// 列名
	private String cellcode;// 列名编码
	private Integer cellno;// 列名顺序
	private String cellmenu;// 所属菜单
	private Long celldeptid;// 所属部门ID
	private Long cellroleid;// 所属角色ID
	private Long celluserid;// 所属人ID

	public Long getCellinfoId() {
		return cellinfoId;
	}

	public void setCellinfoId(Long cellinfoId) {
		this.cellinfoId = cellinfoId;
	}

	public String getCellname() {
		return cellname;
	}

	public void setCellname(String cellname) {
		this.cellname = cellname;
	}

	public String getCellcode() {
		return cellcode;
	}

	public void setCellcode(String cellcode) {
		this.cellcode = cellcode;
	}

	public Integer getCellno() {
		return cellno;
	}

	public void setCellno(Integer cellno) {
		this.cellno = cellno;
	}

	public String getCellmenu() {
		return cellmenu;
	}

	public void setCellmenu(String cellmenu) {
		this.cellmenu = cellmenu;
	}

	public Long getCelldeptid() {
		return celldeptid;
	}

	public void setCelldeptid(Long celldeptid) {
		this.celldeptid = celldeptid;
	}

	public Long getCellroleid() {
		return cellroleid;
	}

	public void setCellroleid(Long cellroleid) {
		this.cellroleid = cellroleid;
	}

	public Long getCelluserid() {
		return celluserid;
	}

	public void setCelluserid(Long celluserid) {
		this.celluserid = celluserid;
	}

	@Override
	public String getIdColumnName() {
		// TODO Auto-generated method stub
		return "cellinfoId";
	}

}
