package com.hjcrm.resource.entity;

import com.hjcrm.publics.util.BaseEntity;

/**
 * 通过学员信息
 * 
 * @author likang
 * @date 2016-10-31 下午5:04:27
 */
public class Passstudent extends BaseEntity {

	private Long passStudentid;// 通过学员主键ID
	private String address;// 地址
	private String name;// 姓名
	private String idCard;// 身份证号码
	private String password;// 密码
	private String mobile;// 手机号
	private String tel;// 座机
	private String email;// 邮箱
	private String company;// 单位名称
	private String homeAddress;// 家庭地址
	private String zhiwu;// 职位
	private String school;// 毕业院校
	private String xueli;// 学历
	private Integer isFaPiao;// 是否已开发票 0未开 1已开
	private String jiaoyuTime;// 继续教育日期
	private String note;// 备注
	private String coursename;// 项目
	private String birthday;// 生日
	private Integer dr;// 删除标志 0未删除 1已删除

	public Long getPassStudentid() {
		return passStudentid;
	}

	public void setPassStudentid(Long passStudentid) {
		this.passStudentid = passStudentid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getZhiwu() {
		return zhiwu;
	}

	public void setZhiwu(String zhiwu) {
		this.zhiwu = zhiwu;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getXueli() {
		return xueli;
	}

	public void setXueli(String xueli) {
		this.xueli = xueli;
	}

	public Integer getIsFaPiao() {
		return isFaPiao;
	}

	public void setIsFaPiao(Integer isFaPiao) {
		this.isFaPiao = isFaPiao;
	}

	public String getJiaoyuTime() {
		return jiaoyuTime;
	}

	public void setJiaoyuTime(String jiaoyuTime) {
		this.jiaoyuTime = jiaoyuTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	@Override
	public String getIdColumnName() {
		return "passStudentid";
	}

}
