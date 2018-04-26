package com.hjcrm.system.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.hjcrm.publics.annotation.Transient;
import com.hjcrm.publics.util.BaseEntity;

/**
 * crm系统用户实体
 * 
 * @author likang
 * @date 2016-10-13 上午10:29:25
 */
public class User extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	
	private Long userid;// 用户id
	private String username;// 用户真实姓名
	private String nickname;// 用户昵称
	private String userphoto;//用户头像
	private String email;//邮箱
	private String password;// 密码
	private String phone;// 手机号码
	private String mobile;// 座机
	private Long deptid;// 部门ID
	private Long roleid;// 角色ID
	private Integer state;// 状态 0 可用 1不可用
	private Integer sex;// 性别 0男 1女
	private String wxopenid;// 关联微信ID
	private String qqopenid;// 关联QQID
	private Integer dr;// 删除标志 0未删除 1已删除
	private Long create_id;//创建人ID
	private Long update_id;//更新人ID
	private Timestamp create_time;//创建时间
	private Timestamp update_time;//更新时间
	private String note;//备注
	private Integer ischange;//是否修改密码
	private Integer deptgroup;//部门大类：0市场部 、1基金销售、 2机构客户部、 3重庆代理1、4重庆代理2 、5西安代理 、6南京代理1、 7南京代理2、8苏州代理、9泰州代理、10长春代理 、11太原代理
	
	@Transient
	private String deptname;//部门名称
	@Transient
	private String rolename;//角色名称
	@Transient
	private Double sumMoney;//业绩
	@Transient
	private String addCount;//录入资源量
	
	@Transient
	private String priceAll;//业绩总和
	@Transient
	private String priceAM;//A面
	@Transient
	private String priceAW;//A网
	@Transient
	private String priceCM;//C面
	@Transient
	private String priceCW;//C网
	@Transient
	private String priceYHCY;//银行从业
	@Transient
	private String priceJJCJB;//基金串讲班
	@Transient
	private String priceJJBGB;//基金包过班
	@Transient
	private String priceJJTFB;//基金退费班
	@Transient
	private String priceJJHFFB;//基金后付费班
	@Transient
	private String priceJJTKB;//基金题库班
	@Transient
	private String priceZJJJS;//中级经济师
	@Transient
	private String priceKJCY;//会计从业
	@Transient
	private String priceCJKJ;//初级会计
	@Transient
	private String priceQHCY;//期货从业
	@Transient
	private String priceZQCY;//证券从业
	@Transient
	private String priceAZTSZB;//A真题实战班
	
	
	
	public String getPriceAll() {
		return priceAll;
	}

	public void setPriceAll(String priceAll) {
		this.priceAll = priceAll;
	}

	public String getPriceAM() {
		return priceAM;
	}

	public void setPriceAM(String priceAM) {
		this.priceAM = priceAM;
	}

	public String getPriceAW() {
		return priceAW;
	}

	public void setPriceAW(String priceAW) {
		this.priceAW = priceAW;
	}

	public String getPriceCM() {
		return priceCM;
	}

	public void setPriceCM(String priceCM) {
		this.priceCM = priceCM;
	}

	public String getPriceCW() {
		return priceCW;
	}

	public void setPriceCW(String priceCW) {
		this.priceCW = priceCW;
	}

	public String getPriceYHCY() {
		return priceYHCY;
	}

	public void setPriceYHCY(String priceYHCY) {
		this.priceYHCY = priceYHCY;
	}

	public String getPriceJJCJB() {
		return priceJJCJB;
	}

	public void setPriceJJCJB(String priceJJCJB) {
		this.priceJJCJB = priceJJCJB;
	}

	public String getPriceJJBGB() {
		return priceJJBGB;
	}

	public void setPriceJJBGB(String priceJJBGB) {
		this.priceJJBGB = priceJJBGB;
	}

	public String getPriceJJTFB() {
		return priceJJTFB;
	}

	public void setPriceJJTFB(String priceJJTFB) {
		this.priceJJTFB = priceJJTFB;
	}

	public String getPriceJJHFFB() {
		return priceJJHFFB;
	}

	public void setPriceJJHFFB(String priceJJHFFB) {
		this.priceJJHFFB = priceJJHFFB;
	}

	public String getPriceZJJJS() {
		return priceZJJJS;
	}

	public void setPriceZJJJS(String priceZJJJS) {
		this.priceZJJJS = priceZJJJS;
	}

	public String getPriceKJCY() {
		return priceKJCY;
	}

	public void setPriceKJCY(String priceKJCY) {
		this.priceKJCY = priceKJCY;
	}

	public String getPriceCJKJ() {
		return priceCJKJ;
	}

	public void setPriceCJKJ(String priceCJKJ) {
		this.priceCJKJ = priceCJKJ;
	}

	public String getPriceQHCY() {
		return priceQHCY;
	}

	public void setPriceQHCY(String priceQHCY) {
		this.priceQHCY = priceQHCY;
	}

	public String getPriceZQCY() {
		return priceZQCY;
	}

	public void setPriceZQCY(String priceZQCY) {
		this.priceZQCY = priceZQCY;
	}

	public String getPriceAZTSZB() {
		return priceAZTSZB;
	}

	public void setPriceAZTSZB(String priceAZTSZB) {
		this.priceAZTSZB = priceAZTSZB;
	}

	public String getPriceJJTKB() {
		return priceJJTKB;
	}

	public void setPriceJJTKB(String priceJJTKB) {
		this.priceJJTKB = priceJJTKB;
	}

	public Integer getDeptgroup() {
		return deptgroup;
	}

	public void setDeptgroup(Integer deptgroup) {
		this.deptgroup = deptgroup;
	}

	public String getAddCount() {
		return addCount;
	}

	public void setAddCount(String addCount) {
		this.addCount = addCount;
	}

	public Integer getIschange() {
		return ischange;
	}

	public void setIschange(Integer ischange) {
		this.ischange = ischange;
	}

	public Double getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(Double sumMoney) {
		this.sumMoney = sumMoney;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserphoto() {
		return userphoto;
	}

	public void setUserphoto(String userphoto) {
		this.userphoto = userphoto;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getDeptid() {
		return deptid;
	}

	public void setDeptid(Long deptid) {
		this.deptid = deptid;
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	 

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getWxopenid() {
		return wxopenid;
	}

	public void setWxopenid(String wxopenid) {
		this.wxopenid = wxopenid;
	}

	public String getQqopenid() {
		return qqopenid;
	}

	public void setQqopenid(String qqopenid) {
		this.qqopenid = qqopenid;
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
		return "userid";
	}

}
