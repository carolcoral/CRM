package com.hjcrm.resource.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.resource.entity.Dealrecord;
import com.hjcrm.resource.entity.Resource;
import com.hjcrm.resource.entity.Student;
import com.hjcrm.resource.entity.Visitrecord;
import com.hjcrm.resource.service.IStudentService;
import com.hjcrm.system.entity.User;

/**
 * 学员管理接口实现类
 * @author likang
 * @date 2016-11-7 下午4:15:52
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private IDataAccess<Student> studentDao;
	@Autowired
	private IDataAccess<Dealrecord> dealRecordDao;
	@Autowired
	private IDataAccess<Resource> resourceDao;
	@Autowired
	private IDataAccess<Visitrecord> visitrecordDao;
	@Autowired
	private IDataAccess<User> userDao;
	
	/**
	 * 增加或者修改学员信息
	 */
	
	public void saveOrUpdate(Student student) {
		if (student != null) {
			if (student.getStudentId() != null) {//修改
				student.setUpdate_time(new Timestamp(System.currentTimeMillis()));
				student.setUpdate_id(UserContext.getLoginUser().getUserid());
				studentDao.update(student);
			}else{//增加
				student.setCreate_time(new Timestamp(System.currentTimeMillis()));
				studentDao.insert(student);
			}
		}
	}
	
	/**
	 * 查询学员信息
	 */
	
	public List<Student> queryStudents(String resourceId,String studentIds,Long deptid,Long roleid,Long userid,PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("studentIds", studentIds);
		param.put("deptid", deptid);
//		param.put("belongid", userid);
		List<Student> list = studentDao.queryByStatment("queryStudents", param, pageBean);
		for (Student student : list) {
			Long studentId = student.getStudentId();
			param.clear();
			if (student.getResourceId() != null && !"".equals(student.getResourceId())) {
				param.clear();
				param.put("resourceId", student.getResourceId());
				param.put("deptid", deptid);
				param.put("userid", userid);
				param.put("roleid", roleid);
				List<Visitrecord> visitrecord = visitrecordDao.queryByStatment("queryResourceVisitrecord", param, null);
				student.setVisitrecord(visitrecord);
				List<Dealrecord> listrecord = dealRecordDao.queryByStatment("queryStudentDealrecord", param, null);
				student.setDealrecord(listrecord);
			}else{
				param.clear();
				param.put("studentId", studentId);
				param.put("deptid", deptid);
				param.put("userid", userid);
				param.put("roleid", roleid);
				List<Dealrecord> listrecord = dealRecordDao.queryByStatment("queryStudentDealrecord", param, null);
				student.setDealrecord(listrecord);
				List<Visitrecord> visitrecord = visitrecordDao.queryByStatment("queryStudentVisitrecord", param, null);
				student.setVisitrecord(visitrecord);
			}
		}
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				param.clear();
				if (list.get(i).getResourceId() != null) {
					param.put("resourceId", list.get(i).getResourceId());
				}else{
					param.put("studentId", list.get(i).getStudentId());
				}
				List<Student> listscore = studentDao.queryByStatment("querystudentScore", param, null);
				if (listscore != null && listscore.size() > 0) {
					list.get(i).setJijin1Score(listscore.get(0).getJijin1Score());
					list.get(i).setJijin2Score(listscore.get(0).getJijin2Score());
					list.get(i).setFlfgScore(listscore.get(0).getFlfgScore());
					list.get(i).setGrlcScore(listscore.get(0).getGrlcScore());
					list.get(i).setFxglScore(listscore.get(0).getFxglScore());
					list.get(i).setGsxdScore(listscore.get(0).getGsxdScore());
					list.get(i).setGrdkScore(listscore.get(0).getGrdkScore());
					list.get(i).setJjjczdScore(listscore.get(0).getJjjczdScore());
					list.get(i).setJrzyScore(listscore.get(0).getJrzyScore());
					list.get(i).setGsglScore(listscore.get(0).getGsglScore());
					list.get(i).setGsxdScore(listscore.get(0).getGsxdScore());
					list.get(i).setCzssScore(listscore.get(0).getCzssScore());
					list.get(i).setZqscScore(listscore.get(0).getZqscScore());
					list.get(i).setJrscScore(listscore.get(0).getJrscScore());
					list.get(i).setQhflfgScore(listscore.get(0).getQhflfgScore());
					list.get(i).setQhjczsScore(listscore.get(0).getQhjczsScore());
					list.get(i).setCjfgScore(listscore.get(0).getCjfgScore());
					list.get(i).setKjjcScore(listscore.get(0).getKjjcScore());
					list.get(i).setCjkjScore(listscore.get(0).getCjkjScore());
					list.get(i).setJjfjcScore(listscore.get(0).getJjfjcScore());
				}
			}
		}
		return list;
	}

	

	/**
	 * 查询学员管理列表
	 */
	
	public List<Student> queryStudentlist(Long roleid,String studentIds, Long deptid,Long userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("userid", userid);
		param.put("roleid", roleid);
		param.put("studentIds", studentIds);
		List<Student> list = studentDao.queryByStatment("queryStudentlist", param, pageBean);
		return list;
	}

	
	/**
	 * 查询销售已提交未确认到账的信息
	 */
	
	public List<Student> queryStudnetMatchs(String studentName,PageBean processPageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("studentName", studentName);
		List<Student> list = studentDao.queryByStatment("queryStudnetMatchs", param, processPageBean);
		return list;
	}

	
	
	/**
	 * 查询学员成交记录
	 */
	
	public List<Dealrecord> queryStudentDealrecord(String studentId,PageBean pageBean) {
		if (studentId != null && !"".equals(studentId)) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("studentId", studentId);
			List<Dealrecord> list = dealRecordDao.queryByStatment("queryStudentDealrecord", param, pageBean);
			return list;
		}
		return null;
	}

	/**
	 * 根据学员条件，查询(筛选)
	 */
	
	public List<Student> queryStudentBySceen(String isnet,Long deptid,Long roleid,Long userid,Student student, PageBean pageBean) {
		if (student != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("dealStartTime", student.getDealStartTime() != null && !"".equals(student.getDealStartTime().trim())? student.getDealStartTime():null);
			param.put("dealEndTime", student.getDealEndTime()!= null && !"".equals(student.getDealEndTime().trim())? student.getDealEndTime():null);
			param.put("studentName", student.getStudentName());
			param.put("source", student.getSource());
			param.put("phone", student.getPhone());
			param.put("courseid", student.getCourseid());
			param.put("belongid", student.getBelongid());
			param.put("subjectid", student.getSubjectid());
			param.put("banci", student.getBanci());
			param.put("company", student.getCompany());
			param.put("studentColor", student.getStudentColor());
			
			param.put("arriveStartTime", student.getArriveStartTime()!= null && !"".equals(student.getArriveStartTime().trim())? student.getArriveStartTime():null);
			param.put("arriveEndTime", student.getArriveEndTime()!= null && !"".equals(student.getArriveEndTime().trim())? student.getArriveEndTime():null);
			param.put("arrive_money", student.getArrive_money()!= null && !"".equals(student.getArrive_money().trim())? student.getArrive_money():null);
			param.put("remitWay", student.getRemitWay()!= null && !"".equals(student.getRemitWay().trim())? student.getRemitWay():null);
			param.put("remituser", student.getRemituser());
			param.put("idCard", student.getIdCard());
			param.put("issign", student.getIssign());
			param.put("ispass", student.getIspass());
			param.put("classNum", student.getClassNum());
			param.put("courseversion", student.getCourseversion());
			
			param.put("matchinfoStarttime", student.getMatchinfoStarttime() != null && !"".equals(student.getMatchinfoStarttime().trim())? student.getMatchinfoStarttime():null);
			param.put("matchinfoEndtime", student.getMatchinfoEndtime()!= null && !"".equals(student.getMatchinfoEndtime().trim())? student.getMatchinfoEndtime():null);
			param.put("mailTime", student.getMailTime()!= null && !"".equals(student.getMailTime())? student.getMailTime():null);

			param.put("deptid", deptid);
			param.put("userid", userid);
			param.put("roleid", roleid);
			
			param.put("payStartTime", student.getPayStartTime());
			param.put("scoretime", student.getScoretime());
			param.put("payEndTime", student.getPayEndTime());
			param.put("arrive_time", student.getArrive_time());
			
			
			param.put("isnet", isnet);
			
			param.put("studentstate", student.getStudentstate() != null ?student.getStudentstate():null);//行政部--增加--学员状态--筛选字段
			
			List<Student> list = studentDao.queryByStatment("queryStudentBySceen", param, pageBean);
			return list;
		}
		return null;
	}

	
	/**
	 * 修改资源的学员状态
	 */
	
	public void updateResourceStudentstate(String resourceIds, Integer studentState) {
		if (resourceIds != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("studentState", studentState);
			param.put("resourceIds", resourceIds);
			resourceDao.updateByStatment("updateResourceStudentstate", param);
		}
	}

	/**
	 * 删除学员(自建/新增状态)
	 */
	
	public void deleteStudent(String studentId) {
		if (studentId != null && !"".equals(studentId)) {
			studentDao.deleteByIds(Student.class, studentId);
		}
	}

	/**
	 * 修改学员退回的学员状态
	 */
	
	public void updateStudentstate(String studentIds, Integer studentstate,Timestamp returnTime,String returnId,String returnNote) {
		if (studentIds != null && !"".equals(studentIds.trim()) && studentstate != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("studentIds", studentIds);
			param.put("studentstate", studentstate);
			param.put("returnId", returnId != null && !"".equals(returnId)?returnId:null);
			param.put("returnTime", returnTime!= null && !"".equals(returnTime)?returnTime:null);
			param.put("returnNote", returnNote!= null && !"".equals(returnNote)?returnNote:null);
			studentDao.updateByStatment("updateStudentstate", param);
		}
		
	}

	/**
	 * 到账学员总表  查询
	 */
	
	public List<Student> queryYesStudentMatchinfo(String deptid,PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		List<Student> list=  studentDao.queryByStatment("queryYesStudentMatchinfo", param, pageBean);
		return list;
	}

	/**
	 * 已到账学员分配客服
	 */
	
	public void assignStudent(String resourceIds, String studentIds,String customerId,String headmaster,Integer studentstate,Timestamp customer_time) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("studentIds", studentIds);
		param.put("customerId", customerId);
		param.put("studentstate", studentstate);
		param.put("customer_time", customer_time);
		param.put("headmaster", headmaster);
		studentDao.updateByStatment("assignStudent", param);
	}
	
	/**
	 * 查询有网络培训费的学员列表
	 */
	
	public List<Student> queryNetworkEduMoney(String studentIds,Long deptid,Long userid,Long roleid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("userid", userid);
		param.put("roleid", roleid);
		param.put("studentIds", studentIds);
		List<Student> list = studentDao.queryByStatment("queryNetworkEduMoney", param, pageBean);
		return list;
	}

	/**
	 * 查询已通过学员列表
	 */
	
	public List<Student> queryPassStudents(String phone,PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("phone", phone);
		List<Student> list = studentDao.queryByStatment("queryPassStudents", null, pageBean);
		return list;
	}

	/**
	 * 在线购买
	 * 	查询
	 */
	
	public List<Student> queryOnLineStudents(String phone,String courseid,PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		if(courseid!=null&&!"".equals(courseid))
		param.put("courseid", courseid);
		if(phone!=null&&!"".equals(phone))
		param.put("phone", phone);
		List<Student> list = studentDao.queryByStatment("queryOnLineStudents", param, pageBean);
		return list;
	}

	/**
	 * 根据代汇款查询学员
	 */
	
	public List<Student> queryStudentBysname(String remituser) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("remituser", remituser);
		List<Student> list = studentDao.queryByStatment("queryStudentBysname", param, null);
		return list;
	}

	
	/**
	 * 通过代汇款人，查询汇款金额和
	 */
	
	public List<Student> queryStudentSumPayMoney(String remituser) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("remituser", remituser);
		List<Student> list = studentDao.queryByStatment("queryStudentSumPayMoney", param, null);
		return list;
	}

	/**
	 * 行政-网络培训费学员-提交财务
	 */
	
	public void commitTocaiwu(String resourceIds, String studentIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("studentIds", studentIds);
		param.put("commitTime_caiwu", new Timestamp(System.currentTimeMillis()));
		studentDao.updateByStatment("commitTocaiwu", param);
	}

	/**
	 * 根据身份证号，查询重复的网络培训费学员
	 */
	
	public List<Student> queryRepeatStudents(String idCard, String isInfo,PageBean pageBean) {
		List<Student> list = new ArrayList<Student>();
		if (isInfo != null && !"".equals(isInfo) && "false".equals(isInfo)) {
			list = studentDao.queryByStatment("queryRepeatStudentsfalse", null, pageBean);
		}else if(isInfo != null && !"".equals(isInfo) && "true".equals(isInfo)){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("idCard", idCard);
			list = studentDao.queryByStatment("queryRepeatStudentstrue", param, pageBean);
		}
		return list;
	}

	/**
	 * 查询所有客服人员
	 */
	
	public List<User> queryAllCustoms() {
		List<User> list = userDao.queryByStatment("queryAllCustoms", null, null);
		return list;
	}


	/**
	 * 客服部专用筛选功能接口
	 */
	
	public List<Student> queryCustomStudentBySceen(String menuCode,String deptid, String roleid, String userid, Student student,PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuCode", menuCode);
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		
		param.put("studentName", student.getStudentName());
		param.put("phone", student.getPhone());
		param.put("idCard", student.getIdCard());
		param.put("source", student.getSource());
		param.put("studentstate", student.getStudentstate());
		param.put("belongid", student.getBelongid());
		param.put("courseid", student.getCourseid());

		param.put("kefuNote1",student.getKefuNote1());
		param.put("dealStartTime", student.getDealStartTime() != null && !"".equals(student.getDealStartTime().trim())? student.getDealStartTime():null);
		param.put("dealEndTime", student.getDealEndTime()!= null && !"".equals(student.getDealEndTime().trim())? student.getDealEndTime():null);
		param.put("phone", student.getPhone());
		param.put("subjectid", student.getSubjectid());
		param.put("banci", student.getBanci());
		param.put("company", student.getCompany());
		param.put("studentColor", student.getStudentColor());
		param.put("studentEvaluate", student.getStudentEvaluate());
		
		param.put("arriveStartTime", student.getArriveStartTime()!= null && !"".equals(student.getArriveStartTime().trim())? student.getArriveStartTime():null);
		param.put("arriveEndTime", student.getArriveEndTime()!= null && !"".equals(student.getArriveEndTime().trim())? student.getArriveEndTime():null);
		param.put("arrive_money", student.getArrive_money()!= null && !"".equals(student.getArrive_money().trim())? student.getArrive_money():null);
		param.put("remitWay", student.getRemitWay()!= null && !"".equals(student.getRemitWay().trim())? student.getRemitWay():null);
		param.put("remituser", student.getRemituser());
		param.put("idCard", student.getIdCard());
		param.put("issign", student.getIssign());
		param.put("ispass", student.getIspass());
		param.put("ispass1", student.getIspass1());
		param.put("ispass2", student.getIspass2());
		param.put("classNum", student.getClassNum());
		param.put("courseversion", student.getCourseversion());
		
		param.put("matchinfoStarttime", student.getMatchinfoStarttime() != null && !"".equals(student.getMatchinfoStarttime().trim())? student.getMatchinfoStarttime():null);
		param.put("matchinfoEndtime", student.getMatchinfoEndtime()!= null && !"".equals(student.getMatchinfoEndtime().trim())? student.getMatchinfoEndtime():null);
		param.put("mailStartTime", student.getMailStartTime() != null && !"".equals(student.getMailStartTime().trim())? student.getMailStartTime():null);
		param.put("mailEndTime", student.getMailEndTime()!= null && !"".equals(student.getMailEndTime().trim())? student.getMailEndTime():null);
		
		
		param.put("examStartTime", student.getExamStartTime() != null && !"".equals(student.getExamStartTime().trim())? student.getExamStartTime():null);
		param.put("examEndTime", student.getExamEndTime()!= null && !"".equals(student.getExamEndTime().trim())? student.getExamEndTime():null);
		param.put("mailTime", student.getMailTim()!= null && !"".equals(student.getMailTim())? student.getMailTim():null);

		param.put("deptid", deptid);
		param.put("userid", userid);
		param.put("roleid", roleid);
		
		param.put("payStartTime", student.getPayStartTime());
		param.put("scoretime", student.getScoretim());
		param.put("payEndTime", student.getPayEndTime());
		param.put("arrive_time", student.getArrive_time());
		
		param.put("customerId", student.getCustomerId());
		
		
		
		List<Student> list = studentDao.queryByStatment("queryCustomStudentBySceen", param, pageBean);
		return list;
	}
	
	/**
	 * 查询AFP总表学员(客服)
	 */
	
	public List<Student> queryAFPTotalStudents(String deptid, String roleid,String userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Student> list = studentDao.queryByStatment("queryAFPTotalStudents", param, pageBean);
		return list;
	}
	
	/**
	 * 查询AFP回访学员(客服)
	 */
	
	public List<Student> queryAFPhfStudents(String deptid, String roleid,String userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Student> list = studentDao.queryByStatment("queryAFPhfStudents", param, pageBean);
		return list;
	}


	/**
	 * 查询CFP总表学员(客服)
	 */
	
	public List<Student> queryCFPTotalStudents(String deptid, String roleid,String userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Student> list = studentDao.queryByStatment("queryCFPTotalStudents", param, pageBean);
		return list;
	}
	
	/**
	 * 查询CFP回访学员(客服)
	 */
	
	public List<Student> queryCFPhfStudents(String deptid, String roleid,String userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Student> list = studentDao.queryByStatment("queryCFPhfStudents", param, pageBean);
		return list;
	}


	/**
	 * 查询基金总表学员(客服)
	 */
	
	public List<Student> queryjijinTotalStudents(String deptid, String roleid,String userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Student> list = studentDao.queryByStatment("queryjijinTotalStudents", param, pageBean);
		return list;
	}
	
	/**
	 * 查询基金回访学员(客服)
	 */
	
	public List<Student> queryjijinhfStudents(String deptid, String roleid,String userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Student> list = studentDao.queryByStatment("queryjijinhfStudents", param, pageBean);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				param.clear();
				if (list.get(i).getResourceId() != null) {
					param.put("resourceId", list.get(i).getResourceId());
				}else{
					param.put("studentId", list.get(i).getStudentId());
				}
				List<Student> listscore = studentDao.queryByStatment("querystudentScore", param, null);
				if (listscore != null && listscore.size() > 0) {
					list.get(i).setJijin1Score(listscore.get(0).getJijin1Score());
					list.get(i).setJijin2Score(listscore.get(0).getJijin2Score());
				}
			}
		}
		return list;
	}

	/**
	 * 查询银从总表学员(客服)
	 */
	
	public List<Student> queryyincongTotalStudents(String deptid,String roleid, String userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Student> list = studentDao.queryByStatment("queryyincongTotalStudents", param, pageBean);
		return list;
	}
	
	/**
	 * 查询银从回访学员(客服)
	 */
	
	public List<Student> queryyinconghfStudents(String deptid, String roleid,String userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Student> list = studentDao.queryByStatment("queryyinconghfStudents", param, pageBean);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				param.clear();
				if (list.get(i).getResourceId() != null) {
					param.put("resourceId", list.get(i).getResourceId());
				}else{
					param.put("studentId", list.get(i).getStudentId());
				}
				List<Student> listscore = studentDao.queryByStatment("querystudentScore", param, null);
				if (listscore != null && listscore.size() > 0) {
					list.get(i).setFlfgScore(listscore.get(0).getFlfgScore());
					list.get(i).setGrlcScore(listscore.get(0).getGrlcScore());
					list.get(i).setFxglScore(listscore.get(0).getFxglScore());
					list.get(i).setGsxdScore(listscore.get(0).getGsxdScore());
					list.get(i).setGrdkScore(listscore.get(0).getGrdkScore());
				}
			}
		}
		return list;
	}

	/**
	 *  查询中级总表学员(客服)
	 */
	
	public List<Student> queryzhongjiTotalStudents(String deptid,String roleid, String userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Student> list = studentDao.queryByStatment("queryzhongjiTotalStudents", param, pageBean);
		return list;
	}
	
	/**
	 * 查询中级回访学员(客服)
	 */
	
	public List<Student> queryzhongjihfStudents(String deptid, String roleid,String userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Student> list = studentDao.queryByStatment("queryzhongjihfStudents", param, pageBean);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				param.clear();
				if (list.get(i).getResourceId() != null) {
					param.put("resourceId", list.get(i).getResourceId());
				}else{
					param.put("studentId", list.get(i).getStudentId());
				}
				List<Student> listscore = studentDao.queryByStatment("querystudentScore", param, null);
				if (listscore != null && listscore.size() > 0) {
					list.get(i).setJjjczdScore(listscore.get(0).getJjjczdScore());
					list.get(i).setJrzyScore(listscore.get(0).getJrzyScore());
					list.get(i).setGsglScore(listscore.get(0).getGsglScore());
					list.get(i).setGsxdScore(listscore.get(0).getGsxdScore());
					list.get(i).setCzssScore(listscore.get(0).getCzssScore());
				}
			}
		}
		return list;
	}

	/**
	 * 查询证券总表学员(客服)
	 */
	
	public List<Student> queryzhengquanTotalStudents(String deptid,String roleid, String userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Student> list = studentDao.queryByStatment("queryzhengquanTotalStudents", param, pageBean);
		return list;
	}
	
	/**
	 * 查询证券回访学员(客服)
	 */
	
	public List<Student> queryzhengquanhfStudents(String deptid, String roleid,String userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Student> list = studentDao.queryByStatment("queryzhengquanhfStudents", param, pageBean);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				param.clear();
				if (list.get(i).getResourceId() != null) {
					param.put("resourceId", list.get(i).getResourceId());
				}else{
					param.put("studentId", list.get(i).getStudentId());
				}
				List<Student> listscore = studentDao.queryByStatment("querystudentScore", param, null);
				if (listscore != null && listscore.size() > 0) {
					list.get(i).setZqscScore(listscore.get(0).getZqscScore());
					list.get(i).setJrscScore(listscore.get(0).getJrscScore());
				}
			}
		}
		return list;
	}


	/**
	 * 查询期货总表学员(客服)
	 */
	
	public List<Student> queryqihuoTotalStudents(String deptid, String roleid,String userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Student> list = studentDao.queryByStatment("queryqihuoTotalStudents", param, pageBean);
		return list;
	}
	
	/**
	 * 查询期货回访学员(客服)
	 */
	
	public List<Student> queryqihuohfStudents(String deptid, String roleid,String userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Student> list = studentDao.queryByStatment("queryqihuohfStudents", param, pageBean);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				param.clear();
				if (list.get(i).getResourceId() != null) {
					param.put("resourceId", list.get(i).getResourceId());
				}else{
					param.put("studentId", list.get(i).getStudentId());
				}
				List<Student> listscore = studentDao.queryByStatment("querystudentScore", param, null);
				if (listscore != null && listscore.size() > 0) {
					list.get(i).setQhflfgScore(listscore.get(0).getQhflfgScore());
					list.get(i).setQhjczsScore(listscore.get(0).getQhjczsScore());
				}
			}
		}
		return list;
	}

	/**
	 *  查询会计总表学员(客服)
	 */
	
	public List<Student> querykuaijiTotalStudents(String deptid, String roleid,String userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Student> list = studentDao.queryByStatment("querykuaijiTotalStudents", param, pageBean);
		return list;
	}
	
	/**
	 * 查询会计回访学员(客服)
	 */
	
	public List<Student> querykuaijihfStudents(String deptid, String roleid,String userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Student> list = studentDao.queryByStatment("querykuaijihfStudents", param, pageBean);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				param.clear();
				if (list.get(i).getResourceId() != null) {
					param.put("resourceId", list.get(i).getResourceId());
				}else{
					param.put("studentId", list.get(i).getStudentId());
				}
				List<Student> listscore = studentDao.queryByStatment("querystudentScore", param, null);
				if (listscore != null && listscore.size() > 0) {
					list.get(i).setCjfgScore(listscore.get(0).getCjfgScore());
					list.get(i).setKjjcScore(listscore.get(0).getKjjcScore());
				}
			}
		}
		return list;
	}

	/**
	 * 查询初级总表学员(客服)
	 */
	
	public List<Student> querychujiTotalStudents(String deptid, String roleid,String userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Student> list = studentDao.queryByStatment("querychujiTotalStudents", param, pageBean);
		return list;
	}

	/**
	 * 查询初级回访学员(客服)
	 */
	
	public List<Student> querychujihfStudents(String deptid, String roleid,String userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Student> list = studentDao.queryByStatment("querychujihfStudents", param, pageBean);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				param.clear();
				if (list.get(i).getResourceId() != null) {
					param.put("resourceId", list.get(i).getResourceId());
				}else{
					param.put("studentId", list.get(i).getStudentId());
				}
				List<Student> listscore = studentDao.queryByStatment("querystudentScore", param, null);
				if (listscore != null && listscore.size() > 0) {
					list.get(i).setCjkjScore(listscore.get(0).getCjkjScore());
					list.get(i).setJjfjcScore(listscore.get(0).getJjfjcScore());
				}
			}
		}
		return list;
	}

	/**
	 * 查询到账信息列表导出数据
	 */
	
	public List<Student> queryStudentsExport(Long roleid, String studentIds,Long deptid, Long userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("userid", userid);
		param.put("roleid", roleid);
		param.put("studentIds", studentIds);
		List<Student> list = studentDao.queryByStatment("queryStudentsExport", param, pageBean);
		return list;
	}

	/**
	 * 查询客服人员（行政部专用）
	 */
	
	public List<User> queryxzCustoms() {
		List<User> list = userDao.queryByStatment("queryxzCustoms", null, null);
		return list;
	}

	/**
	 * 回访表
	 */
	
	public List<Student> queryhfStudents(String resourceId, String studentIds,Long deptid, Long userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("studentIds", studentIds);
		param.put("deptid", deptid);
		param.put("belongid", userid);
		List<Student> list = studentDao.queryByStatment("queryStudents", param, pageBean);
		for (Student student : list) {
			Long studentId = student.getStudentId();
			param.clear();
			if (student.getResourceId() != null && !"".equals(student.getResourceId())) {
				param.clear();
				param.put("resourceId", student.getResourceId());
				List<Visitrecord> visitrecord = visitrecordDao.queryByStatment("queryResourceVisitrecord", param, null);
				student.setVisitrecord(visitrecord);
				List<Dealrecord> listrecord = dealRecordDao.queryByStatment("queryhfStudentDealrecord", param, null);
				student.setDealrecord(listrecord);
			}else{
				param.clear();
				param.put("studentId", studentId);
				List<Dealrecord> listrecord = dealRecordDao.queryByStatment("queryhfStudentDealrecord", param, null);
				student.setDealrecord(listrecord);
				List<Visitrecord> visitrecord = visitrecordDao.queryByStatment("queryStudentVisitrecord", param, null);
				student.setVisitrecord(visitrecord);
			}
		}
		return list;
	}

	/**
	 * 特殊情况表
	 */
	
	public List<Student> querySpecialStudents(Student student,Long deptid, Long roleid,Long userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		param.put("studentName", student.getStudentName());
		param.put("phone", student.getPhone());
		param.put("idCard", student.getIdCard());
		List<Student> list = studentDao.queryByStatment("querySpecialStudents", param, pageBean);
		return list;
	}

	/**
	 * 通过学员表
	 */
	
	public List<Student> customQueryPassStudents(Student student,Long deptid, Long roleid,Long userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		param.put("studentName", student.getStudentName());
		param.put("phone", student.getPhone());
		param.put("idCard", student.getIdCard());
		List<Student> list = studentDao.queryByStatment("customQueryPassStudents", param, pageBean);
		return list;
	}

	/**
	 * 批量修改颜色
	 */
	
	public void studentColorSign(String studentIds, String studentColor) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("studentIds", studentIds);
		param.put("studentColor", studentColor);
		studentDao.updateByStatment("studentColorSign", param);
	}

	/**
	 * 查询学员各科成绩 
	 */
	
	public List<Student> querystudentScore(String studentId, String resourceId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("studentId", studentId);
		param.put("resourceId", resourceId);
		List<Student> list = studentDao.queryByStatment("querystudentScore", param, null);
		return list;
	}

	
	public Student queryStudentByphone(String phone) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("phone", phone);
		List<Student> list = studentDao.queryByStatment("queryStudentByphone", param, null);
		if (list !=null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	
	public void updateispass(Long studentIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("studentIds", studentIds);
		studentDao.updateByStatment("updateispass", param);
	}
	
	public void updatedcispass(Long studentIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("studentIds", studentIds);
		dealRecordDao.updateByStatment("updatedcispass", param);
	}

}
