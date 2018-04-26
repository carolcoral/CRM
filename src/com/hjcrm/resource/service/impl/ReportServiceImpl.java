package com.hjcrm.resource.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.resource.entity.Student;
import com.hjcrm.resource.service.IReportService;
import com.hjcrm.system.entity.User;

@Service
@Transactional(rollbackFor = Exception.class)
public class ReportServiceImpl implements IReportService {
	
	@Autowired
	private IDataAccess<Student> studentDao;
	@Autowired
	private IDataAccess<User> usreDao;

	/**
	 * 查询总表学员数据--财务部
	 */
	
	public List<Student> queryStudentscaiwu(Student student,Long roleid, String studentIds,Long deptid, Long userid, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("userid", userid);
		param.put("roleid", roleid);
		param.put("studentIds", studentIds);
		
		param.put("arriveStartTime", (student.getArriveStartTime() != null && !"".equals(student.getArriveStartTime().trim()))?student.getArriveStartTime():null);
		param.put("arriveEndTime", (student.getArriveEndTime() != null && !"".equals(student.getArriveEndTime().trim()))?student.getArriveEndTime():null);
		param.put("remitWay", (student.getRemitWay() != null && !"".equals(student.getRemitWay().trim()))?student.getRemitWay():null);
		param.put("remituser", (student.getRemituser() != null && !"".equals(student.getRemituser().trim()))?student.getRemituser():null);
		param.put("studentName", (student.getStudentName() != null && !"".equals(student.getStudentName().trim()))?student.getStudentName():null);
//		param.put("phone", (student.getPhone()!= null && !"".equals(student.getPhone().trim()))?student.getPhone():null);
		param.put("idCard", (student.getIdCard() != null && !"".equals(student.getIdCard().trim()))?student.getIdCard():null);
		param.put("belongid", (student.getBelongid() != null && !"".equals(student.getBelongid()))?student.getBelongid():null);
		param.put("courseid", (student.getCourseid() != null && !"".equals(student.getCourseid()))?student.getCourseid():null);
		
		
		if (student != null) {
			param.put("phone", student.getPhone());
		}
		if (studentIds != null && !"".equals(studentIds.trim())) {
			param.clear();
			param.put("studentIds", studentIds);//资源IDS
		}
		List<Student> list = studentDao.queryByStatment("queryStudentscaiwu", param, pageBean);
		return list;
	}

	/**
	 *  用户录入统计-全部
	 */
	
	public List<User> queryUserAddCountAll(PageBean pageBean) {
		List<User> list = usreDao.queryByStatment("queryUserAddCountAll", null, pageBean);
		return list;
	}

	/**
	 *  用户录入统计-本月
	 */
	
	public List<User> queryUserAddCountMoth(PageBean pageBean) {
		List<User> list = usreDao.queryByStatment("queryUserAddCountMoth", null, pageBean);
		return list;
	}
	
	/**
	 * 用户录入统计--本周
	 */
	
	public List<User> queryUserAddCountWeek(PageBean pageBean) {
		List<User> list = usreDao.queryByStatment("queryUserAddCountWeek", null, pageBean);
		return list;
	}

	/**
	 * 用户录入统计--今日
	 */
	
	public List<User> queryUserAddCountToday(String countTime,PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("countTime", countTime);
		List<User> list = usreDao.queryByStatment("queryUserAddCountToday", param, pageBean);
		return list;
	}

	/**
	 *用户录入统计
	 * 按照时间条件进行选取
	 */
	
	public List<User> queryUserAddCountTime(String countTime,PageBean processPageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("countTime", countTime);
		List<User> list = usreDao.queryByStatment("queryUserAddCountTime", param, processPageBean);
		return list;
	}
	

	/**
	 * 查询业绩--全部
	 */
	
	public List<User> queryPerformanceAll() {
		List<User> list = usreDao.queryByStatment("queryPerformanceAll", null, null);
		return list;
	}
	
	/**
	 * 查询业绩--全部
	 */
	
	public List<User> queryPerformanceAllnotAC() {
		List<User> list = usreDao.queryByStatment("queryPerformanceAllnotAC", null, null);
		return list;
	}

	/**
	 * 查询业绩--本月
	 */
	
	public List<User> queryPerformanceTodayMothnotAC() {
		List<User> list = usreDao.queryByStatment("queryPerformanceTodayMothnotAC", null, null);
		return list;
	}
	
	/**
	 * 查询业绩--本月
	 */
	
	public List<User> queryPerformanceTodayMoth() {
		List<User> list = usreDao.queryByStatment("queryPerformanceTodayMoth", null, null);
		return list;
	}
	
	/**
	 * 查询业绩--本周  likang
	 */
	
	public List<User> queryPerformanceTodayWeek() {
		List<User> list = usreDao.queryByStatment("queryPerformanceTodayWeek", null, null);
		return list;
	}
	
	/**
	 * 查询业绩--本周  likang
	 */
	
	public List<User> queryPerformanceTodayWeeknotAC() {
		List<User> list = usreDao.queryByStatment("queryPerformanceTodayWeeknotAC", null, null);
		return list;
	}
	
	/**
	 * 查询业绩--昨日  likang
	 */
	
	public List<User> queryPerformanceYestoday() {
		List<User> list = usreDao.queryByStatment("queryPerformanceYestoday", null, null);
		return list;
	}
	/**
	 * 查询业绩--昨日  likang
	 */
	
	public List<User> queryPerformanceYestodaynotAC() {
		List<User> list = usreDao.queryByStatment("queryPerformanceYestodaynotAC", null, null);
		return list;
	}

	/**
	 * 查询业绩--课程区分业绩
	 */
	
	public List<User> queryPerformanceCourse(String courseid) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("courseid", courseid);
		List<User> list = usreDao.queryByStatment("queryPerformanceCourse", param, null);
		return list;
	}

	/**
	 * 查询业绩--月份筛选
	 */
	
	public List<User> queryPerformanceMoth(String moth) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("moth", moth);
		List<User> list = usreDao.queryByStatment("queryPerformanceMoth", param, null);
		return list;
	}

	/**
	 * 查询所有销售客服机构代理的提成资源列表
	 */
	
	public List<Student> queryCommissionAll(Long userid, Long deptgroup,Long roleid, String dealStartTime, String dealEndTime,PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userid", userid);
		param.put("deptgroup", deptgroup);
		if (roleid == 1 || roleid == 33 || roleid == 34 || roleid == 35) {
			param.put("roleid", null);
		}else{
			param.put("roleid", "false");
		}
		param.put("dealStartTime", dealStartTime);
		param.put("dealEndTime", dealEndTime);
		List<Student> list = studentDao.queryByStatment("queryCommissionAll", param, pageBean);
		return list;
	}

	/**
	 * 根据招生老师ID和课程ID，查询该老师的总成交金额
	 */
	
	public List<Student> querydealpriceSum(String deptgroupName,Long belongid, Integer courseid) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("belongid", belongid);
		if ("0".equals(deptgroupName)) {//市场部
			if (courseid != null && courseid > 1 ) {//CFP及其他科目
				param.put("courseid", 2);
			}else if(courseid != null && courseid == 1){//AFP面授
				param.put("courseid", 1);
			}
		}else if("1".equals(deptgroupName)){//基金销售
			if (courseid != null && courseid > 4) {//非AC项目
				param.put("courseid", 3);
			}else if(courseid != null && (courseid == 2 || courseid == 3 || courseid == 4 )){//CFP+AFP网络
				param.put("courseid", 4);
			}else if(courseid != null && courseid == 1){//AFP面授
				param.put("courseid", 5);
			}
		}else{
			param.put("courseid", null);
		}
		List<Student> list = studentDao.queryByStatment("querydealpriceSum", param, null);
		return list;
	}

	/**
	 * 业绩明细
	 */
	
	public List<User> queryPerformanceDetail(String userName, String userid,String startTime, String endTime, PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userName", userName);
		param.put("userid", userid);
		param.put("startTime",startTime);
		param.put("endTime", endTime);
		List<User> list = usreDao.queryByStatment("queryPerformanceDetail", param, pageBean);
		return list;
	}
}
