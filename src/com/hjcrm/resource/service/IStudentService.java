package com.hjcrm.resource.service;

import java.sql.Timestamp;
import java.util.List;

import com.hjcrm.publics.util.PageBean;
import com.hjcrm.resource.entity.Dealrecord;
import com.hjcrm.resource.entity.Student;
import com.hjcrm.system.entity.User;

/**
 * 学员管理接口
 * @author likang
 * @date 2016-11-7 下午4:13:03
 */
public interface IStudentService {
	
	
	/**
	 * 增加或者修改学员信息
	 * @param student
	 * @author likang 
	 * @date 2016-11-8 上午9:07:08
	 */
	public void saveOrUpdate(Student student);
	
	/**
	 * 删除学员(自建/新增状态)
	 * @param studentId
	 * @author likang 
	 * @date 2016-11-8 下午12:00:29
	 */
	public void deleteStudent(String studentId);
	

	/**
	 * 查询学员信息
	 * @param studentId
	 * @param deptid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-7 下午4:16:10
	 */
	public List<Student> queryStudents(String resourceId,String studentIds,Long deptid,Long roleid,Long userid,PageBean pageBean);
	
	/**
	 * 回访表
	 * @param resourceId
	 * @param studentIds
	 * @param deptid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-29 下午2:43:35
	 */
	public List<Student> queryhfStudents(String resourceId,String studentIds,Long deptid,Long userid,PageBean pageBean);
	
	/**
	 * 查询学员管理列表
	 * @param studentIds
	 * @param deptid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-15 下午2:44:52
	 */
	public List<Student> queryStudentlist(Long roleid,String studentIds,Long deptid,Long userid,PageBean pageBean);
	
	public Student queryStudentByphone(String phone);
	
	/**
	 * 查询学员成交记录
	 * @param studentId
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-7 下午4:51:36
	 */
	public List<Dealrecord> queryStudentDealrecord(String studentId,PageBean pageBean);
	
	/**
	 * 根据学员条件，查询(筛选)
	 * @param student
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-7 下午5:01:42
	 */
	public List<Student> queryStudentBySceen(String isnet,Long deptid,Long roleid,Long userid,Student student,PageBean pageBean);
	
	/**
	 * 修改资源的学员状态
	 * @param resourceIds
	 * @param studentState
	 * @author likang 
	 * @date 2016-11-8 上午11:33:12
	 */
	public void updateResourceStudentstate(String resourceIds,Integer studentState);
	
	/**
	 * 修改学员的学员状态
	 * @param studentIds 学员IDS
	 * @param studentstate 状态
	 * @param returnTime
	 * @param returnId
	 * @author likang 
	 * @date 2016-11-9 下午6:28:38
	 */
	public void updateStudentstate(String studentIds,Integer studentstate,Timestamp returnTime,String returnId,String returnNote);
	
	/**
	 * 到账学员总表  查询
	 * @param deptid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-14 上午9:20:35
	 */
	public List<Student> queryYesStudentMatchinfo(String deptid,PageBean pageBean);
	
	/**
	 * 已到账学员分配客服
	 * @param resourceIds
	 * @param studentIds
	 * @param customerId
	 * @param studentstate
	 * @param customer_time
	 * @author likang 
	 * @date 2016-11-14 上午11:07:05
	 */
	public void assignStudent(String resourceIds,String studentIds,String customerId,String headmaster, Integer studentstate,Timestamp customer_time);
	
	/**
	 * 查询有网络培训费的学员列表
	 * @param deptid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-14 上午11:21:10
	 */
	public List<Student> queryNetworkEduMoney(String studentIds,Long deptid,Long userid,Long roleid,PageBean pageBean);
	
	/**
	 * 查询已通过或者已关课学员列表
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-14 上午11:44:08
	 */
	public List<Student> queryPassStudents(String phone,PageBean pageBean);
	
	
	/**
	 * 在线购买
	 * 		查询
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-14 上午11:55:22
	 */
	public List<Student> queryOnLineStudents(String phone,String courseid,PageBean pageBean);

	/**
	 * 查询销售已提交未确认到账的信息
	 * @param processPageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-18 下午6:28:08
	 */
	public List<Student> queryStudnetMatchs(String studentName,PageBean processPageBean);
	
	/**
	 * 根据代汇款查询学员
	 * @param remituser
	 * @return
	 * @author likang 
	 * @date 2016-11-21 上午11:10:40
	 */
	public List<Student> queryStudentBysname(String remituser);
	
	/**
	 * 通过代汇款人，查询汇款金额和
	 * @param remituser
	 * @return
	 * @author likang 
	 * @date 2016-11-21 下午2:02:43
	 */
	public List<Student> queryStudentSumPayMoney(String remituser);
	
	/**
	 * 行政-网络培训费学员-提交财务
	 * @param resourceIds
	 * @param studentIds
	 * @author likang 
	 * @date 2016-11-21 下午3:24:32
	 */
	public void commitTocaiwu(String resourceIds,String studentIds);
	
	/**
	 * 根据身份证号，查询重复的网络培训费学员
	 * @param idCard
	 * @param isInfo
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-22 上午9:57:27
	 */
	public List<Student> queryRepeatStudents(String idCard,String isInfo,PageBean pageBean);
	
	/**
	 * 查询所有客服人员
	 * @return
	 * @author likang 
	 * @date 2016-11-22 下午5:16:19
	 */
	public List<User> queryAllCustoms();
	
	/**
	 * 查询客服人员（行政部专用）
	 * @return
	 * @author likang 
	 * @date 2016-11-22 下午5:16:19
	 */
	public List<User> queryxzCustoms();
	
	/**
	 * 客服部专用筛选功能接口
	 * @param menuCode
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param student
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-23 下午4:24:09
	 */
	public List<Student> queryCustomStudentBySceen(String menuCode,String deptid,String roleid,String userid,Student student,PageBean pageBean);
	
	/**
	 * 查询AFP总表学员(客服)
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-22 下午5:27:30
	 */
	public List<Student> queryAFPTotalStudents(String deptid,String roleid,String userid,PageBean pageBean);
	
	/**
	 * 查询AFP回访学员(客服)
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-24 上午10:57:13
	 */
	public List<Student> queryAFPhfStudents(String deptid,String roleid,String userid,PageBean pageBean);
	
	/**
	 * 查询CFP总表学员(客服)
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-24 上午9:38:23
	 */
	public List<Student> queryCFPTotalStudents(String deptid,String roleid,String userid,PageBean pageBean);
	
	/**
	 * 查询CFP回访学员(客服)
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-24 上午9:38:23
	 */
	public List<Student> queryCFPhfStudents(String deptid,String roleid,String userid,PageBean pageBean);
	
	/**
	 * 查询基金总表学员(客服)
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-24 上午9:38:23
	 */
	public List<Student> queryjijinTotalStudents(String deptid,String roleid,String userid,PageBean pageBean);
	
	/**
	 * 查询基金回访学员(客服)
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-24 上午9:38:23
	 */
	public List<Student> queryjijinhfStudents(String deptid,String roleid,String userid,PageBean pageBean);
	
	/**
	 * 查询银从总表学员(客服)
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-24 上午9:38:23
	 */
	public List<Student> queryyincongTotalStudents(String deptid,String roleid,String userid,PageBean pageBean);
	
	/**
	 * 查询银从回访学员(客服)
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-24 上午9:38:23
	 */
	public List<Student> queryyinconghfStudents(String deptid,String roleid,String userid,PageBean pageBean);
	
	/**
	 * 查询中级总表学员(客服)
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-24 上午9:38:23
	 */
	public List<Student> queryzhongjiTotalStudents(String deptid,String roleid,String userid,PageBean pageBean);
	
	/**
	 * 查询中级回访学员(客服)
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-24 上午9:38:23
	 */
	public List<Student> queryzhongjihfStudents(String deptid,String roleid,String userid,PageBean pageBean);
	
	/**
	 * 查询证券总表学员(客服)
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-24 上午9:38:23
	 */
	public List<Student> queryzhengquanTotalStudents(String deptid,String roleid,String userid,PageBean pageBean);
	
	/**
	 * 查询证券回访学员(客服)
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-24 上午9:38:23
	 */
	public List<Student> queryzhengquanhfStudents(String deptid,String roleid,String userid,PageBean pageBean);
	
	/**
	 * 查询期货总表学员(客服)
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-24 上午9:38:23
	 */
	public List<Student> queryqihuoTotalStudents(String deptid,String roleid,String userid,PageBean pageBean);
	
	/**
	 * 查询期货回访学员(客服)
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-24 上午9:38:23
	 */
	public List<Student> queryqihuohfStudents(String deptid,String roleid,String userid,PageBean pageBean);
	
	/**
	 * 查询会计总表学员(客服)
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-24 上午9:38:23
	 */
	public List<Student> querykuaijiTotalStudents(String deptid,String roleid,String userid,PageBean pageBean);
	
	/**
	 * 查询会计回访学员(客服)
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-24 上午9:38:23
	 */
	public List<Student> querykuaijihfStudents(String deptid,String roleid,String userid,PageBean pageBean);
	
	/**
	 * 查询初级总表学员(客服)
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-24 上午9:38:23
	 */
	public List<Student> querychujiTotalStudents(String deptid,String roleid,String userid,PageBean pageBean);
	
	/**
	 * 查询初级回访学员(客服)
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-24 上午9:38:23
	 */
	public List<Student> querychujihfStudents(String deptid,String roleid,String userid,PageBean pageBean);
	
	/**
	 * 查询到账信息列表导出数据
	 * @param studentIds
	 * @param deptid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-15 下午2:44:52
	 */
	public List<Student> queryStudentsExport(Long roleid,String studentIds,Long deptid,Long userid,PageBean pageBean);
	
	/**
	 * 特殊情况表
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-12-2 下午1:51:56
	 */
	public List<Student> querySpecialStudents(Student student,Long deptid,Long roleid,Long userid,PageBean pageBean);
	
	/**
	 * 通过学员表
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-12-2 下午1:51:56
	 */
	public List<Student> customQueryPassStudents(Student student,Long deptid,Long roleid,Long userid,PageBean pageBean);
	
	/**
	 * 批量修改颜色
	 * @param studentIds
	 * @param studentColor
	 * @author likang 
	 * @date 2016-12-6 下午12:25:14
	 */
	public void studentColorSign(String studentIds,String studentColor);
	
	/**
	 * 查询学员各科成绩 
	 * @param studentId
	 * @param resourceId
	 * @return
	 * @author likang 
	 * @date 2016-12-29 上午11:59:09
	 */
	public List<Student> querystudentScore(String studentId,String resourceId);
	
	
	public void updateispass(Long studentIds);
	public void updatedcispass(Long studentIds);
	
}
