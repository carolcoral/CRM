package com.hjcrm.resource.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.hjcrm.publics.annotation.Transient;
import com.hjcrm.publics.util.BaseEntity;

/**
 * 资源
 * 
 * @author likang
 * @date 2016-10-31 上午11:36:16
 */
public class Resource extends BaseEntity {
	

	private Long resourceId;// 资源主键ID
	private String billno;// 单据号
	private Long userid;// 创建人id
	private Long deptid;//创建人部门ID
	private Long belongid;// 分配销售人员ID
	private String createrName;// 资源创建者姓名
	private Timestamp create_time;// 资源创建时间
	private String resourceName;// 姓名
	private String phone;// 手机号
	private String tel;// 固定电话
	private String weixin;// 微信
	private String qq;// QQ号
	private String address;// 地区
	private Integer sex;// 性别 0男 1女
	private Integer state;// 资源状态  0未分配 1已分配(未处理) 2已处理
	private Integer isStudent;// 是否为学员 0资源 1学员
	private Integer source;// 渠道: 0自建  1课程注册  2在线注册  3app下载注册   4电话咨询   5金考网注册用户   6线上渠道  7在线咨询  8大库资源 9 在线购买
	private Integer courseid;// 咨询课程
	private String resourceLevel;// 资源等级 A B C D
	private Timestamp assignTime;// 资源分配时间
	private Timestamp firstVisitTime;// 首次回访时间
	private Timestamp nextVisitTime;// 下次回访时间
	private String yunYingNote;// 运营备注
	private String xiaoShouNote;// 销售备注
	private Long update_id;// 修改人
	private Timestamp update_time;// 修改时间
	private Integer dr;// 删除标志 0未删除 1已删除
	private Integer studentstate;//学员状态 0新增 1已成交 2已提交 3已到账 4已分配 5已转入 6已通过考试 7已关课 8已退回
	private String resourceColor;//资源星级客户颜色  
	private Integer isolddata;//是否历史数据 0 否 1是
	private Integer isZhuanyi;//是否转入  0否 1是
	private String email;// 邮箱
	private String idCard;// 身份证号码
	private String yunYingResourceLevel;//运营资源等级 
	private String isWeixin;//是否有微信 0：无  1：有
	
	@Transient
	private Timestamp nearVisitTime;//最新回访时间
	@Transient
	private String visitRecord;//最近回访记录
	
	@Transient
	private String courseName;//咨询课程名称
	@Transient
	private String belongName;//归属者姓名
	@Transient
	private String visitCount;//回访次数
	@Transient
	private String visitStartTime;//回访时间(开始)
	@Transient
	private String visitEndTime;//回访时间(结束)
	@Transient
	private String createStartTime;//创建时间(开始)
	@Transient
	private String createEndTime;//创建时间(结束)
	
	@Transient
	private String total;//总量资源
	@Transient
	private String acount;//A类
	@Transient
	private String bcount;//B类
	@Transient
	private String ccount;//C类
	@Transient
	private String dcount;//D类
	@Transient
	private String cjcount;//成交
	@Transient
	private String wclcount;//未处理资源
	@Transient
	private String yclcount;//已处理资源
	@Transient
	private String zxzxcount;//在线咨询
	@Transient
	private String dhzxcount;//电话咨询
	@Transient
	private String kczccount;//课程注册
	@Transient
	private String zxzccount;//在线注册
	@Transient
	private String appcount;//APP下载注册
	@Transient
	private String xsqdcount;//线上渠道
	@Transient
	private String jkwzccount;//金考网注册用户
	@Transient
	private String dkzycount;//大库资源
	@Transient
	private String zxgmcount;//在线购买
	@Transient
	private String jxjycount;//继续教育
	@Transient
	private String xjkhcount;//星级客户
	@Transient
	private String zijiancount;
	@Transient
	private String weixincount;
	@Transient
	private String todaytotal;//今日录入资源
	@Transient
	private String todayzxzxcount;//今日在线咨询
	@Transient
	private String todaydhzxcount;//今日电话咨询
	@Transient
	private String todaykczccount;//今日课程注册
	@Transient
	private String todayzxzccount;//今日在线注册
	@Transient
	private String todayappcount;//今日APP下载注册
	@Transient
	private String todayxsqdcount;//今日线上渠道
	@Transient
	private String todayjkwzccount;//今日金考网注册用户
	@Transient
	private String todaydkzycount;//今日大库资源
	@Transient
	private String todayzxgmcount;//今日在线购买
	@Transient
	private String todayjxjycount;//今日继续教育
	@Transient
	private String todayzijian;//今日自建资源
	
	@Transient
	private String studentNum;//成交学员个数
	
	
	 

	public String getWeixincount() {
		return weixincount;
	}

	public void setWeixincount(String weixincount) {
		this.weixincount = weixincount;
	}

	public String getYclcount() {
		return yclcount;
	}

	public void setYclcount(String yclcount) {
		this.yclcount = yclcount;
	}

	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getYunYingResourceLevel() {
		return yunYingResourceLevel;
	}

	public void setYunYingResourceLevel(String yunYingResourceLevel) {
		this.yunYingResourceLevel = yunYingResourceLevel;
	}

	public Integer getIsZhuanyi() {
		return isZhuanyi;
	}

	public void setIsZhuanyi(Integer isZhuanyi) {
		this.isZhuanyi = isZhuanyi;
	}

	public String getZijiancount() {
		return zijiancount;
	}

	public void setZijiancount(String zijiancount) {
		this.zijiancount = zijiancount;
	}

	public String getTodayzijian() {
		return todayzijian;
	}

	public void setTodayzijian(String todayzijian) {
		this.todayzijian = todayzijian;
	}

	public Integer getIsolddata() {
		return isolddata;
	}

	public void setIsolddata(Integer isolddata) {
		this.isolddata = isolddata;
	}

	public String getVisitRecord() {
		return visitRecord;
	}

	public void setVisitRecord(String visitRecord) {
		this.visitRecord = visitRecord;
	}

	public String getXjkhcount() {
		return xjkhcount;
	}

	public void setXjkhcount(String xjkhcount) {
		this.xjkhcount = xjkhcount;
	}

	public String getResourceColor() {
		return resourceColor;
	}

	public void setResourceColor(String resourceColor) {
		this.resourceColor = resourceColor;
	}

	public String getJxjycount() {
		return jxjycount;
	}

	public void setJxjycount(String jxjycount) {
		this.jxjycount = jxjycount;
	}

	public String getTodayjxjycount() {
		return todayjxjycount;
	}

	public void setTodayjxjycount(String todayjxjycount) {
		this.todayjxjycount = todayjxjycount;
	}

	public String getTodayzxgmcount() {
		return todayzxgmcount;
	}

	public void setTodayzxgmcount(String todayzxgmcount) {
		this.todayzxgmcount = todayzxgmcount;
	}

	public String getZxgmcount() {
		return zxgmcount;
	}

	public void setZxgmcount(String zxgmcount) {
		this.zxgmcount = zxgmcount;
	}

	public Timestamp getNearVisitTime() {
		return nearVisitTime;
	}

	public void setNearVisitTime(Timestamp nearVisitTime) {
		this.nearVisitTime = nearVisitTime;
	}

	public String getVisitStartTime() {
		return visitStartTime;
	}

	public void setVisitStartTime(String visitStartTime) {
		this.visitStartTime = visitStartTime;
	}

	public String getVisitEndTime() {
		return visitEndTime;
	}

	public void setVisitEndTime(String visitEndTime) {
		this.visitEndTime = visitEndTime;
	}

	public String getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}

	public String getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}

	public String getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(String visitCount) {
		this.visitCount = visitCount;
	}

	public String getIsWeixin() {
		return isWeixin;
	}

	public void setIsWeixin(String isWeixin) {
		this.isWeixin = isWeixin;
	}

	public String getBelongName() {
		return belongName;
	}

	public void setBelongName(String belongName) {
		this.belongName = belongName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getAcount() {
		return acount;
	}

	public void setAcount(String acount) {
		this.acount = acount;
	}

	public String getBcount() {
		return bcount;
	}

	public void setBcount(String bcount) {
		this.bcount = bcount;
	}

	public String getCcount() {
		return ccount;
	}

	public void setCcount(String ccount) {
		this.ccount = ccount;
	}

	public String getDcount() {
		return dcount;
	}

	public void setDcount(String dcount) {
		this.dcount = dcount;
	}

	public String getCjcount() {
		return cjcount;
	}

	public void setCjcount(String cjcount) {
		this.cjcount = cjcount;
	}

	public String getWclcount() {
		return wclcount;
	}

	public void setWclcount(String wclcount) {
		this.wclcount = wclcount;
	}

	public String getZxzxcount() {
		return zxzxcount;
	}

	public void setZxzxcount(String zxzxcount) {
		this.zxzxcount = zxzxcount;
	}

	public String getDhzxcount() {
		return dhzxcount;
	}

	public void setDhzxcount(String dhzxcount) {
		this.dhzxcount = dhzxcount;
	}

	public String getKczccount() {
		return kczccount;
	}

	public void setKczccount(String kczccount) {
		this.kczccount = kczccount;
	}

	public String getZxzccount() {
		return zxzccount;
	}

	public void setZxzccount(String zxzccount) {
		this.zxzccount = zxzccount;
	}

	public String getAppcount() {
		return appcount;
	}

	public void setAppcount(String appcount) {
		this.appcount = appcount;
	}

	public String getXsqdcount() {
		return xsqdcount;
	}

	public void setXsqdcount(String xsqdcount) {
		this.xsqdcount = xsqdcount;
	}

	public String getJkwzccount() {
		return jkwzccount;
	}

	public void setJkwzccount(String jkwzccount) {
		this.jkwzccount = jkwzccount;
	}

	public String getDkzycount() {
		return dkzycount;
	}

	public void setDkzycount(String dkzycount) {
		this.dkzycount = dkzycount;
	}

	public String getTodaytotal() {
		return todaytotal;
	}

	public void setTodaytotal(String todaytotal) {
		this.todaytotal = todaytotal;
	}

	public String getTodayzxzxcount() {
		return todayzxzxcount;
	}

	public void setTodayzxzxcount(String todayzxzxcount) {
		this.todayzxzxcount = todayzxzxcount;
	}

	public String getTodaydhzxcount() {
		return todaydhzxcount;
	}

	public void setTodaydhzxcount(String todaydhzxcount) {
		this.todaydhzxcount = todaydhzxcount;
	}

	public String getTodaykczccount() {
		return todaykczccount;
	}

	public void setTodaykczccount(String todaykczccount) {
		this.todaykczccount = todaykczccount;
	}

	public String getTodayzxzccount() {
		return todayzxzccount;
	}

	public void setTodayzxzccount(String todayzxzccount) {
		this.todayzxzccount = todayzxzccount;
	}

	public String getTodayappcount() {
		return todayappcount;
	}

	public void setTodayappcount(String todayappcount) {
		this.todayappcount = todayappcount;
	}

	public String getTodayxsqdcount() {
		return todayxsqdcount;
	}

	public void setTodayxsqdcount(String todayxsqdcount) {
		this.todayxsqdcount = todayxsqdcount;
	}

	public String getTodayjkwzccount() {
		return todayjkwzccount;
	}

	public void setTodayjkwzccount(String todayjkwzccount) {
		this.todayjkwzccount = todayjkwzccount;
	}

	public String getTodaydkzycount() {
		return todaydkzycount;
	}

	public void setTodaydkzycount(String todaydkzycount) {
		this.todaydkzycount = todaydkzycount;
	}

	public Long getDeptid() {
		return deptid;
	}

	public void setDeptid(Long deptid) {
		this.deptid = deptid;
	}

	public Integer getStudentstate() {
		return studentstate;
	}

	public void setStudentstate(Integer studentstate) {
		this.studentstate = studentstate;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getBelongid() {
		return belongid;
	}

	public void setBelongid(Long belongid) {
		this.belongid = belongid;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getIsStudent() {
		return isStudent;
	}

	public void setIsStudent(Integer isStudent) {
		this.isStudent = isStudent;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getCourseid() {
		return courseid;
	}

	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}

	public String getResourceLevel() {
		return resourceLevel;
	}

	public void setResourceLevel(String resourceLevel) {
		this.resourceLevel = resourceLevel;
	}

	public Timestamp getAssignTime() {
		return assignTime;
	}

	public void setAssignTime(Timestamp assignTime) {
		this.assignTime = assignTime;
	}

	public Timestamp getFirstVisitTime() {
		return firstVisitTime;
	}

	public void setFirstVisitTime(Timestamp firstVisitTime) {
		this.firstVisitTime = firstVisitTime;
	}

	public Timestamp getNextVisitTime() {
		return nextVisitTime;
	}

	public void setNextVisitTime(Timestamp nextVisitTime) {
		this.nextVisitTime = nextVisitTime;
	}

	public String getYunYingNote() {
		return yunYingNote;
	}

	public void setYunYingNote(String yunYingNote) {
		this.yunYingNote = yunYingNote;
	}

	public String getXiaoShouNote() {
		return xiaoShouNote;
	}

	public void setXiaoShouNote(String xiaoShouNote) {
		this.xiaoShouNote = xiaoShouNote;
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

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	@Override
	public String getIdColumnName() {
		// TODO Auto-generated method stub
		return "resourceId";
	}

}
