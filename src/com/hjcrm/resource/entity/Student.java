package com.hjcrm.resource.entity;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hjcrm.publics.annotation.Transient;
import com.hjcrm.publics.util.BaseEntity;

/**
 * 学员信息
 * 
 * @author likang
 * @date 2016-10-31 上午11:49:29
 */
public class Student extends BaseEntity {

	private Long studentId;// 学员表主键id
	private Long resourceId;// 资源id
	private Long userid;// 用户id
	private Long belongid;//学员归属人ID
	private Long customerId;//客服人员ID
	private Timestamp customer_time;//分配客服时间
	private String studentName;//学员姓名
	private String phone;//学员手机号
	private String tel;//座机
	private String weixin;//微信号
	private String qq;//QQ号
	private String address;//地区
	private Integer sex;//性别 0男 1女
	private Integer source;//渠道: 0自建  1课程注册  2在线注册  3app下载注册   4电话咨询   5金考网注册用户   6线上渠道  7在线咨询  8大库资源 9 在线购买
	private String resourceLevel;//资源等级     A B C D
	private String idCard;// 身份证号码
	private String nation;// 民族
	private String education;// 学历
	private String school;// 毕业院校
	private String email;// 邮箱
	private String company;// 工作单位
	private String department;// 工作部门
	private String position;// 职务
	private String companyAddr;// 公司地址
	private String homeAddr;// 家庭住址
	private String companyTel;// 公司电话
	private Integer isjieye;// 是否协助结业 0：否 1：是
	@JsonProperty 
	private String LCWname; // 理财网用户名
	@JsonProperty
	private String LCWpassword;// 理财网密码
	private Timestamp LCWoutTime;// 理财网过期时间
	private String banci;//班级
	private String classNum;// 上课班号
	private String qici;// 期次
	private Integer isSignAgreement;// 是否签订协议 0未签订 1已签订
	private String courseversion;// 课件版本
	private Timestamp mailTime;// 邮寄时间
	private String mailTim;
	private Integer isHaveCourse;// 是否上过课 0未上过课 1已上过课
	private String kefuNote1;// 客服备注1
	private String kefuNote2;// 客服备注2
	private String baokaopassword;// 报考密码
	private Integer dr;// 删除标志 0未删除 1已删除
	private Integer studentstate;//学员状态 0新增 1已成交 2已提交 3已到账 4已分配 5已转入 6已通过考试 7已关课 8已退回
	private String returnNote;//退回原因
	private Timestamp returnTime;//退回时间
	private Long returnId;//退回人
	private Timestamp create_time;//创建时间
	private Timestamp update_time;//修改时间
	private Long update_id;//修改人
	private Timestamp commit_time;//提交行政日期
	private String headmaster;//班主任
	private Integer courseid;//课程ID
	private Timestamp matchinfo_time;//确认到账时间
	private String arrive_time;//收款时间
	private String arrive_money;//收款金额
	private String remitWay;//汇款方式
	private Integer ishavenetedu;//是否存在网络培训费 0无 1有
	private Integer isOnlineBuy;//是否在线购买 0否 1是
	private String netedumoney;//网络培训费金额
	private String invoiceinfo;//发票情况
	private String preferinfo;//优惠信息
	private String remituser;//代汇款人
	private Integer ispass;// 是否通过 0未通过 1通过 2缺考
	private Timestamp passtime;// 通过时间
	private Timestamp dealtime;// 成交时间
	private String dealprice;// 成交金额
	private Integer iscommitcaiwu;//行政是否提交财务 0未提交 1已提交
	private Timestamp commitTime_caiwu;//行政提交财务时间
	@JsonProperty
	private String paytime;//支付日期
	private String xingzhengNote;//行政备注
	private Timestamp scoretime;//考试日期
	private String scoretim;
	private String studentEvaluate;//学员评价     优 良 中 差
	
	private String tuifei;//退费
	private String tuifeitime;//退费时间
	private String shenhe;//审核情况
	private String caiwunote;//财务备注
	private String specialinfo;//特殊情况
	private String studentColor;//学员颜色标记
	private Integer isolddata;//是否历史数据 0 否 1是
	
	@Transient
	private String acscoretime;//AFP/CFP考试日期
	@Transient
	private String ispass1;//科目一是否通过
	@Transient
	private String ispass2;//科目二是否通过
	@Transient
	private String deptName;//招生老师的部门名称
	@Transient
	private String 	deptgroupName;//招生老师的部门大类
	@Transient
	private double proportion;//提成比例
	@Transient
	private double shouldpayMoney;//应发提成金额
	@Transient
	private double actualpayMoney;//实发提成金额
	@Transient
	private Integer userstate;// 用户是否离职状态 0 可用 1不可用
	@Transient
	private double dealpriceSum;//总成交金额（同一个招生老师，对应的课程，已确认到账之后）
	
	
	@Transient
	private String mailStartTime;//邮寄日期(开始)
	@Transient
	private String mailEndTime;//邮寄日期(结束)
	@Transient
	private String customerName;//分配客服姓名
	@Transient
	private String payStartTime;//支付开始日期
	@Transient
	private String payEndTime;//支付结束日期
	@Transient
	private String sumPayMoney;//总金额（成交金额 + 网络培训费）
	@Transient
	private String arriveStartTime;//收款开始日期
	@Transient
	private String arriveEndTime;//收款结束日期
	@Transient
	private String issign;//财务专用标识
	@Transient
	private String isAllPass;//是否全科通过
	
	@Transient
	private String commitStarttime;//提交日期(开始)
	@Transient
	private String commitEndtime;//提交日期(结束)
	@Transient
	private String matchinfoStarttime;//确认到账开始时间
	@Transient
	private String matchinfoEndtime;//确认到账结束时间
	
	@Transient
	private String examStartTime;//考试日期(开始)
	@Transient
	private String examEndTime;//考试日期(结束)
	
	@Transient
	private String dealStartTime;//成交日期(开始)
	@Transient
	private String dealEndTime;//成交日期(结束)
	@Transient
	private String belongName;//归属者姓名/招生老师
	@Transient
	private String courseName;//课程名称
	@Transient
	private String subjectname;//科目名称
	@Transient
	private Long subjectid;//科目ID
	@Transient
	private String subjectids;//科目ID
	@Transient
	private List<Dealrecord> dealrecord;//成交信息
	@Transient
	private List<Visitrecord> visitrecord;//回访信息
	
	@Transient
	private String ishuifang;
	@Transient
	private String huifang;
	
	@Transient
	private String afp;//AFP
	@Transient
	private String touzi;//投资
	@Transient
	private String shuiwu;//税务
	@Transient
	private String fuli;//福利
	@Transient
	private String baoxian;//保险
	@Transient
	private String zonghe;//综合
	@Transient
	private String touziExamDate;//投资考试日期
	@Transient
	private String shuiwuExamDate;//税务考试日期
	@Transient
	private String fuliExamDate;//福利考试日期
	@Transient
	private String baoxianExamDate;//保险考试日期
	@Transient
	private String zongheExamDate;//综合考试日期
	
	
	@Transient
	private String jijin1;//基金1
	@Transient
	private String jijin2;//基金2
	@Transient
	private String flfg;//法律法规与综合能力//公共基础
	@Transient
	private String grlc;//个人理财
	@Transient
	private String fxgl;//风险管理
	@Transient
	private String gsxd;//公司信贷
	@Transient
	private String grdk;//个人贷款
	@Transient
	private String jjjczd;//经济基础知识
	@Transient
	private String jrzy;//金融专业知识与实务
	@Transient
	private String gsgl;//工商管理知识与实务
	@Transient
	private String czss;//财政税收专业知识与实务
	@Transient
	private String zqsc;//证券市场基本法律法规
	@Transient
	private String jrsc;//金融市场基础知识
	@Transient
	private String qhflfg;//期货法律法规
	@Transient
	private String qhjczs;//期货基础知识
	@Transient
	private String cjfg;//财经法规与会计职业道德
	@Transient
	private String kjjc;//会计基础
	@Transient
	private String cjkj;//初级会计实务
	@Transient
	private String jjfjc;//经济法基础
	@Transient
	private String jijin1Score;//基金1考试成绩
	@Transient
	private String jijin2Score;//基金2考试成绩
	@Transient
	private String flfgScore;//法律法规与综合能力考试成绩
	@Transient
	private String grlcScore;//个人理财考试成绩
	@Transient
	private String fxglScore;//风险管理考试成绩
	@Transient
	private String gsxdScore;//公司信贷考试成绩
	@Transient
	private String grdkScore;//个人贷款考试成绩
	@Transient
	private String jjjczdScore;//经济基础知识考试成绩
	@Transient
	private String jrzyScore;//金融专业知识与实务考试成绩
	@Transient
	private String gsglScore;//工商管理知识与实务考试成绩
	@Transient
	private String czssScore;//财政税收专业知识与实务考试成绩
	@Transient
	private String zqscScore;//证券市场基本法律法规考试成绩
	@Transient
	private String jrscScore;//金融市场基础知识考试成绩
	@Transient
	private String qhflfgScore;//期货法律法规考试成绩
	@Transient
	private String qhjczsScore;//期货基础知识考试成绩
	@Transient
	private String cjfgScore;//财经法规与会计职业道德考试成绩
	@Transient
	private String kjjcScore;//会计基础考试成绩
	@Transient
	private String cjkjScore;//初级会计实务考试成绩
	@Transient
	private String jjfjcScore;//经济法基础考试成绩
	

	
	
	

	public String getSubjectids() {
		return subjectids;
	}

	public void setSubjectids(String subjectids) {
		this.subjectids = subjectids;
	}

	public String getIspass2() {
		return ispass2;
	}

	public void setIspass2(String ispass2) {
		this.ispass2 = ispass2;
	}

	public String getIspass1() {
		return ispass1;
	}

	public void setIspass1(String ispass1) {
		this.ispass1 = ispass1;
	}

	

	public String getMailStartTime() {
		return mailStartTime;
	}

	public void setMailStartTime(String mailStartTime) {
		this.mailStartTime = mailStartTime;
	}

	public String getMailEndTime() {
		return mailEndTime;
	}

	public void setMailEndTime(String mailEndTime) {
		this.mailEndTime = mailEndTime;
	}

	public String getExamStartTime() {
		return examStartTime;
	}

	public void setExamStartTime(String examStartTime) {
		this.examStartTime = examStartTime;
	}

	public String getExamEndTime() {
		return examEndTime;
	}

	public void setExamEndTime(String examEndTime) {
		this.examEndTime = examEndTime;
	}

	public String getHuifang() {
		return huifang;
	}

	public void setHuifang(String huifang) {
		this.huifang = huifang;
	}

	public String getIshuifang() {
		return ishuifang;
	}

	public void setIshuifang(String ishuifang) {
		this.ishuifang = ishuifang;
	}

	public String getAcscoretime() {
		return acscoretime;
	}

	public void setAcscoretime(String acscoretime) {
		this.acscoretime = acscoretime;
	}

	public String getMailTim() {
		return mailTim;
	}

	public void setMailTim(String mailTim) {
		this.mailTim = mailTim;
	}

	public String getScoretim() {
		return scoretim;
	}

	public void setScoretim(String scoretim) {
		this.scoretim = scoretim;
	}

	public Integer getIsOnlineBuy() {
		return isOnlineBuy;
	}

	public void setIsOnlineBuy(Integer isOnlineBuy) {
		this.isOnlineBuy = isOnlineBuy;
	}

	public double getDealpriceSum() {
		return dealpriceSum;
	}

	public void setDealpriceSum(double dealpriceSum) {
		this.dealpriceSum = dealpriceSum;
	}

	public Integer getUserstate() {
		return userstate;
	}

	public void setUserstate(Integer userstate) {
		this.userstate = userstate;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptgroupName() {
		return deptgroupName;
	}

	public void setDeptgroupName(String deptgroupName) {
		this.deptgroupName = deptgroupName;
	}

	public double getProportion() {
		return proportion;
	}

	public void setProportion(double proportion) {
		this.proportion = proportion;
	}

	public double getShouldpayMoney() {
		return shouldpayMoney;
	}

	public void setShouldpayMoney(double shouldpayMoney) {
		this.shouldpayMoney = shouldpayMoney;
	}

	public double getActualpayMoney() {
		return actualpayMoney;
	}

	public void setActualpayMoney(double actualpayMoney) {
		this.actualpayMoney = actualpayMoney;
	}

	public Integer getIsolddata() {
		return isolddata;
	}

	public void setIsolddata(Integer isolddata) {
		this.isolddata = isolddata;
	}

	public String getStudentColor() {
		return studentColor;
	}

	public void setStudentColor(String studentColor) {
		this.studentColor = studentColor;
	}

	public String getSpecialinfo() {
		return specialinfo;
	}

	public void setSpecialinfo(String specialinfo) {
		this.specialinfo = specialinfo;
	}

	public String getTuifei() {
		return tuifei;
	}

	public void setTuifei(String tuifei) {
		this.tuifei = tuifei;
	}

	public String getTuifeitime() {
		return tuifeitime;
	}

	public void setTuifeitime(String tuifeitime) {
		this.tuifeitime = tuifeitime;
	}

	public String getShenhe() {
		return shenhe;
	}

	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}

	public String getCaiwunote() {
		return caiwunote;
	}

	public void setCaiwunote(String caiwunote) {
		this.caiwunote = caiwunote;
	}

	public String getMatchinfoStarttime() {
		return matchinfoStarttime;
	}

	public void setMatchinfoStarttime(String matchinfoStarttime) {
		this.matchinfoStarttime = matchinfoStarttime;
	}

	public String getMatchinfoEndtime() {
		return matchinfoEndtime;
	}

	public void setMatchinfoEndtime(String matchinfoEndtime) {
		this.matchinfoEndtime = matchinfoEndtime;
	}

	public String getAfp() {
		return afp;
	}

	public void setAfp(String afp) {
		this.afp = afp;
	}

	public String getIsAllPass() {
		return isAllPass;
	}

	public void setIsAllPass(String isAllPass) {
		this.isAllPass = isAllPass;
	}

	public String getKjjc() {
		return kjjc;
	}

	public void setKjjc(String kjjc) {
		this.kjjc = kjjc;
	}

	public String getKjjcScore() {
		return kjjcScore;
	}

	public void setKjjcScore(String kjjcScore) {
		this.kjjcScore = kjjcScore;
	}

	public String getStudentEvaluate() {
		return studentEvaluate;
	}

	public void setStudentEvaluate(String studentEvaluate) {
		this.studentEvaluate = studentEvaluate;
	}

	public Timestamp getScoretime() {
		return scoretime;
	}

	public void setScoretime(Timestamp scoretime) {
		this.scoretime = scoretime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPayStartTime() {
		return payStartTime;
	}

	public void setPayStartTime(String payStartTime) {
		this.payStartTime = payStartTime;
	}

	public String getPayEndTime() {
		return payEndTime;
	}

	public void setPayEndTime(String payEndTime) {
		this.payEndTime = payEndTime;
	}

	public String getXingzhengNote() {
		return xingzhengNote;
	}

	public void setXingzhengNote(String xingzhengNote) {
		this.xingzhengNote = xingzhengNote;
	}

	@JsonIgnore
	public String getPaytime() {
		return paytime;
	}

	@JsonIgnore
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}

	public String getSumPayMoney() {
		return sumPayMoney;
	}

	public void setSumPayMoney(String sumPayMoney) {
		this.sumPayMoney = sumPayMoney;
	}

	public Integer getIscommitcaiwu() {
		return iscommitcaiwu;
	}

	public void setIscommitcaiwu(Integer iscommitcaiwu) {
		this.iscommitcaiwu = iscommitcaiwu;
	}

	public Timestamp getCommitTime_caiwu() {
		return commitTime_caiwu;
	}

	public void setCommitTime_caiwu(Timestamp commitTime_caiwu) {
		this.commitTime_caiwu = commitTime_caiwu;
	}

	public String getCommitStarttime() {
		return commitStarttime;
	}

	public void setCommitStarttime(String commitStarttime) {
		this.commitStarttime = commitStarttime;
	}

	public String getCommitEndtime() {
		return commitEndtime;
	}

	public void setCommitEndtime(String commitEndtime) {
		this.commitEndtime = commitEndtime;
	}

	public String getIssign() {
		return issign;
	}

	public void setIssign(String issign) {
		this.issign = issign;
	}

	public String getArriveStartTime() {
		return arriveStartTime;
	}

	public void setArriveStartTime(String arriveStartTime) {
		this.arriveStartTime = arriveStartTime;
	}

	public String getArriveEndTime() {
		return arriveEndTime;
	}

	public void setArriveEndTime(String arriveEndTime) {
		this.arriveEndTime = arriveEndTime;
	}

	public Integer getIshavenetedu() {
		return ishavenetedu;
	}

	public void setIshavenetedu(Integer ishavenetedu) {
		this.ishavenetedu = ishavenetedu;
	}

	public String getNetedumoney() {
		return netedumoney;
	}

	public void setNetedumoney(String netedumoney) {
		this.netedumoney = netedumoney;
	}

	public Integer getIspass() {
		return ispass;
	}

	public void setIspass(Integer ispass) {
		this.ispass = ispass;
	}

	public Timestamp getPasstime() {
		return passtime;
	}

	public void setPasstime(Timestamp passtime) {
		this.passtime = passtime;
	}

	public String getHeadmaster() {
		return headmaster;
	}

	public void setHeadmaster(String headmaster) {
		this.headmaster = headmaster;
	}

	public List<Visitrecord> getVisitrecord() {
		return visitrecord;
	}

	public void setVisitrecord(List<Visitrecord> visitrecord) {
		this.visitrecord = visitrecord;
	}

	public String getInvoiceinfo() {
		return invoiceinfo;
	}

	public void setInvoiceinfo(String invoiceinfo) {
		this.invoiceinfo = invoiceinfo;
	}

	public Timestamp getMatchinfo_time() {
		return matchinfo_time;
	}

	public void setMatchinfo_time(Timestamp matchinfo_time) {
		this.matchinfo_time = matchinfo_time;
	}

	public String getRemituser() {
		return remituser;
	}

	public void setRemituser(String remituser) {
		this.remituser = remituser;
	}

	public Integer getCourseid() {
		return courseid;
	}

	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}

	public Long getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(Long subjectid) {
		this.subjectid = subjectid;
	}

	public Timestamp getDealtime() {
		return dealtime;
	}

	public void setDealtime(Timestamp dealtime) {
		this.dealtime = dealtime;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDealprice() {
		return dealprice;
	}

	public void setDealprice(String dealprice) {
		this.dealprice = dealprice;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public List<Dealrecord> getDealrecord() {
		return dealrecord;
	}

	public void setDealrecord(List<Dealrecord> dealrecord) {
		this.dealrecord = dealrecord;
	}

	public String getBelongName() {
		return belongName;
	}

	public void setBelongName(String belongName) {
		this.belongName = belongName;
	}

	public Timestamp getCustomer_time() {
		return customer_time;
	}

	public void setCustomer_time(Timestamp customer_time) {
		this.customer_time = customer_time;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Timestamp getCommit_time() {
		return commit_time;
	}

	public void setCommit_time(Timestamp commit_time) {
		this.commit_time = commit_time;
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

	public String getDealStartTime() {
		return dealStartTime;
	}

	public void setDealStartTime(String dealStartTime) {
		this.dealStartTime = dealStartTime;
	}

	public String getDealEndTime() {
		return dealEndTime;
	}

	public void setDealEndTime(String dealEndTime) {
		this.dealEndTime = dealEndTime;
	}

	public String getReturnNote() {
		return returnNote;
	}

	public void setReturnNote(String returnNote) {
		this.returnNote = returnNote;
	}

	public Timestamp getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Timestamp returnTime) {
		this.returnTime = returnTime;
	}

	public Long getReturnId() {
		return returnId;
	}

	public void setReturnId(Long returnId) {
		this.returnId = returnId;
	}

	public Long getBelongid() {
		return belongid;
	}

	public void setBelongid(Long belongid) {
		this.belongid = belongid;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}


	public String getResourceLevel() {
		return resourceLevel;
	}

	public void setResourceLevel(String resourceLevel) {
		this.resourceLevel = resourceLevel;
	}

	 

	public Integer getStudentstate() {
		return studentstate;
	}

	public void setStudentstate(Integer studentstate) {
		this.studentstate = studentstate;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCompanyAddr() {
		return companyAddr;
	}

	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}

	public String getHomeAddr() {
		return homeAddr;
	}

	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}

	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	 
	public String getArrive_time() {
		return arrive_time;
	}

	public void setArrive_time(String arrive_time) {
		this.arrive_time = arrive_time;
	}

	public String getArrive_money() {
		return arrive_money;
	}

	public void setArrive_money(String arrive_money) {
		this.arrive_money = arrive_money;
	}

	public String getRemitWay() {
		return remitWay;
	}

	public void setRemitWay(String remitWay) {
		this.remitWay = remitWay;
	}

	public Integer getIsjieye() {
		return isjieye;
	}

	public void setIsjieye(Integer isjieye) {
		this.isjieye = isjieye;
	}
	
	@JsonIgnore
	public String getLCWname() {
		return LCWname;
	}
	
	@JsonIgnore
	public void setLCWname(String lCWname) {
		LCWname = lCWname;
	}

	@JsonIgnore
	public String getLCWpassword() {
		return LCWpassword;
	}

	@JsonIgnore
	public void setLCWpassword(String lCWpassword) {
		LCWpassword = lCWpassword;
	}

	public Timestamp getLCWoutTime() {
		return LCWoutTime;
	}

	public void setLCWoutTime(Timestamp lCWoutTime) {
		LCWoutTime = lCWoutTime;
	}

	public String getBanci() {
		return banci;
	}

	public void setBanci(String banci) {
		this.banci = banci;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public String getQici() {
		return qici;
	}

	public void setQici(String qici) {
		this.qici = qici;
	}

	public String getPreferinfo() {
		return preferinfo;
	}

	public void setPreferinfo(String preferinfo) {
		this.preferinfo = preferinfo;
	}

	public Integer getIsSignAgreement() {
		return isSignAgreement;
	}

	public void setIsSignAgreement(Integer isSignAgreement) {
		this.isSignAgreement = isSignAgreement;
	}

	public String getCourseversion() {
		return courseversion;
	}

	public void setCourseversion(String courseversion) {
		this.courseversion = courseversion;
	}

	public Timestamp getMailTime() {
		return mailTime;
	}

	public void setMailTime(Timestamp mailTime) {
		this.mailTime = mailTime;
	}

	public Integer getIsHaveCourse() {
		return isHaveCourse;
	}

	public void setIsHaveCourse(Integer isHaveCourse) {
		this.isHaveCourse = isHaveCourse;
	}


	public String getKefuNote1() {
		return kefuNote1;
	}

	public void setKefuNote1(String kefuNote1) {
		this.kefuNote1 = kefuNote1;
	}

	public String getKefuNote2() {
		return kefuNote2;
	}

	public void setKefuNote2(String kefuNote2) {
		this.kefuNote2 = kefuNote2;
	}

	public String getBaokaopassword() {
		return baokaopassword;
	}

	public void setBaokaopassword(String baokaopassword) {
		this.baokaopassword = baokaopassword;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}
	

	public String getTouzi() {
		return touzi;
	}

	public void setTouzi(String touzi) {
		this.touzi = touzi;
	}

	public String getShuiwu() {
		return shuiwu;
	}

	public void setShuiwu(String shuiwu) {
		this.shuiwu = shuiwu;
	}

	public String getFuli() {
		return fuli;
	}

	public void setFuli(String fuli) {
		this.fuli = fuli;
	}

	public String getBaoxian() {
		return baoxian;
	}

	public void setBaoxian(String baoxian) {
		this.baoxian = baoxian;
	}

	public String getZonghe() {
		return zonghe;
	}

	public void setZonghe(String zonghe) {
		this.zonghe = zonghe;
	}

	public String getTouziExamDate() {
		return touziExamDate;
	}

	public void setTouziExamDate(String touziExamDate) {
		this.touziExamDate = touziExamDate;
	}

	public String getShuiwuExamDate() {
		return shuiwuExamDate;
	}

	public void setShuiwuExamDate(String shuiwuExamDate) {
		this.shuiwuExamDate = shuiwuExamDate;
	}

	public String getFuliExamDate() {
		return fuliExamDate;
	}

	public void setFuliExamDate(String fuliExamDate) {
		this.fuliExamDate = fuliExamDate;
	}

	public String getBaoxianExamDate() {
		return baoxianExamDate;
	}

	public void setBaoxianExamDate(String baoxianExamDate) {
		this.baoxianExamDate = baoxianExamDate;
	}

	public String getZongheExamDate() {
		return zongheExamDate;
	}

	public void setZongheExamDate(String zongheExamDate) {
		this.zongheExamDate = zongheExamDate;
	}

	public String getJijin1() {
		return jijin1;
	}

	public void setJijin1(String jijin1) {
		this.jijin1 = jijin1;
	}

	public String getJijin2() {
		return jijin2;
	}

	public void setJijin2(String jijin2) {
		this.jijin2 = jijin2;
	}

	public String getFlfg() {
		return flfg;
	}

	public void setFlfg(String flfg) {
		this.flfg = flfg;
	}

	public String getGrlc() {
		return grlc;
	}

	public void setGrlc(String grlc) {
		this.grlc = grlc;
	}

	public String getFxgl() {
		return fxgl;
	}

	public void setFxgl(String fxgl) {
		this.fxgl = fxgl;
	}

	public String getGsxd() {
		return gsxd;
	}

	public void setGsxd(String gsxd) {
		this.gsxd = gsxd;
	}

	public String getGrdk() {
		return grdk;
	}

	public void setGrdk(String grdk) {
		this.grdk = grdk;
	}

	public String getJjjczd() {
		return jjjczd;
	}

	public void setJjjczd(String jjjczd) {
		this.jjjczd = jjjczd;
	}

	public String getJrzy() {
		return jrzy;
	}

	public void setJrzy(String jrzy) {
		this.jrzy = jrzy;
	}

	public String getGsgl() {
		return gsgl;
	}

	public void setGsgl(String gsgl) {
		this.gsgl = gsgl;
	}

	public String getCzss() {
		return czss;
	}

	public void setCzss(String czss) {
		this.czss = czss;
	}

	public String getZqsc() {
		return zqsc;
	}

	public void setZqsc(String zqsc) {
		this.zqsc = zqsc;
	}

	public String getJrsc() {
		return jrsc;
	}

	public void setJrsc(String jrsc) {
		this.jrsc = jrsc;
	}

	public String getQhflfg() {
		return qhflfg;
	}

	public void setQhflfg(String qhflfg) {
		this.qhflfg = qhflfg;
	}

	public String getQhjczs() {
		return qhjczs;
	}

	public void setQhjczs(String qhjczs) {
		this.qhjczs = qhjczs;
	}

	public String getCjfg() {
		return cjfg;
	}

	public void setCjfg(String cjfg) {
		this.cjfg = cjfg;
	}

	public String getCjkj() {
		return cjkj;
	}

	public void setCjkj(String cjkj) {
		this.cjkj = cjkj;
	}

	public String getJjfjc() {
		return jjfjc;
	}

	public void setJjfjc(String jjfjc) {
		this.jjfjc = jjfjc;
	}

	public String getJijin1Score() {
		return jijin1Score;
	}

	public void setJijin1Score(String jijin1Score) {
		this.jijin1Score = jijin1Score;
	}

	public String getJijin2Score() {
		return jijin2Score;
	}

	public void setJijin2Score(String jijin2Score) {
		this.jijin2Score = jijin2Score;
	}

	public String getFlfgScore() {
		return flfgScore;
	}

	public void setFlfgScore(String flfgScore) {
		this.flfgScore = flfgScore;
	}

	public String getGrlcScore() {
		return grlcScore;
	}

	public void setGrlcScore(String grlcScore) {
		this.grlcScore = grlcScore;
	}

	public String getFxglScore() {
		return fxglScore;
	}

	public void setFxglScore(String fxglScore) {
		this.fxglScore = fxglScore;
	}

	public String getGsxdScore() {
		return gsxdScore;
	}

	public void setGsxdScore(String gsxdScore) {
		this.gsxdScore = gsxdScore;
	}

	public String getGrdkScore() {
		return grdkScore;
	}

	public void setGrdkScore(String grdkScore) {
		this.grdkScore = grdkScore;
	}

	public String getJjjczdScore() {
		return jjjczdScore;
	}

	public void setJjjczdScore(String jjjczdScore) {
		this.jjjczdScore = jjjczdScore;
	}

	public String getJrzyScore() {
		return jrzyScore;
	}

	public void setJrzyScore(String jrzyScore) {
		this.jrzyScore = jrzyScore;
	}

	public String getGsglScore() {
		return gsglScore;
	}

	public void setGsglScore(String gsglScore) {
		this.gsglScore = gsglScore;
	}

	public String getCzssScore() {
		return czssScore;
	}

	public void setCzssScore(String czssScore) {
		this.czssScore = czssScore;
	}

	public String getZqscScore() {
		return zqscScore;
	}

	public void setZqscScore(String zqscScore) {
		this.zqscScore = zqscScore;
	}

	public String getJrscScore() {
		return jrscScore;
	}

	public void setJrscScore(String jrscScore) {
		this.jrscScore = jrscScore;
	}

	public String getQhflfgScore() {
		return qhflfgScore;
	}

	public void setQhflfgScore(String qhflfgScore) {
		this.qhflfgScore = qhflfgScore;
	}

	public String getQhjczsScore() {
		return qhjczsScore;
	}

	public void setQhjczsScore(String qhjczsScore) {
		this.qhjczsScore = qhjczsScore;
	}

	public String getCjfgScore() {
		return cjfgScore;
	}

	public void setCjfgScore(String cjfgScore) {
		this.cjfgScore = cjfgScore;
	}

	public String getCjkjScore() {
		return cjkjScore;
	}

	public void setCjkjScore(String cjkjScore) {
		this.cjkjScore = cjkjScore;
	}

	public String getJjfjcScore() {
		return jjfjcScore;
	}

	public void setJjfjcScore(String jjfjcScore) {
		this.jjfjcScore = jjfjcScore;
	}

	@Override
	public String getIdColumnName() {
		// TODO Auto-generated method stub
		return "studentId";
	}
}
